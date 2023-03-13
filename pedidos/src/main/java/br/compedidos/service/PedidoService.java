package br.compedidos.service;


import br.compedidos.dto.PedidoDTO;
import br.compedidos.dto.StatusDTO;

import java.util.List;

public interface PedidoService {

    List<PedidoDTO> obterTodos();

    PedidoDTO obterPorId(String id);


    PedidoDTO criarPedido(PedidoDTO dto);

    PedidoDTO atualizaStatus(String id, StatusDTO dto);

    void aprovaPagamentoPedido(String id);
}
