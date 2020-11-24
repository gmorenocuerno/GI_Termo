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
import com.gpinfinity.service.ITerEmpleadoFamiliaIndicadorServices;
import com.gpinfinity.utils.Utils;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
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
    private int selectedMes;
    private int rptPeriodoInicial;
    private int rptPeriodoFinal;
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
    ITerEmpleadoFamiliaIndicadorServices iterEmpleadoFamiliaIndicadorServices;

    @PostConstruct
    public void init() {
        loadContextBeanSring();
        loadPeriodList();
        textilesLoadTotal();
        textilesLoadMensual();
        textilesLoadAcumulado();
        selectedMes = 0;  
        


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

    public void periodFilasMostrar(){
    String mes=periodo.substring(4);  
    if(mes.equals("")){
    selectedMes = 0;            
    }
    else {
    selectedMes =  Integer.parseInt(mes);
    }
    System.out.println("Mes Seleccionado" +  selectedMes);
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

    public void generarReporte(){
    String file_name = "Reporte_Comisiones.csv";

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
            iterEmpleadoFamiliaIndicadorServices.reporteDataTextiles(rptPeriodoInicial, rptPeriodoFinal).forEach((obj) -> {

                    try {
                         List<Object> list1 = new ArrayList<>();
                        list1.add(obj.getPeriodo());
                        list1.add(obj.getAreanegocio());
                        list1.add(obj.getFilial());
                        list1.add(obj.getIdEmpleado());
                        list1.add(obj.getEmpleado());
                        list1.add(obj.getSalario());
                        list1.add(obj.getCalculo());
                        list1.add(obj.getPorceVariable());
                        lists.add(list1);

                      
                    } catch (Exception ex) {
                        Logger.getLogger(dowloadTemplate.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });            
            String[] header = {"Periodo","Area de Negocio","Filial","Id Empleado","Empleado","Salario","Calculo","Porcentaje Calculado"};

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
        iterEmpleadoFamiliaIndicadorServices =  ApplicationContextProvider.getApplicationContext().getBean(ITerEmpleadoFamiliaIndicadorServices.class);

    }

}
