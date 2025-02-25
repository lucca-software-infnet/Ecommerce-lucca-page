package EcommercePage.producingwebservice.model.domain;

import javax.persistence.Entity;

@Entity
public class Sobremesa extends Produto {

	private int quantidade;
	private boolean doce;
	private String informacao;
	private String descricao;
	private String imagem;


	@Override
	public String toString() {
		
		return String.format("%s - quantidade (%d) - doce (%s) - informacao (%s)- imagem (%s)" , 
					super.toString(), quantidade, doce, informacao, descricao,imagem
				);
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public boolean isDoce() {
		return doce;
	}
	public void setDoce(boolean doce) {
		this.doce = doce;
	}
	public String getInformacao() {
		return informacao;
	}
	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
}
