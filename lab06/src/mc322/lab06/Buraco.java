package mc322.lab06;

public class Buraco extends Componente {
	
	public Buraco(int i, int j, String rep) {
		super(i, j, rep); 
		secundarios = new Componente[4];
		gerarSecundarios(i+1, j, 0);
		gerarSecundarios(i, j+1, 1);
		gerarSecundarios(i-1, j, 2);
		gerarSecundarios(i, j-1, 3);
	}
	
	private void gerarSecundarios(int i, int j, int k) {
		if (coordenadasValidas(i, j)) {
			secundarios[k] = new Brisa(i, j);
		}
		else {
			secundarios[k] = null;
		}
	}
	
	public void exibirMensagem() {
		System.out.println("Voce caiu em um buraco!\n");
	}

}
