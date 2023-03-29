package br.com.alurafood.avaliacao.avaliacao.service.impl;


import br.com.alurafood.avaliacao.avaliacao.dto.ResponseDTO;
import br.com.alurafood.avaliacao.avaliacao.model.Avaliacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final AvaliacaoServiceImpl serviceAvaliacao;
    private final MediaServiceImpl serviceMedia;

    public ResponseDTO criarAvalicao(Avaliacao avaliacao) {
        var avaliacaoCriada = serviceAvaliacao.criar(avaliacao);
        var mediaCriada = serviceMedia.criarMedia(avaliacaoCriada.getPedidoId(),avaliacaoCriada.getNota());
        return new ResponseDTO(avaliacaoCriada, mediaCriada);
    }
}
