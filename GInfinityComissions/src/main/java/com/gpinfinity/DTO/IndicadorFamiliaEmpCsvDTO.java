/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.DTO;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author piru876
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndicadorFamiliaEmpCsvDTO implements Serializable{
    private String idAreaNegocio;
    private String idEmpleado;
    private String idIndicador;
    private String idFamilia;
    private String periodo;
    private String meta;
    private String real;
    
}
