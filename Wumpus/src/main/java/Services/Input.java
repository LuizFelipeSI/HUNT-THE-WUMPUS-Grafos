package Services;

import java.util.Scanner;

public class Input {

    public String digitarOpcoes() {
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }

    public boolean verificarOpcao(String opcao) {
        boolean eNumero = true;
        for (char c : opcao.toCharArray()) {
            if (!Character.isDigit(c)) {
                eNumero = false;
                break;
            }
        }
        return eNumero;
    }
}
