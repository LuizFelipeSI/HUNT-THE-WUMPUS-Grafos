O código consiste em um jogo de HUNT THE WUMPUS feito utilizando GRAFOS e utilizando Enum para realizar o mapeamento das cavernas, fazendo com que uma caverna tenha de 2 até 4 conexões com outras cavernas. O jogo possui um total de 25 cavernas.

REGRAS DO JOGO:
- O objetivo do jogo é achar a caverna em que o WUMPUS está, e lançar uma flecha nele, fazendo com que o jogador vença o jogo.

- O player (apenas 1) jogador humano inicia em uma caverna livre aleatória e começa com 100 de vida.
  
- O monstro Wumpus (apenas 1) se o jogador entrar na caverna do monstro, o jogador perde o jogo.
  (Nas cavernas ao redor do Wumpus, o player consegue sentir o odor)

- Poço (2) provoca 50 de dano na vida do jogador quando ele entra nesta caverna, se a vida do jogador chegar a 0 o jogador perde o jogo.
  (Nas cavernas ao redor do Poço, o player consegue sentir a brisa)

- Morcego (apenas 1) transporta o jogador para outra caverna aleatória.
  (Nas cavernas ao redor de um Morcego, o player consegue ouvir o bater das asas)
  
- Flecha (3 cavernas com apenas 1 flecha cada caverna). Quando o player acha uma flecha, incrementa seu número, quando o player lança ela, decrementa seu número. Ela só chega até a próxima caverna do lado escolhido. Se as flechas do jogador acabarem e não tiver mais flechas no mapa, o jogador perde o jogo.
