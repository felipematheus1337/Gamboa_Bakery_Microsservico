package br.com.alurafood.avaliacao.avaliacao.controller;

import br.com.alurafood.avaliacao.avaliacao.dto.ResponseDTO;
import br.com.alurafood.avaliacao.avaliacao.model.Avaliacao;
import br.com.alurafood.avaliacao.avaliacao.service.impl.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/business")
@RestController
@RequiredArgsConstructor
public class BusinessController {


    private final BusinessService service;


    @PostMapping
    public ResponseEntity<ResponseDTO> criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarAvalicao(avaliacao));
    }
}
