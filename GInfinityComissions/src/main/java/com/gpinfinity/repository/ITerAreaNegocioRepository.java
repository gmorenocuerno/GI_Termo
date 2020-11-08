/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.repository;

import com.gpinfinity.entities.TerAreaNegocio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nivrist
 */
@Repository
public interface ITerAreaNegocioRepository extends JpaRepository<TerAreaNegocio, Integer>{
    
    @Query(nativeQuery = true, value = "select  *  from  ter_area_negocio where estado ='A' and upper(descripcion) not like '%TEXTIL%' ")
    public List<TerAreaNegocio> findEstado();
    
    
    @Query(nativeQuery = true, value = "select  *  from  ter_area_negocio where estado ='A'")
    public List<TerAreaNegocio> findEstadoAll();
    
}
