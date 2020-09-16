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
 * @author nivrist
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TerParametroIndicadorPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_area_negocio")
    private int idAreaNegocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_indicador")
    private int idIndicador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;

}
