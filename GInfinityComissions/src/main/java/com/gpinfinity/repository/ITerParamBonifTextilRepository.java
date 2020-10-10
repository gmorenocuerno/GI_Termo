/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.repository;

import com.gpinfinity.entities.TerParamBonifTextil;
import com.gpinfinity.entities.TerParamBonifTextilPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nivrist
 */
@Repository
public interface ITerParamBonifTextilRepository extends JpaRepository<TerParamBonifTextil, TerParamBonifTextilPK>{
    
    @Query(nativeQuery = true, value = "SELECT isnull(max(id)+1,1)  FROM TER_PARAM_BONIF_TEXTIL")
    public int nextId();
    
    @Query(nativeQuery = true , value = "SELECT cast(a.id as varchar) idParam , b.descripcion descArea, a.descripcion , cast(a.bono as varchar) parambono , a.estado , cast(a.periodo as varchar) paramPeriodo , a.tipo , cast(b.id as varchar) idarea FROM TER_PARAM_BONIF_TEXTIL a , TER_AREA_NEGOCIO b\n" +
"where a.id_arena_negocio=b.id")
    public List<Object[]> allParamBoniTextil();
    
}
