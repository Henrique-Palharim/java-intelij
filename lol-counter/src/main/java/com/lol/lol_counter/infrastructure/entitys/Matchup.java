package com.lol.lol_counter.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "matchup")
@Entity
public class Matchup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // campeão a ser counterado
    @ManyToOne
    @JoinColumn(name = "campeao_id", nullable = false)
    private Campeao campeao;

    // campeões que são o counters dele
    @ManyToOne
    @JoinColumn(name = "campeao_counter_id", nullable = false)
    private Campeao counter;

    @Column(name = "runas_recomendadas", length = 1000)
    private String runasRecomendadas;

    @Column(name = "itens_recomendados", length = 1000)
    private String itensRecomendados;

    @Column(name = "dicas", length = 2000)
    private String dicas;

}