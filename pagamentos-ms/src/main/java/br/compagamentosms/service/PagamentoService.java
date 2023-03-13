package br.compagamentosms.service;

import br.compagamentosms.dto.PagamentoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagamentoService {

    Page<PagamentoDTO> obterTodos(Pageable paginacao);

    PagamentoDTO obterPorId(Long id);

    PagamentoDTO criarPagamento(PagamentoDTO dto);

    PagamentoDTO atualizarPagamento(Long id, PagamentoDTO dto);


    void excluirPagamento(Long id);

    void confirmarPagamento(Long id);


    void alteraStatus(Long id);
}
