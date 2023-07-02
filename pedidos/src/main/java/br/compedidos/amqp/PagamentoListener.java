package br.compedidos.amqp;


import br.compedidos.dto.PagamentoDTO;
import br.compedidos.dto.StatusPagamento;
import br.compedidos.exception.BusinessException;
import br.compedidos.model.Pedido;
import br.compedidos.model.Status;
import br.compedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PagamentoListener {

    private final PedidoRepository repository;

    @RabbitListener(queues = "${rabbitmq-queue}")
    public void recebePagamentoConfirmado(@Payload PagamentoDTO pagamentoDTO) {

        Optional<Pedido> pedidoExists = repository.findById(pagamentoDTO.getPedidoId());

        if(pedidoExists.isEmpty() || pagamentoDTO.getStatus() == StatusPagamento.CANCELADO) {
            throw new BusinessException("Pedido não existe!");
        }

        pedidoExists.get().setStatus(Status.CONFIRMADO);
        repository.save(pedidoExists.get());

        String mensagem = """
                Dados do pagamento: %s
                Número do pedido: %s
                Valor R$: %s
                Status: %s 
                """.formatted(pagamentoDTO.getId(),
                pagamentoDTO.getPedidoId(),
                pagamentoDTO.getValor(),
                pagamentoDTO.getStatus());

        System.out.println("Pagamento confirmado: " + mensagem);
    }
}
