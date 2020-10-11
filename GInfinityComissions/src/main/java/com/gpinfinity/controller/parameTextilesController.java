/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.controller;

import com.gpinfinity.DTO.TerParamBoniTextilDTO;
import com.gpinfinity.config.ApplicationContextProvider;
import com.gpinfinity.entities.TerParamBonifTextil;
import com.gpinfinity.entities.TerParamBonifTextilPK;
import com.gpinfinity.entities.TerParamTextil;
import com.gpinfinity.service.ITerAreaNegocioServices;
import com.gpinfinity.service.ITerParamBonifTextilServices;
import com.gpinfinity.service.ITerParamTextilServices;
import com.gpinfinity.utils.UsrDetails;
import com.gpinfinity.utils.Utils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author nivrist
 */
@Named(value = "paramTextil")
@ViewScoped
@Data
@EqualsAndHashCode(callSuper = false)
public class parameTextilesController extends Utils implements Serializable {

    private int paramTextilId;
    private String paramTextilDescripcion;
    private String paramTextilLlave;
    private String paramTextilEstado;
    private String paramTextilValor;
    private List<TerParamTextil> listTerParamTextil;
    private TerParamTextil selectedTerParamTextil;
    private boolean parametroSelectedTextil = false;

    private int paramId;
    private String paramDescripcion;
    private String paramEstado;
    private String paramBono;
    private String calcSelectedAreaNegocio;
    private String paramTipo;
    private List<TerParamBoniTextilDTO> litsParamBoniTextil;
    private TerParamBoniTextilDTO selectedParamBoniTextil;
    private List<SelectItem> listAreaNegocio;
    private Date calPeriodo;
    private boolean parametroselected = false;

    UsrDetails usrDetails;
    ITerAreaNegocioServices terAreaNegocioServices;
    ITerParamBonifTextilServices terParamBonifTextilServices;
    ITerParamTextilServices iTerParamTextilServices;

    /**
     * Creates a new instance of parameTextilesController
     */
    @PostConstruct
    public void init() {
        loadContextBeanSring();
        clearParamBoniTextil();

        clearParamTextil();

        listAreaNegocio = new ArrayList<>();
        terAreaNegocioServices.listAllAreaNegocio().forEach((arn) -> {
            listAreaNegocio.add(new SelectItem(arn.getId(), arn.getDescripcion()));
        });

    }

    public void clearParamTextil() {
        parametroSelectedTextil = false;
        listTerParamTextil = new ArrayList<>();
        listTerParamTextil = iTerParamTextilServices.findAll();
        setParamTextilDescripcion("");
        setParamTextilEstado("");
        setParamTextilLlave("");
        setParamTextilValor("");
        setParamTextilId((iTerParamTextilServices.getNextId()));
        selectedTerParamTextil = null;

    }

    public void createdParamTextil() {

        try {
            
            
            TerParamTextil ter = new TerParamTextil();
            ter.setDescripcion(getParamTextilDescripcion());
            ter.setEstado(getParamTextilEstado());
            ter.setId(getParamTextilId());
            ter.setLlave(getParamTextilLlave());
            ter.setValor(new BigDecimal(getParamTextilValor()));
            if (parametroSelectedTextil) {
                ter.setUsuarioModificacion(usrDetails.get_userName());
                ter.setFechaModificacion(usrDetails.getDate());
                ter.setUsuarioCreacion(selectedTerParamTextil.getUsuarioCreacion());
                ter.setFechaCreacion(selectedTerParamTextil.getFechaCreacion());
            } else {                
                ter.setUsuarioCreacion(usrDetails.get_userName());
                ter.setFechaCreacion(usrDetails.getDate());
                
            }
            iTerParamTextilServices.createParameter(ter);
            addsimplemessages((parametroSelectedTextil ? "Datos de parametros actualizados!" : "Datos de parametros guardados!"));
            clearParamTextil();
        } catch (Exception ex) {
            ex.printStackTrace();
            addsimplemessageserror((parametroSelectedTextil ? "Error actualizando parametros!" : "Error creando paramteros!"));
        }

    }

