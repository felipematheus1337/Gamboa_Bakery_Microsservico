package br.com.alurafood.avaliacao.avaliacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaDTO {


    private Long id;

    private Double notaMedia;
    private String pedidoId;

    private Integer quantidadeAvaliacao;
}
