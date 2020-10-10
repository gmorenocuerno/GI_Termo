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
 * @author nivrist
 */
@Embeddable
public class TerParamBonifTextilPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_arena_negocio")
    private int idArenaNegocio;

    public TerParamBonifTextilPK() {
    }

    public TerParamBonifTextilPK(int id, int idArenaNegocio) {
        this.id = id;
        this.idArenaNegocio = idArenaNegocio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdArenaNegocio() {
        return idArenaNegocio;
    }

    public void setIdArenaNegocio(int idArenaNegocio) {
        this.idArenaNegocio = idArenaNegocio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idArenaNegocio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerParamBonifTextilPK)) {
            return false;
        }
        TerParamBonifTextilPK other = (TerParamBonifTextilPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idArenaNegocio != other.idArenaNegocio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gpinfinity.entities.TerParamBonifTextilPK[ id=" + id + ", idArenaNegocio=" + idArenaNegocio + " ]";
    }
    
}
