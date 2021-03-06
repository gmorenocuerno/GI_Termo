/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nivrist
 */
@Entity
@Table(name = "ter_indicador_area_negocio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TerIndicadorAreaNegocio.findAll", query = "SELECT t FROM TerIndicadorAreaNegocio t")
    , @NamedQuery(name = "TerIndicadorAreaNegocio.findByIdAreaNegocio", query = "SELECT t FROM TerIndicadorAreaNegocio t WHERE t.terIndicadorAreaNegocioPK.idAreaNegocio = :idAreaNegocio")
    , @NamedQuery(name = "TerIndicadorAreaNegocio.findById", query = "SELECT t FROM TerIndicadorAreaNegocio t WHERE t.terIndicadorAreaNegocioPK.id = :id")
    , @NamedQuery(name = "TerIndicadorAreaNegocio.findByDescripcion", query = "SELECT t FROM TerIndicadorAreaNegocio t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TerIndicadorAreaNegocio.findByEstado", query = "SELECT t FROM TerIndicadorAreaNegocio t WHERE t.estado = :estado")
    , @NamedQuery(name = "TerIndicadorAreaNegocio.findByUsuarioCreacion", query = "SELECT t FROM TerIndicadorAreaNegocio t WHERE t.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "TerIndicadorAreaNegocio.findByFechaCreacion", query = "SELECT t FROM TerIndicadorAreaNegocio t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TerIndicadorAreaNegocio.findByUsuarioModificacion", query = "SELECT t FROM TerIndicadorAreaNegocio t WHERE t.usuarioModificacion = :usuarioModificacion")
    , @NamedQuery(name = "TerIndicadorAreaNegocio.findByFechaModificacion", query = "SELECT t FROM TerIndicadorAreaNegocio t WHERE t.fechaModificacion = :fechaModificacion")})
public class TerIndicadorAreaNegocio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TerIndicadorAreaNegocioPK terIndicadorAreaNegocioPK;
    @Size(max = 150)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terIndicadorAreaNegocio")
    private Collection<TerEmpleadoFamiliaIndicador> terEmpleadoFamiliaIndicadorCollection;
    @JoinColumn(name = "id_area_negocio", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TerAreaNegocio terAreaNegocio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terIndicadorAreaNegocio")
    private Collection<TerParametroIndicador> terParametroIndicadorCollection;

    public TerIndicadorAreaNegocio() {
    }

    public TerIndicadorAreaNegocio(TerIndicadorAreaNegocioPK terIndicadorAreaNegocioPK) {
        this.terIndicadorAreaNegocioPK = terIndicadorAreaNegocioPK;
    }

    public TerIndicadorAreaNegocio(int idAreaNegocio, int id) {
        this.terIndicadorAreaNegocioPK = new TerIndicadorAreaNegocioPK(idAreaNegocio, id);
    }

    public TerIndicadorAreaNegocioPK getTerIndicadorAreaNegocioPK() {
        return terIndicadorAreaNegocioPK;
    }

    public void setTerIndicadorAreaNegocioPK(TerIndicadorAreaNegocioPK terIndicadorAreaNegocioPK) {
        this.terIndicadorAreaNegocioPK = terIndicadorAreaNegocioPK;
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
    public Collection<TerEmpleadoFamiliaIndicador> getTerEmpleadoFamiliaIndicadorCollection() {
        return terEmpleadoFamiliaIndicadorCollection;
    }

    public void setTerEmpleadoFamiliaIndicadorCollection(Collection<TerEmpleadoFamiliaIndicador> terEmpleadoFamiliaIndicadorCollection) {
        this.terEmpleadoFamiliaIndicadorCollection = terEmpleadoFamiliaIndicadorCollection;
    }

    public TerAreaNegocio getTerAreaNegocio() {
        return terAreaNegocio;
    }

    public void setTerAreaNegocio(TerAreaNegocio terAreaNegocio) {
        this.terAreaNegocio = terAreaNegocio;
    }

    @XmlTransient
    public Collection<TerParametroIndicador> getTerParametroIndicadorCollection() {
        return terParametroIndicadorCollection;
    }

    public void setTerParametroIndicadorCollection(Collection<TerParametroIndicador> terParametroIndicadorCollection) {
        this.terParametroIndicadorCollection = terParametroIndicadorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (terIndicadorAreaNegocioPK != null ? terIndicadorAreaNegocioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerIndicadorAreaNegocio)) {
            return false;
        }
        TerIndicadorAreaNegocio other = (TerIndicadorAreaNegocio) object;
        if ((this.terIndicadorAreaNegocioPK == null && other.terIndicadorAreaNegocioPK != null) || (this.terIndicadorAreaNegocioPK != null && !this.terIndicadorAreaNegocioPK.equals(other.terIndicadorAreaNegocioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gpinfinity.entities.TerIndicadorAreaNegocio[ terIndicadorAreaNegocioPK=" + terIndicadorAreaNegocioPK + " ]";
    }
    
}
