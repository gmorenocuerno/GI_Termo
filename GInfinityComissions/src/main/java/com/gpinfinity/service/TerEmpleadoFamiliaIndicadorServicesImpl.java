/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.DTO.CsvDataIndicadorPlantilla;
import com.gpinfinity.DTO.CsvDataTableEmpFamIndicador;
import com.gpinfinity.DTO.CsvIndicadorErrorLoad;
import com.gpinfinity.DTO.EmpleadosCalcDTO;
import com.gpinfinity.DTO.IndicadorFamiliaEmpCsvDTO;
import com.gpinfinity.DTO.ReporteComisionesDTO;
import com.gpinfinity.entities.TerEmpleadoFamiliaIndicador;
import com.gpinfinity.entities.TerEmpleadoFamiliaIndicadorPK;
import com.gpinfinity.repository.ITerEmpleadoFamiliaIndicadorRepository;
import com.gpinfinity.utils.UsrDetails;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

/**
 *
 * @author nivrist
 */
@Service
public class TerEmpleadoFamiliaIndicadorServicesImpl implements ITerEmpleadoFamiliaIndicadorServices {

    @Autowired
    ITerEmpleadoFamiliaIndicadorRepository iterEmpleadoFamiliaIndicadorRepository;

    @Autowired
    UsrDetails usrDet;

    @Override
    public CsvIndicadorErrorLoad crearIndicadorFamEmp(IndicadorFamiliaEmpCsvDTO csvDto) {
        int creado = 0;
        CsvIndicadorErrorLoad resp;
        try {
            TerEmpleadoFamiliaIndicadorPK pk = TerEmpleadoFamiliaIndicadorPK.builder()
                    .idAreaNegocio(Integer.parseInt(csvDto.getIdAreaNegocio()))
                    .idEmpleado(Integer.parseInt(csvDto.getIdEmpleado()))
                    .idFamilia(Integer.parseInt(csvDto.getIdFamilia()))
                    .idIndicador(Integer.parseInt(csvDto.getIdIndicador()))
                    .build();
            TerEmpleadoFamiliaIndicador terFamEmp = TerEmpleadoFamiliaIndicador.builder()
                    .estado("A")
                    .fechaCreacion(usrDet.getDate())
                    .usuarioCreacion(usrDet.get_userName())
                    .terEmpleadoFamiliaIndicadorPK(pk)
                    .montoReal(new BigDecimal(csvDto.getReal()))
                    .periodo(Integer.parseInt(csvDto.getPeriodo()))
                    .meta(new BigDecimal(csvDto.getMeta()))
                    .build();
            iterEmpleadoFamiliaIndicadorRepository.save(terFamEmp);
            resp = CsvIndicadorErrorLoad.builder()
                    .estadoLinea("C")
                    .mensaje("Linea cargada con exito")
                    .build();
            creado = 1;
        } catch (NumberFormatException | TransactionSystemException exc) {
            resp = CsvIndicadorErrorLoad.builder()
                    .estadoLinea("E")
                    .mensaje("Error al intentar realizar la carga")
                    .build();
            System.out.println("Causa de error:" + exc.getCause());
            System.out.println("Message de error:" + exc.getMessage());
            exc.printStackTrace();
        }
        return resp;
    }

    @Override
    public List<CsvDataTableEmpFamIndicador> allDataEmpFamIndicador(int idAreaNegocio, int periodo) {
        List<CsvDataTableEmpFamIndicador> listDataTableCsv = new ArrayList<>();
        iterEmpleadoFamiliaIndicadorRepository.allDataEmpFamIndicador(idAreaNegocio, periodo).forEach((obj) -> {
            CsvDataTableEmpFamIndicador data = CsvDataTableEmpFamIndicador.builder()
                    .rowNum(obj[0].toString())
                    .descAreaNegocio(obj[1].toString())
                    .nombreEmpleado(obj[2].toString())
                    .descIndicador(obj[3].toString())
                    .descFamilia(obj[4].toString())
                    .periodo(obj[5].toString())
                    .meta(obj[6].toString())
                    .montoReal(obj[7].toString())
                    .porcentajeCumplimiento(obj[8].toString())
                    .peso(obj[9].toString())
                    .porcentajeVariable(obj[10].toString())
                    .ponderacion(obj[11].toString())
                    .montoAplicado(obj[12].toString())
                    .montoCalculado(obj[13].toString())
                    .filial(obj[14].toString())
                    .build();
            listDataTableCsv.add(data);

        });

        return listDataTableCsv;

    }

