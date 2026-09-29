package com.lol.lol_counter.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "campeao")
@Entity

public class Campeao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "chave_riot")
    private String chaveRiot; // ex: "Aatrox", "MonkeyKing" (usado para montar a URL da imagem)

    @Column(name = "url_imagem")
    private String urlImagem;
}