package EcommercePage.producingwebservice.model.domain;

import javax.persistence.Entity;

@Entity
public class Bebida extends Produto {

    private String nome;
    private boolean gelada;
    private String marca;
    private float valor;
    private String codigoDeBarras;
    private float peso;
    
    

   

    // Construtor padrão
    public Bebida() {
    }

    // Construtor com todos os atributos
    public Bebida(String nome, boolean gelada, String marca, float valor, String codigoDeBarras, float peso, Solicitante solicitante) {
        this.nome = nome;
        this.gelada = gelada;
        this.marca = marca;
        this.valor = valor;
        this.codigoDeBarras = codigoDeBarras;
        this.peso = peso;
    }

    // Método toString
    @Override
    public String toString() {
        return String.format(
                "Bebida [ nome (%s) - valor(%.2f) - codigoDeBarras(%s) - gelada(%s) - marca(%s) - peso(%.2f)  ]",
                nome, valor, codigoDeBarras, gelada, marca, peso, super.toString());
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isGelada() {
        return gelada;
    }

    public void setGelada(boolean gelada) {
        this.gelada = gelada;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
   

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Bebida bebida = (Bebida) o;

        if (gelada != bebida.gelada)
            return false;
        if (Float.compare(bebida.valor, valor) != 0)
            return false;
        if (Float.compare(bebida.peso, peso) != 0)
            return false;
        if (nome != null ? !nome.equals(bebida.nome) : bebida.nome != null)
            return false;
        if (marca != null ? !marca.equals(bebida.marca) : bebida.marca != null)
            return false;
        return codigoDeBarras != null ? codigoDeBarras.equals(bebida.codigoDeBarras) : bebida.codigoDeBarras == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (gelada ? 1 : 0);
        result = 31 * result + (marca != null ? marca.hashCode() : 0);
        result = 31 * result + (Float.floatToIntBits(valor));
        result = 31 * result + (codigoDeBarras != null ? codigoDeBarras.hashCode() : 0);
        result = 31 * result + (Float.floatToIntBits(peso));
        return result;
    }

}

// @Entity
// public class Bebida extends Produto {

// private String nome;
// private boolean gelada;
// private float tamanho;
// private String marca;
// private float valor;
// private Integer codigoDeBarras;

// @Override
// public String toString() {

// return String.format("%s - gelada (%s) - tamanho (%.2f) - marca (%s) )",
// super.toString(), gelada, tamanho, marca
// );
// }

// public boolean isGelada() {
// return gelada;
// }
// public void setGelada(boolean gelada) {
// this.gelada = gelada;
// }
// public float getTamanho() {
// return tamanho;
// }
// public void setTamanho(float tamanho) {
// this.tamanho = tamanho;
// }
// public String getMarca() {
// return marca;
// }
// public void setMarca(String marca) {
// this.marca = marca;
// }

// }