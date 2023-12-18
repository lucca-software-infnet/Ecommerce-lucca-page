package EcommercePage.producingwebservice;



import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import EcommercePage.producingwebservice.model.domain.Comida;
import EcommercePage.producingwebservice.model.service.ComidaService;

@Order(3)
@Component
public class ComidaLoader implements ApplicationRunner {

	@Autowired
	private ComidaService comidaService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		FileReader file = new FileReader("files/comidas.txt");
		BufferedReader leitura = new BufferedReader(file);
		
		String linha = leitura.readLine();
		
		String[] campos = null;
		
		while(linha != null) {
			campos = linha.split(";");

			Comida comida = new Comida();
			comida.setNome(campos[0]);
			comida.setValor(Float.valueOf(campos[1]));
			comida.setCodigo(Integer.valueOf(campos[2]));
			comida.setIngredientes(campos[3]);
			comida.setPeso(Float.valueOf(campos[4]));
			comida.setVegana(Boolean.valueOf(campos[5]));
			
			comidaService.incluir(comida);
			
			linha = leitura.readLine();
		}

		for(Comida comida : comidaService.obterLista()) {
			System.out.println("[COMIDA] " + comida);			
		}
				
		leitura.close();
	}

}
