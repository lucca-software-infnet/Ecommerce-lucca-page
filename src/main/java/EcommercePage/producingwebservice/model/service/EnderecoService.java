package EcommercePage.producingwebservice.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EcommercePage.producingwebservice.model.Clients.IEnderecoClient;
import EcommercePage.producingwebservice.model.domain.Endereco;

@Service
public class EnderecoService {
    @Autowired
    private IEnderecoClient enderecoClient;

    public Endereco buscaCep( String cep){
        return enderecoClient.buscaCep(cep);
    }
}
