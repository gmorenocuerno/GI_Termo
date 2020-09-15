/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.repository;

import com.gpinfinity.entities.TerParametroIndicador;
import com.gpinfinity.entities.TerParametroIndicadorPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author piru876
 */
@Repository
public interface ITerParametroIndicadorRepository  extends JpaRepository<TerParametroIndicador, TerParametroIndicadorPK>{
    
    @Query(nativeQuery = true, value = "SELECT isnull(max(id)+1,1)  FROM ter_parametro_indicador")
    public int nextId();
    
    
    @Query(nativeQuery = true, value = "SELECT cast(a.id as varchar) as id, a.descripcion as descparametro\n" +
", cast(a.id_area_negocio as varchar) as idareanegocio ,b.descripcion as descareanegocio\n" +
",cast(a.id_indicador as varchar) idindicador , c.descripcion as descindicador,a.estado   \n" +
"FROM ter_parametro_indicador a\n" +
"INNER JOIN ter_area_negocio b ON b.id = a.id_area_negocio\n" +
"INNER JOIN ter_indicador_area_negocio c  ON c.id_area_negocio = a.id_area_negocio and c.id = a.id_indicador")
    public List<Object[]> allParametroIndicador(); 
    
}
