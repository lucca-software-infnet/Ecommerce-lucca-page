package EcommercePage.producingwebservice.model.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import EcommercePage.producingwebservice.model.domain.Produto;
import EcommercePage.producingwebservice.model.service.ProdutoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    

    
    @GetMapping(value = "/listar")
    public List<Produto> obterLista() {
        return (List<Produto>) produtoService.obterLista();
    }

    @GetMapping(value = "/incluir")
    public void incluir(@RequestBody Produto produto) {
        produtoService.incluir(produto);
    }

    @GetMapping(value = "/{id}/excluir")
    public void excluir(@PathVariable Integer id) {
        produtoService.excluir(id);
    }

    @GetMapping(value = "/buscar")
    public List<Produto> buscarPorNome(@RequestParam String nome) {
        System.out.println("Buscando produtos com nome: " + nome);
        return produtoService.buscarPorNome(nome);
    }

    

}
