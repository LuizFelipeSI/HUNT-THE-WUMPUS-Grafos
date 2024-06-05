package Services;

import Models.*;
import Views.Output;

import java.util.*;

public class Partida {
    Caverna[] cavernas = new Caverna[25];
    ArrayList<Integer> cavernasVisitadas = new ArrayList<>();
    int cavernaAtual;
    int contadorFlechas = 3;
    boolean indicadorFimDeJogo = false;
    Labirinto arvore = new Labirinto();
    Player player = new Player("player");
    Flecha flecha = new Flecha("flecha");
    Wumpus wumpus = new Wumpus("wumpus");
    Poco poco = new Poco("poço");
    Morcego morcego = new Morcego("morcego");
    public void iniciarPartdia() {

        Output output = new Output();
        output.imprimirFraseInicio();

        for (int j = 0; j < cavernas.length; j++) {
            cavernas[j] = arvore.criarNovoNo();
        }

        arvore.criarSubArvore(cavernas[24], cavernas[23], null, cavernas[19], null);
        arvore.criarSubArvore(cavernas[23], cavernas[22], cavernas[24], cavernas[18], null);
        arvore.criarSubArvore(cavernas[22], cavernas[21], cavernas[23], cavernas[17], null);
        arvore.criarSubArvore(cavernas[21], cavernas[20], cavernas[22], cavernas[16], null);
        arvore.criarSubArvore(cavernas[20], null, cavernas[21], cavernas[15], null);
        arvore.criarSubArvore(cavernas[19], cavernas[18], null, cavernas[14], cavernas[24]);
        arvore.criarSubArvore(cavernas[18], cavernas[17], cavernas[19], cavernas[13], cavernas[23]);
        arvore.criarSubArvore(cavernas[17], cavernas[16], cavernas[18], cavernas[12], cavernas[22]);
        arvore.criarSubArvore(cavernas[16], cavernas[15], cavernas[17], cavernas[11], cavernas[21]);
        arvore.criarSubArvore(cavernas[15], null, cavernas[16], cavernas[10], cavernas[20]);
        arvore.criarSubArvore(cavernas[14], cavernas[13], null, cavernas[9], cavernas[19]);
        arvore.criarSubArvore(cavernas[13], cavernas[12], cavernas[14], cavernas[8], cavernas[18]);
        arvore.criarSubArvore(cavernas[12], cavernas[11], cavernas[13], cavernas[7], cavernas[17]);
        arvore.criarSubArvore(cavernas[11], cavernas[10], cavernas[12], cavernas[6], cavernas[16]);
        arvore.criarSubArvore(cavernas[10], null, cavernas[11], cavernas[5], cavernas[15]);
        arvore.criarSubArvore(cavernas[9], cavernas[8], null, cavernas[4], cavernas[14]);
        arvore.criarSubArvore(cavernas[8], cavernas[7], cavernas[9], cavernas[3], cavernas[13]);
        arvore.criarSubArvore(cavernas[7], cavernas[6], cavernas[8], cavernas[2], cavernas[12]);
        arvore.criarSubArvore(cavernas[6], cavernas[5], cavernas[7], cavernas[1], cavernas[11]);
        arvore.criarSubArvore(cavernas[5], null, cavernas[6], cavernas[0], cavernas[10]);
        arvore.criarSubArvore(cavernas[4], cavernas[3], null, null, cavernas[9]);
        arvore.criarSubArvore(cavernas[3], cavernas[2], cavernas[4], null, cavernas[8]);
        arvore.criarSubArvore(cavernas[2], cavernas[1], cavernas[3], null, cavernas[7]);
        arvore.criarSubArvore(cavernas[1], cavernas[0], cavernas[2], null, cavernas[6]);
        arvore.criarRaiz(cavernas[0], null, cavernas[1], null, cavernas[5]);

        Random r = new Random();
        int[] posicoes = new int[8];
        Set<Integer> gerados = new HashSet<>();

        for (int i = 0; i < 8; i++) {
            int num;
            do {
                num = r.nextInt(25);
            } while (gerados.contains(num));
            posicoes[i] = num;
            gerados.add(num);
        }

        cavernas[posicoes[0]].setInimigo(wumpus);
        cavernas[posicoes[1]].setInimigo(poco);
        cavernas[posicoes[2]].setInimigo(poco);
        cavernas[posicoes[3]].setInimigo(morcego);
        cavernas[posicoes[4]].setFlecha(flecha);
        cavernas[posicoes[5]].setFlecha(flecha);
        cavernas[posicoes[6]].setFlecha(flecha);
        cavernas[posicoes[7]].setPlayer(player);
        cavernaAtual = posicoes[7];
        cavernasVisitadas.add(posicoes[7]);

        mover();
    }

