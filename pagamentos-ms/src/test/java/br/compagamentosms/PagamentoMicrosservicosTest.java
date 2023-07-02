package br.compagamentosms;


import br.compagamentosms.dto.PagamentoDTO;
import br.compagamentosms.http.PedidoClient;
import br.compagamentosms.model.Pagamento;
import br.compagamentosms.model.Status;
import br.compagamentosms.repository.PagamentoRepository;
import br.compagamentosms.service.impl.PagamentoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PagamentoMicrosservicosTest {

    private static final Long ID = 1L;
    private static final BigDecimal valor = new BigDecimal(200.0);
    private static final String nome = "Felipe";

    private static final String numero = "302150121";

    private static final String expiracao = "08/2024";

    private static final String codigo = "422";

    private static final Status status = Status.CRIADO;

    private static final String pedidoId = "1L";

    private static final Long formaDePagamentoId = 1L;

    @InjectMocks
    private PagamentoServiceImpl service;

    @Mock
    private PagamentoRepository repository;

    @Mock
    private PedidoClient client;

    private ModelMapper mapper;

    private Pagamento pagamento;

    private PagamentoDTO pagamentoDTO;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapper = new ModelMapper();
        service = new PagamentoServiceImpl(repository, mapper, client);
        startPagamento();
    }

    @Test
    void quandoCriarUmPagamentoRetornarSucesso() {
        when(repository.save(any())).thenReturn(pagamento);

        PagamentoDTO resposta = service.criarPagamento(pagamentoDTO);

        assertNotNull(resposta);
        assertEquals(resposta.getClass(), PagamentoDTO.class);
        assertEquals(resposta.getStatus(), pagamentoDTO.getStatus());
    }

    private void startPagamento() {
        pagamento = new Pagamento();
        pagamento.setFormaDePagamentoId(formaDePagamentoId);
        pagamento.setId(ID);
        pagamento.setStatus(status);
        pagamento.setNome(nome);
        pagamento.setExpiracao(expiracao);
        pagamento.setNumero(numero);
        pagamento.setCodigo(codigo);
        pagamento.setValor(valor);
        pagamento.setPedidoId(pedidoId);
        pagamentoDTO = mapper.map(pagamento, PagamentoDTO.class);
    }
}
