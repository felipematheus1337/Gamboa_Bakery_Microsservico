package br.com.alurafood.avaliacao.avaliacao.service;

import br.com.alurafood.avaliacao.avaliacao.dto.MediaDTO;

public interface MediaService {

    MediaDTO criarMedia(String pedidoId, Integer nota);
}
