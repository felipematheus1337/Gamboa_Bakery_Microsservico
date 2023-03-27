package br.compedidos.amqp;


import br.compedidos.dto.PagamentoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

    @RabbitListener(queues = "${rabbitmq-queue}")
    public void recebeMensagem(@Payload PagamentoDTO pagamentoDTO) {

        String mensagem = """
                Dados do pagamento: %s
                Número do pedido: %s
                Valor R$: %s
                Status: %s 
                """.formatted(pagamentoDTO.getId(),
                pagamentoDTO.getPedidoId(),
                pagamentoDTO.getValor(),
                pagamentoDTO.getStatus());

        System.out.println("Recebi a mensagem: " + mensagem);
    }
}
