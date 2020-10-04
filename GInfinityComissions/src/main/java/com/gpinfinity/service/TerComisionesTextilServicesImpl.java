/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.service;

import com.gpinfinity.DTO.CsvTextilErrorLoad;
import com.gpinfinity.DTO.TextilAnualDTO;
import com.gpinfinity.DTO.TextilCsvLoad;
import com.gpinfinity.DTO.TextilMensualDTO;
import com.gpinfinity.DTO.TextilTotalDTO;
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

    @Override
    public List<TextilTotalDTO> textilesTotalList() {
        List<TextilTotalDTO> textilDto =  new ArrayList<>();
        iTerComisionesTextilRespository.textilesTotal().forEach(obj->{
        
        TextilTotalDTO dto = TextilTotalDTO.builder()
                .tipoBono((obj[0]!=null?obj[0].toString():""))
                .bono((obj[1]!=null?obj[1].toString():"0"))
                .jan((obj[2]!=null?obj[2].toString():"0"))
                .feb((obj[3]!=null?obj[3].toString():"0"))
                .mar((obj[4]!=null?obj[4].toString():"0"))
                .apr((obj[5]!=null?obj[5].toString():"0"))
                .may((obj[6]!=null?obj[6].toString():"0"))
                .jun((obj[7]!=null?obj[7].toString():"0"))
                .jul((obj[8]!=null?obj[8].toString():"0"))
                .aug((obj[9]!=null?obj[9].toString():"0"))
                .sep((obj[10]!=null?obj[10].toString():"0"))
                .oct((obj[11]!=null?obj[11].toString():"0"))
                .nov((obj[12]!=null?obj[12].toString():"0"))
                .dec((obj[13]!=null?obj[13].toString():"0"))
                .build();
                textilDto.add(dto);
        
        });
        
        return textilDto;
        
    }

    @Override
    public List<TextilMensualDTO> textilesMensualList() {
        List<TextilMensualDTO> textilDto =  new ArrayList<>();
        iTerComisionesTextilRespository.textilesMensual().forEach(obj->{
        
        TextilMensualDTO dto = TextilMensualDTO.builder()
                .tipoBono((obj[0]!=null?obj[0].toString():""))
                .bono((obj[1]!=null?obj[1].toString():"0"))
                .jan((obj[2]!=null?obj[2].toString():"0"))
                .feb((obj[3]!=null?obj[3].toString():"0"))
                .mar((obj[4]!=null?obj[4].toString():"0"))
                .apr((obj[5]!=null?obj[5].toString():"0"))
                .may((obj[6]!=null?obj[6].toString():"0"))
                .jun((obj[7]!=null?obj[7].toString():"0"))
                .jul((obj[8]!=null?obj[8].toString():"0"))
                .aug((obj[9]!=null?obj[9].toString():"0"))
                .sep((obj[10]!=null?obj[10].toString():"0"))
                .oct((obj[11]!=null?obj[11].toString():"0"))
                .nov((obj[12]!=null?obj[12].toString():"0"))
                .dec((obj[13]!=null?obj[13].toString():"0"))
                .build();
                textilDto.add(dto);
        
        });
        
        return textilDto;
    }

    @Override
    public List<TextilAnualDTO> textilesAcumuladoList() {
        List<TextilAnualDTO> textilDto =  new ArrayList<>();
        iTerComisionesTextilRespository.textilesAcumulado().forEach(obj->{
        
        TextilAnualDTO dto = TextilAnualDTO.builder()
                .tipoBono((obj[0]!=null?obj[0].toString():""))
                .bono((obj[1]!=null?obj[1].toString():"0"))
                .jan((obj[2]!=null?obj[2].toString():"0"))
                .feb((obj[3]!=null?obj[3].toString():"0"))
                .mar((obj[4]!=null?obj[4].toString():"0"))
                .apr((obj[5]!=null?obj[5].toString():"0"))
                .may((obj[6]!=null?obj[6].toString():"0"))
                .jun((obj[7]!=null?obj[7].toString():"0"))
                .jul((obj[8]!=null?obj[8].toString():"0"))
                .aug((obj[9]!=null?obj[9].toString():"0"))
                .sep((obj[10]!=null?obj[10].toString():"0"))
                .oct((obj[11]!=null?obj[11].toString():"0"))
                .nov((obj[12]!=null?obj[12].toString():"0"))
                .dec((obj[13]!=null?obj[13].toString():"0"))
                .build();
                textilDto.add(dto);
        
        });
        
        return textilDto;
    }
    
}
