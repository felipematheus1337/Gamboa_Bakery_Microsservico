package br.com.alurafood.avaliacao.avaliacao.repository;

import br.com.alurafood.avaliacao.avaliacao.model.Media;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaRedisRepository extends CrudRepository<Media, Long> {

    Optional<Media> findByPedidoId(String pedidoId);
}
