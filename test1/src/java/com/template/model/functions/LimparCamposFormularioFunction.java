package com.template.model.functions;

import com.template.model.dto.PlayerDTO;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import java.util.List;

public class LimparCamposFormularioFunction {

    public static void executar(List<TextField> todosCampos, TableView<PlayerDTO> tabelaContas) {
        todosCampos.forEach(TextInputControl::clear);
        tabelaContas.getSelectionModel().clearSelection();
    }
}