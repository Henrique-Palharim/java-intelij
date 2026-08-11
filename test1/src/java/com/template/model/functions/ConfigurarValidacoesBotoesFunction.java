package com.template.model.functions;

import com.template.model.dto.PlayerDTO;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.Property;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import java.util.List;

public class ConfigurarValidacoesBotoesFunction {

    public static void executar(
            List<TextField> todosCampos,
            TableView<PlayerDTO> tabelaContas,
            Button btnCadastrar,
            Button btnLimpar,
            Button btnAlterar,
            Button btnExcluir
    ) {
        Property<?>[] propriedadesTexto = todosCampos.stream()
                .map(TextInputControl::textProperty)
                .toArray(Property[]::new);

        BooleanBinding algumCampoVazio = Bindings.createBooleanBinding(
                () -> todosCampos.stream().anyMatch(c -> CampoVazioFunction.executar(c.getText())),
                propriedadesTexto
        );

        BooleanBinding todosCamposVazios = Bindings.createBooleanBinding(
                () -> todosCampos.stream().allMatch(c -> CampoVazioFunction.executar(c.getText())),
                propriedadesTexto
        );

        btnCadastrar.disableProperty().bind(algumCampoVazio);
        btnLimpar.disableProperty().bind(todosCamposVazios);

        BooleanBinding nenhumItemSelecionado = tabelaContas.getSelectionModel().selectedItemProperty().isNull();
        btnAlterar.disableProperty().bind(algumCampoVazio.or(nenhumItemSelecionado));
        btnExcluir.disableProperty().bind(nenhumItemSelecionado);
    }
}