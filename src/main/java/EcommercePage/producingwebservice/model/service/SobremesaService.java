package EcommercePage.producingwebservice.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EcommercePage.producingwebservice.model.domain.Sobremesa;
import EcommercePage.producingwebservice.model.repositories.SobremesaRepository;

@Service
public class SobremesaService {
	
	@Autowired
	private SobremesaRepository sobremesaRepository;

	public void incluir(Sobremesa sobremesa) {

		sobremesaRepository.save(sobremesa);
	}

	public Collection<Sobremesa> obterLista() {

		return (Collection<Sobremesa>) sobremesaRepository.findAll();
	}

	public void excluir(Integer id){
		sobremesaRepository.deleteById(id);
	}
}