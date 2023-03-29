package br.com.alurafood.avaliacao.avaliacao.service.impl;


import br.com.alurafood.avaliacao.avaliacao.dto.MediaDTO;
import br.com.alurafood.avaliacao.avaliacao.model.Media;
import br.com.alurafood.avaliacao.avaliacao.repository.MediaRedisRepository;
import br.com.alurafood.avaliacao.avaliacao.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService  {

    private final MediaRedisRepository repository;
    private final ModelMapper mapper;


    @Override
    public MediaDTO criarMedia(String pedidoId, Integer nota) {
        var mediaExists = repository.findByPedidoId(pedidoId);
        if(mediaExists.isEmpty()) {
            Media media = new Media();
            media.setPedidoId(pedidoId);
            media.setQuantidadeAvaliacao(1);
            media.setNotaMedia(nota.doubleValue());
            return mapper.map(repository.save(media),MediaDTO.class);
        }
        Media media = mediaExists.get();
        media.setQuantidadeAvaliacao(media.getQuantidadeAvaliacao() + 1);
        Double novaMedia = (media.getNotaMedia() * (media.getQuantidadeAvaliacao())
        + nota) / media.getQuantidadeAvaliacao();
        media.setNotaMedia(novaMedia);
        return mapper.map(repository.save(media),MediaDTO.class);
    }
}
