package EcommercePage.producingwebservice.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Comida extends Produto {

    private String imagem;
    private String nome;
    private float peso;
    private boolean vegana;
    private String ingredientes;
    private String descricao;
    private String marca;
    private String tipo;
    private float valor;
    
    
    
     @OneToOne(cascade = CascadeType.DETACH)
	 @JoinColumn(name = "idSolicitante")
	 private Solicitante solicitante;


   

    public Comida() {
    }

    public Comida(String imagem, String nome, float peso, boolean vegana, String ingredientes, String descricao,
            String marca, String tipo, float valor, Solicitante solicitante) {
        this.imagem = imagem;
        this.nome = nome;
        this.peso = peso;
        this.vegana = vegana;
        this.ingredientes = ingredientes;
        this.descricao = descricao;
        this.marca = marca;
        this.tipo = tipo;
        this.valor = valor;
        this.solicitante = solicitante;
        
    }

    @Override
    public String toString() {
        return String.format(
                "Comida [  nome (%s) - valor(%.2f)   - ingredientes(%s) - peso (%.2f) - vegana(%s) - imagem (%s) - descricao (%s) - marca (%s) - tipo (%s) - solicitante (%s) ]",
                nome, valor, ingredientes, peso, vegana, imagem, descricao, marca, tipo,solicitante,
                super.toString());
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }


    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Comida comida = (Comida) o;

        if (Float.compare(comida.peso, peso) != 0)
            return false;
        if (vegana != comida.vegana)
            return false;
        if (imagem != null ? !imagem.equals(comida.imagem) : comida.imagem != null)
            return false;
        if (nome != null ? !nome.equals(comida.nome) : comida.nome != null)
            return false;
        if (ingredientes != null ? !ingredientes.equals(comida.ingredientes) : comida.ingredientes != null)
            return false;
        if (descricao != null ? !descricao.equals(comida.descricao) : comida.descricao != null)
            return false;
        if (marca != null ? !marca.equals(comida.marca) : comida.marca != null)
            return false;
        return tipo != null ? tipo.equals(comida.tipo) : comida.tipo == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (imagem != null ? imagem.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (peso != +0.0f ? Float.floatToIntBits(peso) : 0);
        result = 31 * result + (vegana ? 1 : 0);
        result = 31 * result + (ingredientes != null ? ingredientes.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (marca != null ? marca.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        return result;
    }
}

