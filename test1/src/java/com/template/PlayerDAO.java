package com.template;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {

    private static final Logger logger = Logger.getLogger(PlayerDAO.class.getName());

    // INSERT para inserir dados de players no bdd
    public void insertPlayer(PlayerDTO player)
    {
        Conexao con = new Conexao();

        String sql = "insert into player (nickname, tag, senha, email, level, elo, role_principal, "
                + "role_secundaria, champion_favorito, servidor) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection c = con.conectaBD();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setString(1, player.getNickname());
            ps.setString(2, player.getTag());
            ps.setString(3, player.getSenha());
            ps.setString(4, player.getEmail());
            ps.setInt(5, player.getLevel());
            ps.setString(6, player.getElo());
            ps.setString(7, player.getRole_principal());
            ps.setString(8, player.getRole_secundaria());
            ps.setString(9, player.getChampion_favorito());
            ps.setString(10, player.getServidor());

            /*
                executeUpdate() já é um método pronto da API JDBC do Java

                1 - Envia o SQL pro banco (MySQL, PostgreSQL, etc.)
                2 - O banco executa o comando (INSERT, UPDATE, ou DELETE)
                3 - Retorna quantas linhas foram afetadas
            */
            ps.executeUpdate();
            System.out.println("Player inserido com sucesso!");
        }
        catch (SQLException e)
        {
            logger.log(Level.SEVERE, "Erro ao inserir player", e);
        }

    }

    // UPDATE para atualizar os dados dos players
    public void updatePlayer(PlayerDTO player)
    {
        Conexao con = new Conexao();

        String sql = "update player set nickname = ?, tag = ?, senha = ?, email = ?, level = ?, elo = ?, "
                + "role_principal = ?, role_secundaria = ?, champion_favorito = ?, servidor = ? "
                + "WHERE id = ?";

        try (
                Connection c = con.conectaBD();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setString(1, player.getNickname());
            ps.setString(2, player.getTag());
            ps.setString(3, player.getSenha());
            ps.setString(4, player.getEmail());
            ps.setInt(5, player.getLevel());
            ps.setString(6, player.getElo());
            ps.setString(7, player.getRole_principal());
            ps.setString(8, player.getRole_secundaria());
            ps.setString(9, player.getChampion_favorito());
            ps.setString(10, player.getServidor());
            ps.setInt(11, player.getId()); // WHERE id = ?

            ps.executeUpdate();
            System.out.println("\nPlayer atualizado.");
        }
        catch (SQLException e)
        {
            logger.log(Level.SEVERE, "Erro ao atualizar player", e);
        }

    }

    // DELETE para remvover os dados de players
    public void deletePlayer(int id)
    {
        Conexao con = new Conexao();
        String sql = "delete from player where id = ?";

        try (
                Connection c = con.conectaBD();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Player de id " + id + " excluido.");
        }
        catch (SQLException e)
        {
            logger.log(Level.SEVERE, "Erro ao excluir player", e);
        }

    }

    // SELECT para visualizar todos os players
    public void selectPlayers()
    {
        Conexao con = new Conexao();

        String sql = "select id, nickname, tag from player";

        try (
                Connection c = con.conectaBD();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next())
            {
                PlayerDTO player = new PlayerDTO();
                player.setId(rs.getInt("id"));
                player.setNickname(rs.getString("nickname"));
                player.setTag(rs.getString("tag"));

                System.out.println(player.getId() + " - " + player.getNickname() + "#" + player.getTag());
            }
        }
        catch (SQLException e)
        {
            logger.log(Level.SEVERE, "Erro ao selecionar players", e);
        }

    }

    // SELECT para visualizar todos os dados de todos os players
    public void selectPlayersCompleto()
    {
        Conexao con = new Conexao();

        String sql = "select * from player";

        try (
                Connection c = con.conectaBD();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next())
            {
                PlayerDTO player = new PlayerDTO();
                player.setId(rs.getInt("id"));
                player.setNickname(rs.getString("nickname"));
                player.setTag(rs.getString("tag"));
                player.setSenha(rs.getString("senha"));
                player.setEmail(rs.getString("email"));
                player.setLevel(rs.getInt("level"));
                player.setElo(rs.getString("elo"));
                player.setRole_principal(rs.getString("role_principal"));
                player.setRole_secundaria(rs.getString("role_secundaria"));
                player.setChampion_favorito(rs.getString("champion_favorito"));
                player.setServidor(rs.getString("servidor"));


                System.out.println("Player " + player.getId() + ": \n" + player.getNickname() + "#" + player.getTag() +
                        "\nsenha: " + player.getSenha() + "\nemail: " + player.getEmail() +
                        "\nlevel: " + player.getLevel() + "\nelo: " + player.getElo() +
                        "\nrole principal: " + player.getRole_principal() +
                        "\nrole secundaria: " + player.getRole_secundaria() +
                        "\nchampion fav: " + player.getChampion_favorito() +
                        "\nservidor: " + player.getServidor() + "\n"
                );
            }
        }
        catch (SQLException e)
        {
            logger.log(Level.SEVERE, "Erro ao selecionar players", e);
        }

    }

}