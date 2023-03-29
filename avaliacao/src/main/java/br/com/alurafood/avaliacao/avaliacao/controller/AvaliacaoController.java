package br.com.alurafood.avaliacao.avaliacao.controller;

import br.com.alurafood.avaliacao.avaliacao.dto.AvaliacaoDTO;
import br.com.alurafood.avaliacao.avaliacao.service.impl.AvaliacaoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/avaliacaoutils")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoServiceImpl service;


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

    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
