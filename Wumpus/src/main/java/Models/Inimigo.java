package Models;

public abstract class Inimigo {

    private String nome;

    public Inimigo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
