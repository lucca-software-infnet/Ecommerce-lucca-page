package EcommercePage.producingwebservice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import EcommercePage.producingwebservice.model.domain.Bebida;
import EcommercePage.producingwebservice.model.domain.Comida;
import EcommercePage.producingwebservice.model.domain.Pedido;
import EcommercePage.producingwebservice.model.domain.Produto;
import EcommercePage.producingwebservice.model.domain.Sobremesa;
import EcommercePage.producingwebservice.model.domain.Solicitante;
import EcommercePage.producingwebservice.model.service.PedidoService;

@Order(5)
@Component
public class PedidoLoader implements ApplicationRunner {

    @Autowired
    private PedidoService pedidoService;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
    
        FileReader file = new FileReader("files/pedidos2.txt");
        BufferedReader leitura = new BufferedReader(file);
        
        String linha = leitura.readLine();
        
        String[] campos = null;
        
        Pedido pedido = null;
        
        while(linha != null) {
            campos = linha.split(";");
            
            switch (campos[0]) {
            case "P":
                                
                pedido = new Pedido();
                pedido.setDescricao(campos[1]);
                pedido.setWeb(Boolean.valueOf(campos[2]));
                pedido.setData(LocalDateTime.now());
                pedido.setSolicitante(new Solicitante(Integer.valueOf(campos[3])));
                pedido.setProdutos(new ArrayList<Produto>());
                                
                break;
    
            case "B":
                Bebida bebida = new Bebida();
                bebida.setId(Integer.valueOf(campos[1]));
    
                pedido.getProdutos().add(bebida);
                
                break;
                
            case "C":
                Comida comida = new Comida();
                comida.setId(Integer.valueOf(campos[1]));
    
                pedido.getProdutos().add(comida);
                
                break;
    
            case "S":
                Sobremesa sobremesa = new Sobremesa();
                sobremesa.setId(Integer.valueOf(campos[1]));
                
                pedido.getProdutos().add(sobremesa);
                
                break;
    
            default:
                break;
            }

            pedidoService.incluir(pedido);
            linha = leitura.readLine();
        }
        for(Pedido p : pedidoService.obterLista()) {
            System.out.println("[PEDIDO] " + p);			
        } 
        leitura.close();
    }
}