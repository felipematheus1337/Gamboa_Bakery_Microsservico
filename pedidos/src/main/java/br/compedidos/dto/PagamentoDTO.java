package br.compedidos.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class PagamentoDTO {

    private Long id;
    private BigDecimal valor;
    private String nome;
    private String numero;
    private String expiracao;
    private String codigo;
    private StatusPagamento status;
    private Long formaDePagamentoId;
    private String pedidoId;
}
