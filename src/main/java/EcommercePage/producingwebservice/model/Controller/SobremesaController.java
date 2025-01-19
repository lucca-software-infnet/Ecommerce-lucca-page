package EcommercePage.producingwebservice.model.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EcommercePage.producingwebservice.model.domain.Sobremesa;
import EcommercePage.producingwebservice.model.service.SobremesaService;

@RestController
@RequestMapping("/api/sobremesa")
public class SobremesaController {

    @Autowired
    private SobremesaService sobremesaService;

    @GetMapping(value = "/listar")
    public List<Sobremesa> obterLista() {
        return (List<Sobremesa>) sobremesaService.obterLista();
    }

    @GetMapping(value = "/incluir")
    public void incluir(@RequestBody Sobremesa sobremesa) {
        sobremesaService.incluir(sobremesa);
    }

    @GetMapping(value = "/{id}/excluir")
    public void excluir(@PathVariable Integer id) {
        sobremesaService.excluir(id);
    }

}
