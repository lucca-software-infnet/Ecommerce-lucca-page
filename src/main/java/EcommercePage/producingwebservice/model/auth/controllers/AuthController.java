package EcommercePage.producingwebservice.model.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EcommercePage.producingwebservice.model.auth.services.AuthorizationService;
import EcommercePage.producingwebservice.model.dtos.AuthenticationDto;
import EcommercePage.producingwebservice.model.dtos.LoginRequest;
import EcommercePage.producingwebservice.model.dtos.RegisterDto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;


// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     @Autowired
//     private AuthenticationManager authenticationManager;

//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//         try {
//             Authentication auth = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//             );
//             SecurityContextHolder.getContext().setAuthentication(auth);
//             return ResponseEntity.ok("Login successful");
//         } catch (AuthenticationException e) {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//         }
//     }
// }


@RestController
@RequestMapping("auth")
public class AuthController {
   
    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto authetinticationDto){
        return authorizationService.login(authetinticationDto);
    }


    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody RegisterDto registerDto){
        return authorizationService.register(registerDto);
    }
}
