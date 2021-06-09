/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.config;

import com.gpinfinity.entities.Usuarios;
import com.gpinfinity.service.IUsuarioServices;
import com.gpinfinity.utils.AuthLDAP;
import com.gpinfinity.utils.UsrDetails;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author nivrist
 */

public class AuthenticationProviderSec implements AuthenticationProvider {

    public AuthenticationProviderSec() {
        super();
    }

    @Autowired
    IUsuarioServices usuarioService;
    
    @Autowired
    UsrDetails usrDetails;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        BCryptPasswordEncoder pass = new BCryptPasswordEncoder(12);
        Authentication authRet = null;
        try{
        
        List<GrantedAuthority> roles = new ArrayList<>();
        GrantedAuthority role = null;
        String usuario = auth.getPrincipal().toString().trim();
        Usuarios user = usuarioService.getUsuarioVerificar(usuario);
        String password = auth.getCredentials().toString().trim();
        
        if (usuario.isEmpty() && password.isEmpty()) {
              
            throw new BadCredentialsException("Usuario y contraseña con requeridos");

        } else {
            
            if(user!=null){
                AuthLDAP autLDAP = new AuthLDAP("ldap://172.16.1.3:389", "simple", usuario, password);

                if (autLDAP.isAutenticado()) {
                //if (/*autLDAP.isAutenticado()*/true) {
                    role = new SimpleGrantedAuthority("ROLE_AUTHENTICATION");
                    roles.add(role);
                    usrDetails.set_userName(usuario);
                    authRet = new UsernamePasswordAuthenticationToken(usuario, password, roles);

                } else {

                    throw new BadCredentialsException("Usuario o Contraseña Invalidos");
                }
            }

        }

        
        }catch(BadCredentialsException e){
            
            throw new BadCredentialsException("Usuario o Contraseña Invalidos");
        
        }
        return authRet;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}
