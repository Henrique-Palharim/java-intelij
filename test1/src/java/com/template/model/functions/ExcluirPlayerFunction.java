package com.template.model.functions;

import com.template.model.dao.PlayerDAO;

public class ExcluirPlayerFunction {

    private final PlayerDAO playerDAO = new PlayerDAO();

    public void executar(int id) {
        playerDAO.deletePlayer(id);
    }
}