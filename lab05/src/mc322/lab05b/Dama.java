package mc322.lab05b;

public class Dama extends Peca {
    public Dama(int i, int j, char cor) {
        super(i, j, cor);
    }

    // Adiciona verificação própria da dama
    public boolean movimentoValido(int distVert, char[] trajeto) {
        if (super.movimentoValido(distVert, trajeto)) {
            if (trajeto.length > 2) {
                int pecasNoCaminho = 0;
                for (int i = 0; i < trajeto.length; i++) {
                    if (trajeto[i] == cor || trajeto[i] == Character.toLowerCase(cor)) {
                        return false;
                    }
                    else if (trajeto[i] != '-') {
                        if (pecasNoCaminho == 1) {
                            return false;
                        }
                        else {
                            pecasNoCaminho++; 
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
}
