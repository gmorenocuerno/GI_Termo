/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.entities;

import java.io.Serializable;
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
@Table(name = "ter_familia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TerFamilia.findAll", query = "SELECT t FROM TerFamilia t")
    , @NamedQuery(name = "TerFamilia.findByIdAreaNegocio", query = "SELECT t FROM TerFamilia t WHERE t.terFamiliaPK.idAreaNegocio = :idAreaNegocio")
    , @NamedQuery(name = "TerFamilia.findById", query = "SELECT t FROM TerFamilia t WHERE t.terFamiliaPK.id = :id")
    , @NamedQuery(name = "TerFamilia.findByDescripcion", query = "SELECT t FROM TerFamilia t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TerFamilia.findByEstado", query = "SELECT t FROM TerFamilia t WHERE t.estado = :estado")
    , @NamedQuery(name = "TerFamilia.findByUsuarioCreacion", query = "SELECT t FROM TerFamilia t WHERE t.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "TerFamilia.findByFechaCreacion", query = "SELECT t FROM TerFamilia t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TerFamilia.findByUsuarioModificacion", query = "SELECT t FROM TerFamilia t WHERE t.usuarioModificacion = :usuarioModificacion")
    , @NamedQuery(name = "TerFamilia.findByFechaModificacion", query = "SELECT t FROM TerFamilia t WHERE t.fechaModificacion = :fechaModificacion")})
public class TerFamilia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TerFamiliaPK terFamiliaPK;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
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
    @JoinColumn(name = "id_area_negocio", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TerAreaNegocio terAreaNegocio;

    public TerFamilia() {
    }

    public TerFamilia(TerFamiliaPK terFamiliaPK) {
        this.terFamiliaPK = terFamiliaPK;
    }

    public TerFamilia(int idAreaNegocio, int id) {
        this.terFamiliaPK = new TerFamiliaPK(idAreaNegocio, id);
    }

    public TerFamiliaPK getTerFamiliaPK() {
        return terFamiliaPK;
    }

    public void setTerFamiliaPK(TerFamiliaPK terFamiliaPK) {
        this.terFamiliaPK = terFamiliaPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (terFamiliaPK != null ? terFamiliaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerFamilia)) {
            return false;
        }
        TerFamilia other = (TerFamilia) object;
        if ((this.terFamiliaPK == null && other.terFamiliaPK != null) || (this.terFamiliaPK != null && !this.terFamiliaPK.equals(other.terFamiliaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gpinfinity.entities.TerFamilia[ terFamiliaPK=" + terFamiliaPK + " ]";
    }
    
}
