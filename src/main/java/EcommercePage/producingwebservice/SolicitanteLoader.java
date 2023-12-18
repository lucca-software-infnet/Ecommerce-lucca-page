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
import EcommercePage.producingwebservice.model.service.EnderecoService;
import EcommercePage.producingwebservice.model.service.SolicitanteService;



@Order(1)
@Component
public class SolicitanteLoader implements ApplicationRunner {
	
	@Autowired
	private SolicitanteService solicitanteService;
	@Autowired
	private EnderecoService enderecoService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		FileReader file = new FileReader("files/solicitantes.txt");
		BufferedReader leitura = new BufferedReader(file);
		
		String linha = leitura.readLine();
		
		String[] campos = null;
		
		while(linha != null) {
			campos = linha.split(";");

			String cep = campos[3];
			Endereco endereco = enderecoService.buscaCep(cep);

			Solicitante solicitante = new Solicitante();
			solicitante.setNome(campos[0]);
			solicitante.setCpf(campos[1]);
			solicitante.setEmail(campos[2]);
			solicitante.setEndereco(endereco);
			
			
			solicitanteService.incluir(solicitante);
			
			linha = leitura.readLine();
		}

		for(Solicitante solicitante : solicitanteService.obterLista()) {
			System.out.println("[SOLICITANTE] " + solicitante);			
		}
				
		leitura.close();
	}
}