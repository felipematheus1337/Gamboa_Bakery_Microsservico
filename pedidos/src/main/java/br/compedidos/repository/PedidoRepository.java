package br.compedidos.repository;

import br.compedidos.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {
}
