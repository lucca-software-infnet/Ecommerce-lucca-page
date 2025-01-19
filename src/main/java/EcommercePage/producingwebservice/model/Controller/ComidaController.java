package EcommercePage.producingwebservice.model.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EcommercePage.producingwebservice.model.domain.Comida;
import EcommercePage.producingwebservice.model.service.ComidaService;

@RestController
@RequestMapping("/api/comida")
public class ComidaController {

    @Autowired
    private ComidaService comidaService;

    @GetMapping(value = "/listar")
    public List<Comida> obterLista() {
        return (List<Comida>) comidaService.obterLista();
    }

    @GetMapping(value = "/incluir")
    public void incluir(@RequestBody Comida comida) {
        comidaService.incluir(comida);
    }

    @GetMapping(value = "/{id}/excluir")
    public void excluir(@PathVariable Integer id) {
        comidaService.excluir(id);
    }

}

