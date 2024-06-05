package Views;

import Models.Player;

import java.util.ArrayList;

public class Output {

    public void imprimirOpcoesMenuPrincipal() {
        System.out.println("HUNT THE WUMPUS");
        System.out.println("1 - Iniciar o jogo");
        System.out.println("2 - Sair");
    }

    public void imprimirSomenteNumeros() {
        System.out.println("Somente números são válidos!");
    }

    public void imprimirOpcaoInvalida() {
        System.out.println("Opção inválida");
    }

    public void imprimirFraseInicio() {
        System.out.println("Você acorda em uma caverna!");
    }

    public void imprimirOpcoesMover(ArrayList<Integer> cavernasVisitadas, Player player, String opcaoNorte,
                                    String opcaoLeste, String opcaoSul, String opcaoOeste, String opcaoFlecha) {
        System.out.println("Caminho feito: " + cavernasVisitadas);
        System.out.println("Vida: " + player.getVida());
        System.out.println("Flechas: " + player.getFlechas());
        System.out.println(opcaoNorte);
        System.out.println(opcaoLeste);
        System.out.println(opcaoSul);
        System.out.println(opcaoOeste);
        System.out.println(opcaoFlecha);
    }

    public void imprimirOpcoesAtirar(ArrayList<Integer> cavernasVisitadas, Player player, String opcaoNorte,
                                     String opcaoLeste, String opcaoSul, String opcaoOeste) {
        System.out.println("Caminho feito: " + cavernasVisitadas);
        System.out.println("Vida: " + player.getVida());
        System.out.println("Flechas: " + player.getFlechas());
        System.out.println(opcaoNorte);
        System.out.println(opcaoLeste);
        System.out.println(opcaoSul);
        System.out.println(opcaoOeste);
        System.out.println("5 - Voltar");
    }

    public void imprimirNaoHaFlechas() {
        System.out.println("Você não tem flechas!");
    }

    public void imprimirVitoria() {
        System.out.println("Você matou o monstro!!!");
    }

    public void imprimirDerrota(Player player) {
        System.out.println("Vida: " + player.getVida());
        System.out.println("Você morreu!!!");
    }

    public void imprimirErro() {
        System.out.println("Você errou");
    }

    public void imprimirPocoProximo() {
        System.out.println("Você sente uma brisa");
    }

    public void imprimirPoco() {
        System.out.println("Você caiu dentro de um poço e se machucou (-50)");
    }

    public void imprimirMorcegoProximo() {
        System.out.println("Você ouve um bater de asas");
    }

    public void imprimirMorcego() {
        System.out.println("Você encontrou um morcego e ele te levou para outra caverna");
    }

    public void imprimirWumpusProximo() {
        System.out.println("Você sente um odor");
    }

    public void imprimirWumpus() {
        System.out.println("Você entrou na caverna do monstro e ele te matou!!!");
    }

    public void imprimirPegarFlecha() {
        System.out.println("Você pegou uma flecha");
    }

    public void imprimirDerrotaFlechas() {
        System.out.println("Todas as flechas do mapa acabaram, você não pode mais matar o monstro!!!");
    }
}
