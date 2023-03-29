package br.com.alurafood.avaliacao.avaliacao.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseDTO {

    private AvaliacaoDTO avaliacaoDTO;
    private MediaDTO mediaDTO;
}
