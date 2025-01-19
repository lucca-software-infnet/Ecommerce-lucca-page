package EcommercePage.producingwebservice.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EcommercePage.producingwebservice.model.domain.Bebida;
import EcommercePage.producingwebservice.model.repositories.BebidaRepository;

@Service
public class BebidaService {
	
	@Autowired
	private BebidaRepository bebidaRepository;
	
	public void incluir(Bebida bebida){
		bebidaRepository.save(bebida);
	}
	public Collection<Bebida> obterLista(){
		return (Collection<Bebida>) bebidaRepository.findAll();
	}
	public void excluir(Integer id){
		bebidaRepository.deleteById(id);
	}

}