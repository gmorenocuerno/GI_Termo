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
import com.gpinfinity.entities.TerEmpleado;
import java.util.List;

/**
 *
 * @author nivrist
 */
public interface ITerEmpleadoFamiliaIndicadorServices {
    
public CsvIndicadorErrorLoad crearIndicadorFamEmp(IndicadorFamiliaEmpCsvDTO csvDto);

public List<CsvDataTableEmpFamIndicador> allDataEmpFamIndicador(int idAreaNegocio , int periodo);

public void calcularComision(int areaNegocio , int periodo);

public List<ReporteComisionesDTO> reporteData(int periodoInicial , int periodoFinal, int areaNegocio);

public List<ReporteComisionesDTO> reporteDataTextiles(int periodoInicial , int periodoFinal);

public List<String> allPeriodo();

public void mergeTableTerEmpFamIndicador();

public List<Object[]> listEmpeladoByAreaNegocio(int  idAreaNegocio);

public List<CsvDataIndicadorPlantilla> listaCsvIndicadorPlantilla();

public List<EmpleadosCalcDTO> listAllEmpleadosCalc(int idAreaNegocio , int periodo);
    
}
