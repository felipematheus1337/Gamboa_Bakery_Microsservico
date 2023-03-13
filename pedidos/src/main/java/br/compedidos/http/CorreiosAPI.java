package br.compedidos.http;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "correios-api", url = "https://viacep.com.br/ws")
public interface CorreiosAPI {

    @GetMapping("/{cep}/json")
    EnderecoHttp consultarCep(@PathVariable String cep);
}
