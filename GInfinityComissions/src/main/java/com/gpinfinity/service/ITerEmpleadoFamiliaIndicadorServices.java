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
import java.util.List;

/**
 *
 * @author piru876
 */
public interface ITerEmpleadoFamiliaIndicadorServices {
    
public CsvIndicadorErrorLoad crearIndicadorFamEmp(IndicadorFamiliaEmpCsvDTO csvDto);

public List<CsvDataTableEmpFamIndicador> allDataEmpFamIndicador();

public void calcularComision(int areaNegocio , int periodo);

public List<String> allPeriodo();

public void mergeTableTerEmpFamIndicador();

public List<EmpleadosCalcDTO> listAllEmpleadosCalc();
    
}