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
        BufferedReader leitura = new BufferedReader(new FileReader("files/bebidas.txt"));
        String linha;
        while ((linha = leitura.readLine()) != null) {
            String[] campos = linha.split(";");

            Bebida bebida = new Bebida();
            bebida.setNome(campos[0]);
            bebida.setValor(Float.parseFloat(campos[1]));
            bebida.setCodigo(Integer.parseInt(campos[2]));
            bebida.setGelada(Boolean.parseBoolean(campos[3]));
            bebida.setMarca(campos[4]);
            bebida.setPeso(Float.parseFloat(campos[5]));
            bebida.setDescricao(campos[6]);
            bebida.setImagem(campos[7]); 

            bebidaService.incluir(bebida);
            
        }
        leitura.close();
        
        for (Bebida bebida : bebidaService.obterLista()) {
            System.out.println("[BEBIDA] " + bebida);
        }
    }
}