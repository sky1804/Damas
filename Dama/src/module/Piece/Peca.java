package module.Piece;

import module.Tabuleiro.Tabuleiro;

public abstract class Peca {

    protected char color;
    protected char tipo;

    public Peca(char tipo, char color) {
        this.tipo = tipo;
        this.color = color;
    }

    public char getTipo() {
        return tipo;
    }

    public char getColor() {
        return color;
    }

    public abstract boolean movimentoValido(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tabuleiro);
}

