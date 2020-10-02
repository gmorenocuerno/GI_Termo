/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.DTO.CsvTextilErrorLoad;
import com.gpinfinity.DTO.TextilCsvLoad;
import com.gpinfinity.entities.TerComisionesTextil;
import com.gpinfinity.repository.ITerComisionesTextilRespository;
import com.gpinfinity.utils.UsrDetails;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

/**
 *
 * @author nivrist
 */

@Service
public class TerComisionesTextilServicesImpl implements ITerComisionesTextilServices{

    @Autowired
    ITerComisionesTextilRespository iTerComisionesTextilRespository;
    
    @Autowired
    UsrDetails usrDet;
    
    @Override
    public CsvTextilErrorLoad crearTerTextiles(TextilCsvLoad csvDto) {
        
        CsvTextilErrorLoad resp;
        //int idcol = iTerComisionesTextilRespository.getSeqtextiles();
        try {
            
            TerComisionesTextil terFamEmp = TerComisionesTextil.builder()
                    .estado("A")
                    .fechaCreacion(usrDet.getDate())
                    .usuarioCreacion(usrDet.get_userName())
                    .per(Integer.parseInt(csvDto.getPeriodo()))
                    .valorMensual(new BigDecimal(csvDto.getValorMensual()))
                    .valorPresupuestoMensual(new BigDecimal(csvDto.getValorPresupuestoMensual()))
                    .valorRealMensual(new BigDecimal(csvDto.getValorRealMensual()))
                    .valorCxcV30(new BigDecimal(csvDto.getValorCxc30()))
                    .valorInventario45(new BigDecimal(csvDto.getValorInventario45()))
                    .build();
            iTerComisionesTextilRespository.save(terFamEmp);
            resp = CsvTextilErrorLoad.builder()
                    .estadoLinea("C")
                    .mensaje("Linea cargada con exito")
                    .build();
            
        } catch (NumberFormatException | TransactionSystemException exc) {
            resp = CsvTextilErrorLoad.builder()
                    .estadoLinea("E")
                    .mensaje("Error al intentar realizar la carga")
                    .build();
            System.out.println("Causa de error:" + exc.getCause());
            System.out.println("Message de error:" + exc.getMessage());
            exc.printStackTrace();
        }
        return resp;
    }

    @Override
    public List<String> allPeriodo() {
        List<String> listPeriodo =  new ArrayList<>();
        iTerComisionesTextilRespository.allPeriodo().forEach((obj)->{        
            listPeriodo.add(obj);
        });        
        return listPeriodo;
    }

    @Override
    public int ejecutarProcedimientoTextil(int periodo) {
        int flag =0;
        try {
             flag =0;
            iTerComisionesTextilRespository.calculaComisiones(periodo);
            
        } catch (Exception ex) {
            flag=1;
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<TerComisionesTextil> allTerComisionesTextiles(int periodo) {        
        List<TerComisionesTextil> lista  =  iTerComisionesTextilRespository.allTerComisionesTextil(periodo);
       return lista;
    }
    
}
