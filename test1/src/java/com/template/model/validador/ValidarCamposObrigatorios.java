package com.template.model.validador;

public class ValidarCamposObrigatorios implements Validador<String> {

    private final String nomeCampo;
    private final String valor;
    private String mensagemErro;

    public ValidarCamposObrigatorios(String nomeCampo, String valor) {
        this.nomeCampo = nomeCampo;
        this.valor = valor;
    }

    @Override
    public boolean validar() {
        if (valor == null || valor.trim().isEmpty()) {
            this.mensagemErro = "O campo " + nomeCampo + " é obrigatório.";
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return mensagemErro;
    }

    @Override
    public String getValor() {
        return this.valor != null ? this.valor.trim() : null;
    }
}