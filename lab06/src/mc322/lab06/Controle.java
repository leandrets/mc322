package mc322.lab06;

import java.util.Scanner;

public class Controle {
	
	public String inicializar(Scanner teclado) {
		System.out.println("Como devemos te chamar, heroi?");
		String nome = teclado.nextLine();
		System.out.println(nome + ", encontre o Ouro e saia da caverna sem ser morto pelo Wumpus "
				+ "e sem cair em um buraco!\n"
				+ "Buracos causam brisa nas salas vizinhas e "
				+ "o Wumpus causa fedor nas salas vizinhas.\n"
				+ "Voce so tem uma flecha e deve equipa-la antes de entrar em uma sala.\n"
				+ "A flecha será disparada automaticamente ao adentrar a sala, entao use com sabedoria.\n"
				+ "Pontuacao: +1000 por sair da caverna com o Ouro\n"
				+ "           -1000 por ser morto pelo Wumpus ou cair em um buraco\n"
				+ "           -15 para cada movimento dentro da caverna\n"
				+ "           -100 por usar a flecha\n"
				+ "           +500 por matar o Wumpus\n"
				+ "Comandos: w para ir para cima\n"
				+ "          a para ir para a esquerda\n"
				+ "          s para ir para baixo;\n"
				+ "          d para ir para a direita\n"
				+ "          k para equipar a flecha\n"
				+ "          c para pegar o ouro\n"
				+ "          q para sair do jogo.\n"
				+ "Boa Sorte!\n");
		return nome;
	}
	
	/* Recebe o comando e pede ao Herói para executá-lo. Se o herói ganhou, perdeu, ou pediu para sair,
	 * retorna true (jogo terminou), caso contrário retorna false (jogo deve continuar).*/
	public boolean executar(String comando, Caverna caverna, Heroi heroi) {
		boolean pediuParaSair = false;
		if (comando.equals("w")) {
			heroi.mover(-1, 0, caverna);
		}	
		else if (comando.equals("a")) {
			heroi.mover(0, -1, caverna);
		}
		else if (comando.equals("s")) {
			heroi.mover(1, 0, caverna);
		}
		else if (comando.equals("d")) {
			heroi.mover(0, 1, caverna);
		}
		else if (comando.equals("k")) {
			heroi.equiparFlecha();
		}
		else if (comando.equals("c")) {
			heroi.capturarOuro(caverna);
		}
		else if (comando.equals("q")) {
			pediuParaSair = true;
		}
		else {
			System.out.println("Comando Invalido!");
			return false;
		}
		if (heroi.getGanhou() ||heroi.getPerdeu() || pediuParaSair) {
			caverna.imprimir(heroi.getNome(), heroi.getPontuacao());
			verificarFinal(heroi);
			return true;
		}
		return false;
	}

	/* Chamada quando o jogo termina, imprime a mensagem correspondante para vitória, derrota,
	 * ou desistência.*/ 
	public void verificarFinal(Heroi heroi) {
		if (heroi.getGanhou() && heroi.getOuroCapturado()) {
			System.out.println("Voce ganhou =D !!!");
		}
		else if (heroi.getPerdeu()) {
			System.out.println("Voce perdeu =( ...");
		}
		else {
			System.out.println("Volte sempre !");
		}
	}
	
}
