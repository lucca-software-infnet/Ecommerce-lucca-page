package EcommercePage.producingwebservice;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import EcommercePage.producingwebservice.model.domain.Endereco;
import EcommercePage.producingwebservice.model.domain.Solicitante;
import EcommercePage.producingwebservice.model.enums.UserRole;
import EcommercePage.producingwebservice.model.service.EnderecoService;
import EcommercePage.producingwebservice.model.service.SolicitanteService;

@Component
@Order(1)
public class SolicitanteLoader implements ApplicationRunner {

    @Autowired
    private SolicitanteService solicitanteService;

    @Autowired
    private EnderecoService enderecoService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        BufferedReader leitura = null;
        
            leitura = new BufferedReader(new FileReader("files/solicitantes.txt"));
            String linha;
            Solicitante solicitante = null;

            while ((linha = leitura.readLine()) != null) {
                String[] campos = linha.split(";");
                
                
                        if (solicitante != null) {
                            solicitanteService.incluir(solicitante);
                        }
                        solicitante = new Solicitante();
                        solicitante.setNome(campos[0]);
                        solicitante.setCpf(campos[1]);
                        solicitante.setEmail(campos[2]);

                        String cep = campos[3];
                        Endereco endereco = enderecoService.buscaCep(cep);
                        solicitante.setEndereco(endereco);
                        solicitante.setPassword(campos[4]);
                        if ("ADMIN".equalsIgnoreCase(campos[5])) {
                            solicitante.setUserRole(UserRole.ADMIN);
                        } else {
                            solicitante.setUserRole(UserRole.USER);
                        }      
                }
            
            if (solicitante != null) {
                solicitanteService.incluir(solicitante);
            }

            for (Solicitante s : solicitanteService.obterLista()) {
                System.out.println("[SOLICITANTE] " + s);
            }
        
            if (leitura != null) {
                leitura.close();
            }
        
    }
}



