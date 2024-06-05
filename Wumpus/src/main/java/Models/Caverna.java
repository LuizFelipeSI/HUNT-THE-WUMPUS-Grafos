package Models;

public class Caverna {

    private Caverna leste; //null caso não houver conexão deste lado
    private Caverna oeste;
    private Caverna norte;
    private Caverna sul;
    public Inimigo inimigo; //null caso estiver livre
    private Flecha flecha; //null caso estiver livre
    private Player player; //null caso estiver livre

    public Caverna getLeste() {
        return leste;
    }

    public Caverna getOeste() {
        return oeste;
    }

    public Caverna getNorte() {
        return norte;
    }

    public Caverna getSul() {
        return sul;
    }

    public Inimigo getInimigo() {
        return inimigo;
    }

    public void setInimigo(Inimigo inimigo) {
        this.inimigo = inimigo;
    }

    public Flecha getFlecha() {
        return flecha;
    }

    public void setFlecha(Flecha flecha) {
        this.flecha = flecha;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void conectar(Direcao direcao, Caverna caverna) {
        switch (direcao) {
            case LESTE:
                this.leste = caverna;
                break;
            case OESTE:
                this.oeste = caverna;
                break;
            case NORTE:
                this.norte = caverna;
                break;
            case SUL:
                this.sul = caverna;
                break;
        }
    }
}
