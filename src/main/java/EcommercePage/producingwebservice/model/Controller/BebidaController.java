package EcommercePage.producingwebservice.model.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EcommercePage.producingwebservice.model.domain.Bebida;
import EcommercePage.producingwebservice.model.service.BebidaService;

@RestController
@RequestMapping("/api/bebida")
public class BebidaController {

    @Autowired
    private BebidaService bebidaService;

    @GetMapping(value = "/listar")
    public List<Bebida> obterLista() {
        return (List<Bebida>) bebidaService.obterLista();
    }

    @GetMapping(value = "/incluir")
    public void incluir(@RequestBody Bebida bebida) {
        bebidaService.incluir(bebida);
    }

    @GetMapping(value = "/{id}/excluir")
    public void excluir(@PathVariable Integer id) {
        bebidaService.excluir(id);
    }

}

