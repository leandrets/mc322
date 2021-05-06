package mc322.lab05b;

public class Peao extends Peca {
    public Peao(int i, int j, char cor) {
        super(i, j, cor);
    }

    // Adiciona verificação própria do peão
    public boolean movimentoValido(int distVert, char[] trajeto) {
        if (super.movimentoValido(distVert, trajeto)) {
            if (Math.abs(distVert) > 2) {
                return false;   // Peão andando pra trás ou mais de 2 casas
            }
            else if (cor == 'b' && distVert == -1) {
                return false;
            }
            else if (cor == 'p' && distVert == 1) {
                return false;
            }
            else if (trajeto.length == 2 && trajeto[0] == '-') {
            	return false;
            }
            return true;
        }
        return false;
    }
}
