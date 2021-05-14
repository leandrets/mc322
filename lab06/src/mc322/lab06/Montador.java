package mc322.lab06;

public class Montador {

	public boolean montarCaverna(String salas[][], Caverna caverna, Heroi heroi) {
		int numHeroi = 0, numOuro = 0, numWumpus = 0, numBuraco = 0;
		for (int k = 0; k < salas.length; k++) {
			int i = Integer.parseInt(salas[k][0].substring(0, 1)) - 1;
			int j = Integer.parseInt(salas[k][0].substring(2, 3)) - 1;
			String conteudo = salas[k][1];
			Componente componente = null;
			if (conteudo.equals("P")) {
				numHeroi++;
				if (i != 0 || j != 0) {
					return false;
				}
				if (caverna.incluir(heroi) == false) {
					return false;
				}
			}
			else if (conteudo.equals("O")) {
				componente = new Ouro(i, j, conteudo);
				numOuro++;
			}	
			else if (conteudo.equals("B")) {
				componente = new Buraco(i, j, conteudo);
				numBuraco++;
			}
			else if (conteudo.equals("W")) {
				componente = new Wumpus(i, j, conteudo);
				numWumpus++;
			}
			else {
				continue;
			}
			if (i != 0 || j!= 0) {
				if (caverna.incluir(componente) == false) {
					return false;
				}
			}
		}
		if (numHeroi != 1 || numOuro != 1 || numWumpus != 1 || numBuraco < 2 || numBuraco > 3) {
			return false;
		}
		return true;
	}
}
