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
public class TerFamiliaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_area_negocio")
    private int idAreaNegocio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;

    public TerFamiliaPK() {
    }

    public TerFamiliaPK(int idAreaNegocio, int id) {
        this.idAreaNegocio = idAreaNegocio;
        this.id = id;
    }

    public int getIdAreaNegocio() {
        return idAreaNegocio;
    }

    public void setIdAreaNegocio(int idAreaNegocio) {
        this.idAreaNegocio = idAreaNegocio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAreaNegocio;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerFamiliaPK)) {
            return false;
        }
        TerFamiliaPK other = (TerFamiliaPK) object;
        if (this.idAreaNegocio != other.idAreaNegocio) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gpinfinity.entities.TerFamiliaPK[ idAreaNegocio=" + idAreaNegocio + ", id=" + id + " ]";
    }
    
}
