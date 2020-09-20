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
            + "cast(isnull(a.monto_calculado,0) as varchar) as monto_calculado\n"
            + "FROM ter_empleado_familia_indicador a\n"
            + "INNER JOIN ter_empleado b on b.id = a.id_empleado and b.id_area_negocio = a.id_area_negocio\n"
            + "INNER JOIN ter_area_negocio c on c.id = a.id_area_negocio\n"
            + "INNER JOIN ter_familia d on d.id = a.id_familia and d.id_area_negocio = a.id_area_negocio\n"
            + "INNER JOIN ter_indicador_area_negocio e ON e.id = a.id_indicador and e.id_area_negocio = a.id_area_negocio\n"
            + "INNER JOIN ter_empleado_familia f on f.id_area_negocio = a.id_area_negocio and f.id_familia = a.id_familia\n"
            + "and f.id_empleado = a.id_empleado and f.id_indicador = a.id_indicador")
    public List<Object[]> allDataEmpFamIndicador();

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
            + ", cast(ISNULL((SUM(A.monto_calculado)/B.sueldo)*100,0) as varchar) as porc_calculado\n"
            + "FROM TER_empleado_familia_indicador A\n"
            + "INNER JOIN TER_empleado B ON B.id = A.id_empleado\n"
            + "INNER JOIN TER_area_negocio C ON C.id = A.id_area_negocio\n"
            + "group by \n"
            + "A.periodo\n"
            + ", A.id_area_negocio\n"
            + ", C.descripcion\n"
            + ", B.nombre\n"
            + ", B.sueldo\n"
            + "ORDER BY A.id_area_negocio\n")
    public List<Object[]> listAllEmpleadosCalc();

}
