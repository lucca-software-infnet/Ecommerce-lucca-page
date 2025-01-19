package EcommercePage.producingwebservice.model.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import EcommercePage.producingwebservice.model.domain.Sobremesa;

@Repository
public interface SobremesaRepository extends CrudRepository<Sobremesa, Integer> {

}
