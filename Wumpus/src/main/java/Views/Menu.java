package Views;

import Services.*;

public class Menu {

    public void imprimirMenu() {
        while (true) {
            Output output = new Output();
            output.imprimirOpcoesMenuPrincipal();
            Input input = new Input();
            String opcao = input.digitarOpcoes();
            boolean eNumero = true;
            for (char c : opcao.toCharArray()) {
                if (!Character.isDigit(c)) {
                    eNumero = false;
                    break;
                }
            }
            if (!eNumero) {
                output.imprimirSomenteNumeros();
            } else {
                int numero = Integer.parseInt(opcao);
                if (numero == 1) {
                    Partida p = new Partida();
                    p.iniciarPartdia();
                } else if (numero == 2) {
                    break;
                } else {
                    output.imprimirOpcaoInvalida();
                }
            }
        }
    }
}
