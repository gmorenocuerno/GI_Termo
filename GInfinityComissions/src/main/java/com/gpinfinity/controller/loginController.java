/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.controller;

import com.gpinfinity.config.ApplicationContextProvider;
import com.gpinfinity.entities.Usuarios;
import com.gpinfinity.service.IUsuarioServices;
import com.gpinfinity.utils.Utils;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author nivrist
 */
@Named(value = "loginController")
@SessionScoped
@Data
@EqualsAndHashCode(callSuper = false)
public class loginController extends Utils implements Serializable {

    private boolean editFields = false;

    private List<Usuarios> userlist;
    private Usuarios selectedUser;
    IUsuarioServices usuarioServices;
    private String userName;
    private String email;
    private String pass;
    private String estado;
    private String edad;
    private String fullName;
    
   

    /**
     * Creates a new instance of loginController
     */
    public void clear() {
        selectedUser = null;
        editFields = false;
        setEdad("");
        setEmail("");
        setEstado("");
        setPass("");
        setUserName("");
        setFullName("");

    }

    public String crearUsuario() {

        if (validNullString(getUserName())) {
            addsimplemessageserror("Nombre de usuario es requerido");
            return null;
        }
        if (validNullString(getFullName())) {
            addsimplemessageserror("Nombre de completo es requerido");
            return null;
        }

        if (validNullString(getPass())) {
            addsimplemessageserror("Contraseña es requerido");
            return null;
        }
        if (validNullString(getEdad())) {
            addsimplemessageserror("Edad es requerido");
            return null;
        }
        if (validNullString(getEmail())) {
            addsimplemessageserror("Correo electronico es requerido");
            return null;
        }
        if (validNullString(getEstado())) {
            addsimplemessageserror("Estado es requerido");
            return null;
        }

        if (!editFields) {
            if (usuarioServices.getUsuarioVerificar(userName) != null) {
                addsimplemessageserror("Nombre de usuario existente!");
                return null;
            }
        }

        Usuarios user = Usuarios.builder().userName(userName)
                .edad(Integer.parseInt(edad))
                .email(email)
                .fechaCreacion(new Date())
                .fullName(fullName)
                .password(pass)
                .status(estado)
                .usuarioCreacion("SYSTEM")
                .build();
        usuarioServices.createdUsuario(user);
        
        if(!editFields){addsimplemessages("Usuario creado!");}else{addsimplemessages("Usuario actualizado!");}

        userlist = usuarioServices.listAllUsuarios();

        clear();

        return null;

    }

    public void logOut() {
        try {            
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logout");
            dispatcher.forward(request, response);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (ServletException | IOException ex) {
        }
        
        

    }
    
    public void loadUserData() {
        editFields = true;
        setEdad(String.valueOf(selectedUser.getEdad()));
        setFullName(selectedUser.getFullName());
        setUserName(selectedUser.getUserName());
        setEmail(selectedUser.getEmail());
        setPass(selectedUser.getPassword());
        setEstado(selectedUser.getStatus());

    }

    public loginController() {
    }

    @PostConstruct
    public void init() {

        usuarioServices = ApplicationContextProvider.getApplicationContext().getBean(IUsuarioServices.class);
        userlist = usuarioServices.listAllUsuarios();
    }

    public String ingresar() {

        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
            dispatcher.forward(request, response);
            FacesContext.getCurrentInstance().responseComplete();
            

        } catch (IOException | ServletException e) {
             
        }
        return null;
    }
    

}
