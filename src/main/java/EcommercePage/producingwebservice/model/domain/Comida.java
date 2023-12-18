package EcommercePage.producingwebservice.model.domain;



import javax.persistence.Entity;

@Entity
public class Comida extends Produto {

	private float peso;
	private boolean vegana;
	private String ingredientes;

	@Override
	public String toString() {
		
		return String.format("%s - peso (%.2f) - vegana (%s) - ingredientes (%s)", 
					super.toString(), peso, vegana, ingredientes
				);
	}

	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public boolean isVegana() {
		return vegana;
	}
	public void setVegana(boolean vegana) {
		this.vegana = vegana;
	}
	public String getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}
}