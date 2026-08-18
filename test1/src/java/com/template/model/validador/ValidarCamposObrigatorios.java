package com.template.model.validador;

public class ValidarCamposObrigatorios implements Validador<String> {

    private final String nomeCampo;
    private String mensagemErro;

    // recebe o nome do campo para montar a mensagem de erro
    public ValidarCamposObrigatorios(String nomeCampo) {
        this.nomeCampo = nomeCampo;
    }

    // verifica se é nulo ou se contém apenas espaços
    @Override
    public boolean validar(String valor) {

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
}