/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.entities.TerIndicadorAreaNegocio;
import com.gpinfinity.entities.TerIndicadorAreaNegocioPK;
import com.gpinfinity.entities.TerParametroIndicador;
import com.gpinfinity.entities.TerParametroIndicadorPK;
import com.gpinfinity.DTO.TerParametrosDTO;
import java.util.List;

/**
 *
 * @author piru876
 */
public interface ITerParametroIndicadorServices {
    
    public List<TerParametroIndicador> findAll();
    public void createParameter(TerParametroIndicador param);
    public void updateParameter(TerParametroIndicador param);
    public void deleteParamter(TerParametroIndicadorPK param);
    public int getNextId();
    public List<TerParametrosDTO> allParametros();
    public TerParametroIndicador findByPk(TerParametroIndicadorPK pk);
    
    
    
}
