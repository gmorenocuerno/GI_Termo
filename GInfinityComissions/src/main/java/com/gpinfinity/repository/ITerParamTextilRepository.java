/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.repository;

import com.gpinfinity.entities.TerParamTextil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nivrist
 */
@Repository
public interface ITerParamTextilRepository extends JpaRepository<TerParamTextil, Integer>{
    
    @Query(nativeQuery = true, value = "SELECT isnull(max(id)+1,1)  FROM TER_PARAM_TEXTIL")
    public int nextId();
    
}