    public void loadFormSelectedRowTextil() {
        parametroSelectedTextil = true;
        setParamTextilDescripcion(selectedTerParamTextil.getDescripcion());
        setParamTextilEstado(selectedTerParamTextil.getEstado());
        setParamTextilLlave(selectedTerParamTextil.getLlave());
        setParamTextilValor(selectedTerParamTextil.getValor().toString());
        setParamTextilId(selectedTerParamTextil.getId());

    }

    public parameTextilesController() {
    }

    public void crearParamteroBoniTextil() {

        try {
            Date date = calPeriodo;
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int year = localDate.getYear();
            int month = localDate.getMonthValue();
            int periodo = Integer.parseInt(year + "" + ((month >= 1) && (month <= 9) ? Integer.valueOf("0") + "" + month : month));
            TerParamBonifTextilPK pk = new TerParamBonifTextilPK();
            pk.setId(getParamId());
            pk.setIdAreaNegocio(Integer.parseInt(getCalcSelectedAreaNegocio()));
            TerParamBonifTextil ter = new TerParamBonifTextil();
            ter.setBono(new BigDecimal(getParamBono()));
            ter.setDescripcion(getParamDescripcion());
            ter.setEstado(getParamEstado());
            ter.setPeriodo(periodo);
            ter.setTipo(getParamTipo());
            ter.setTerParamBonifTextilPK(pk);
            if (parametroselected) {
                ter.setFechaModificacion(usrDetails.getDate());
                ter.setUsuarioModificacion(usrDetails.get_userName());
                
            } else {
                ter.setUsuarioCreacion(usrDetails.get_userName());
                ter.setFechaCreacion(new Date());

            }
            terParamBonifTextilServices.createParameter(ter);
            addsimplemessages((parametroselected ? "Datos de parametros actualizados!" : "Datos de parametros guardados!"));
            clearParamBoniTextil();
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            addsimplemessageserror((parametroselected ? "Error actualizando parametros!" : "Error creando paramteros!"));
        }

    }

    public void loadFormSelectedRow() {
        parametroselected = true;
        setParamId(Integer.valueOf(selectedParamBoniTextil.getId()));
        setParamBono(selectedParamBoniTextil.getBono());
        setCalcSelectedAreaNegocio(selectedParamBoniTextil.getIdAreaNegocio());
        setParamDescripcion(selectedParamBoniTextil.getDescripcion());
        setParamTipo(selectedParamBoniTextil.getTipo());
        setParamEstado(selectedParamBoniTextil.getEstado());
        try {
            String mes = selectedParamBoniTextil.getPeriodo().substring(4);
            String anio = selectedParamBoniTextil.getPeriodo().substring(0, 4);
            String sDate1 = "01/" + mes + "/" + anio;
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            setCalPeriodo(date1);
        } catch (ParseException es) {
            es.printStackTrace();
        }

    }

    public void clearParamBoniTextil() {

        setParamId(terParamBonifTextilServices.getNextId());
        setParamBono("");
        setCalcSelectedAreaNegocio("");
        setParamDescripcion("");
        setParamTipo("");
        setCalPeriodo(null);
        setParamEstado("");
        parametroselected = false;
        litsParamBoniTextil = new ArrayList<>();
        selectedParamBoniTextil = null;
        litsParamBoniTextil = terParamBonifTextilServices.findAll();
    }

    public void loadContextBeanSring() {
        usrDetails = ApplicationContextProvider.getApplicationContext().getBean(UsrDetails.class);
        terAreaNegocioServices = ApplicationContextProvider.getApplicationContext().getBean(ITerAreaNegocioServices.class);
        terParamBonifTextilServices = ApplicationContextProvider.getApplicationContext().getBean(ITerParamBonifTextilServices.class);
        iTerParamTextilServices = ApplicationContextProvider.getApplicationContext().getBean(ITerParamTextilServices.class);

    }

}
