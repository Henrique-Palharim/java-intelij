package com.template.model.functions;

import java.util.Arrays;

public class ValidarCamposObrigatoriosFunction {

    public static void executar(String... campos) {
        boolean algumVazio = Arrays.stream(campos).anyMatch(CampoVazioFunction::executar);

        if (algumVazio) {
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
        }
    }
}