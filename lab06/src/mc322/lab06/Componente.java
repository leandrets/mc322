package mc322.lab06;

public class Componente {
	protected int i, j;
	protected String rep;
	public Componente[] secundarios;
	
	public Componente(int i, int j, String rep) {
		this.i = i;
		this.j = j;
		this.rep = rep;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	public String getRep() {
		return rep;
	}
	
	/* Retorna true se as coordenadas estão dentro do tabuleiro (caverna).*/
	protected boolean coordenadasValidas(int i, int j) {
		if (i >= 0 && i < 4 && j >= 0 && j < 4) {
			return true;
		}
		return false;
	}
	
	public void exibirMensagem() {}

}
