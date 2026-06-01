package com.template;

public class PlayerDTO {

    // colunas da tabela "player"
    private int id;
    private String nickname;
    private String tag;
    private String senha;
    private String email;
    private int level;
    private String elo;
    private String role_principal;
    private String role_secundaria;
    private String champion_favorito;
    private String servidor;

    // getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public String getElo() { return elo; }
    public void setElo(String elo) { this.elo = elo; }

    public String getRole_principal() { return role_principal; }
    public void setRole_principal(String role_principal) { this.role_principal = role_principal; }

    public String getRole_secundaria() { return role_secundaria; }
    public void setRole_secundaria(String role_secundaria) { this.role_secundaria = role_secundaria; }

    public String getChampion_favorito() { return champion_favorito; }
    public void setChampion_favorito(String champion_favorito) { this.champion_favorito = champion_favorito; }

    public String getServidor() { return servidor; }
    public void setServidor(String servidor) { this.servidor = servidor; }

}
