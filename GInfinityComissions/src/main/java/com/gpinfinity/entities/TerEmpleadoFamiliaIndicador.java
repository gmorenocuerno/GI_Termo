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
@Entity
@Table(name = "ter_empleado_familia_indicador")
@XmlRootElement
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TerEmpleadoFamiliaIndicador implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TerEmpleadoFamiliaIndicadorPK terEmpleadoFamiliaIndicadorPK;
    @Column(name = "periodo")
    private Integer periodo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto_meta")
    private BigDecimal meta;
    @Column(name = "monto_real")
    private BigDecimal montoReal;
    @Column(name = "porcentaje_cumplimiento")
    private BigDecimal porcentajeCumplimiento;
    @Column(name = "peso")
    private BigDecimal peso;
    @Column(name = "porcentaje_variable")
    private BigDecimal porcentajeVariable;
    @Column(name = "ponderacion")
    private BigDecimal ponderacion;
    @Column(name = "monto_calculado")
    private BigDecimal montoCalculado;
    @Column(name = "monto_aplicado")
    private BigDecimal montoAplicado;
    @Size(max = 25)
    @Column(name = "filial")
    private String filial;
    @Column(name = "tasa_conversion")
    private BigDecimal tasaConversion;
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
