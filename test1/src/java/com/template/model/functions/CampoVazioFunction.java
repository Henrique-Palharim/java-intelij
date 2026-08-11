package com.template.model.functions;

public class CampoVazioFunction {

    public static boolean executar(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}