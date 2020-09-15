/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author piru876
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TerEmpleadoFamiliaIndicadorPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_area_negocio")
    private int idAreaNegocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empleado")
    private int idEmpleado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_indicador")
    private int idIndicador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_familia")
    private int idFamilia;

    
}
