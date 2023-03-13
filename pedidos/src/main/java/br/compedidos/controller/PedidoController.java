package br.compedidos.controller;


import br.compedidos.dto.PedidoDTO;
import br.compedidos.dto.StatusDTO;
import br.compedidos.service.Impl.PedidoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoServiceImpl service;

    @GetMapping()
    public List<PedidoDTO> listarTodos() {
        return service.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> listarPorId(@PathVariable @NotNull String id) {
        PedidoDTO dto = service.obterPorId(id);

        return  ResponseEntity.ok(dto);
    }

    @GetMapping("/porta")
    public String retornaPorta(@Value("${local.server.port}") String porta){
        return String.format("Requisição respondida pela instância executando na porta %s", porta);
    }

    @PostMapping()
    public ResponseEntity<PedidoDTO> realizaPedido(@RequestBody @Valid PedidoDTO dto, UriComponentsBuilder uriBuilder) {
        var pedidoRealizado = service.criarPedido(dto);

        URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoRealizado.getId()).toUri();

        return ResponseEntity.created(endereco).body(pedidoRealizado);

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoDTO> atualizaStatus(@PathVariable String id, @RequestBody StatusDTO status){
        PedidoDTO dto = service.atualizaStatus(id, status);

        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{id}/pago")
    public ResponseEntity<Void> aprovaPagamento(@PathVariable @NotNull String id) {
        service.aprovaPagamentoPedido(id);

        return ResponseEntity.ok().build();

    }

}
