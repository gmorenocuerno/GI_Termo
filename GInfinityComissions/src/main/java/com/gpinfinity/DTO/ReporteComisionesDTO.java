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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteComisionesDTO {
    private String periodo;
    private String areanegocio;
    private String filial;
    private String idEmpleado;
    private String Salario;
    private String calculoMensual;
    private String calculoAcumulado;
    private String porceVariableMensual;
    private String porceVariableAcumulado;
    private String empleado;
}
