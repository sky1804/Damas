package module.Piece;

import module.Tabuleiro.Tabuleiro;

public class Rainha extends Peca {

    public Rainha(char tipo, char color) {
        super(tipo, color);
    }

    @Override
    public boolean movimentoValido(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tabuleiro) {
        int deltaLinha = linhaDestino - linhaOrigem;
        int deltaColuna = colunaDestino - colunaOrigem;

        if (Math.abs(deltaLinha) != Math.abs(deltaColuna)) {
            return false; // Movimento não é diagonal
        }


        int passoLinha = Integer.signum(deltaLinha);
        int passoColuna = Integer.signum(deltaColuna);
        int linha = linhaOrigem + passoLinha;
        int coluna = colunaOrigem + passoColuna;
        boolean encontrouPecaAdversaria = false;

        while (linha != linhaDestino && coluna != colunaDestino) {
            Peca pecaAtual = tabuleiro.getPeca(linha, coluna);
            if (pecaAtual != null) {
                if (pecaAtual.getColor() == this.getColor()) {
                    return false; // Encontrou uma peça aliada no caminho
                }
                if (encontrouPecaAdversaria) {
                    return false; // Já encontrou uma peça adversária, não pode pular mais
                }
                encontrouPecaAdversaria = true;
            }
            linha += passoLinha;
            coluna += passoColuna;
        }

        // O destino deve ser vazio e deve estar após a peça capturada, se houver uma
        Peca pecaDestino = tabuleiro.getPeca(linhaDestino, colunaDestino);
        return pecaDestino == null && (!encontrouPecaAdversaria || (linha - passoLinha == linhaOrigem && coluna - passoColuna == colunaOrigem));
    }
}

