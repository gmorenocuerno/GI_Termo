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

/**
 *
 * @author piru876
 */
@Embeddable
public class TerEmpleadoFamiliaPK implements Serializable {

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

    public TerEmpleadoFamiliaPK() {
    }

    public TerEmpleadoFamiliaPK(int idAreaNegocio, int idEmpleado, int idIndicador, int idFamilia) {
        this.idAreaNegocio = idAreaNegocio;
        this.idEmpleado = idEmpleado;
        this.idIndicador = idIndicador;
        this.idFamilia = idFamilia;
    }

    public int getIdAreaNegocio() {
        return idAreaNegocio;
    }

    public void setIdAreaNegocio(int idAreaNegocio) {
        this.idAreaNegocio = idAreaNegocio;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(int idIndicador) {
        this.idIndicador = idIndicador;
    }

    public int getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(int idFamilia) {
        this.idFamilia = idFamilia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAreaNegocio;
        hash += (int) idEmpleado;
        hash += (int) idIndicador;
        hash += (int) idFamilia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerEmpleadoFamiliaPK)) {
            return false;
        }
        TerEmpleadoFamiliaPK other = (TerEmpleadoFamiliaPK) object;
        if (this.idAreaNegocio != other.idAreaNegocio) {
            return false;
        }
        if (this.idEmpleado != other.idEmpleado) {
            return false;
        }
        if (this.idIndicador != other.idIndicador) {
            return false;
        }
        if (this.idFamilia != other.idFamilia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gpinfinity.entities.TerEmpleadoFamiliaPK[ idAreaNegocio=" + idAreaNegocio + ", idEmpleado=" + idEmpleado + ", idIndicador=" + idIndicador + ", idFamilia=" + idFamilia + " ]";
    }
    
}
