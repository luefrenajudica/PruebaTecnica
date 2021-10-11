/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebatecnica.service;

import com.pruebatecnica.DTO.Header;
import com.pruebatecnica.dao.PhoneDaoIface;
import com.pruebatecnica.entitys.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pruebatecnica.dao.UserDaoIface;
import com.pruebatecnica.entitys.Phones;
import com.pruebatecnica.util.Expresion;
import java.util.Date;

/**
 *
 * @author rpalacios
 */
@RestController
@RequestMapping("user")
public class UsersREST {

    @Autowired
    private UserDaoIface userDao;

    @Autowired
    private PhoneDaoIface phoneDao;

    @GetMapping
    public ResponseEntity<Header> getfindAll() {
        try {
            List<Users> usersList = userDao.findAll();
            return ResponseEntity.ok(new Header("Consulta exitosa", usersList));
        } catch (Exception ex) {
            return ResponseEntity.ok(new Header("ERROR: " + ex.getMessage()));
        }
    }

    @RequestMapping(value = "{userId}")
    public ResponseEntity<Header> getFindById(@PathVariable("userId") String userId) {
        try {
            Optional<Users> optional = userDao.findById(userId);
            if (optional.isPresent()) {
                Users user = optional.get();
                user.setToken(user.getId());
                return ResponseEntity.ok(new Header("Consulta exitosa", user));
            } else {
                return ResponseEntity.ok(new Header("No se encontro ningún registro por el ID " + userId));
            }
        } catch (Exception ex) {
            return ResponseEntity.ok(new Header("ERROR: " + ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Header> create(@RequestBody Users user) {
        try {
            String mensaje = "";
            if (Expresion.email(user.getEmail())) {
                if (!userDao.findAllUsersByEmail(user.getEmail()).isEmpty()) {
                    mensaje += "El correo ya registrado - ";
                }
            } else {
                mensaje += "Formato de email incorrecto - ";
            }
            if (!Expresion.contrasena(user.getPassword())) {
                mensaje += "Formato de password incorrecto - ";
            }
            if (mensaje.equals("")) {
                user.setCreated(new Date());
                user.setLast_login(new Date());
                user.setIsactive(true);
                Users newUser = userDao.save(user);
                if (user.getPhones() != null && !user.getPhones().isEmpty()) {
                    for (Phones phone : user.getPhones()) {
                        phone.setUsersId(newUser.getId());
                        phoneDao.save(phone);
                    }
                }
                return ResponseEntity.ok(new Header("Usuario creado exitosamente", newUser));
            } else {
                return ResponseEntity.ok(new Header(mensaje));
            }
        } catch (Exception ex) {
            return ResponseEntity.ok(new Header("ERROR: " + ex.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<Header> update(@RequestBody Users user) {
        try {
            Optional<Users> optional = userDao.findById(user.getId());
            if (optional.isPresent()) {
                user.setModified(new Date());
                Users newUser = userDao.save(user);
                return ResponseEntity.ok(new Header("Usuario actualizado exitosamente", newUser));
            } else {
                return ResponseEntity.ok(new Header("No se encontro ningún registro por el ID " + user.getId()));
            }
        } catch (Exception ex) {
            return ResponseEntity.ok(new Header("ERROR: " + ex.getMessage()));
        }
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Header> deleteById(@PathVariable("userId") String userId) {
        try {
            Optional<Users> optional = userDao.findById(userId);
            if (optional.isPresent()) {
                userDao.deleteById(userId);
                return ResponseEntity.ok(new Header("Registro borrado con exito"));
            } else {
                return ResponseEntity.ok(new Header("No se encontro ningún registro por el ID " + userId));
            }
        } catch (Exception ex) {
            return ResponseEntity.ok(new Header("ERROR: " + ex.getMessage()));
        }
    }

}
