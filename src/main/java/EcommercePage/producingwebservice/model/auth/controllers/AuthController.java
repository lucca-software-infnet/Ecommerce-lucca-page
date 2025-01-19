package EcommercePage.producingwebservice.model.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EcommercePage.producingwebservice.model.auth.services.AuthorizationService;
import EcommercePage.producingwebservice.model.dtos.AuthenticationDto;
import EcommercePage.producingwebservice.model.dtos.RegisterDto;

import javax.validation.Valid;


@RestController
@RequestMapping("auth")
public class AuthController {
   
    @Autowired
    AuthorizationService authorizationService; 

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        return authorizationService.login(authenticationDto); 
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDto) {
        return authorizationService.register(registerDto); 
    }
}

