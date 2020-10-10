/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nivrist
 */
@Entity
@Table(name = "ter_area_negocio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TerAreaNegocio.findAll", query = "SELECT t FROM TerAreaNegocio t")
    , @NamedQuery(name = "TerAreaNegocio.findById", query = "SELECT t FROM TerAreaNegocio t WHERE t.id = :id")
    , @NamedQuery(name = "TerAreaNegocio.findByDescripcion", query = "SELECT t FROM TerAreaNegocio t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TerAreaNegocio.findByEstado", query = "SELECT t FROM TerAreaNegocio t WHERE t.estado = :estado")
    , @NamedQuery(name = "TerAreaNegocio.findByUsuarioCreacion", query = "SELECT t FROM TerAreaNegocio t WHERE t.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "TerAreaNegocio.findByFechaCreacion", query = "SELECT t FROM TerAreaNegocio t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TerAreaNegocio.findByUsuarioModificacion", query = "SELECT t FROM TerAreaNegocio t WHERE t.usuarioModificacion = :usuarioModificacion")
    , @NamedQuery(name = "TerAreaNegocio.findByFechaModificacion", query = "SELECT t FROM TerAreaNegocio t WHERE t.fechaModificacion = :fechaModificacion")})
public class TerAreaNegocio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terAreaNegocio")
    private Collection<TerEmpleado> terEmpleadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terAreaNegocio")
    private Collection<TerFamilia> terFamiliaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terAreaNegocio")
    private Collection<TerIndicadorAreaNegocio> terIndicadorAreaNegocioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terAreaNegocio")
    private Collection<TerParamBonifTextil> terParamBonifTextilCollection;

    public TerAreaNegocio() {
    }

    public TerAreaNegocio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @XmlTransient
    public Collection<TerEmpleado> getTerEmpleadoCollection() {
        return terEmpleadoCollection;
    }

    public void setTerEmpleadoCollection(Collection<TerEmpleado> terEmpleadoCollection) {
        this.terEmpleadoCollection = terEmpleadoCollection;
    }

    @XmlTransient
    public Collection<TerFamilia> getTerFamiliaCollection() {
        return terFamiliaCollection;
    }

    public void setTerFamiliaCollection(Collection<TerFamilia> terFamiliaCollection) {
        this.terFamiliaCollection = terFamiliaCollection;
    }

    @XmlTransient
    public Collection<TerIndicadorAreaNegocio> getTerIndicadorAreaNegocioCollection() {
        return terIndicadorAreaNegocioCollection;
    }

    public void setTerIndicadorAreaNegocioCollection(Collection<TerIndicadorAreaNegocio> terIndicadorAreaNegocioCollection) {
        this.terIndicadorAreaNegocioCollection = terIndicadorAreaNegocioCollection;
    }

    @XmlTransient
    public Collection<TerParamBonifTextil> getTerParamBonifTextilCollection() {
        return terParamBonifTextilCollection;
    }

    public void setTerParamBonifTextilCollection(Collection<TerParamBonifTextil> terParamBonifTextilCollection) {
        this.terParamBonifTextilCollection = terParamBonifTextilCollection;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerAreaNegocio)) {
            return false;
        }
        TerAreaNegocio other = (TerAreaNegocio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gpinfinity.entities.TerAreaNegocio[ id=" + id + " ]";
    }
    
}
