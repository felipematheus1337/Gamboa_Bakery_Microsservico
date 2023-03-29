package br.com.alurafood.avaliacao.avaliacao.service.impl;

import br.com.alurafood.avaliacao.avaliacao.dto.AvaliacaoDTO;
import br.com.alurafood.avaliacao.avaliacao.exception.BusinessException;
import br.com.alurafood.avaliacao.avaliacao.model.Avaliacao;
import br.com.alurafood.avaliacao.avaliacao.repository.AvaliacaoRepository;
import br.com.alurafood.avaliacao.avaliacao.service.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AvaliacaoServiceImpl  implements AvaliacaoService {

    private final AvaliacaoRepository repository;
    private final ModelMapper mapper;

    @Override
    public AvaliacaoDTO criar(Avaliacao avaliacao) {
        return mapper.map(repository.save(avaliacao),AvaliacaoDTO.class);
    }

    @Override
    public List<AvaliacaoDTO> listar() {
        return repository.findAll()
                .stream()
                .map(x -> mapper.map(x,AvaliacaoDTO.class))
                .toList();
    }

    @Override
    public Optional<AvaliacaoDTO> buscar(Long id) {
        var optAvaliacao = repository.findById(id);

        if(optAvaliacao.isEmpty()) {
            throw new BusinessException("Avaliação não encontrada");
        }
        return Optional.of(mapper.map(optAvaliacao.get(),AvaliacaoDTO.class));
    }

    @Override
    public void excluir(Long id) {
     repository.deleteById(id);
    }
}
