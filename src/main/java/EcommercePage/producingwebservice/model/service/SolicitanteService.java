package EcommercePage.producingwebservice.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EcommercePage.producingwebservice.model.domain.Solicitante;
import EcommercePage.producingwebservice.model.repositories.SolicitanteRepository;
import EcommercePage.producingwebservice.model.repositories.UserRepository;

@Service
public class SolicitanteService {
	
	@Autowired
	private SolicitanteRepository solicitanteRepository;

	private final UserRepository userRepository;
	
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

	public SolicitanteService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}