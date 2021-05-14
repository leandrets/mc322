package mc322.lab06;

public class Sala {
	private Componente[] conteudo = new Componente[4];
	private int ultimo = 0;
	private boolean descoberta = false;
	
	public Sala(boolean entrada) {
		conteudo = new Componente[4];
		ultimo = 0;
		if (entrada) {
			descoberta = true;
		}
		else {
			descoberta = false;
		}
	}
	
	/* Adiciona um componente no seu conteúdo. Retorna falso se: já existe um componente igual na sala;
	 * ou se está se tentando adicionar mais de um componente único na mesma sala (Buraco, Ouro e Wumpus).*/
	public boolean adicionar(Componente comp) {
		if (temComponente(comp.getRep())) {
			return false;
		}
		conteudo[ultimo] = comp;
		String[] compsUnicos = {"O", "B", "W"};
		if (temComponente(compsUnicos) > 1) {
			return false;
		}
		ultimo++;
		return true;
	}
	
	/* Recebe um componente (nesse caso, Heroi ou Ouro) e o remove da sala.*/
	public void remover(Componente comp) {
		for (int k = 0; k < conteudo.length; k++) {
			if (conteudo[k] != null) {
				if (conteudo[k].getRep().equals(comp.getRep())) {
					conteudo[k] = null;
					remanejarConteudo(k);
					break;
				}
			}
		}

	}
	
	/* Retorna true há um determinado componente na sala, false caso contrário.*/
	public boolean temComponente(String rep) {
		for (int k = 0; k < ultimo; k++) {
			if (conteudo[k] != null) {
				if (conteudo[k].getRep().equals(rep)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/* Recebe vários componentes e retorna quantos deles existem na sala.*/
	public int temComponente(String[] reps) {
		int contador = 0;
		for (int k = 0; k < reps.length; k++) {
			if (temComponente(reps[k])) {
				contador++;
			}
		}
		return contador;
	}
	
	public String toString() {
		if (descoberta) {
			if (temComponente("O")) {
				return "O";
			}
			else if (temComponente("B")) {
				return "B";
			}
			else if (temComponente("W")) {
				return "W";
			}
			else if (temComponente("O")) {
				return "0";
			}
			else if (temComponente("P")) {
				return "P";
			}
			else if (temComponente("f")) {
				return "f";
			}
			else if (temComponente("b")) {
				return "b";
			}
			else {
				return "#";
			}
		}
		return "-";
	}
	
	/* Chamada após a remoção de um componente, reorganiza o vetor conteudo para que não
	haja 'fendas' no vetor. */
	public void remanejarConteudo(int indice) {
		for (int i = indice; i < 3; i++) {
			conteudo[i] = conteudo[i+1]; 
		}
		conteudo[3] = null;
		int i = 0;
		while (conteudo[i] != null) {
			i++;
		}
		ultimo = i;
	}
	
	/* Atriubui true ao booleano descoberta e pede ao componente que exiba sua mensagem. */
	public void descobrir() {
		for (int k = 0; k < conteudo.length; k++) {
			if (conteudo[k] != null) {
				if (!conteudo[k].getRep().equals("O") || !descoberta){
					conteudo[k].exibirMensagem();
				}
			}
		}
		descoberta = true;
	}
}
