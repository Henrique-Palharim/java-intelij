
package com.campeoes.cadastro_campeoes.infrastructure.repository;

import com.campeoes.cadastro_campeoes.infrastructure.entitys.Campeao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampeaoRepository extends JpaRepository<Campeao, Long> {
}
