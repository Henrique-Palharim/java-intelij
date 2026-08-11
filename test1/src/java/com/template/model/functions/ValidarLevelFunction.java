package com.template.model.functions;

public class ValidarLevelFunction {

    public static int executar(String level) {
        try {
            return Integer.parseInt(level.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O campo Level deve conter apenas números.");
        }
    }
}