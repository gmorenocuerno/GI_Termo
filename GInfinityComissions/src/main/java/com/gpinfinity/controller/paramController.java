/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.controller;

import com.gpinfinity.DTO.CsvDataTableEmpFamIndicador;
import com.gpinfinity.DTO.CsvIndicadorErrorLoad;
import com.gpinfinity.DTO.EmpleadosCalcDTO;
import com.gpinfinity.DTO.IndicadorFamiliaEmpCsvDTO;
import com.gpinfinity.config.ApplicationContextProvider;
import com.gpinfinity.entities.TerParametroIndicador;
import com.gpinfinity.entities.TerParametroIndicadorPK;
import com.gpinfinity.DTO.TerParametrosDTO;
import com.gpinfinity.service.ITerAreaNegocioServices;
import com.gpinfinity.service.ITerEmpleadoFamiliaIndicadorServices;
import com.gpinfinity.service.ITerIndicadorAreaNegocioServices;
import com.gpinfinity.service.ITerParametroIndicadorServices;
import com.gpinfinity.utils.UsrDetails;
import com.gpinfinity.utils.Utils;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import javax.inject.Named;
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
import javax.faces.view.ViewScoped;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author nivrist
 */
@Named(value = "paramController")
@ViewScoped
@Data
@EqualsAndHashCode(callSuper = false)
public class paramController extends Utils implements Serializable {

    /**
     * Creates a new instance of paramController
     */
    public paramController() {
    }

    private int paramId;
    private String calcSelectedPeriodo;
    private int rptPeriodoInicial;
    private int rptPeriodoFinal;
    private int rptAreaNegocio;
    private String calcSelectedAreaNegocio;
    private int buscarAreanegocio;
    private int buscarPeriodo;
    private String paramIdIndicador;
    private String paramIdAreaNegocio;
    private String paramDescripcion;
    private String paramRnkInicial;
    private String paramRnkFinal;
    private String paramPorcent;
    private String paramEstado;
    
    private List<TerParametrosDTO> listTerParametroIndicador;
    private TerParametrosDTO selectedTerParametroIndicador;
    private List<SelectItem> listAreaNegocio;
    private List<SelectItem> listAreaNegocioParametro;
    private List<SelectItem> listPeriodo;
    private List<SelectItem> listIndicador;
    private UploadedFile file;
    private List<CsvIndicadorErrorLoad> listCsvLoadErrors;
    private List<CsvDataTableEmpFamIndicador> listCsvDataTableEmpFamIndicador;
    private List<EmpleadosCalcDTO> listEmpleadosDto;

    private boolean parametroselected = false;

    ITerAreaNegocioServices terAreaNegocioServices;
    ITerIndicadorAreaNegocioServices terIndicadorAreaNegocioServices;
    ITerParametroIndicadorServices terParametroIndicadorServices;
    ITerEmpleadoFamiliaIndicadorServices iterEmpleadoFamiliaIndicadorServices;
    UsrDetails usrDetails;
     
    
    @PostConstruct
    public void Init() {

        loadContextBeanSring();
        clearParametroForm();
        //loadDataEmpFamIndicador();
        //loadListEmpleadosCalcDto();
        listAreaNegocio = new ArrayList<>();
        terAreaNegocioServices.listAllAreaNegocio().forEach((arn) -> {
            listAreaNegocio.add(new SelectItem(arn.getId(), arn.getDescripcion()));
        });
        listAreaNegocioParametro = new ArrayList<>();
        terAreaNegocioServices.listAllAreaNegocioConTextil().forEach((arn) -> {
            listAreaNegocioParametro.add(new SelectItem(arn.getId(), arn.getDescripcion()));
        });
        
        
        
        
       

        loadPeriodList();

    }
    
   
    

