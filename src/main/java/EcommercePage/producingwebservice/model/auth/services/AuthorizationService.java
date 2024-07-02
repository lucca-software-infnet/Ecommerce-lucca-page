package EcommercePage.producingwebservice.model.auth.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import EcommercePage.producingwebservice.model.domain.Solicitante;
import EcommercePage.producingwebservice.model.dtos.AuthenticationDto;
import EcommercePage.producingwebservice.model.dtos.LoginResponseDto;
import EcommercePage.producingwebservice.model.dtos.RegisterDto;
import EcommercePage.producingwebservice.model.repositories.UserRepository;
import EcommercePage.producingwebservice.model.security.TokenService;

import javax.validation.Valid;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private ApplicationContext context; // Contexto da aplicação para obter o gerenciador de autenticação

    @Autowired
    private UserRepository userRepository; // Repositório para operações relacionadas aos usuários

    @Autowired
    private TokenService tokenService; // Serviço para geração de tokens de autenticação

    private AuthenticationManager authenticationManager; // Gerenciador de autenticação

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email); // Busca um usuário pelo email no repositório
    }

    // Método para lidar com a requisição de login
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto data) {
        authenticationManager = context.getBean(AuthenticationManager.class); // Obtém o gerenciador de autenticação

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword); // Autentica o usuário com base nas credenciais
        var token = tokenService.generateToken((Solicitante) auth.getPrincipal()); // Gera um token JWT para o usuário autenticado
        return ResponseEntity.ok(new LoginResponseDto(token)); // Retorna uma resposta com o token de login
    }

    // Método para lidar com a requisição de registro de um novo usuário
    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDto) {
        // Verifica se o usuário já existe pelo email
        if (this.userRepository.findByEmail(registerDto.email()) != null) {
            return ResponseEntity.badRequest().build(); // Retorna bad request se o usuário já existir
        }

        // Não codifica novamente se a senha já estiver codificada
        String encryptedPassword = registerDto.password();

        // Cria um novo usuário com os dados fornecidos
        Solicitante newUser = new Solicitante(registerDto.email(), encryptedPassword, registerDto.role());
        newUser.setCreatedAt(new Date(System.currentTimeMillis()));

        // Salva o novo usuário no repositório
        this.userRepository.save(newUser);

        // Retorna uma resposta de sucesso
        return ResponseEntity.ok().build();
    }
    


    // public ResponseEntity<Object> register (@RequestBody RegisterDto registerDto){
    //     if (this.userRepository.findByEmail(registerDto.email()) != null ) return ResponseEntity.badRequest().build();
    //     String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        
    //     Solicitante newUser = new Solicitante(registerDto.email(), encryptedPassword, registerDto.role());
    //     newUser.setCreatedAt(new Date(System.currentTimeMillis()));
    //     this.userRepository.save(newUser);
    //     return ResponseEntity.ok().build();
    // }

}
