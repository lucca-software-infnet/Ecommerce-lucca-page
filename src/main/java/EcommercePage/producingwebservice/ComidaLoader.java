package EcommercePage.producingwebservice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
    public void run(ApplicationArguments args) throws IOException {
        BufferedReader leitura = new BufferedReader(new FileReader("files/comidas.txt"));
        String linha;
        while ((linha = leitura.readLine()) != null) {
            String[] campos = linha.split(";");

            if (campos.length < 10) { 
                System.out.println("Linha incompleta: " + linha);
                continue;
            } 
            Comida comida = new Comida();
            comida.setNome(campos[0]);
            comida.setValor(Float.parseFloat(campos[1]));
            comida.setCodigo(Integer.parseInt(campos[2]));
            comida.setIngredientes(campos[3]);
            comida.setPeso(Float.parseFloat(campos[4]));
            comida.setVegana(Boolean.parseBoolean(campos[5]));
            comida.setImagem(campos[6]);
            comida.setDescricao(campos[7]);
            comida.setMarca(campos[8]);
            comida.setTipo(campos[9]);
            // comida.setSolicitante(new Solicitante(Integer.valueOf(campos[10])));
            comidaService.incluir(comida);
        }
        leitura.close();

        for (Comida comida : comidaService.obterLista()) {
            System.out.println("[COMIDA] " + comida);
        }
    }
}