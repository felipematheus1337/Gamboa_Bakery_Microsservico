package br.compagamentosms.service.impl;


import br.compagamentosms.dto.PagamentoDTO;
import br.compagamentosms.http.PedidoClient;
import br.compagamentosms.model.Pagamento;
import br.compagamentosms.model.Status;
import br.compagamentosms.repository.PagamentoRepository;
import br.compagamentosms.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository repository;

    private final ModelMapper mapper;

    private final PedidoClient pedido;



    @Override
    public Page<PagamentoDTO> obterTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> mapper.map(p, PagamentoDTO.class));
    }

    @Override
    public PagamentoDTO obterPorId(Long id) {
        var pagamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return mapper.map(pagamento, PagamentoDTO.class);
    }

    @Override
    public PagamentoDTO criarPagamento(PagamentoDTO dto) {
        var pagamento = mapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return mapper.map(pagamento, PagamentoDTO.class);
    }

    @Override
    public PagamentoDTO atualizarPagamento(Long id, PagamentoDTO dto) {
        var pagamento = mapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return mapper.map(pagamento, PagamentoDTO.class);
    }

    @Override
    public void excluirPagamento(Long id) {
      repository.deleteById(id);
    }

    @Override
    public void confirmarPagamento(Long id) {
        Optional<Pagamento> pagamento = repository.findById(id);

        if (pagamento.isEmpty()) {
            throw new EntityNotFoundException();
        }

        pagamento.get().setStatus(Status.CONFIRMADO);
        repository.save(pagamento.get());
        pedido.aprovaPagamento(pagamento.get().getPedidoId());
    }

    @Override
    public void alteraStatus(Long id) {
        Optional<Pagamento> pagamento = repository.findById(id);

        if (pagamento.isEmpty()) {
            throw new EntityNotFoundException();
        }

        pagamento.get().setStatus(Status.CONFIRMADO_SEM_INTEGRACAO);
        repository.save(pagamento.get());
    }
}
