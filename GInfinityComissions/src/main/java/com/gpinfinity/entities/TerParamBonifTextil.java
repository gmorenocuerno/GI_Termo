/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nivrist
 */
@Entity
@Table(name = "TER_PARAM_BONIF_TEXTIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TerParamBonifTextil.findAll", query = "SELECT t FROM TerParamBonifTextil t")
    , @NamedQuery(name = "TerParamBonifTextil.findById", query = "SELECT t FROM TerParamBonifTextil t WHERE t.terParamBonifTextilPK.id = :id")
    , @NamedQuery(name = "TerParamBonifTextil.findByIdArenaNegocio", query = "SELECT t FROM TerParamBonifTextil t WHERE t.terParamBonifTextilPK.idArenaNegocio = :idArenaNegocio")
    , @NamedQuery(name = "TerParamBonifTextil.findByDescripcion", query = "SELECT t FROM TerParamBonifTextil t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TerParamBonifTextil.findByPeriodo", query = "SELECT t FROM TerParamBonifTextil t WHERE t.periodo = :periodo")
    , @NamedQuery(name = "TerParamBonifTextil.findByBono", query = "SELECT t FROM TerParamBonifTextil t WHERE t.bono = :bono")
    , @NamedQuery(name = "TerParamBonifTextil.findByTipo", query = "SELECT t FROM TerParamBonifTextil t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "TerParamBonifTextil.findByEstado", query = "SELECT t FROM TerParamBonifTextil t WHERE t.estado = :estado")
    , @NamedQuery(name = "TerParamBonifTextil.findByUsuarioCreacion", query = "SELECT t FROM TerParamBonifTextil t WHERE t.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "TerParamBonifTextil.findByFechaCreacion", query = "SELECT t FROM TerParamBonifTextil t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TerParamBonifTextil.findByUsuarioModificacion", query = "SELECT t FROM TerParamBonifTextil t WHERE t.usuarioModificacion = :usuarioModificacion")
    , @NamedQuery(name = "TerParamBonifTextil.findByFechaModificacion", query = "SELECT t FROM TerParamBonifTextil t WHERE t.fechaModificacion = :fechaModificacion")})
public class TerParamBonifTextil implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TerParamBonifTextilPK terParamBonifTextilPK;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "periodo")
    private Integer periodo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "bono")
    private BigDecimal bono;
    @Size(max = 50)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Size(max = 50)
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 50)
    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "id_arena_negocio", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TerAreaNegocio terAreaNegocio;

    public TerParamBonifTextil() {
    }

    public TerParamBonifTextil(TerParamBonifTextilPK terParamBonifTextilPK) {
        this.terParamBonifTextilPK = terParamBonifTextilPK;
    }

    public TerParamBonifTextil(int id, int idArenaNegocio) {
        this.terParamBonifTextilPK = new TerParamBonifTextilPK(id, idArenaNegocio);
    }

    public TerParamBonifTextilPK getTerParamBonifTextilPK() {
        return terParamBonifTextilPK;
    }

    public void setTerParamBonifTextilPK(TerParamBonifTextilPK terParamBonifTextilPK) {
        this.terParamBonifTextilPK = terParamBonifTextilPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public BigDecimal getBono() {
        return bono;
    }

    public void setBono(BigDecimal bono) {
        this.bono = bono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public TerAreaNegocio getTerAreaNegocio() {
        return terAreaNegocio;
    }

    public void setTerAreaNegocio(TerAreaNegocio terAreaNegocio) {
        this.terAreaNegocio = terAreaNegocio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (terParamBonifTextilPK != null ? terParamBonifTextilPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerParamBonifTextil)) {
            return false;
        }
        TerParamBonifTextil other = (TerParamBonifTextil) object;
        if ((this.terParamBonifTextilPK == null && other.terParamBonifTextilPK != null) || (this.terParamBonifTextilPK != null && !this.terParamBonifTextilPK.equals(other.terParamBonifTextilPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gpinfinity.entities.TerParamBonifTextil[ terParamBonifTextilPK=" + terParamBonifTextilPK + " ]";
    }
    
}
