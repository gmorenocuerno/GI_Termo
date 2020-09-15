/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.utils;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author piru876
 */
public class Utils implements Serializable
{
    
    
    public static String getMessegesBundles(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        String message = bundle.getString(key);
        return message;
    }

    public void addmessageswarn(String messages) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, Utils.getMessegesBundles(messages), ""));
    }

    public void addmessagesok(String messages) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Utils.getMessegesBundles(messages), ""));
    }

    public void addmessageserror(String messages) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utils.getMessegesBundles(messages), ""));
    }

    public void addsimplemessages(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
    }

    public void addsimplemessageswarn(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
    }

    public void addsimplemessageserror(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
    }

    public boolean validMail(String mail) {
        String pattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(mail);
        return m.find();
    }

    public boolean validNullString(String field) {
        boolean nul = false;
        if (field == null || field.equals("")) {
            nul = true;
        }
        return nul;
    }
    
}