    public void loadListEmpeladosCalcDtoFiltro(){
        
        listEmpleadosDto = new ArrayList<>();
        listEmpleadosDto = iterEmpleadoFamiliaIndicadorServices.listAllEmpleadosCalc(Integer.parseInt(calcSelectedAreaNegocio), Integer.parseInt(calcSelectedPeriodo));
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
            iterEmpleadoFamiliaIndicadorServices.reporteData(rptPeriodoInicial, rptPeriodoFinal, rptAreaNegocio).forEach((obj) -> {

                    try {
                         List<Object> list1 = new ArrayList<>();
                        list1.add(obj.getPeriodo());
                        list1.add(obj.getAreanegocio());
                        list1.add(obj.getFilial());
                        list1.add(obj.getTasa());
                        list1.add(obj.getIdEmpleado());
                        list1.add(obj.getEmpleado());
                        list1.add(obj.getSalario());
                        list1.add(obj.getCalculoMensualLocal());
                        list1.add(obj.getPorceVariableMensualLocal());
                        lists.add(list1);

                      
                    } catch (Exception ex) {
                        Logger.getLogger(dowloadTemplate.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });            
            String[] header = {"Periodo","Area de Negocio","Filial","Tasa", "Id Empleado","Empleado","Salario","Calculo","Porcentaje Calculado"};

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
        iterEmpleadoFamiliaIndicadorServices.allPeriodo().forEach((per) -> {
            listPeriodo.add(new SelectItem(per, per));
        });
    }

    public void loadIndicador() {

        listIndicador = new ArrayList<>();
        terIndicadorAreaNegocioServices.findByIdAreaNegocio(Integer.parseInt(getParamIdAreaNegocio())).forEach((iarn) -> {
            listIndicador.add(new SelectItem(iarn.getTerIndicadorAreaNegocioPK().getId(), iarn.getDescripcion()));
        });

    }

    public String crearParametro() {

        try {

            if (usrDetails.converStringToBigDecimal(paramRnkInicial).compareTo(usrDetails.converStringToBigDecimal(paramRnkFinal)) == 1) {
                addsimplemessageswarn("El rango inicial no puedo ser mayor al rango final");
                return null;
            }

            TerParametroIndicadorPK pk = TerParametroIndicadorPK.builder().id(paramId).idAreaNegocio(Integer.parseInt(paramIdAreaNegocio)).idIndicador(Integer.parseInt(paramIdIndicador)).build();
            TerParametroIndicador prametro = TerParametroIndicador.builder()
                    .descripcion(paramDescripcion)
                    .estado(paramEstado)
                    .porcVariable(usrDetails.converStringToBigDecimal(paramPorcent))
                    .rangoFinal(usrDetails.converStringToBigDecimal(paramRnkFinal))
                    .rangoInicial(usrDetails.converStringToBigDecimal(paramRnkInicial))
                    .usuarioCreacion(usrDetails.get_userName())
                    .fechaCreacion(usrDetails.getDate())
                    .terParametroIndicadorPK(pk).build();

            terParametroIndicadorServices.createParameter(prametro);
            addsimplemessages("Datos de parametros guardados!");
            clearParametroForm();
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            addsimplemessageserror("Error creando parametros.");

        }

        return null;
    }

    public void clearParametroForm() {

        setParamId(terParametroIndicadorServices.getNextId());
        listTerParametroIndicador = new ArrayList<>();
        listTerParametroIndicador = terParametroIndicadorServices.allParametros();
        setParamDescripcion("");
        setParamEstado("");
        setParamIdAreaNegocio("");
        setParamIdIndicador("");
        setParamPorcent("");
        setParamRnkFinal("");
        setParamRnkInicial("");
        selectedTerParametroIndicador = null;
        parametroselected = false;
        

    }

    public void loadForm() {
        parametroselected = true;
        TerParametroIndicadorPK pk = TerParametroIndicadorPK.builder()
                .id(Integer.parseInt(selectedTerParametroIndicador.getId()))
                .idAreaNegocio(Integer.parseInt(selectedTerParametroIndicador.getIdAreaNegocio()))
                .idIndicador(Integer.parseInt(selectedTerParametroIndicador.getIdIndicador()))
                .build();

        TerParametroIndicador param = terParametroIndicadorServices.findByPk(pk);
        listIndicador = new ArrayList<>();
        terIndicadorAreaNegocioServices.findByIdAreaNegocio(param.getTerParametroIndicadorPK().getIdAreaNegocio()).forEach((iarn) -> {
            listIndicador.add(new SelectItem(iarn.getTerIndicadorAreaNegocioPK().getId(), iarn.getDescripcion()));
        });
        setParamDescripcion(param.getDescripcion());
        setParamEstado(param.getEstado());
        setParamId(param.getTerParametroIndicadorPK().getId());
        setParamIdAreaNegocio(String.valueOf(param.getTerParametroIndicadorPK().getIdAreaNegocio()));
        setParamIdIndicador(String.valueOf(param.getTerParametroIndicadorPK().getIdIndicador()));
        setParamPorcent(param.getPorcVariable().toString());
        setParamRnkFinal(param.getRangoFinal().toString());
        setParamRnkInicial(param.getRangoInicial().toString());

    }
    int linea = 2;

    public void handleFileUpload(FileUploadEvent event) {
        List<List<String>> records = new ArrayList<>();
        List<IndicadorFamiliaEmpCsvDTO> listIndicadorFaEmCsv = new ArrayList<>();
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

            IndicadorFamiliaEmpCsvDTO csv = IndicadorFamiliaEmpCsvDTO.builder()
                    .idAreaNegocio(rws.get(0).replace("\"", "").trim())
                    .idEmpleado(rws.get(2).replace("\"", "").trim())
                    .idFamilia(rws.get(7).replace("\"", "").trim())
                    .idIndicador(rws.get(5).replace("\"", "").trim())
                    .meta((!rws.get(10).equals(" ")?rws.get(10).replace("\"", "").trim():"0"))
                    .periodo((!rws.get(9).equals(" ")?rws.get(9).replace("\"", "").trim():"0"))
                    .real((!rws.get(11).equals(" ")?rws.get(11).replace("\"", "").trim():"0")).build();
            listIndicadorFaEmCsv.add(csv);
        });
        linea = 2;
        listCsvLoadErrors = new ArrayList<>();
        listIndicadorFaEmCsv.forEach((csvDto) -> {

            CsvIndicadorErrorLoad resp = iterEmpleadoFamiliaIndicadorServices.crearIndicadorFamEmp(csvDto);
            resp.setLinea(String.valueOf(linea));
            linea += 1;
            listCsvLoadErrors.add(resp);
        });
        iterEmpleadoFamiliaIndicadorServices.mergeTableTerEmpFamIndicador();
        addsimplemessages("Archivo " + event.getFile().getFileName() + " cargado");
        loadDataEmpFamIndicador();
        loadPeriodList();
        
    }

