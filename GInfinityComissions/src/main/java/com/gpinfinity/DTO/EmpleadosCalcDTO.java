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
 *
 * @author nivrist
 */

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EmpleadosCalcDTO {
    private String periodo;
    private String areNegocio;
    private String nombre;
    private String salario;
    private String calculado;
    private String porcenCalculado;
    private String idEmpleado;
    
    
}
