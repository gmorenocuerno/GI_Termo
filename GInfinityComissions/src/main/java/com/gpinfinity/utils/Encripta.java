/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gpinfinity.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Gustavo
 * @description
 * @date
 */
public class Encripta {
    public static void main(String args[]){
        String password = "123";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        String hashedPassword = passwordEncoder.encode(password);

        System.out.println("hash: "+hashedPassword);
    }
}
