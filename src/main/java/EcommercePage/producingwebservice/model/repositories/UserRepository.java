package EcommercePage.producingwebservice.model.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import EcommercePage.producingwebservice.model.domain.Solicitante;


public interface UserRepository extends JpaRepository<Solicitante, Integer>{
    UserDetails findByEmail(String email);
}
