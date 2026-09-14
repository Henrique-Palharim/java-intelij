package com.template.model.service;

import com.template.model.dao.PlayerDAO;
import com.template.model.dto.PlayerDTO;
import com.template.model.validador.ContaValidador;
import com.template.model.validador.IPlayerValidador;
import javafx.collections.ObservableList;

public class PlayerService implements IPlayerService {

    private final IPlayerValidador validador;
    private final PlayerDAO playerDAO;

    public PlayerService() {
        this(new ContaValidador(), new PlayerDAO());
    }

    public PlayerService(IPlayerValidador validador, PlayerDAO playerDAO) {
        this.validador = validador;
        this.playerDAO = playerDAO;
    }

    @Override
    public PlayerDTO cadastrarPlayer(
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        PlayerDTO player = validador.criarEValidarPlayer(
                nickname, tag, senha, email, level,
                elo, rolePrincipal, roleSecundaria, championFavorito, servidor
        );
        playerDAO.insertPlayer(player);
        return player;
    }

    @Override
    public void atualizarPlayer(
            PlayerDTO playerExistente,
            String nickname, String tag, String senha, String email, String level,
            String elo, String rolePrincipal, String roleSecundaria,
            String championFavorito, String servidor
    ) {
        PlayerDTO dadosAtualizados = validador.criarEValidarPlayer(
                nickname, tag, senha, email, level,
                elo, rolePrincipal, roleSecundaria, championFavorito, servidor
        );

        playerExistente.setNickname(dadosAtualizados.getNickname());
        playerExistente.setTag(dadosAtualizados.getTag());
        playerExistente.setSenha(dadosAtualizados.getSenha());
        playerExistente.setEmail(dadosAtualizados.getEmail());
        playerExistente.setLevel(dadosAtualizados.getLevel());
        playerExistente.setElo(dadosAtualizados.getElo());
        playerExistente.setRole_principal(dadosAtualizados.getRole_principal());
        playerExistente.setRole_secundaria(dadosAtualizados.getRole_secundaria());
        playerExistente.setChampion_favorito(dadosAtualizados.getChampion_favorito());
        playerExistente.setServidor(dadosAtualizados.getServidor());

        playerDAO.updatePlayer(playerExistente);
    }

    @Override
    public void excluirPlayer(int id) {
        playerDAO.deletePlayer(id);
    }

    @Override
    public ObservableList<PlayerDTO> listarTodos() {
        return playerDAO.listarTodos();
    }
}