/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.entities.TerEmpleado;
import com.gpinfinity.entities.TerEmpleadoPK;
import java.util.List;

/**
 *
 * @author nivrist
 */
public interface ITerEmpleadoServices {
    public List<TerEmpleado> findAll();
    public void createEmpleado(TerEmpleado param);
    public void updateEmpleado(TerEmpleado param);
    public void deleteEmpleado(TerEmpleado param);
    public int getNextId();
    public TerEmpleado findByPk(TerEmpleadoPK pk);
    
}
