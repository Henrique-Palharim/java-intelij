package com.template.model.validador;

public class NumInteiroValidador implements Validador<String> {

    private final String nomeCampo;
    private final int valorMinimo;
    private String mensagemErro;

    public NumInteiroValidador(String nomeCampo, int valorMinimo) {
        this.nomeCampo = nomeCampo;
        this.valorMinimo = valorMinimo;
    }

    @Override
    public boolean validar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            this.mensagemErro = "O campo " + nomeCampo + " não pode estar vazio.";
            return false;
        }

        try {
            int numero = Integer.parseInt(valor.trim());
            if (numero < valorMinimo) {
                this.mensagemErro = "O campo " + nomeCampo + " deve ser no mínimo " + valorMinimo + ".";
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            this.mensagemErro = "O campo " + nomeCampo + " deve conter apenas números inteiros.";
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return mensagemErro;
    }
}