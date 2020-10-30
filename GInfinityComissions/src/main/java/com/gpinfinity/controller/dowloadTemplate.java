/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.controller;

import com.gpinfinity.config.ApplicationContextProvider;
import com.gpinfinity.service.ITerEmpleadoFamiliaIndicadorServices;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import static java.lang.System.out;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author nivrist
 */
@Named(value = "dowloadTemplate")
@RequestScoped
public class dowloadTemplate implements Serializable {

    private StreamedContent fileDowloadCsv;

    /**
     * Creates a new instance of dowloadTemplate
     */
    public dowloadTemplate() {
        /*fileDowloadCsv = DefaultStreamedContent.builder()
                .name("plantilla_carga_textiles.csv")
                .contentType("text/csv")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/planilla_carga_textiles.csv"))
                .build();*/
    }

    ITerEmpleadoFamiliaIndicadorServices iTerEmpleadoFamiliaIndicadorServices;

    @PostConstruct
    public void Init() {
        loadContextBeanSring();

    }

    public StreamedContent getDowloadFileCsvTextiles() {

        InputStream stream = getClass().getClassLoader().getResourceAsStream("planilla_carga_textiles.csv");
        StreamedContent streamedContent = new DefaultStreamedContent(stream, "text/csv", "Template_textiles.csv");

        System.out.println(streamedContent);
        return streamedContent;
    }

    public StreamedContent getDowloadFileCsvIndicadores() {

        InputStream stream = getClass().getClassLoader().getResourceAsStream("plantilla_b2d.csv");
        StreamedContent streamedContent = new DefaultStreamedContent(stream, "text/csv", "Template_indicadores.csv");

        System.out.println(streamedContent);
        return streamedContent;
    }

    public StreamedContent getFileDowloadCsv() {
        return fileDowloadCsv;
    }

    public void setFileDowloadCsv(StreamedContent fileDowloadCsv) {
        this.fileDowloadCsv = fileDowloadCsv;
    }

    public void download() {
        String file_name = "Template_indicadores.csv";

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseCharacterEncoding("UTF-8");
        ec.setResponseContentType("text/csv");
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + file_name + "\"");

        BufferedOutputStream csvOut;
        try {
            csvOut = new BufferedOutputStream(ec.getResponseOutputStream());

            try (BufferedWriter csvWriter = new BufferedWriter(new OutputStreamWriter(csvOut, StandardCharsets.UTF_8))) {
                csvWriter.append("id_area_negocio,area_de_negocio,id_empleado,cod_empleado,nombre,id_indicador,indicador,id_familia,familia,periodo,meta,real");
                csvWriter.append("\n");

                iTerEmpleadoFamiliaIndicadorServices.listaCsvIndicadorPlantilla().forEach((obj) -> {

                    try {
                        csvWriter.append(obj.getIdAreaNeogocio() + "," + obj.getAreaNegocio() + "," + obj.getIdEmpelado() + "," + obj.getCodEmpleado() + "," + obj.getNombre() + "," + obj.getIdIndicador() + "," + obj.getIndicador() + "," + obj.getIdFamilia() + "," + obj.getFamilia() + ", , , ");
                        csvWriter.append("\n");

                    } catch (IOException ex) {
                        Logger.getLogger(dowloadTemplate.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
                csvWriter.flush();
            }
            csvOut.close();
        } catch (IOException ex) {

        } finally {
            fc.responseComplete();
        }

    }

    public void downloadPlantilla() {
        String file_name = "Template_indicadores.csv";

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseCharacterEncoding("UTF-8");
        ec.setResponseContentType("text/csv");
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + file_name + "\"");
        BufferedOutputStream csvOut = null;
        try {
            csvOut = new BufferedOutputStream(ec.getResponseOutputStream());
            List<List<Object>> lists = new ArrayList<>();
            iTerEmpleadoFamiliaIndicadorServices.listaCsvIndicadorPlantilla().forEach((obj) -> {

                    try {
                         List<Object> list1 = new ArrayList<>();
                        list1.add(obj.getIdAreaNeogocio());
                        list1.add(obj.getAreaNegocio());
                        list1.add(obj.getIdEmpelado());
                        list1.add(obj.getCodEmpleado());
                        list1.add(obj.getNombre());
                        list1.add(obj.getIdIndicador());
                        list1.add(obj.getIndicador());
                        list1.add(obj.getIdFamilia());
                        list1.add(obj.getFamilia());
                        list1.add(" ");
                        list1.add(" ");
                        list1.add(" ");
                        
                        lists.add(list1);

                      
                    } catch (Exception ex) {
                        Logger.getLogger(dowloadTemplate.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
            
           
            
            
            
            String[] header = {"id_area_negocio","area_de_negocio","id_empleado","cod_empleado","nombre","id_indicador","indicador","id_familia","familia","periodo","meta","real"};

// Ellipsis out  
            exportCSVFile(csvOut, lists, "UTF-8", header);
            csvOut.close();
        } catch (IOException ex) {
            Logger.getLogger(dowloadTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            fc.responseComplete();
        }

    }

    public void exportCSVFile(OutputStream out, Iterable<?> iter, String charset, String... header) {
        try {
            // Write bom to prevent Chinese miscoding  
            byte[] bytes = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
            out.write(bytes);

            OutputStreamWriter osw = new OutputStreamWriter(out, charset);
            CSVFormat csvFormat = CSVFormat.EXCEL.withHeader(header);

            CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);
            csvPrinter.printRecords(iter);
            csvPrinter.flush();
            csvPrinter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadContextBeanSring() {
        iTerEmpleadoFamiliaIndicadorServices = ApplicationContextProvider.getApplicationContext().getBean(ITerEmpleadoFamiliaIndicadorServices.class);

    }

}
