/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.controller;

import com.gpinfinity.config.ApplicationContextProvider;
import com.gpinfinity.entities.TerEmpleado;
import com.gpinfinity.entities.TerEmpleadoPK;
import com.gpinfinity.service.ITerAreaNegocioServices;
import com.gpinfinity.service.ITerEmpleadoServices;
import com.gpinfinity.service.ITerParamBonifTextilServices;
import com.gpinfinity.utils.UsrDetails;
import com.gpinfinity.utils.Utils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
@Named(value = "empleadoController")
@ViewScoped
@Data
@EqualsAndHashCode(callSuper = false)
public class empleadoController extends Utils implements Serializable {

     private int paramId;
     private String paramNombre;
     private String calcSelectedAreaNegocio;
     private String paramSueldo;
     private String paramIdEmpleado;
     private String paramIdVendedor;
     private String paramEstado;
     private String paramFilial;
     private String paramTasa;
     private List<SelectItem> listAreaNegocio;
     private List<TerEmpleado> listEmpleados;
     private List<TerEmpleado> listEmpleadosFilter;
     private TerEmpleado selectedEmpleados;
      private boolean parametroSelectedEmpleado = false;
     
     UsrDetails usrDetails;
     ITerAreaNegocioServices terAreaNegocioServices;
     ITerEmpleadoServices terEmpleadoServices;
    /**
     * Creates a new instance of empleadoController
     */
    public empleadoController() {
    }
    
    @PostConstruct
    public void Init(){
    loadContextBeanSring();
    clearFormEmpleado();
    listAreaNegocio = new ArrayList<>();
        terAreaNegocioServices.listAllAreaNegocioConTextil().forEach((arn) -> {
            listAreaNegocio.add(new SelectItem(arn.getId(), arn.getDescripcion()));
        });
    }
    
    public void createdEmpleado() {

        try {
            
            
            TerEmpleado ter = new TerEmpleado();
            TerEmpleadoPK  pk  = new TerEmpleadoPK();
            pk.setId(getParamId());
            pk.setIdAreaNegocio(Integer.parseInt(getCalcSelectedAreaNegocio()));
            ter.setNombre(getParamNombre());
            ter.setEstado(getParamEstado());
            ter.setIdEmpleado(getParamIdEmpleado());
            ter.setIdVendedor(getParamIdVendedor());
            ter.setSueldo(new BigDecimal(getParamSueldo()));
            ter.setFilial(getParamFilial());
            ter.setTasaConversion(new BigDecimal(getParamTasa()));
            ter.setTerEmpleadoPK(pk);
            if (parametroSelectedEmpleado) {
                ter.setUsuarioModificacion(usrDetails.get_userName());
                ter.setFechaModificacion(usrDetails.getDate());
                ter.setUsuarioCreacion(selectedEmpleados.getUsuarioCreacion());
                ter.setFechaCreacion(selectedEmpleados.getFechaCreacion());
            } else {                
                ter.setUsuarioCreacion(usrDetails.get_userName());
                ter.setFechaCreacion(usrDetails.getDate());
                
            }
            terEmpleadoServices.createEmpleado(ter);
            addsimplemessages((parametroSelectedEmpleado ? "Datos de empleado actualizados!" : "Datos de empleado guardados!"));
            clearFormEmpleado();
        } catch (Exception ex) {
            ex.printStackTrace();
            addsimplemessageserror((parametroSelectedEmpleado ? "Error actualizando empleado!" : "Error creando empleado!"));
        }

    }
    
    
    public void loadFormSelectedRow() {
        
        parametroSelectedEmpleado = true;
        setParamId(selectedEmpleados.getTerEmpleadoPK().getId());
        setParamEstado(selectedEmpleados.getEstado());
        setCalcSelectedAreaNegocio(String.valueOf(selectedEmpleados.getTerAreaNegocio().getId()));
        setParamNombre(selectedEmpleados.getNombre());
        setParamSueldo(selectedEmpleados.getSueldo().toString());
        setParamIdEmpleado(selectedEmpleados.getIdEmpleado());
        setParamIdVendedor(selectedEmpleados.getIdVendedor());
        setParamFilial(selectedEmpleados.getFilial());
        setParamTasa(String.valueOf((selectedEmpleados.getTasaConversion()!=null?selectedEmpleados.getTasaConversion():0)));
        

    }
    
    public void clearFormEmpleado(){
        listEmpleados =  new  ArrayList<>();
        listEmpleados = terEmpleadoServices.findAll();
        selectedEmpleados  = null;
        setCalcSelectedAreaNegocio("");
        setParamEstado("");
        setParamIdEmpleado("");
        setParamIdVendedor("");
        setParamNombre("");
        setParamSueldo("");  
        setParamFilial("");
        setParamTasa("");
                
         parametroSelectedEmpleado = false;
        setParamId((terEmpleadoServices.getNextId()));
    }
    
    public void loadContextBeanSring() {
        usrDetails = ApplicationContextProvider.getApplicationContext().getBean(UsrDetails.class);
        terAreaNegocioServices = ApplicationContextProvider.getApplicationContext().getBean(ITerAreaNegocioServices.class);
        terEmpleadoServices = ApplicationContextProvider.getApplicationContext().getBean(ITerEmpleadoServices.class);
        

    }
    
}
