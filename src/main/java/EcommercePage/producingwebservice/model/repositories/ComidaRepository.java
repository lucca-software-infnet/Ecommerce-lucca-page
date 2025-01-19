package EcommercePage.producingwebservice.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import EcommercePage.producingwebservice.model.domain.Comida;

@Repository
public interface ComidaRepository extends CrudRepository<Comida, Integer> {

}
