/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.entities.TerParamTextil;
import java.util.List;

/**
 *
 * @author nivrist
 */
public interface ITerParamTextilServices {
    
    public List<TerParamTextil> findAll();
    public void createParameter(TerParamTextil param);
    public void updateParameter(TerParamTextil param);
    public void deleteParamter(TerParamTextil param);
    public int getNextId();
    
    
}
