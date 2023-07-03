package br.com.alurafood.avaliacao.avaliacao.controller;

import br.com.alurafood.avaliacao.avaliacao.dto.AvaliacaoDTO;
import br.com.alurafood.avaliacao.avaliacao.service.impl.AvaliacaoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/avaliacaoutils")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoServiceImpl service;
    private final RabbitTemplate rabbitTemplate;
    private static final  String QUEUENAME = "avaliacao-queue";


    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscar(@PathVariable Long id) {
      var avaliacao = service.buscar(id);
        return avaliacao.<ResponseEntity<Object>>map(avaliacaoDTO -> ResponseEntity.status(HttpStatus.OK)
                .body(avaliacaoDTO)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<Void> criarAvaliacao(@RequestBody @Valid AvaliacaoDTO dto) {
        this.rabbitTemplate.convertAndSend(QUEUENAME, dto);

        return ResponseEntity.noContent().build();
    }




}
