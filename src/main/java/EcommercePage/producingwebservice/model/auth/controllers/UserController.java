package EcommercePage.producingwebservice.model.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EcommercePage.producingwebservice.model.domain.Solicitante;
import EcommercePage.producingwebservice.model.service.SolicitanteService;

import java.util.Collection;

@RequestMapping("/users")
@RestController
public class UserController {
    private final SolicitanteService solicitanteService;

    public UserController(SolicitanteService solicitanteService) {
        this.solicitanteService = solicitanteService;
    }

    @GetMapping("/me")
    public ResponseEntity<Solicitante> authenticatedUser () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Solicitante currentUser  = (Solicitante) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser );
    }

    @GetMapping("/")
    public ResponseEntity<Collection<Solicitante>> allUsers() {
        Collection<Solicitante> users = solicitanteService.obterLista(); // Chama o método que retorna Collection
        return ResponseEntity.ok(users); // Retorna a coleção
    }
}