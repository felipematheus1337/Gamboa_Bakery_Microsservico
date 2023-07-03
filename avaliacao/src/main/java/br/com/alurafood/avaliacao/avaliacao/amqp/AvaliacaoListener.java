package br.com.alurafood.avaliacao.avaliacao.amqp;

import br.com.alurafood.avaliacao.avaliacao.dto.AvaliacaoDTO;
import br.com.alurafood.avaliacao.avaliacao.dto.PagamentoDto;
import br.com.alurafood.avaliacao.avaliacao.exception.BusinessException;
import br.com.alurafood.avaliacao.avaliacao.model.Avaliacao;
import br.com.alurafood.avaliacao.avaliacao.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AvaliacaoListener {

    private final AvaliacaoRepository repository;
    private final ModelMapper mapper;
    private static final  String QUEUENAME = "avaliacao-queue";


    @RabbitListener(queues = QUEUENAME)
    public void recebeMensagem(@Payload AvaliacaoDTO avaliacao) {

        if(avaliacao.getDescricao() == null) {
            throw new BusinessException("Não é possível salvar uma avaliação sem uma descrição, seja mais específico!");
        } else if(avaliacao.getNota() < 0) {
            throw new BusinessException("Não é possível salvar uma avaliação com nota menor do que zero!");
        } else if (avaliacao.getPedidoId() == null) {
            throw new BusinessException("Não é possível salvar uma avaliação sem um pedido vinculado!");
        }

        Avaliacao avaliacaoParaSalvar = mapper.map(avaliacao, Avaliacao.class);

        repository.save(avaliacaoParaSalvar);

    }
}
