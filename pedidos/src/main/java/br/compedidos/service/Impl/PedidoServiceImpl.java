package br.compedidos.service.Impl;

import br.compedidos.dto.PedidoDTO;
import br.compedidos.dto.StatusDTO;
import br.compedidos.exception.BusinessException;
import br.compedidos.model.Pedido;
import br.compedidos.model.Status;
import br.compedidos.repository.PedidoRepository;
import br.compedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;

    private final ModelMapper mapper;

    @Override
    public List<PedidoDTO> obterTodos() {
        return repository.findAll().stream()
                .map(p -> mapper.map(p, PedidoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PedidoDTO obterPorId(String id) {
        var pedido = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Invalid ID!"));

        return mapper.map(pedido, PedidoDTO.class);
    }

    @Override
    public PedidoDTO criarPedido(PedidoDTO dto) {
        var pedido = mapper.map(dto, Pedido.class);

        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(Status.REALIZADO);
        Pedido salvo = repository.save(pedido);

        return mapper.map(pedido, PedidoDTO.class);
    }

    @Override
    public PedidoDTO atualizaStatus(String id, StatusDTO dto) {
        var pedido = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Invalid ID"));
        pedido.setStatus(dto.getStatus());
        repository.save(pedido);
        return mapper.map(pedido, PedidoDTO.class);
    }

    @Override
    public void aprovaPagamentoPedido(String id) {
        var pedido = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Invalid ID"));

        pedido.setStatus(Status.PAGO);
        repository.save(pedido);

    }
}
