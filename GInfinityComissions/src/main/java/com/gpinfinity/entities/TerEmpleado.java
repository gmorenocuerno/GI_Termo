/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ter_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TerEmpleado.findAll", query = "SELECT t FROM TerEmpleado t")
    , @NamedQuery(name = "TerEmpleado.findByIdAreaNegocio", query = "SELECT t FROM TerEmpleado t WHERE t.terEmpleadoPK.idAreaNegocio = :idAreaNegocio")
    , @NamedQuery(name = "TerEmpleado.findById", query = "SELECT t FROM TerEmpleado t WHERE t.terEmpleadoPK.id = :id")
    , @NamedQuery(name = "TerEmpleado.findByNombre", query = "SELECT t FROM TerEmpleado t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TerEmpleado.findBySueldo", query = "SELECT t FROM TerEmpleado t WHERE t.sueldo = :sueldo")
    , @NamedQuery(name = "TerEmpleado.findByEstado", query = "SELECT t FROM TerEmpleado t WHERE t.estado = :estado")
    , @NamedQuery(name = "TerEmpleado.findByUsuarioCreacion", query = "SELECT t FROM TerEmpleado t WHERE t.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "TerEmpleado.findByFechaCreacion", query = "SELECT t FROM TerEmpleado t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TerEmpleado.findByUsuarioModificacion", query = "SELECT t FROM TerEmpleado t WHERE t.usuarioModificacion = :usuarioModificacion")
    , @NamedQuery(name = "TerEmpleado.findByFechaModificacion", query = "SELECT t FROM TerEmpleado t WHERE t.fechaModificacion = :fechaModificacion")})
public class TerEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TerEmpleadoPK terEmpleadoPK;
    @Size(max = 150)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sueldo")
    private BigDecimal sueldo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terEmpleado")
    private Collection<TerEmpleadoFamilia> terEmpleadoFamiliaCollection;
    @JoinColumn(name = "id_area_negocio", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TerAreaNegocio terAreaNegocio;

    public TerEmpleado() {
    }

    public TerEmpleado(TerEmpleadoPK terEmpleadoPK) {
        this.terEmpleadoPK = terEmpleadoPK;
    }

    public TerEmpleado(int idAreaNegocio, int id) {
        this.terEmpleadoPK = new TerEmpleadoPK(idAreaNegocio, id);
    }

    public TerEmpleadoPK getTerEmpleadoPK() {
        return terEmpleadoPK;
    }

    public void setTerEmpleadoPK(TerEmpleadoPK terEmpleadoPK) {
        this.terEmpleadoPK = terEmpleadoPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
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
    public Collection<TerEmpleadoFamilia> getTerEmpleadoFamiliaCollection() {
        return terEmpleadoFamiliaCollection;
    }

    public void setTerEmpleadoFamiliaCollection(Collection<TerEmpleadoFamilia> terEmpleadoFamiliaCollection) {
        this.terEmpleadoFamiliaCollection = terEmpleadoFamiliaCollection;
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
        hash += (terEmpleadoPK != null ? terEmpleadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerEmpleado)) {
            return false;
        }
        TerEmpleado other = (TerEmpleado) object;
        if ((this.terEmpleadoPK == null && other.terEmpleadoPK != null) || (this.terEmpleadoPK != null && !this.terEmpleadoPK.equals(other.terEmpleadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gpinfinity.entities.TerEmpleado[ terEmpleadoPK=" + terEmpleadoPK + " ]";
    }
    
}
