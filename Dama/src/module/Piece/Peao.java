package module.Piece;

import module.Tabuleiro.Tabuleiro;

public class Peao extends Peca{

    public Peao(char tipo, char color){
        super(tipo, color);
    }

    @Override
    public boolean movimentoValido(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tabuleiro){
        int deltaLinha = linhaDestino - linhaOrigem;
        int deltaColuna = colunaDestino - colunaOrigem;
    

        // Movimentos normais
        if (color == 'b') {
            if (deltaLinha == -1 && Math.abs(deltaColuna) == 1 && tabuleiro.getPeca(linhaDestino, colunaDestino) == null) {
                return true;
            }
        } else if (color == 'p') {
            if (deltaLinha == 1 && Math.abs(deltaColuna) == 1 && tabuleiro.getPeca(linhaDestino, colunaDestino) == null) {
                return true;
            }
        }

        // Movimentos de captura
        if (Math.abs(deltaLinha) == 2 && Math.abs(deltaColuna) == 2) {
            int meioLinha = (linhaOrigem + linhaDestino) / 2;
            int meioColuna = (colunaOrigem + colunaDestino) / 2;
            Peca pecaCapturada = tabuleiro.getPeca(meioLinha, meioColuna);
            if (pecaCapturada != null && pecaCapturada.getColor() != this.color && tabuleiro.getPeca(linhaDestino, colunaDestino) == null) {
                return true;
            }
        }

        return false;
    }

}
