package br.com.alurafood.avaliacao.avaliacao.service;

import br.com.alurafood.avaliacao.avaliacao.dto.AvaliacaoDTO;
import br.com.alurafood.avaliacao.avaliacao.model.Avaliacao;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoService {

    AvaliacaoDTO criar(Avaliacao avaliacao);

    List<AvaliacaoDTO> listar();

    Optional<AvaliacaoDTO> buscar(Long id);

    void excluir(Long id);

}
