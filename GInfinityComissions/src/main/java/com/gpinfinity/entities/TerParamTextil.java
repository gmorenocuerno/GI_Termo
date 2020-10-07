/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nivrist
 */
@Entity
@Table(name = "TER_PARAM_TEXTIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TerParamTextil.findAll", query = "SELECT t FROM TerParamTextil t")
    , @NamedQuery(name = "TerParamTextil.findById", query = "SELECT t FROM TerParamTextil t WHERE t.id = :id")
    , @NamedQuery(name = "TerParamTextil.findByDescripcion", query = "SELECT t FROM TerParamTextil t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TerParamTextil.findByLlave", query = "SELECT t FROM TerParamTextil t WHERE t.llave = :llave")
    , @NamedQuery(name = "TerParamTextil.findByValor", query = "SELECT t FROM TerParamTextil t WHERE t.valor = :valor")
    , @NamedQuery(name = "TerParamTextil.findByEstado", query = "SELECT t FROM TerParamTextil t WHERE t.estado = :estado")
    , @NamedQuery(name = "TerParamTextil.findByUsuarioCreacion", query = "SELECT t FROM TerParamTextil t WHERE t.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "TerParamTextil.findByFechaCreacion", query = "SELECT t FROM TerParamTextil t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TerParamTextil.findByUsuarioModificacion", query = "SELECT t FROM TerParamTextil t WHERE t.usuarioModificacion = :usuarioModificacion")
    , @NamedQuery(name = "TerParamTextil.findByFechaModificacion", query = "SELECT t FROM TerParamTextil t WHERE t.fechaModificacion = :fechaModificacion")})
public class TerParamTextil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "llave")
    private String llave;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
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

    public TerParamTextil() {
    }

    public TerParamTextil(Integer id) {
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

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerParamTextil)) {
            return false;
        }
        TerParamTextil other = (TerParamTextil) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gpinfinity.entities.TerParamTextil[ id=" + id + " ]";
    }
    
}
