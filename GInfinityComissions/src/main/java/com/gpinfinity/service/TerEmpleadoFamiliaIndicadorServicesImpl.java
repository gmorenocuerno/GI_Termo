/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.DTO.CsvDataTableEmpFamIndicador;
import com.gpinfinity.DTO.CsvIndicadorErrorLoad;
import com.gpinfinity.DTO.EmpleadosCalcDTO;
import com.gpinfinity.DTO.IndicadorFamiliaEmpCsvDTO;
import com.gpinfinity.entities.TerEmpleadoFamiliaIndicador;
import com.gpinfinity.entities.TerEmpleadoFamiliaIndicadorPK;
import com.gpinfinity.repository.ITerEmpleadoFamiliaIndicadorRepository;
import com.gpinfinity.utils.UsrDetails;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

/**
 *
 * @author piru876
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
    public List<CsvDataTableEmpFamIndicador> allDataEmpFamIndicador() {
        List<CsvDataTableEmpFamIndicador> listDataTableCsv = new ArrayList<>();
        iterEmpleadoFamiliaIndicadorRepository.allDataEmpFamIndicador().forEach((obj) -> {
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
        List<String> listPeriodo =  new ArrayList<>();
        iterEmpleadoFamiliaIndicadorRepository.allPeriodo().forEach((obj)->{        
            listPeriodo.add(obj);
        });        
        return listPeriodo;
    }

    @Override
    public void mergeTableTerEmpFamIndicador() {
        iterEmpleadoFamiliaIndicadorRepository.mergeTableTerEmpFamIndicador();
    }

    @Override
    public List<EmpleadosCalcDTO> listAllEmpleadosCalc() {
        List<EmpleadosCalcDTO> listEmp =  new ArrayList<>();
        iterEmpleadoFamiliaIndicadorRepository.listAllEmpleadosCalc().forEach((emp)->{
            EmpleadosCalcDTO empDto = EmpleadosCalcDTO.builder()
                    .periodo(emp[0].toString())
                    .areNegocio(emp[1].toString())
                    .nombre(emp[2].toString())
                    .salario(emp[3].toString())
                    .calculado(emp[4].toString())
                    .porcenCalculado(emp[5].toString())
                    .build();
            listEmp.add(empDto);
        
        });
        
        return listEmp;
    }

}
