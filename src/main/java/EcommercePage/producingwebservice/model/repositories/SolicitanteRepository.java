package EcommercePage.producingwebservice.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import EcommercePage.producingwebservice.model.domain.Solicitante;
import java.util.Optional;

public interface SolicitanteRepository extends JpaRepository<Solicitante, Integer> {
    Optional<Solicitante> findByEmail(String email); // ✅ Adiciona o método de busca por email
}
