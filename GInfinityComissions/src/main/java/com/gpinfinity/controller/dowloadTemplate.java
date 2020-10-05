/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.controller;

import java.io.InputStream;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author nivrist
 */
@Named(value = "dowloadTemplate")
@RequestScoped
public class dowloadTemplate implements Serializable{
private StreamedContent fileDowloadCsv;
    /**
     * Creates a new instance of dowloadTemplate
     */
    public dowloadTemplate() {
         /*fileDowloadCsv = DefaultStreamedContent.builder()
                .name("plantilla_carga_textiles.csv")
                .contentType("text/csv")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/planilla_carga_textiles.csv"))
                .build();*/
    }

    public StreamedContent getDowloadFileCsvTextiles(){  
        
         InputStream stream = getClass().getClassLoader().getResourceAsStream("planilla_carga_textiles.csv");
         StreamedContent streamedContent =  new DefaultStreamedContent(stream, "text/csv", "Template_textiles.csv");
         
         System.out.println(streamedContent);
         return streamedContent;    
    }
    
    public StreamedContent getDowloadFileCsvIndicadores(){  
        
         InputStream stream = getClass().getClassLoader().getResourceAsStream("plantilla_b2d.csv");
         StreamedContent streamedContent =  new DefaultStreamedContent(stream, "text/csv", "Template_indicadores.csv");
         
         System.out.println(streamedContent);
         return streamedContent;    
    }
    
    public StreamedContent getFileDowloadCsv() {
        return fileDowloadCsv;
    }

    public void setFileDowloadCsv(StreamedContent fileDowloadCsv) {
        this.fileDowloadCsv = fileDowloadCsv;
    }
    
}
