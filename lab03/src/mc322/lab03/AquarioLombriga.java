package lab03;

public class AquarioLombriga {
	int a, l;
    int cauda, cabeca;
    String direcao = "";
    String aqua_esq = "", lomb = "", aqua_dir = "";
    
    AquarioLombriga(int a, int l, int p) {
        p = p-1;
        if (l > a) {
        	a = l;
        }
        if (p + l > a) {
        	p = 0;
        }
        this.l = l;
        this.a = a-1;
        direcao = "direita";
        cauda = p;
        cabeca = p + l - 1;
        int i;
        for (i = 0; i < l-1; i++) {
        	lomb += "@";
        }
        lomb += "0";
        for (i = 0; i < p; i++) {
        	aqua_esq += "#";
        }
        for (i = 0; i < (a - (l+p)); i++) {
        	aqua_dir += "#";
        }
    }
    
    public void crescer() {
        if (direcao == "direita" && cauda > 0) {
        	lomb = "@" + lomb;
            aqua_esq = aqua_esq.substring(1);
            cauda--;
        }
        else if (direcao == "esquerda" && cauda < a) {
        	lomb = lomb + "@";
            aqua_dir = aqua_dir.substring(1);
            cauda++;
        }
        l++;
    }
    
    public void virar() {
    	int i, t = lomb.length() - 1;
    	boolean mudou = false;
    	lomb = "";
        if (direcao == "direita") {
            lomb = "0";
            direcao = "esquerda";
            mudou = true;
        }
        for (i = 0; i < t; i++) {
        	lomb += "@";
        }
        if (mudou == false) {
            lomb += "0";
            direcao = "direita";
        }
        int aux = cabeca;
        cabeca = cauda;
        cauda = aux;
    }
    
    public void mover() {
        if (direcao == "direita" && cabeca < a) {
            aqua_esq += "#";
            aqua_dir = aqua_dir.substring(1);
            cabeca++;
            cauda++;
        }
        else if (direcao == "esquerda" && cabeca > 0) {
            aqua_dir += "#";
            aqua_esq = aqua_esq.substring(1);   
            cabeca--;
            cauda--;
        }
        else {
            virar();
        }
    }
    
    public String apresenta() {
        return (aqua_esq + lomb + aqua_dir);
    }
}
