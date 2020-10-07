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
@NoArgsConstructor
@Builder
public class TerParamBoniTextilDTO {
    
    private String id;
    private String descripcionAreaNegocio;
    private String descripcion;
    private String bono;
    private String periodo;
    private String estado;
    private String tipo;
    private String idAreaNegocio;
    
}
