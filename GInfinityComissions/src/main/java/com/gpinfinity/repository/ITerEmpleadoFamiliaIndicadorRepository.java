/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.repository;

import com.gpinfinity.entities.TerEmpleadoFamiliaIndicador;
import com.gpinfinity.entities.TerEmpleadoFamiliaIndicadorPK;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nivrist
 */
@Repository
public interface ITerEmpleadoFamiliaIndicadorRepository extends JpaRepository<TerEmpleadoFamiliaIndicador, TerEmpleadoFamiliaIndicadorPK> {

    @Query(nativeQuery = true, value = "SELECT cast(ROW_NUMBER() OVER (ORDER BY b.nombre) as varchar) row_num ,\n"
            + "c.descripcion as descareaNegocio ,\n"
            + "b.nombre nombreEmpleado ,\n"
            + "e.descripcion descIndicador,\n"
            + "d.descripcion descFamilia,\n"
            + "cast(isnull(a.periodo,0) as varchar) as periodo ,\n"
            + "cast(isnull(a.monto_meta,0) as varchar) as meta ,\n"
            + "cast(isnull(a.monto_real,0) as varchar) as monto_real ,\n"
            + "cast(isnull(a.porcentaje_cumplimiento,0) as varchar) as porcentaje_cumplimiento ,\n"
            + "cast(isnull(f.peso,0) as varchar) as peso ,\n"
            + "cast(isnull(a.porcentaje_variable,0) as varchar) as porcentaje_variable,\n"
            + "cast(isnull(a.ponderacion,0) as varchar) as ponderacion ,\n"
            + "cast(isnull(a.monto_aplicado,0) as varchar) as monto_aplicado,\n"
            + "cast(isnull(a.monto_calculado,0) as varchar) as monto_calculado , \n" 
            + "isnull(b.filial,'') fili\n"
            + "FROM ter_empleado_familia_indicador a\n"
            + "INNER JOIN ter_empleado b on b.id = a.id_empleado and b.id_area_negocio = a.id_area_negocio\n"
            + "INNER JOIN ter_area_negocio c on c.id = a.id_area_negocio\n"
            + "INNER JOIN ter_familia d on d.id = a.id_familia and d.id_area_negocio = a.id_area_negocio\n"
            + "INNER JOIN ter_indicador_area_negocio e ON e.id = a.id_indicador and e.id_area_negocio = a.id_area_negocio\n"
            + "INNER JOIN ter_empleado_familia f on f.id_area_negocio = a.id_area_negocio and f.id_familia = a.id_familia\n"
            + "and f.id_empleado = a.id_empleado and f.id_indicador = a.id_indicador where  f.id_area_negocio = ?  and a.periodo = ? ")
    public List<Object[]> allDataEmpFamIndicador(int idAreaNegocio , int periodo);
    
     @Query(nativeQuery = true, value = "SELECT \n" +
"             cast(isnull(a.periodo,0) as varchar) as periodo\n" +
"             , C.descripcion \n" +
"			 ,isnull(b.filial,'') fili\n" +
"			 ,cast(b.id_empleado as varchar) idempe \n" +
"             , B.nombre\n" +
"             , cast(B.sueldo as varchar) suel\n" +
"             , cast(ISNULL(SUM(A.monto_calculado),0) as varchar) as calculado\n" +
"             , cast(ISNULL((SUM(A.monto_calculado)/B.sueldo)*100,0) as varchar) as porc_calculado 			   \n" +
"             FROM TER_empleado_familia_indicador A\n" +
"             INNER JOIN TER_empleado B ON B.id = A.id_empleado\n" +
"             INNER JOIN TER_area_negocio C ON C.id = A.id_area_negocio \n" +
"			 where a.periodo between ? and  ? and upper(c.descripcion) not like '%TEXTIL%' \n" +
"             group by \n" +
"             A.periodo\n" +
"             , A.id_area_negocio\n" +
"             , C.descripcion\n" +
"			 , B.FILIAL\n" +
"			 , b.id_empleado\n" +
"             , B.nombre\n" +
"             , B.sueldo 			 \n" +
"             ORDER BY A.id_area_negocio")
    public List<Object[]> genReporteUn(int periodoInicial , int periodoFinal);
    
    
         @Query(nativeQuery = true, value = "select  \n" +
                                    "cast(isnull(A.periodo,0) as varchar) as periodo \n" +
                                    ", 'B2B-TX' as descripcion \n" +
                                    ",isnull(A.filial,'') fili \n" +
                                    ",cast(b.id_empleado as varchar) idempe  \n" +
                                    ", B.descripcion \n" +
                                    ", cast(B.valor as varchar) suel \n" +
                                    ", cast(ISNULL((A.monto_bonificacion_mensual),0) as varchar) as calculado_mensual\n" +
                                    ", cast(ISNULL((A.monto_bonificacion_acumulado),0) as varchar) as calculado_acumulado\n" +
                                    ", cast(ISNULL(((A.porc_cumplimiento_mensual)),0) as varchar) as porc_calculado_mensual\n" +
                                    ", cast(ISNULL(((A.porc_cumplimiento_acumulado)),0) as varchar) as porc_calculado_acumulado\n" +
                                    "from TER_COMISIONES_TOTALES_TEXTIL A\n" +
                                    "INNER JOIN TER_TIPO_BONO_TEXTIL B ON B.id = A.id_tipo_bono_textil AND B.periodo = A.periodo\n" +
                                    " where a.periodo between ? and  ? \n" +
                                    " order by a.periodo, b.id_empleado")
    public List<Object[]> genReporteUnTextil(int periodoInicial , int periodoFinal);

    
    

