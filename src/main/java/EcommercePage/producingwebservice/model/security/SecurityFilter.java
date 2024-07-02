package EcommercePage.producingwebservice.model.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import EcommercePage.producingwebservice.model.repositories.UserRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    // Método executado para cada requisição HTTP
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        System.out.println("Token Recuperado: " + token); // Log do token recuperado

        if (token != null) {
            try {
                var email = tokenService.validateToken(token);
                System.out.println("Email do Token: " + email); // Log do email extraído
                UserDetails user = userRepository.findByEmail(email);
                System.out.println("Usuário encontrado: " + user); // Log do usuário encontrado
                
                if (user != null) {
                    // Define a autenticação para o usuário encontrado
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    System.out.println("Nenhum usuário encontrado para o email: " + email);
                }
            } catch (Exception e) {
                System.out.println("Erro na validação do token: " + e.getMessage()); // Log de erro na validação do token
            }
        } else {
            System.out.println("Nenhum token encontrado no request."); // Log quando nenhum token é encontrado
        }
        filterChain.doFilter(request, response);
    }

    // Método para recuperar o token do cabeçalho de autorização da requisição
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        System.out.println("Cabeçalho de Autorização: " + authHeader); // Log do cabeçalho de autorização
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.replace("Bearer ", "");
    }
}


// @Component
// public class SecurityFilter extends OncePerRequestFilter {

//     @Autowired 
//     TokenService tokenService;

//     @Autowired
//     UserRepository userRepository;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         var token = this.recoverToken(request);

//         if (token != null) {
//             var email = tokenService.validateToken(token);
//             System.out.println("Token validado. Email: " + email);
//             UserDetails user = userRepository.findByEmail(email);

//             if (user != null) {
//                 System.out.println("Usuário encontrado: " + user.getUsername());
//                 var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//                 SecurityContextHolder.getContext().setAuthentication(authentication);
//             } else {
//                 System.out.println("Nenhum usuário encontrado para o email: " + email);
//             }
//         } else {
//             System.out.println("Nenhum token encontrado no request.");
//         }
//         filterChain.doFilter(request, response);
//     }

//     private String recoverToken(HttpServletRequest request) {
//         var authHeader = request.getHeader("Authorization");
//         if (authHeader == null) return null;
//         return authHeader.replace("Bearer ", "");
//     }
// }


// @Component
// public class SecurityFilter extends OncePerRequestFilter{
//     @Autowired 
//     TokenService tokenService;

//     @Autowired
//     UserRepository userRepository;
//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         var token = this.recoverToken(request);
    
//         if (token != null) {
//             var email = tokenService.validateToken(token);
//             UserDetails user = userRepository.findByEmail(email);
    
//             if (user != null) {
//                 var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//                 SecurityContextHolder.getContext().setAuthentication(authentication);
//             }
//         }
//         filterChain.doFilter(request, response);
//     }
    



//     private String recoverToken(HttpServletRequest request){
//         var authHeader = request.getHeader("Authorization");
//         if (authHeader == null) return null;
//         return authHeader.replace("Bearer ", "");
//     }
// }
