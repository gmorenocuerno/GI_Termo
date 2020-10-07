/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.DTO.TerParamBoniTextilDTO;
import com.gpinfinity.entities.TerParamBonifTextil;
import com.gpinfinity.entities.TerParamBonifTextilPK;
import java.util.List;

/**
 *
 * @author nivrist
 */
public interface ITerParamBonifTextilServices {
    
    public List<TerParamBoniTextilDTO> findAll();
    public void createParameter(TerParamBonifTextil param);
    public void updateParameter(TerParamBonifTextil param);
    public void deleteParamter(TerParamBonifTextil param);
    public int getNextId();
    public List<TerParamBonifTextil> allParametrosBoniTextil();
    public TerParamBonifTextil findByPk(TerParamBonifTextilPK pk);
    
}
