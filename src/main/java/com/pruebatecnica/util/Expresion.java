/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebatecnica.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rpalacios
 */
public class Expresion {

    public static boolean email(String email) {
        Pattern pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = pat.matcher(email);
        return mat.matches();
    }

    public static boolean contrasena(String email) {
        Pattern pat = Pattern.compile("[A-Z]{1}[a-z]{1,}[0-9]{2}");
        Matcher mat = pat.matcher(email);
        boolean aa = mat.matches();
        return aa;
    }

}
