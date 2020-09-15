/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.entities.Usuarios;
import com.gpinfinity.repository.IUsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author piru876
 */
@Service
public class UsuarioServicesImpl implements IUsuarioServices {

    BCryptPasswordEncoder pass = new BCryptPasswordEncoder(12);
    @Autowired
    IUsuarioRepository usuarioRepository;

    @Override
    public List<Usuarios> listAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuarios getUsuario(String userName) {
        Usuarios user = usuarioRepository.getOne(userName);
        if (user != null) {
            return user;
        }

        return null;

    }

    @Override
    public Usuarios createdUsuario(Usuarios per) {
        Usuarios userdb = getUsuarioVerificar(per.getUserName());
        if (userdb == null) {
            per.setPassword(pass.encode(per.getPassword()));
            return usuarioRepository.save(per);
        } else {
            if (!userdb.getPassword().equals(per.getPassword())) {
                per.setPassword(pass.encode(per.getPassword()));
            }
            return usuarioRepository.save(per);
        }

    }

    @Override
    public Usuarios updateUsuario(Usuarios per) {

        Usuarios persondb = usuarioRepository.getOne(per.getUserName());
        if (persondb == null) {
            return null;
        }

        return usuarioRepository.save(per);
    }

    @Override
    public Usuarios deleteUsuario(String userName) {
        Usuarios persondb = usuarioRepository.getOne(userName);
        if (persondb == null) {
            return null;
        }
        persondb.setStatus("I");
        return usuarioRepository.save(persondb);
    }

    @Override
    public Usuarios getUsuarioVerificar(String userName) {
        Usuarios user = usuarioRepository.verificarUsuario(userName);
        if (user != null) {
            return user;
        }

        return null;
    }

}
