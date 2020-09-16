/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.entities.Usuarios;
import java.util.List;

/**
 *
 * @author nivrist
 */
public interface IUsuarioServices {
    
    public List<Usuarios> listAllUsuarios();
    public Usuarios getUsuario(String userName);    
    public Usuarios getUsuarioVerificar(String userName);
    public Usuarios createdUsuario(Usuarios per);
    public Usuarios updateUsuario(Usuarios per);
    public Usuarios deleteUsuario(String userName);
    
}
