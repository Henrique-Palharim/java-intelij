
package com.campeoes.cadastro_campeoes.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "campeoes")
public class Campeao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String rota;

    @Column(nullable = false)
    private String classe;

    @Column(nullable = false)
    private String regiao;
}
