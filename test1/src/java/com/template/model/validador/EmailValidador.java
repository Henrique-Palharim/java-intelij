package com.template.model.validador;

import java.util.regex.Pattern;

public class EmailValidador implements Validador<String> {

    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.\\w+$";
    private String mensagemErro;

    @Override
    public boolean validar(String email) {
        if (email == null || !Pattern.matches(EMAIL_REGEX, email.trim())) {
            this.mensagemErro = "O e-mail informado é inválido. Exemplo correto: usuario@dominio.com";
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return mensagemErro;
    }
}