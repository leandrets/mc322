package mc322.lab06;

import java.util.Scanner;

public class AppMundoWumpus {
	
	public static void main(String args[]) {
		/* GeradorDeCaverna gerador = new GeradorDeCaverna();
		gerador.escreverConteudo(args[0]); */
		CSVHandling csv = new CSVHandling();
		csv.setDataSource(args[0]);
		String[][] salas = csv.requestCommands();
		
		Montador montador = new Montador();
		Caverna caverna = new Caverna();
		Heroi heroi = new Heroi(0, 0, "P");
		if (montador.montarCaverna(salas, caverna, heroi) == false) {
			System.out.println("Caverna inválida! É obrigatório ter:\n"
					+ "		1 Heroi na posicao (1, 1)\n"
					+ "		1 Wumpus\n"
					+ "		1 Ouro\n"
					+ "		2 ou 3 Buracos.");
			return;
		}
		
		Scanner teclado = new Scanner(System.in);
		Controle controle = new Controle();
		heroi.setNome(controle.inicializar(teclado));
		caverna.descobrir(0, 0);
		
		String comando = "";
		boolean terminar = false;
		do {
			caverna.imprimir(heroi.getNome(), heroi.getPontuacao());
			comando = teclado.nextLine();
			terminar = controle.executar(comando, caverna, heroi);
		} while (!terminar);
	}
}
