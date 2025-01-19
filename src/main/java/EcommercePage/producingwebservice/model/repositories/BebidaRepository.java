package EcommercePage.producingwebservice.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import EcommercePage.producingwebservice.model.domain.Bebida;

@Repository
public interface BebidaRepository extends CrudRepository<Bebida, Integer> {

}
