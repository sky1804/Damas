package module.Tabuleiro;

import module.Piece.Peca;

public class RegrasMovimento {

    public static boolean movimentoValido(Tabuleiro tabuleiro, int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        Peca peca = tabuleiro.getPeca(linhaOrigem, colunaOrigem);

        if (peca == null || tabuleiro.getPeca(linhaDestino, colunaDestino) != null) {
            return false;
        }

        return peca.movimentoValido(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, tabuleiro);
    }
}
