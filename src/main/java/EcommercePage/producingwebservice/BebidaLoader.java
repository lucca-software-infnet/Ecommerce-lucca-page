package EcommercePage.producingwebservice;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import EcommercePage.producingwebservice.model.domain.Bebida;
import EcommercePage.producingwebservice.model.service.BebidaService;

@Order(2)
@Component
public class BebidaLoader implements ApplicationRunner {

    @Autowired
    private BebidaService bebidaService;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        // Leitura do arquivo sem try-catch
        BufferedReader leitura = new BufferedReader(new FileReader("files/bebidas.txt"));
        String linha;
        while ((linha = leitura.readLine()) != null) {
            String[] campos = linha.split(";");

            
            Bebida bebida = new Bebida();
            bebida.setNome(campos[0]);
            bebida.setValor(Float.parseFloat(campos[1]));
            bebida.setCodigoDeBarras(campos[2]);
            bebida.setGelada(Boolean.parseBoolean(campos[3]));
            bebida.setMarca(campos[4]);
            bebida.setPeso(Float.parseFloat(campos[5]));

            bebidaService.incluir(bebida);
            
        }
        leitura.close();
        
        for (Bebida bebida : bebidaService.obterLista()) {
            System.out.println("[BEBIDA] " + bebida);
        }
    }
}


// @Order(2)
// @Component
// public class BebidaLoader implements ApplicationRunner {

// 	@Autowired
// 	private BebidaService bebidaService;
	
// 	@Override
// 	public void run(ApplicationArguments args) throws Exception {

// 		FileReader file = new FileReader("files/bebidas.txt");
// 		BufferedReader leitura = new BufferedReader(file);
		
// 		String linha = leitura.readLine();
		
// 		String[] campos = null;
		
// 		while(linha != null) {
// 			campos = linha.split(";");
// 			// Vendedor vendedor = new Vendedor();
// 			// vendedor.setId(Integer.valueOf(campos[6]));

// 			Bebida bebida = new Bebida();
// 			bebida.setNome(campos[0]);
// 			bebida.setValor(Float.valueOf(campos[1]));
// 			bebida.setCodigo(Integer.valueOf(campos[2]));
// 			bebida.setGelada(Boolean.valueOf(campos[3]));
// 			bebida.setTamanho(Float.valueOf(campos[4]));
// 			bebida.setMarca(campos[5]);
// 			// bebida.setVendedor(vendedor);

// 			bebidaService.incluir(bebida);
			
// 			linha = leitura.readLine();
// 		}

// 		for(Bebida bebida : bebidaService.obterLista()) {
// 			System.out.println("[BEBIDA] " + bebida);			
// 		}
				
// 		leitura.close();
// 	}

// }
