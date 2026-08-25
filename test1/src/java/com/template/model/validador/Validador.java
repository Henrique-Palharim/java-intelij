package com.template.model.validador;

public interface Validador<T> {
    boolean validar();
    String getErrorMessage();
}