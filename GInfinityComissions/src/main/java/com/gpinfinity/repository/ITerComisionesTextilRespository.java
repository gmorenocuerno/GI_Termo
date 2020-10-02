/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.repository;

import com.gpinfinity.entities.TerComisionesTextil;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nivrist
 */
@Repository
public interface ITerComisionesTextilRespository extends JpaRepository<TerComisionesTextil, Integer>{
    @Procedure(procedureName = "TER_COMISIONES_TEXTIL_Generar")
    public void calculaComisiones(@Param("PERIODO") int periodo);
    
    
    @Query(nativeQuery = true, value = "SELECT distinct cast(periodo as varchar) periodo_tbl FROM TER_COMISIONES_TEXTIL order by 1 asc")
    public List<String> allPeriodo();
    
    
    @Query(nativeQuery = true, value = "SELECT * FROM TER_COMISIONES_TEXTIL where periodo =?")
    public List<TerComisionesTextil> allTerComisionesTextil(int periodo);
    
    
    
}
