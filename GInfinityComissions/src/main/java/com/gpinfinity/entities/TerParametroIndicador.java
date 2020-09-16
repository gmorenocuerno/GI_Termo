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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author nivrist
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ter_parametro_indicador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TerParametroIndicador.findAll", query = "SELECT t FROM TerParametroIndicador t")
    , @NamedQuery(name = "TerParametroIndicador.findByIdAreaNegocio", query = "SELECT t FROM TerParametroIndicador t WHERE t.terParametroIndicadorPK.idAreaNegocio = :idAreaNegocio")
    , @NamedQuery(name = "TerParametroIndicador.findByIdIndicador", query = "SELECT t FROM TerParametroIndicador t WHERE t.terParametroIndicadorPK.idIndicador = :idIndicador")
    , @NamedQuery(name = "TerParametroIndicador.findById", query = "SELECT t FROM TerParametroIndicador t WHERE t.terParametroIndicadorPK.id = :id")
    , @NamedQuery(name = "TerParametroIndicador.findByDescripcion", query = "SELECT t FROM TerParametroIndicador t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TerParametroIndicador.findByRangoInicial", query = "SELECT t FROM TerParametroIndicador t WHERE t.rangoInicial = :rangoInicial")
    , @NamedQuery(name = "TerParametroIndicador.findByRangoFinal", query = "SELECT t FROM TerParametroIndicador t WHERE t.rangoFinal = :rangoFinal")
    , @NamedQuery(name = "TerParametroIndicador.findByPorcVariable", query = "SELECT t FROM TerParametroIndicador t WHERE t.porcVariable = :porcVariable")
    , @NamedQuery(name = "TerParametroIndicador.findByEstado", query = "SELECT t FROM TerParametroIndicador t WHERE t.estado = :estado")
    , @NamedQuery(name = "TerParametroIndicador.findByUsuarioCreacion", query = "SELECT t FROM TerParametroIndicador t WHERE t.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "TerParametroIndicador.findByFechaCreacion", query = "SELECT t FROM TerParametroIndicador t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TerParametroIndicador.findByUsuarioModificacion", query = "SELECT t FROM TerParametroIndicador t WHERE t.usuarioModificacion = :usuarioModificacion")
    , @NamedQuery(name = "TerParametroIndicador.findByFechaModificacion", query = "SELECT t FROM TerParametroIndicador t WHERE t.fechaModificacion = :fechaModificacion")})
public class TerParametroIndicador implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TerParametroIndicadorPK terParametroIndicadorPK;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rango_inicial")
    private BigDecimal rangoInicial;
    @Column(name = "rango_final")
    private BigDecimal rangoFinal;
    @Column(name = "porc_variable")
    private BigDecimal porcVariable;
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
    @JoinColumns({
        @JoinColumn(name = "id_area_negocio", referencedColumnName = "id_area_negocio", insertable = false, updatable = false)
        , @JoinColumn(name = "id_indicador", referencedColumnName = "id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TerIndicadorAreaNegocio terIndicadorAreaNegocio;

}
