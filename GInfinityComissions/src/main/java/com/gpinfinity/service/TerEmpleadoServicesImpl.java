/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.entities.TerEmpleado;
import com.gpinfinity.entities.TerEmpleadoPK;
import com.gpinfinity.entities.TerParamBonifTextil;
import com.gpinfinity.repository.ITerEmpleadoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nivrist
 */

@Service
public class TerEmpleadoServicesImpl implements  ITerEmpleadoServices{

    @Autowired
    private ITerEmpleadoRepository iTerEmpleadoRepository;
    
    @Override
    public List<TerEmpleado> findAll() {
        return iTerEmpleadoRepository.findAllOrderEmpleado();
    }

    @Override
    public void createEmpleado(TerEmpleado param) {
       TerEmpleado objdb = iTerEmpleadoRepository.findOne(param.getTerEmpleadoPK());
        if (objdb != null) {
            param.setFechaCreacion(objdb.getFechaCreacion());
            param.setUsuarioCreacion(objdb.getUsuarioCreacion());
        }
        iTerEmpleadoRepository.save(param);
    }

    @Override
    public void updateEmpleado(TerEmpleado param) {
        iTerEmpleadoRepository.save(param);
    }

    @Override
    public void deleteEmpleado(TerEmpleado param) {
        iTerEmpleadoRepository.save(param);
    }

    @Override
    public int getNextId() {
        return iTerEmpleadoRepository.nextId();
    }

    @Override
    public TerEmpleado findByPk(TerEmpleadoPK pk) {
        return iTerEmpleadoRepository.findOne(pk);
    }
    
}
