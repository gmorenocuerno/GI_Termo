/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que define ls tabla para exportar los datos del resultado de los calculos para las areas de negocio de B2D, B2C, B2B Segmentado y Creditos y Cobros
 * @author Grupo Infinity, S.A. DE C.V.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CsvDataTableEmpFamIndicador {
    
    private String rowNum;
    private String descAreaNegocio;
    private String nombreEmpleado;
    private String descIndicador;
    private String descFamilia;
    private String periodo;
    private String meta;
    private String montoReal;
    private String porcentajeCumplimiento;
    private String peso;
    private String porcentajeVariable;
    private String ponderacion;
    private String montoAplicado;
    private String montoCalculado;
    
    
    
}
