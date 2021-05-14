package mc322.lab06;

public class Heroi extends Componente {
	private boolean ouroCapturado, flechaEquipada, temFlecha, ganhou, perdeu;
	private int pontuacao;
	private String nome;
	
	public Heroi(int i, int j, String rep) {
		super(i, j, rep);
		temFlecha = true;
		flechaEquipada = false;
		ouroCapturado = false;
		ganhou = false;
		perdeu = false;
		pontuacao = 0;
	}
	
	public void mover(int direcaoI, int direcaoJ, Caverna caverna) {
		int iDestino = i + direcaoI;
		int jDestino = j + direcaoJ;
		if (coordenadasValidas(iDestino, jDestino)) {
			caverna.excluir(this);
			i = iDestino;
			j = jDestino;
			caverna.incluir(this);
			caverna.descobrir(i, j);
			if (flechaEquipada) {
				pontuar(-100);
			}
			perdeu = caverna.verificarMorte(this);
			flechaEquipada = false;
			pontuar(-15);
		}
		else if (iDestino == 0 || jDestino == -1) {
			if (!ouroCapturado) {
				System.out.println("Voce ainda nao encontrou o Ouro, entao nao pode sair!"
						+ "Continue procurando!");
			}
			else {
				ganhou = true;
				pontuar(1000);
			}
		}
	}
	
	public void pontuar(int pontos) {
		pontuacao += pontos;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void equiparFlecha() {
		if (!temFlecha) {
			System.out.println("Voce nao tem mais flechas!\n");
		}
		else {
			temFlecha = false;
			flechaEquipada = true;
			System.out.println("Sua flecha está equipada!\n");
		}
	}
	
	public void capturarOuro(Caverna caverna) {
		if (ouroCapturado) {
			System.out.println("Voce ja capturou o Ouro! Saia da caverna!\n");
		}
		else if (caverna.temOuro(i, j)) {
			ouroCapturado = true;
			System.out.println("Voce capturou o Ouro! Saia da caverna!\n");
			Componente ouro = new Ouro(i, j, "O");
			caverna.excluir(ouro);
		}
		else {
			System.out.println("Nao ha Ouro nesta sala! Continue procurando!\n");
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getPontuacao() {
		return pontuacao;
	}
	
	public boolean getFlecha() {
		return flechaEquipada;
	}
	
	public boolean getGanhou() {
		return ganhou;
	}
	
	public boolean getOuroCapturado() {
		return ouroCapturado;
	}
	
	public boolean getPerdeu() {
		return perdeu;
	}
	
}
