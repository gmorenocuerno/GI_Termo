/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.controller;

import com.gpinfinity.DTO.CsvTextilErrorLoad;
import com.gpinfinity.DTO.TextilAnualDTO;
import com.gpinfinity.DTO.TextilCsvLoad;
import com.gpinfinity.DTO.TextilMensualDTO;
import com.gpinfinity.DTO.TextilTotalDTO;
import com.gpinfinity.config.ApplicationContextProvider;
import com.gpinfinity.entities.TerComisionesTextil;
import com.gpinfinity.service.ITerComisionesTextilServices;
import com.gpinfinity.utils.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author nivrist
 */
@Named(value = "textilController")
@ViewScoped
@Data
@EqualsAndHashCode(callSuper = false)
public class textilController extends Utils implements Serializable {

    private List<CsvTextilErrorLoad> listCsvLoadErrors;
    private String periodo;
    private int buscarPeriodo;
    private List<SelectItem> listPeriodo;
    private List<TerComisionesTextil> listTableTerComisionesTextil;
    private List<TextilTotalDTO> textiBonoList;
    private List<TextilMensualDTO> textiMensualList;
    private List<TextilAnualDTO> textiAnulList;
     private StreamedContent fileDowloadCsv; 
    

    /**
     * Creates a new instance of textilController
     */
    private ITerComisionesTextilServices iTerComisionesTextilServices;

    @PostConstruct
    public void init() {
        loadContextBeanSring();
        loadPeriodList();
        textilesLoadTotal();
        textilesLoadMensual();
        textilesLoadAcumulado();
        


    }

    public void textilesLoadTotal() {

        textiBonoList = new ArrayList<>();
        textiBonoList = iTerComisionesTextilServices.textilesTotalList();

    }
    
    public void dowloadFileCsv(){
    fileDowloadCsv = DefaultStreamedContent.builder()
                .name("plantilla_carga_textiles.csv")
                .contentType("text/csv")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/planilla_carga_textiles.csv"))
                .build();
    System.out.println(fileDowloadCsv);
    }

    public void textilesLoadMensual() {

        textiMensualList = new ArrayList<>();
        textiMensualList = iTerComisionesTextilServices.textilesMensualList();

    }

    public void textilesLoadAcumulado() {

        textiAnulList = new ArrayList<>();
        textiAnulList = iTerComisionesTextilServices.textilesAcumuladoList();

    }

    public textilController() {
    }

    public void calcularComisionTextil() {

        try {
            int flag = iTerComisionesTextilServices.ejecutarProcedimientoTextil(Integer.parseInt(periodo));
            if (flag == 0) {
                addsimplemessages("Proceso de calculo ejecutado con exito");
                textilesLoadTotal();
                textilesLoadMensual();
                textilesLoadAcumulado();
            } else {
                addsimplemessageserror("Ocurrio un error al ejecutar el Proceso de calculo");
            }

        } catch (NumberFormatException ex) {
            addsimplemessageserror("Ocurrio un error al ejecutar el Proceso de calculo");
        }

    }

    int linea = 2;

    public void handleFileUpload(FileUploadEvent event) {
        List<List<String>> records = new ArrayList<>();
        List<TextilCsvLoad> listIndicadorFaEmCsv = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(event.getFile().getInputStream(), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException ex) {
            Logger.getLogger(paramController.class.getName()).log(Level.SEVERE, null, ex);
        }
        records.remove(0);
        records.forEach((rws) -> {

            TextilCsvLoad csv = TextilCsvLoad.builder()
                    .periodo(rws.get(0).replace("\"", "").trim())
                    .valorMensual(rws.get(1).replace("\"", "").trim())
                    .valorPresupuestoMensual(rws.get(2).replace("\"", "").trim())
                    .valorRealMensual(rws.get(3).replace("\"", "").trim())
                    .valorCxc30(rws.get(4).replace("\"", "").trim())
                    .valorInventario45(rws.get(5).replace("\"", "").trim()).build();
            listIndicadorFaEmCsv.add(csv);
        });
        linea = 2;
        listCsvLoadErrors = new ArrayList<>();
        System.out.println("Valor Csv" + listIndicadorFaEmCsv.size());
        listIndicadorFaEmCsv.forEach((csvDto) -> {

            CsvTextilErrorLoad resp = iTerComisionesTextilServices.crearTerTextiles(csvDto);
            resp.setLinea(String.valueOf(linea));
            linea += 1;
            listCsvLoadErrors.add(resp);
        });
        //iterEmpleadoFamiliaIndicadorServices.mergeTableTerEmpFamIndicador();
        addsimplemessages("Archivo " + event.getFile().getFileName() + " cargado");
        //loadDataEmpFamIndicador();
        loadPeriodList();
        //loadListEmpleadosCalcDto();
    }

    public void loadPeriodList() {

        listPeriodo = new ArrayList<>();
        iTerComisionesTextilServices.allPeriodo().forEach((per) -> {
            listPeriodo.add(new SelectItem(per, per));
        });
    }

    public void loadData() {

        listTableTerComisionesTextil = new ArrayList<>();
        listTableTerComisionesTextil = iTerComisionesTextilServices.allTerComisionesTextiles(buscarPeriodo);

    }

    public void loadContextBeanSring() {

        iTerComisionesTextilServices = ApplicationContextProvider.getApplicationContext().getBean(ITerComisionesTextilServices.class);

    }

}
