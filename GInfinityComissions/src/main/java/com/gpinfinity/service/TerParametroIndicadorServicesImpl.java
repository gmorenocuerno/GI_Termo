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
import com.gpinfinity.repository.ITerParametroIndicadorRepository;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nivrist
 */

@Service
public class TerParametroIndicadorServicesImpl implements ITerParametroIndicadorServices{

    
    
    @Autowired
    ITerParametroIndicadorRepository iterParametroIndicadorRepository;
    
    @Override
    public List<TerParametroIndicador> findAll() {
        return iterParametroIndicadorRepository.findAll();
    }

    @Override
    public void createParameter(TerParametroIndicador param) {
        iterParametroIndicadorRepository.save(param);
    }

    @Override
    public void updateParameter(TerParametroIndicador param) {
        iterParametroIndicadorRepository.save(param);
    }

    @Override
    public void deleteParamter(TerParametroIndicadorPK param) {
       TerParametroIndicador parameterDelete =  iterParametroIndicadorRepository.findOne(param);
       parameterDelete.setEstado("I");
       iterParametroIndicadorRepository.save(parameterDelete);
       
       
    }

    @Override
    public int getNextId() {
        return iterParametroIndicadorRepository.nextId();
    }

    @Override
    public List<TerParametrosDTO> allParametros() {
        List<TerParametrosDTO> list =  new ArrayList<>();
        
        iterParametroIndicadorRepository.allParametroIndicador().forEach((obj) -> {
            TerParametrosDTO  obparam = TerParametrosDTO.builder()
                    .id(obj[0].toString())
                    .descripcion(obj[1].toString())
                    .idAreaNegocio(obj[2].toString())
                    .descripcionAreaNegocio(obj[3].toString())
                    .idIndicador(obj[4].toString()).
                    descripcionIndicador(obj[5].toString()).
                    estado(obj[6].toString()).build();
            list.add(obparam);
        });
        return list;
    }
    
    @Override
    public TerParametroIndicador findByPk(TerParametroIndicadorPK pk) {
       return iterParametroIndicadorRepository.findOne(pk);
    }
    
}
