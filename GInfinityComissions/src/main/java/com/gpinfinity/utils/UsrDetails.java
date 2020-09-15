/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.utils;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author piru876
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UsrDetails {

    private String _userName;

    public BigDecimal converStringToBigDecimal(String val) {
        return new BigDecimal(val);
    }

    public Date getDate() {
        return new Date();
    }

}
