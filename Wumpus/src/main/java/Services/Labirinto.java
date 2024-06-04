package Services;

import Models.Caverna;

public class Labirinto {

    public Caverna criarNovoNo() {
        Caverna novoNo = new Caverna();
        return novoNo;
    }

    public void criarSubArvore(Caverna atual, Caverna oeste, Caverna leste, Caverna norte, Caverna sul) {
        atual.setOeste(oeste);
        atual.setLeste(leste);
        atual.setNorte(norte);
        atual.setSul(sul);
    }

    public void criarRaiz(Caverna atual, Caverna oeste, Caverna leste, Caverna norte, Caverna sul) {
        criarSubArvore(atual, oeste, leste, norte, sul);
    }
}
