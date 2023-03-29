package br.com.alurafood.avaliacao.avaliacao.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvaliacaoDTO {

    private Long id;

    private String descricao;

    private int nota;

    private String pedidoId;
}
