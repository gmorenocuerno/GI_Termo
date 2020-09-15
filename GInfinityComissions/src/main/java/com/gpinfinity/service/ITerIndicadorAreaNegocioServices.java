/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.entities.TerIndicadorAreaNegocio;
import java.util.List;


/**
 *
 * @author piru876
 */
public interface ITerIndicadorAreaNegocioServices {
    
     public List<TerIndicadorAreaNegocio> listAllIndicadorAreaNegocio();
     
     public List<TerIndicadorAreaNegocio>  findByIdAreaNegocio(int idAreaNegocio);
     
     
}
