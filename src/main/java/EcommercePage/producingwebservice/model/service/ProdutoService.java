package EcommercePage.producingwebservice.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EcommercePage.producingwebservice.model.domain.Produto;
import EcommercePage.producingwebservice.model.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void incluir(Produto produto){
		produtoRepository.save(produto);
	}
	public Collection<Produto> obterLista(){
		return (Collection<Produto>) produtoRepository.findAll();
	}
	public void excluir(Integer id){
		produtoRepository.deleteById(id);
	}
	
}
