package EcommercePage.producingwebservice.model.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import EcommercePage.producingwebservice.model.domain.Endereco;

@FeignClient(url ="https://viacep.com.br/ws", name = "enderecoClient")
public interface IEnderecoClient {
    @GetMapping(value = "/{cep}/json/")
    public Endereco buscaCep(@PathVariable String cep);
}
