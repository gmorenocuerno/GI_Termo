/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author piru876
 */
@Entity
@Table(name = "ter_empleado_familia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TerEmpleadoFamilia.findAll", query = "SELECT t FROM TerEmpleadoFamilia t")
    , @NamedQuery(name = "TerEmpleadoFamilia.findByIdAreaNegocio", query = "SELECT t FROM TerEmpleadoFamilia t WHERE t.terEmpleadoFamiliaPK.idAreaNegocio = :idAreaNegocio")
    , @NamedQuery(name = "TerEmpleadoFamilia.findByIdEmpleado", query = "SELECT t FROM TerEmpleadoFamilia t WHERE t.terEmpleadoFamiliaPK.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "TerEmpleadoFamilia.findByIdIndicador", query = "SELECT t FROM TerEmpleadoFamilia t WHERE t.terEmpleadoFamiliaPK.idIndicador = :idIndicador")
    , @NamedQuery(name = "TerEmpleadoFamilia.findByIdFamilia", query = "SELECT t FROM TerEmpleadoFamilia t WHERE t.terEmpleadoFamiliaPK.idFamilia = :idFamilia")
    , @NamedQuery(name = "TerEmpleadoFamilia.findByPeso", query = "SELECT t FROM TerEmpleadoFamilia t WHERE t.peso = :peso")})
public class TerEmpleadoFamilia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TerEmpleadoFamiliaPK terEmpleadoFamiliaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso")
    private BigDecimal peso;
    @JoinColumns({
        @JoinColumn(name = "id_area_negocio", referencedColumnName = "id_area_negocio", insertable = false, updatable = false)
        , @JoinColumn(name = "id_empleado", referencedColumnName = "id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TerEmpleado terEmpleado;

    public TerEmpleadoFamilia() {
    }

    public TerEmpleadoFamilia(TerEmpleadoFamiliaPK terEmpleadoFamiliaPK) {
        this.terEmpleadoFamiliaPK = terEmpleadoFamiliaPK;
    }

    public TerEmpleadoFamilia(int idAreaNegocio, int idEmpleado, int idIndicador, int idFamilia) {
        this.terEmpleadoFamiliaPK = new TerEmpleadoFamiliaPK(idAreaNegocio, idEmpleado, idIndicador, idFamilia);
    }

    public TerEmpleadoFamiliaPK getTerEmpleadoFamiliaPK() {
        return terEmpleadoFamiliaPK;
    }

    public void setTerEmpleadoFamiliaPK(TerEmpleadoFamiliaPK terEmpleadoFamiliaPK) {
        this.terEmpleadoFamiliaPK = terEmpleadoFamiliaPK;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public TerEmpleado getTerEmpleado() {
        return terEmpleado;
    }

    public void setTerEmpleado(TerEmpleado terEmpleado) {
        this.terEmpleado = terEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (terEmpleadoFamiliaPK != null ? terEmpleadoFamiliaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerEmpleadoFamilia)) {
            return false;
        }
        TerEmpleadoFamilia other = (TerEmpleadoFamilia) object;
        if ((this.terEmpleadoFamiliaPK == null && other.terEmpleadoFamiliaPK != null) || (this.terEmpleadoFamiliaPK != null && !this.terEmpleadoFamiliaPK.equals(other.terEmpleadoFamiliaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gpinfinity.entities.TerEmpleadoFamilia[ terEmpleadoFamiliaPK=" + terEmpleadoFamiliaPK + " ]";
    }
    
}
