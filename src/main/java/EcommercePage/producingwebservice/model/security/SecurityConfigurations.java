package EcommercePage.producingwebservice.model.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    // Configuração do filtro de segurança e das permissões das requisições
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Define política de criação de sessão
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/auth/login").permitAll() // Permite acesso sem autenticação ao endpoint /auth/login
                        .antMatchers(HttpMethod.POST, "/auth/register").hasRole("ADMIN") // Requer papel ADMIN para acessar /auth/register
                        .antMatchers("/user/**").hasRole("USER") // Requer papel USER para acessar qualquer endpoint iniciado com /user/
                        .anyRequest().permitAll()) // Permite acesso a qualquer outra requisição
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro de segurança antes do filtro padrão de autenticação por usuário e senha
                .build();
    }

    // Configuração do gerenciador de autenticação
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Configuração do codificador de senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

