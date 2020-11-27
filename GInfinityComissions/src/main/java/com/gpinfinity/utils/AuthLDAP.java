/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpinfinity.utils;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 * Contiene la Funcionalidad de Autenticacion
 * y consulta de atributos de un usuario en el LDAP
 * 
 * @author tgomez
 */
public final class AuthLDAP {
    private String usuario;
    private String clave;
    private String servidor;
    private String dn;
    private String tipoAuth;
    private boolean autenticado;
    private String excepcion;
    
    DirContext dc;
    
    /**
     * Constructor de la conexion con el Motor de LDAP
     *
     * @param server  Servidor en donde se encuentra el LDAP
     * @param ta      Tipo de Autenticacion
     * @param usuario Usuario que desea realizar la conexion
     * @param clave   Clave del usuario
     *
     */
    public AuthLDAP(String server, String ta,String usuario,String clave) {
        this.servidor = server;
        this.dn = "CN=portaluser,OU=Usuarios Genericos,OU=El Salvador,OU=Termo,DC=termonet,DC=com";
        this.tipoAuth = ta;
        this.usuario=usuario;
        this.clave=clave;
        inicializarConexion();
    }
    
    public void inicializarConexion() {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, servidor);
        env.put(Context.SECURITY_AUTHENTICATION, tipoAuth);
        env.put(Context.SECURITY_PRINCIPAL, dn); //se hace una autenticacion con usuario genérico para recuperar dn de Usuario
        env.put(Context.SECURITY_CREDENTIALS, "Termo2016%");
        
        NamingEnumeration<SearchResult> results = null;
 
        try {
            dc = new InitialDirContext(env);
            
            //Se busca y valida el usuario a autenticar
            try {
               SearchControls controles = new SearchControls();
               controles.setSearchScope(SearchControls.SUBTREE_SCOPE); // Buscar en todo el dominio
               controles.setCountLimit(1);   //Numero maximo de entradas a devolver en la busqueda
               controles.setTimeLimit(5000); //Limite de tiempo para SearchControls en milisegundos

               String busqueda = "(&(objectClass=user)(sAMAccountName=" + usuario + "))";

               results = dc.search("dc=termonet,dc=com",busqueda, controles);
               
               if (results.hasMore()) {
                   SearchResult result = (SearchResult) results.next();
                   Attributes attrs = result.getAttributes();
                   Attribute dnAttr = attrs.get("distinguishedName");
                   dn = (String) dnAttr.get();
                   // Usuario existe, validar clave
                   dc.close();
                   env.put(Context.SECURITY_PRINCIPAL, dn);
                   env.put(Context.SECURITY_CREDENTIALS, clave);

                   dc = new InitialDirContext(env);
                   setAutenticado(true);
               }
               else 
                   setAutenticado(false);
            } catch (NamingException ex) { // Invalid Login
                setExcepcion("Error Autenticando mediante LDAP: " + ex.toString());
                setAutenticado(false);
            }
        } catch (NamingException ex) {
            setExcepcion("Error conectando a LDAP con usuario de busqueda: " + ex.toString());
            setAutenticado(false);
        }
    }
    
    /**
     * Retorna el Atributo de la conexion con LDAP actual
     * 
     * @param atributo Nombre del Atributo que se desea obtener
     * @return Attribute con la informacion correspondiente
     */
 
    public Attribute cargarPropiedadConexion(String atributo) {
        Attribute propiedad = null;
 
        try {
            Attributes attrs = dc.getAttributes(dn);
 
            if (attrs == null) {
                propiedad = null;
            } else {
                propiedad = attrs.get(atributo);
            }
        } catch (Exception e) {
            propiedad = null;
        }
        return propiedad;
    }
 
    /*Get's y Set's*/
    public boolean isAutenticado() {
        return autenticado;
    }
    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getExcepcion() { //Devuelve valor de ultima excepcion
        return excepcion;
    }
    public void setExcepcion(String excepcion) {
        this.excepcion = excepcion;
    }
}
