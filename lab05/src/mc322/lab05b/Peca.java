package mc322.lab05b;

public class Peca {
    protected char cor;
    protected int i, j;

    public Peca(int i, int j, char cor) {
        this.i = i;
        this.j = j;
        this.cor = cor;
    }

    public char getCor() {
        return cor;
    }

    public int pecasNoCaminho(char[] trajeto) {
        int pecas = 0;
        for (int i = 0; i < trajeto.length; i++) {
            if (trajeto[i] != '-') {
                pecas++;
            }
        }
        return pecas;
    }

    // Verifica restrições necessárias para ambos os tipos de peça
    public boolean movimentoValido(int distVert, char[] trajeto) {
        if (trajeto.length == 0) {  // Caminho de tamanho 0
           return false; 
        }
        else if (trajeto[trajeto.length - 1] != '-') {  // Casa destino não vazia
            return false;
        }
        else if (trajeto.length == 2) {
            if (trajeto[0] == cor || trajeto[0] == Character.toUpperCase(cor)) {
                return false;   // Captura de peça não oponente
            }
        }
        return true;
    }

}
