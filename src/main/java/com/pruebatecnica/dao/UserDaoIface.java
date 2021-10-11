/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebatecnica.dao;

import com.pruebatecnica.entitys.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rpalacios
 */
public interface UserDaoIface extends JpaRepository<Users, String>{
    
    List<Users> findAllUsersByEmail(String email);
    
}
