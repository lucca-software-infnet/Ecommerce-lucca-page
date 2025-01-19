package EcommercePage.producingwebservice.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import EcommercePage.producingwebservice.model.domain.Solicitante;

@Repository
public interface SolicitanteRepository extends CrudRepository<Solicitante, Integer> {

}


