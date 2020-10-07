/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author nivrist
 */
public class main {

    public static void main(String...args) {
       try{
        String mes="202001".substring(4);  
        String anio="202001".substring(0,4); 
        String sDate1 = "01/"+mes+"/"+anio;
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
        System.out.println("fecha"+date1);
        }catch(ParseException es){
        }
        
    }
    
    
    
}
