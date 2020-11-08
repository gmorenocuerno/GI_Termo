/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.entities.TerAreaNegocio;
import java.util.List;


/**
 *
 * @author nivrist
 */
public interface ITerAreaNegocioServices {
    
    public List<TerAreaNegocio> listAllAreaNegocio();
    
    public List<TerAreaNegocio> listAllAreaNegocioConTextil();
    
}
