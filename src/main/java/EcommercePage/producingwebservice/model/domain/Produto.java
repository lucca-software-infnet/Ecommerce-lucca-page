package EcommercePage.producingwebservice.model.domain;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo( 		
		use = JsonTypeInfo.Id.NAME, 		
		include = JsonTypeInfo.As.PROPERTY, 		
		property = "tipo")
@JsonSubTypes({ 	
	@JsonSubTypes.Type(value = Bebida.class, name = "Bebida"), 	
	@JsonSubTypes.Type(value = Comida.class, name = "Comida"), 	
	@JsonSubTypes.Type(value = Sobremesa.class, name = "Sobremesa") 
})
public abstract class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private float valor;
	private int codigo;

	@ManyToMany(mappedBy = "produtos")
	@JsonBackReference
	private List<Pedido> pedidos;
	
	@Override
	public String toString() {
		
		return String.format("nome (%s) - valor (%.2f) - codigo (%d)", 
					nome, valor, codigo
				);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}