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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
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
@Table(name = "TER_COMISIONES_TEXTIL")
@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TerComisionesTextil implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo")
    private Integer per;
       
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_mensual")
    private BigDecimal valorMensual;
    @Column(name = "valor_presupuesto_mensual")
    private BigDecimal valorPresupuestoMensual;
    @Column(name = "monto_presupuesto_acumulado")
    private BigDecimal montoPresupuestoAcumulado;
    @Column(name = "valor_real_mensual")
    private BigDecimal valorRealMensual;
    @Column(name = "valor_cxc_v30")
    private BigDecimal valorCxcV30;
    @Column(name = "valor_inventario_45")
    private BigDecimal valorInventario45;
    @Column(name = "monto_venta_neta_mensual")
    private BigDecimal montoVentaNetaMensual;
    @Column(name = "monto_venta_neta_acumulada")
    private BigDecimal montoVentaNetaAcumulada;
    @Column(name = "monto_diferencia_mensual")
    private BigDecimal montoDiferenciaMensual;
    @Column(name = "porc_cumplimiento_mensual")
    private BigDecimal porcCumplimientoMensual;
    @Column(name = "monto_bono_mensual")
    private BigDecimal montoBonoMensual;
    @Column(name = "monto_diferencia_acumulado")
    private BigDecimal montoDiferenciaAcumulado;
    @Column(name = "porc_cumplimiento_acumulado")
    private BigDecimal porcCumplimientoAcumulado;
    @Column(name = "monto_bono_acumulado")
    private BigDecimal montoBonoAcumulado;
    @Column(name = "monto_bono_real")
    private BigDecimal montoBonoReal;
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
    
}