    public void mover() {
        verificarMorcego();
        verificarPoco();
        verificarWumpus();
        while(!indicadorFimDeJogo) {
            String opcaoNorte;
            String opcaoLeste;
            String opcaoSul;
            String opcaoOeste;
            String opcaoFlecha;
            if(cavernas[cavernaAtual].getNorte() != null) {
                opcaoNorte = "1 - Ir para o norte {" + compararCaverna(cavernas[cavernaAtual].getNorte()) + "}";
            } else {
                opcaoNorte = "Não há caminho para o norte!";
            }
            if(cavernas[cavernaAtual].getLeste() != null) {
                opcaoLeste = "2 - Ir para o leste {" + compararCaverna(cavernas[cavernaAtual].getLeste()) + "}";
            } else {
                opcaoLeste = "Não há caminho para o leste!";
            }
            if(cavernas[cavernaAtual].getSul() != null) {
                opcaoSul = "3 - Ir para o sul {" + compararCaverna(cavernas[cavernaAtual].getSul()) + "}";
            } else {
                opcaoSul = "Não há caminho para o sul!";
            }
            if(cavernas[cavernaAtual].getOeste() != null) {
                opcaoOeste = "4 - Ir para o oeste {" + compararCaverna(cavernas[cavernaAtual].getOeste()) + "}";
            } else {
                opcaoOeste = "Não há caminho para o oeste!";
            }
            if(verificarInimigo("wumpus")) {
                opcaoFlecha = "5 - Escolher caverna para atirar uma flecha";
            } else {
                opcaoFlecha = "Você ainda não corre perigo";
            }
            Output output = new Output();
            output.imprimirOpcoesMover(cavernasVisitadas, player, opcaoNorte, opcaoLeste, opcaoSul, opcaoOeste, opcaoFlecha);

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
                    if (cavernas[cavernaAtual].getNorte() != null) {
                        cavernas[cavernaAtual].getNorte().setPlayer(cavernas[cavernaAtual].getPlayer());
                        cavernas[cavernaAtual].setPlayer(null);
                        cavernaAtual = compararCaverna(cavernas[cavernaAtual].getNorte());
                        cavernasVisitadas.add(cavernaAtual);
                        verificarCavernas();
                    } else {
                        output.imprimirOpcaoInvalida();
                    }
                } else if (numero == 2) {
                    if (cavernas[cavernaAtual].getLeste() != null) {
                        cavernas[cavernaAtual].getLeste().setPlayer(cavernas[cavernaAtual].getPlayer());
                        cavernas[cavernaAtual].setPlayer(null);
                        cavernaAtual = compararCaverna(cavernas[cavernaAtual].getLeste());
                        cavernasVisitadas.add(cavernaAtual);
                        verificarCavernas();
                    } else {
                        output.imprimirOpcaoInvalida();
                    }
                } else if (numero == 3) {
                    if (cavernas[cavernaAtual].getSul() != null) {
                        cavernas[cavernaAtual].getSul().setPlayer(cavernas[cavernaAtual].getPlayer());
                        cavernas[cavernaAtual].setPlayer(null);
                        cavernaAtual = compararCaverna(cavernas[cavernaAtual].getSul());
                        cavernasVisitadas.add(cavernaAtual);
                        verificarCavernas();
                    } else {
                        output.imprimirOpcaoInvalida();
                    }
                } else if (numero == 4) {
                    if (cavernas[cavernaAtual].getOeste() != null) {
                        cavernas[cavernaAtual].getOeste().setPlayer(cavernas[cavernaAtual].getPlayer());
                        cavernas[cavernaAtual].setPlayer(null);
                        cavernaAtual = compararCaverna(cavernas[cavernaAtual].getOeste());
                        cavernasVisitadas.add(cavernaAtual);
                        verificarCavernas();
                    } else {
                        output.imprimirOpcaoInvalida();
                    }
                } else if (numero == 5) {
                    if (player.getFlechas() > 0) {
                        atirar();
                    } else {
                        output.imprimirNaoHaFlechas();
                    }
                } else {
                    output.imprimirOpcaoInvalida();
                }
            }
        }
    }

    public void atirar() {
        while(player.getFlechas() > 0) {
            String opcaoNorte;
            String opcaoLeste;
            String opcaoSul;
            String opcaoOeste;
            if(cavernas[cavernaAtual].getNorte() != null) {
                opcaoNorte = "1 - Atirar para o norte {" + compararCaverna(cavernas[cavernaAtual].getNorte()) + "}";
            } else {
                opcaoNorte = "Não há caminho para o norte!";
            }
            if(cavernas[cavernaAtual].getLeste() != null) {
                opcaoLeste = "2 - Atirar para o leste {" + compararCaverna(cavernas[cavernaAtual].getLeste()) + "}";
            } else {
                opcaoLeste = "Não há caminho para o leste!";
            }
            if(cavernas[cavernaAtual].getSul() != null) {
                opcaoSul = "3 - Atirar para o sul {" + compararCaverna(cavernas[cavernaAtual].getSul()) + "}";
            } else {
                opcaoSul = "Não há caminho para o sul!";
            }
            if(cavernas[cavernaAtual].getOeste() != null) {
                opcaoOeste = "4 - Atirar para o oeste {" + compararCaverna(cavernas[cavernaAtual].getOeste()) + "}";
            } else {
                opcaoOeste = "Não há caminho para o oeste!";
            }
            Output output = new Output();
            output.imprimirOpcoesAtirar(cavernasVisitadas, player, opcaoNorte, opcaoLeste, opcaoSul, opcaoOeste);

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
                    if (cavernas[cavernaAtual].getNorte() != null) {
                        if (cavernas[cavernaAtual].getNorte().getInimigo() != null) {
                            if (cavernas[cavernaAtual].getNorte().getInimigo().getNome().equals("wumpus")) {
                                output.imprimirVitoria();
                                player.setFlechas(player.getFlechas() - 1);
                                finalizarJogo();
                                break;
                            } else {
                                output.imprimirErro();
                                player.setFlechas(player.getFlechas() - 1);
                                contadorFlechas--;
                                if(contadorFlechas == 0) {
                                    output.imprimirDerrotaFlechas();
                                    finalizarJogo();
                                }
                            }
                        } else {
                            output.imprimirErro();
                            player.setFlechas(player.getFlechas() - 1);
                            contadorFlechas--;
                            if(contadorFlechas == 0) {
                                output.imprimirDerrotaFlechas();
                                finalizarJogo();
                            }
                        }
                    } else {
                        output.imprimirOpcaoInvalida();
                    }
                } else if (numero == 2) {
                    if (cavernas[cavernaAtual].getLeste() != null) {
                        if (cavernas[cavernaAtual].getLeste().getInimigo() != null) {
                            if (cavernas[cavernaAtual].getLeste().getInimigo().getNome().equals("wumpus")) {
                                output.imprimirVitoria();
                                player.setFlechas(player.getFlechas() - 1);
                                finalizarJogo();
                                break;
                            } else {
                                output.imprimirErro();
                                player.setFlechas(player.getFlechas() - 1);
                                contadorFlechas--;
                                if(contadorFlechas == 0) {
                                    output.imprimirDerrotaFlechas();
                                    finalizarJogo();
                                }
                            }
                        } else {
                            output.imprimirErro();
                            player.setFlechas(player.getFlechas() - 1);
                            contadorFlechas--;
                            if(contadorFlechas == 0) {
                                output.imprimirDerrotaFlechas();
                                finalizarJogo();
                            }
                        }
                    } else {
                        output.imprimirOpcaoInvalida();
                    }
                } else if (numero == 3) {
                    if (cavernas[cavernaAtual].getSul() != null) {
                        if (cavernas[cavernaAtual].getSul().getInimigo() != null) {
                            if (cavernas[cavernaAtual].getSul().getInimigo().getNome().equals("wumpus")) {
                                output.imprimirVitoria();
                                player.setFlechas(player.getFlechas() - 1);
                                finalizarJogo();
                                break;
                            } else {
                                output.imprimirErro();
                                player.setFlechas(player.getFlechas() - 1);
                                contadorFlechas--;
                                if(contadorFlechas == 0) {
                                    output.imprimirDerrotaFlechas();
                                    finalizarJogo();
                                }
                            }
                        } else {
                            output.imprimirErro();
                            player.setFlechas(player.getFlechas() - 1);
                            contadorFlechas--;
                            if(contadorFlechas == 0) {
                                output.imprimirDerrotaFlechas();
                                finalizarJogo();
                            }
                        }
                    } else {
                        output.imprimirOpcaoInvalida();
                    }
                } else if (numero == 4) {
                    if (cavernas[cavernaAtual].getOeste() != null) {
                        if (cavernas[cavernaAtual].getOeste().getInimigo() != null) {
                            if (cavernas[cavernaAtual].getOeste().getInimigo().getNome().equals("wumpus")) {
                                output.imprimirVitoria();
                                player.setFlechas(player.getFlechas() - 1);
                                finalizarJogo();
                                break;
                            } else {
                                output.imprimirErro();
                                player.setFlechas(player.getFlechas() - 1);
                                contadorFlechas--;
                                if(contadorFlechas == 0) {
                                    output.imprimirDerrotaFlechas();
                                    finalizarJogo();
                                }
                            }
                        } else {
                            output.imprimirErro();
                            player.setFlechas(player.getFlechas() - 1);
                            contadorFlechas--;
                            if(contadorFlechas == 0) {
                                output.imprimirDerrotaFlechas();
                                finalizarJogo();
                            }
                        }
                    } else {
                        output.imprimirOpcaoInvalida();
                    }
                } else if (numero == 5) {
                    break;
                } else {
                    output.imprimirOpcaoInvalida();
                }
            }
        }
    }

    public int compararCaverna(Caverna caverna) {
        int posicao = 0;
        for(int i = 0; i < cavernas.length; i++) {
            if(cavernas[i].equals(caverna)) {
                posicao = i;
                break;
            }
        }
        return posicao;
    }

    public void verificarCavernas() {
        if(!indicadorFimDeJogo) {
            verificarWumpus();
        }
        if(!indicadorFimDeJogo) {
            verificarPoco();
        }
        if(!indicadorFimDeJogo) {
            verificarMorcego();
        }
        if(!indicadorFimDeJogo) {
            verificarFlechas();
        }
    }

    public void verificarPoco() {
        Output output = new Output();
        if(verificarInimigo("poço")) {
            output.imprimirPocoProximo();
        }

        if(cavernas[cavernaAtual].getInimigo() != null) {
            if(cavernas[cavernaAtual].getInimigo().getNome().equals("poço")) {
                player.setVida(player.getVida() - 50);
                output.imprimirPoco();
            }
        }
        boolean vivo = verificarVida();
        if(!vivo) {
            output.imprimirDerrota(player);
            finalizarJogo();
        }
    }

    public void verificarMorcego() {
        Output output = new Output();
        if(verificarInimigo("morcego")) {
            output.imprimirMorcegoProximo();
        }
        if(cavernas[cavernaAtual].getInimigo() != null) {
            if(cavernas[cavernaAtual].getInimigo().getNome().equals("morcego")) {
                Random r = new Random();
                int cavernaAleatoria = r.nextInt(25);
                cavernas[cavernaAleatoria].setPlayer(cavernas[cavernaAtual].getPlayer());
                cavernas[cavernaAtual].setPlayer(null);
                cavernaAtual = compararCaverna(cavernas[cavernaAleatoria]);
                cavernasVisitadas.add(cavernaAleatoria);
                output.imprimirMorcego();
                verificarWumpus();
                verificarPoco();
                verificarMorcego();
            }
        }
    }

    public void verificarWumpus() {
        Output output = new Output();
        if(verificarInimigo("wumpus")) {
            output.imprimirWumpusProximo();
        }
        if(cavernas[cavernaAtual].getInimigo() != null) {
            if(cavernas[cavernaAtual].getInimigo().getNome().equals("wumpus")) {
                output.imprimirWumpus();
                finalizarJogo();
            }
        }
    }

    public boolean verificarInimigo(String inimigo) {
        if(cavernas[cavernaAtual].getLeste() != null) {
            if(cavernas[cavernaAtual].getLeste().getInimigo() != null) {
                if (cavernas[cavernaAtual].getLeste().getInimigo().getNome().equals(inimigo)) {
                    return true;
                }
            }
        }
        if(cavernas[cavernaAtual].getOeste() != null) {
            if(cavernas[cavernaAtual].getOeste().getInimigo() != null) {
                if (cavernas[cavernaAtual].getOeste().getInimigo().getNome().equals(inimigo)) {
                    return true;
                }
            }
        }
        if(cavernas[cavernaAtual].getNorte() != null) {
            if(cavernas[cavernaAtual].getNorte().getInimigo() != null) {
                if (cavernas[cavernaAtual].getNorte().getInimigo().getNome().equals(inimigo)) {
                    return true;
                }
            }
        }
        if(cavernas[cavernaAtual].getSul() != null) {
            if(cavernas[cavernaAtual].getSul().getInimigo() != null) {
                if (cavernas[cavernaAtual].getSul().getInimigo().getNome().equals(inimigo)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void verificarFlechas() {
        Output output = new Output();
        if(cavernas[cavernaAtual].getFlecha() != null) {
            player.setFlechas(player.getFlechas() + 1);
            cavernas[cavernaAtual].setFlecha(null);
            output.imprimirPegarFlecha();
        }
    }

    public boolean verificarVida() {
        if(player.getVida() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void finalizarJogo() {
        indicadorFimDeJogo = true;
    }
}
