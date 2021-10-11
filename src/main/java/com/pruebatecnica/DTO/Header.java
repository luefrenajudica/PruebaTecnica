/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebatecnica.DTO;

import com.pruebatecnica.entitys.Users;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author rpalacios
 */
public class Header implements Serializable{

    public Header() {
    }

    public Header(String mensaje) {
        this.mensaje = mensaje;
    }

    public Header(String mensaje, Users user) {
        this.mensaje = mensaje;
        this.user = user;
    }

    public Header(String mensaje, List<Users> users) {
        this.mensaje = mensaje;
        this.users = users;
    }
    
    private String mensaje;
    
    private Users user;
    private List<Users> users;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
    
}
