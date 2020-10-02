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
public class CsvTextilErrorLoad {
    
    private String linea;
    private String mensaje;
    private String estadoLinea;
    
    
}
