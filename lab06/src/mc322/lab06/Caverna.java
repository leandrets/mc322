package mc322.lab06;
import java.util.Random;

public class Caverna {
	private Sala[][] salas = new Sala[4][4];
	
	public Caverna() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				salas[i][j] = new Sala(i == 0 && j == 0);
			}
		}
	}
	
	/* Recebe um componente e pede à sala em questão que o adicione, adicionando também seus componentes
	 * secundários (se houver). Se a inclusão ocorrer com sucesso, retorna true, senão retorna false.*/
	public boolean incluir(Componente comp) {
		if (comp != null) {
			if (salas[comp.getI()][comp.getJ()].adicionar(comp)) {
				if (comp.secundarios != null) {
					for (int k = 0; k < 4; k++) {
						if (comp.secundarios[k] != null) {
							incluir(comp.secundarios[k]);
						}
					}
				}
				return true;
			}
		}
		return false;
	}
	
	/* Recebe um componente (Heroi, Ouro ou Wumpus) e pede à sua sala que o remova, bem como seus
	 * componenetes secundários (nesse caso, somente fedor).*/
	public void excluir(Componente comp) {
		salas[comp.getI()][comp.getJ()].remover(comp);
		if (comp.secundarios != null) {
			for (int k = 0; k < 4; k++) {
				if (comp.secundarios[k] != null) {
					excluir(comp.secundarios[k]);
				}
			}
		}
	}
	
	/* Pergunta à sala do heroi se ele morreu (caiu em buraco ou encontrou o Wumpus sem 
	conseguir matá-lo. */
	public boolean verificarMorte(Heroi heroi) {
		int i = heroi.getI();
		int j = heroi.getJ();
		if (salas[i][j].temComponente("B")) {
			heroi.pontuar(-1000);
			return true;
		}
		else if (salas[i][j].temComponente("W")) {
			if (heroi.getFlecha()) {
				Random chance = new Random();
				boolean matou = chance.nextBoolean();
				if (matou) {
					System.out.println("Voce matou o Wumpus!");
					heroi.pontuar(500);
					Componente wumpus = new Wumpus(i, j, "W");
					excluir(wumpus);
				}
				else {
					heroi.pontuar(-1000);
				}
				return !matou;
			}
			heroi.pontuar(-1000);
			return true;
		}
		return false;
	}
	
	public void descobrir(int i, int j) {
		salas[i][j].descobrir();
	}
	
	public boolean temOuro(int i, int j) {
		return salas[i][j].temComponente("O");
	}
	
	public void imprimir(String nome, int pontuacao) {
		for (int i = 0; i < 4; i++) {
			System.out.print(i+1);
			for (int j = 0; j < 4; j++) {
				System.out.print(" " + salas[i][j]);
			}
			System.out.print("\n");
		}
		System.out.println("  1 2 3 4\n");
		System.out.println("Player: " + nome);
		System.out.println("Score: " + pontuacao + "\n");
	}
	
}