    @Override
    public void calcularComision(int areaNegocio, int periodo) {
        try {

            iterEmpleadoFamiliaIndicadorRepository.calculaComisiones(areaNegocio, periodo);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<String> allPeriodo() {
        List<String> listPeriodo = new ArrayList<>();
        iterEmpleadoFamiliaIndicadorRepository.allPeriodo().forEach((obj) -> {
            listPeriodo.add(obj);
        });
        return listPeriodo;
    }

    @Override
    public void mergeTableTerEmpFamIndicador() {
        iterEmpleadoFamiliaIndicadorRepository.mergeTableTerEmpFamIndicador();
    }

    @Override
    public List<EmpleadosCalcDTO> listAllEmpleadosCalc(int idAreaNegocio, int periodo) {
        List<EmpleadosCalcDTO> listEmp = new ArrayList<>();
        iterEmpleadoFamiliaIndicadorRepository.listAllEmpleadosCalc(idAreaNegocio, periodo).forEach((emp) -> {
            EmpleadosCalcDTO empDto = EmpleadosCalcDTO.builder()
                    .periodo(emp[0].toString())
                    .areNegocio(emp[1].toString())
                    .nombre(emp[2].toString())
                    .salario(emp[3].toString())
                    .calculado(emp[4].toString())
                    .porcenCalculado(emp[5].toString())
                    .idEmpleado(emp[6].toString())
                    .build();
            listEmp.add(empDto);

        });

        return listEmp;
    }

    @Override
    public List<CsvDataIndicadorPlantilla> listaCsvIndicadorPlantilla() {
        List<CsvDataIndicadorPlantilla> listEmp = new ArrayList<>();
        iterEmpleadoFamiliaIndicadorRepository.listIndicadorCsvObject().forEach((obj) -> {
            CsvDataIndicadorPlantilla emp = CsvDataIndicadorPlantilla.builder()
                    .idAreaNeogocio((obj[0] != null ? obj[0].toString() : ""))
                    .areaNegocio((obj[1] != null ? obj[1].toString() : ""))
                    .idEmpelado((obj[2] != null ? obj[2].toString() : ""))
                    .codEmpleado((obj[3] != null ? obj[3].toString() : ""))
                    .nombre((obj[4] != null ? obj[4].toString() : ""))
                    .idIndicador((obj[5] != null ? obj[5].toString() : ""))
                    .indicador((obj[6] != null ? obj[6].toString() : ""))
                    .idFamilia((obj[7] != null ? obj[7].toString() : ""))
                    .familia((obj[8] != null ? obj[8].toString() : "")).build();
            listEmp.add(emp);
        });
        return listEmp;
    }

    @Override
    public List<ReporteComisionesDTO> reporteData(int periodoInicial, int periodoFinal) {

        List<ReporteComisionesDTO> listEmp = new ArrayList<>();
        iterEmpleadoFamiliaIndicadorRepository.genReporteUn(periodoInicial, periodoFinal).forEach(o -> {
           ReporteComisionesDTO dta =  ReporteComisionesDTO.builder()
                   .periodo((o[0] !=null ? o[0].toString():""))
                   .areanegocio((o[1] !=null ? o[1].toString():""))
                   .filial((o[2] !=null ? o[2].toString():""))
                   .idEmpleado((o[3] !=null ? o[3].toString():""))
                   .empleado((o[4] !=null ? o[4].toString():""))
                   .Salario((o[5] !=null ? o[5].toString():""))
                   .calculo((o[6] !=null ? o[6].toString():""))
                   .porceVariable((o[7] !=null ? o[7].toString():""))
                   .build();
           listEmp.add(dta);
           String path = File.separator + "var"+ File.separator + "temp";

        });

        return listEmp;
    }

    @Override
    public List<ReporteComisionesDTO> reporteDataTextiles(int periodoInicial, int periodoFinal) {
        List<ReporteComisionesDTO> listEmp = new ArrayList<>();
        iterEmpleadoFamiliaIndicadorRepository.genReporteUnTextil(periodoInicial, periodoFinal).forEach(o -> {
           ReporteComisionesDTO dta =  ReporteComisionesDTO.builder()
                   .periodo((o[0] !=null ? o[0].toString():""))
                   .areanegocio((o[1] !=null ? o[1].toString():""))
                   .filial((o[2] !=null ? o[2].toString():""))
                   .idEmpleado((o[3] !=null ? o[3].toString():""))
                   .empleado((o[4] !=null ? o[4].toString():""))
                   .Salario((o[5] !=null ? o[5].toString():""))
                   .calculo((o[6] !=null ? o[6].toString():""))
                   .porceVariable((o[7] !=null ? o[7].toString():""))
                   .build();
           listEmp.add(dta);
           String path = File.separator + "var"+ File.separator + "temp";

        });

        return listEmp;
    }

}
