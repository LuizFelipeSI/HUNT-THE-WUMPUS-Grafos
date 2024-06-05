package Services;

import Models.Caverna;
import Models.Direcao;

public class Labirinto {

    public Caverna criarNovoNo() {
        Caverna novoNo = new Caverna();
        return novoNo;
    }

    public void criarSubArvore(Caverna atual, Caverna oeste, Caverna leste, Caverna norte, Caverna sul) {
        atual.conectar(Direcao.OESTE, oeste);
        atual.conectar(Direcao.LESTE, leste);
        atual.conectar(Direcao.NORTE, norte);
        atual.conectar(Direcao.SUL, sul);
    }

    public void criarRaiz(Caverna atual, Caverna oeste, Caverna leste, Caverna norte, Caverna sul) {
        criarSubArvore(atual, oeste, leste, norte, sul);
    }
}
