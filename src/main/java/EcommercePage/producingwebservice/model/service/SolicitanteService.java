package EcommercePage.producingwebservice.model.service;


import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EcommercePage.producingwebservice.model.domain.Solicitante;
import EcommercePage.producingwebservice.model.repositories.SolicitanteRepository;


@Service
public class SolicitanteService {
	
	@Autowired
	private SolicitanteRepository solicitanteRepository;

	
	public void incluir(Solicitante solicitante){
		solicitanteRepository.save(solicitante);
	}
	
	public Collection<Solicitante> obterLista(){		
		return (Collection<Solicitante>) solicitanteRepository.findAll();
	}

	public void excluir(Integer id){
		solicitanteRepository.deleteById(id);
	}

	public Solicitante obterPorId(int id) {
		return solicitanteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Solicitante não encontrado"));
	}
	

}