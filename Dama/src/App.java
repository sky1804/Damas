/*
 * Willian Beltrão da Silva
 * 1912082032
 */

import java.util.Scanner;

import module.Piece.Peca;
import module.Tabuleiro.RegrasMovimento;
import module.Tabuleiro.Tabuleiro;

public class App {
    private Tabuleiro tabuleiro;
    private char turnoAtual; // 'b' para branco e 'p' para preto

    public App() {
        tabuleiro = new Tabuleiro();
        turnoAtual = 'p'; //Preto começa
    }

    public void jogar() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                tabuleiro.exibirTabuleiro();
                System.out.println("Turno do jogador: " + (turnoAtual == 'b' ? "Branco" : "Preto"));
                System.out.println("Digite a linha e coluna de origem e destino (ex: 2 3 3 4):");

                int linhaOrigem = scanner.nextInt();
                int colunaOrigem = scanner.nextInt();
                int linhaDestino = scanner.nextInt();
                int colunaDestino = scanner.nextInt();

                Peca peca = tabuleiro.getPeca(linhaOrigem, colunaOrigem);

                if (peca == null) {
                    System.out.println("Não há peça na posição de origem.");
                    continue;
                }

                if (peca.getColor() != turnoAtual) {
                    System.out.println("Movimento inválido! É o turno do " + (turnoAtual == 'b' ? "Branco" : "Preto"));
                    continue;
                }

                if (RegrasMovimento.movimentoValido(tabuleiro, linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
                    tabuleiro.moverPeca(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
                    alternarTurno();
                    
                    // Verificar condição de vitória após cada movimento válido
                    if (tabuleiro.verificarCondicaoDeVitoria()) {
                        System.out.println("Jogo terminado! Vencedor: " + (turnoAtual == 'b' ? "Preto" : "Branco"));
                        break;
                    }
                } else {
                    System.out.println("Movimento inválido!");
                }

            } 
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        
    }

    private void alternarTurno(){
        turnoAtual = (turnoAtual == 'b') ? 'p' : 'b';
    }

    public static void main(String[] args) {
        App jogo = new App();
        jogo.jogar();
    }
}
