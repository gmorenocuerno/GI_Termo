/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.repository;

import com.gpinfinity.entities.TerIndicadorAreaNegocio;
import com.gpinfinity.entities.TerIndicadorAreaNegocioPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nivrist
 */
@Repository
public interface ITerIndicadorAreaNegocioRepository extends JpaRepository<TerIndicadorAreaNegocio, TerIndicadorAreaNegocioPK>{
    
    
    @Query(nativeQuery = true , value = "SELECT *  FROM ter_indicador_area_negocio where id_area_negocio=?")
    public List<TerIndicadorAreaNegocio>  findByIdAreaNegocio(int idAreaNegocio);
    
}
