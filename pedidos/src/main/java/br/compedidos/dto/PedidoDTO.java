package br.compedidos.dto;

import br.compedidos.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private String id;
    private LocalDateTime dataHora;
    private Status status;
    private List<ItemDoPedidoDTO> items = new ArrayList<>();
    private EnderecoDTO endereco;
}
