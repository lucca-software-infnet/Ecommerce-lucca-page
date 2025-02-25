package EcommercePage.producingwebservice;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import EcommercePage.producingwebservice.model.domain.Sobremesa;
import EcommercePage.producingwebservice.model.service.SobremesaService;

@Order(4)
@Component
public class SobremesaLoader implements ApplicationRunner {
	
	@Autowired
	private SobremesaService sobremesaService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		FileReader file = new FileReader("files/sobremesas.txt");
		BufferedReader leitura = new BufferedReader(file);
		
		String linha = leitura.readLine();
		
		String[] campos = null;
		
	 while(linha != null) {
		 campos = linha.split(";");

		 // Verifica se a linha contém o número esperado de campos (8 campos)
		 if (campos.length < 8) {
			 System.err.println("Linha inválida: " + linha);
			 linha = leitura.readLine();
			 continue; // Pula para a próxima linha
		 }

		 Sobremesa sobremesa = new Sobremesa();
		 sobremesa.setNome(campos[0]);
		 sobremesa.setValor(Float.valueOf(campos[1]));
		 sobremesa.setCodigo(Integer.valueOf(campos[2]));
		 sobremesa.setQuantidade(Integer.valueOf(campos[3]));
		 sobremesa.setDoce(Boolean.valueOf(campos[4]));
		 sobremesa.setInformacao(campos[5]);
		 sobremesa.setDescricao(campos[6]);
		 sobremesa.setImagem(campos[7]);
		 
		 sobremesaService.incluir(sobremesa);
		 
		 linha = leitura.readLine();
	 }

	 for(Sobremesa sobremesa : sobremesaService.obterLista()) {
		 System.out.println("[SOBREMESA] " + sobremesa);			
	 }
			 
	 leitura.close();
	}
}