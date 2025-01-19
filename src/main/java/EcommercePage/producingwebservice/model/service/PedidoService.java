package EcommercePage.producingwebservice.model.service;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EcommercePage.producingwebservice.model.domain.Pedido;
import EcommercePage.producingwebservice.model.repositories.PedidoRepository;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public void incluir(Pedido pedido){
		pedidoRepository.save(pedido);
	}
	
	public Collection<Pedido> obterLista(){		
		return (Collection<Pedido>) pedidoRepository.findAll();
	}
	public void excluir(Integer id){
		pedidoRepository.deleteById(id);
	}
}