    public void loadData(){
    listCsvDataTableEmpFamIndicador = new ArrayList<>();
        listCsvDataTableEmpFamIndicador = iterEmpleadoFamiliaIndicadorServices.allDataEmpFamIndicador(buscarAreanegocio,buscarPeriodo);
    }
    
    public void loadDataEmpFamIndicador() {
        listCsvDataTableEmpFamIndicador = new ArrayList<>();
        listCsvDataTableEmpFamIndicador = iterEmpleadoFamiliaIndicadorServices.allDataEmpFamIndicador(buscarAreanegocio,buscarPeriodo);

    }

    public void calcularComisiones() {
        try {
            iterEmpleadoFamiliaIndicadorServices.calcularComision(Integer.parseInt(calcSelectedAreaNegocio), Integer.parseInt(calcSelectedPeriodo));
            loadDataEmpFamIndicador();
            setCalcSelectedAreaNegocio("");
            setCalcSelectedPeriodo("");
            addsimplemessages("Proceso de calculo ejecutado con exito");
            
        } catch (NumberFormatException ex) {
            addsimplemessageserror("Ocurrio un error al ejecutar el Proceso de calculo");
        }
    }

    public void loadContextBeanSring() {

        terAreaNegocioServices = ApplicationContextProvider.getApplicationContext().getBean(ITerAreaNegocioServices.class);
        terParametroIndicadorServices = ApplicationContextProvider.getApplicationContext().getBean(ITerParametroIndicadorServices.class);
        terIndicadorAreaNegocioServices = ApplicationContextProvider.getApplicationContext().getBean(ITerIndicadorAreaNegocioServices.class);
        usrDetails = ApplicationContextProvider.getApplicationContext().getBean(UsrDetails.class);
        iterEmpleadoFamiliaIndicadorServices = ApplicationContextProvider.getApplicationContext().getBean(ITerEmpleadoFamiliaIndicadorServices.class);
    }

}
