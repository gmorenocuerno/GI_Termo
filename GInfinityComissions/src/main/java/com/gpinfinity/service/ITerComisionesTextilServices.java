/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.DTO.CsvTextilErrorLoad;
import com.gpinfinity.DTO.TextilCsvLoad;
import com.gpinfinity.entities.TerComisionesTextil;
import java.util.List;

/**
 *
 * @author nivrist
 */
public interface ITerComisionesTextilServices {
    
    public CsvTextilErrorLoad crearTerTextiles(TextilCsvLoad csvDto);
    
    public List<String> allPeriodo();
    
    public int ejecutarProcedimientoTextil(int periodo);
    
    public List<TerComisionesTextil> allTerComisionesTextiles(int periodo);
}
