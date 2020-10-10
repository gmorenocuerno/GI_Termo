/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.DTO.TerParamBoniTextilDTO;
import com.gpinfinity.entities.TerParamBonifTextil;
import com.gpinfinity.entities.TerParamBonifTextilPK;
import com.gpinfinity.repository.ITerParamBonifTextilRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nivrist
 */
@Service
public class TerParamBonifTextilServicesImpl implements ITerParamBonifTextilServices {

    @Autowired
    private ITerParamBonifTextilRepository iTerParamBonifTextilRepository;

    @Override
    public List<TerParamBoniTextilDTO> findAll() {
        List<TerParamBoniTextilDTO> dto = new ArrayList<>();
        iTerParamBonifTextilRepository.allParamBoniTextil().forEach(obj -> {
            TerParamBoniTextilDTO data = TerParamBoniTextilDTO.builder()
                    .id(obj[0].toString())
                    .descripcionAreaNegocio(obj[1].toString())
                    .descripcion(obj[2].toString())
                    .bono(obj[3].toString())
                    .estado(obj[4].toString())
                    .periodo(obj[5].toString())
                    .tipo(obj[6].toString())
                    .idAreaNegocio(obj[7].toString()).build();
            dto.add(data);
        });
        return dto;
    }

    @Override
    public void createParameter(TerParamBonifTextil param) {
        TerParamBonifTextil objdb = iTerParamBonifTextilRepository.findOne(param.getTerParamBonifTextilPK());
        if (objdb != null) {
            param.setFechaCreacion(objdb.getFechaCreacion());
            param.setUsuarioCreacion(objdb.getUsuarioCreacion());
        }
        iTerParamBonifTextilRepository.save(param);
    }

    @Override
    public void updateParameter(TerParamBonifTextil param) {
        iTerParamBonifTextilRepository.save(param);
    }

    @Override
    public void deleteParamter(TerParamBonifTextil param) {
        iTerParamBonifTextilRepository.save(param);
    }

    @Override
    public int getNextId() {
        return iTerParamBonifTextilRepository.nextId();
    }

    @Override
    public List<TerParamBonifTextil> allParametrosBoniTextil() {
        return iTerParamBonifTextilRepository.findAll();
    }

    @Override
    public TerParamBonifTextil findByPk(TerParamBonifTextilPK pk) {
        return iTerParamBonifTextilRepository.findOne(pk);
    }

}
