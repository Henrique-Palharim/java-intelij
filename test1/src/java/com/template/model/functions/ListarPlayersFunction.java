package com.template.model.functions;

import com.template.model.dao.PlayerDAO;
import com.template.model.dto.PlayerDTO;
import javafx.collections.ObservableList;

public class ListarPlayersFunction {

    private final PlayerDAO playerDAO = new PlayerDAO();

    public ObservableList<PlayerDTO> executar() {
        return playerDAO.listarTodos();
    }
}