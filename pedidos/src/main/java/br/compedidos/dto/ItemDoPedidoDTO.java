package br.compedidos.dto;

import br.compedidos.model.TipoItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDoPedidoDTO {

    private Integer quantidade;
    private String descricao;
    private TipoItem tipoItem;

    public ItemDoPedidoDTO(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
