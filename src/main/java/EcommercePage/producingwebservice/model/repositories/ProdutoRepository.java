package EcommercePage.producingwebservice.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


import EcommercePage.producingwebservice.model.domain.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
 
    
    List<Produto> findByNomeContainingIgnoreCase(String nome);


}