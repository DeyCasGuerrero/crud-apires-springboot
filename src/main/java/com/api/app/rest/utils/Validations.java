package com.api.app.rest.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validations {

    public boolean isValidPassword(String password) {
        // Validar la contraseña: al menos 8 caracteres y al menos un número :V
        String regex = "^(?=.*[0-9])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean isValidEmail(String email) {
        // Validar el correo electrónico con @ y con .com al final :V
        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.com)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
