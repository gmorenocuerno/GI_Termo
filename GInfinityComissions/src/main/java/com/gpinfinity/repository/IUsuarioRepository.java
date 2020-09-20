/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.repository;

import com.gpinfinity.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nivrist
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuarios, String> {
     @Query(nativeQuery = true ,value = "select *  from ter_usuarios where user_name = ? and status ='A' ")
     Usuarios verificarUsuario(String userName);
    
}