    @Procedure(procedureName = "TER_COMISIONES_Generar")
    public void calculaComisiones(@Param("AREA_NEGOCIO") int area_negocio, @Param("PERIODO") int periodo);

    @Query(nativeQuery = true, value = "SELECT distinct cast(periodo as varchar) periodo_tbl FROM ter_empleado_familia_indicador order by 1 asc")
    public List<String> allPeriodo();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(nativeQuery = true, value = "MERGE ter_empleado_familia_indicador b \n"
            + "    USING ter_empleado_familia a\n"
            + "ON (a.id_area_negocio=b.id_area_negocio\n"
            + "  and a.id_empleado=b.id_empleado\n"
            + "  and a.id_familia=b.id_familia\n"
            + "  and a.id_indicador=b.id_indicador)\n"
            + "  WHEN MATCHED\n"
            + "    THEN UPDATE SET b.peso =a.peso;")
    public void mergeTableTerEmpFamIndicador();

    @Query(nativeQuery = true, value = "SELECT \n"
            + "  cast(a.periodo as varchar) perio\n"
            + ", C.descripcion as 'Area de Negocio'\n"
            + ", B.nombre\n"
            + ", cast(B.sueldo as varchar) suel\n"
            + ", cast(ISNULL(SUM(A.monto_calculado),0) as varchar) as calculado\n"
            + ", cast(ISNULL((SUM(A.monto_calculado)/B.sueldo)*100,0) as varchar) as porc_calculado ,cast(b.id_empleado as varchar) idempe \n"
            + "FROM TER_empleado_familia_indicador A\n"
            + "INNER JOIN TER_empleado B ON B.id = A.id_empleado\n"
            + "INNER JOIN TER_area_negocio C ON C.id = A.id_area_negocio where  A.id_area_negocio=? and a.periodo=? \n"
            + "group by \n"
            + "A.periodo\n"
            + ", A.id_area_negocio\n"
            + ", C.descripcion\n"
            + ", B.nombre\n"
            + ", B.sueldo ,b.id_empleado \n"
            + "ORDER BY A.id_area_negocio\n")
    public List<Object[]> listAllEmpleadosCalc(int idAreaNegocio , int periodo);
    
    @Query(nativeQuery = true, value = "SELECT cast(A.id_area_negocio as varchar) idAreaNegocio, \n" +
"       B.descripcion as area_negocio, \n" +
"	   cast(A.id as varchar) as idEmpleado, \n" +
"	   cast(A.id_empleado as varchar) as cod_empleado, \n" +
"	   replace(A.nombre,',',' '), \n" +
"	   cast(C.id_indicador as varchar) as idIndicador, \n" +
"	   d.descripcion as indicador, \n" +
"	   cast(C.id_familia as varchar) as idFamilia, \n" +
"	   E.descripcion as familia, \n" +
"	   '' as periodo, \n" +
"	   '' as monto_meta, \n" +
"	   '' as monto_real\n" +
"FROM TER_EMPLEADO A\n" +
"INNER JOIN TER_AREA_NEGOCIO B ON B.ID = A.id_area_negocio AND B. estado = 'A'\n" +
"INNER JOIN TER_EMPLEADO_FAMILIA C ON C.id_empleado =a.id /*A.id_empleado*/ and C.id_area_negocio = A.id_area_negocio\n" +
"INNER JOIN TER_INDICADOR_AREA_NEGOCIO D ON D.id_area_negocio = A.id_area_negocio AND D.id = C.id_indicador AND D.estado = 'A'\n" +
"INNER JOIN TER_FAMILIA E ON E.id_area_negocio = A.id_area_negocio AND E.id = C.id_familia AND E.estado = 'A'\n" +
"where A.estado = 'A'\n" +
"order by A.id_area_negocio, A.id_empleado")
    public List<Object[]> listIndicadorCsvObject();
    
    

}
