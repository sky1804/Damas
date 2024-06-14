package module.Tabuleiro;

import module.Piece.Peca;
import module.Piece.Rainha;
import module.Piece.Peao;

public class Tabuleiro {
    private static final int TAMANHO_TABULEIRO = 8;
    private Peca[][] tabuleiro = new Peca[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];

    public Tabuleiro() {
        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if ((i + j) % 2 == 1) {
                    if (i < 3) {
                        tabuleiro[i][j] = new Peao('p', 'p');
                    } else if (i > 4) {
                        tabuleiro[i][j] = new Peao('b', 'b');
                    }
                }
            }
        }
    }

    public Peca getPeca(int linha, int coluna) {
        return tabuleiro[linha][coluna];
    }

    public void moverPeca(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        int deltaLinha = linhaDestino - linhaOrigem;
        int deltaColuna = colunaDestino - colunaOrigem;

        // Captura de peça

        if (Math.abs(deltaLinha) == Math.abs(deltaColuna)) {
            int passoLinha = Integer.signum(deltaLinha);
            int passoColuna = Integer.signum(deltaColuna);
            int linha = linhaOrigem + passoLinha;
            int coluna = colunaOrigem + passoColuna;

            while (linha != linhaDestino && coluna != colunaDestino) {
                if (tabuleiro[linha][coluna] != null && tabuleiro[linha][coluna].getColor() != tabuleiro[linhaOrigem][colunaOrigem].getColor()) {
                    tabuleiro[linha][coluna] = null; // Remove a peça capturada
                    break;
                }
                linha += passoLinha;
                coluna += passoColuna;
            }
        }
        
        tabuleiro[linhaDestino][colunaDestino] = tabuleiro[linhaOrigem][colunaOrigem];
        tabuleiro[linhaOrigem][colunaOrigem] = null;

         // Promoção de peão
        if (tabuleiro[linhaDestino][colunaDestino] instanceof Peao) {
            if ((linhaDestino == 0 && tabuleiro[linhaDestino][colunaDestino].getColor() == 'b') ||
                (linhaDestino == TAMANHO_TABULEIRO - 1 && tabuleiro[linhaDestino][colunaDestino].getColor() == 'p')) {
                    char tipoRainha = tabuleiro[linhaDestino][colunaDestino].getColor() == 'b' ? 'B' : 'P';
                tabuleiro[linhaDestino][colunaDestino] = new Rainha(tipoRainha, tabuleiro[linhaDestino][colunaDestino].getColor());
            }
        }
    }

    public boolean verificarCondicaoDeVitoria() {
        boolean temPecaBranca = false;
        boolean temPecaPreta = false;

        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                Peca peca = tabuleiro[i][j];
                if (peca != null) {
                    if (peca.getColor() == 'b') {
                        temPecaBranca = true;
                    } else if (peca.getColor() == 'p') {
                        temPecaPreta = true;
                    }
                }
                if (temPecaBranca && temPecaPreta) {
                    return false; // Jogo continua
                }
            }
        }

        return true; // Alguém venceu
    }
    

    public void exibirTabuleiro() {
        System.out.print("");
        for (int j = 0; j < TAMANHO_TABULEIRO; j++){
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                System.out.print((tabuleiro[i][j] != null ? tabuleiro[i][j].getTipo() : '.') + " ");
            }
            System.out.println(i);
        }
    }
}
