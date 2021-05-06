package mc322.lab05b;

public class AppDama {

	public static String[] executaJogo(String input, String output) {
		CSVHandling csv = new CSVHandling();
	    csv.setDataSource(input);
	    String comandos[] = csv.requestCommands();
	    
	    String[] estados = new String[comandos.length+1];
	    Tabuleiro tab = new Tabuleiro();
	    estados[0] = tab.getEstado();
	    tab.imprimirTabuleiro(null, null, true);
	    
	    for (int i = 0; i < comandos.length; i++) {
	    	tab.solicitaMovimento(comandos[i]);
	    	estados[i+1] = tab.getEstado();
	    }
        tab.exportarArquivo(output);
	    return estados;
	}
	public static void main(String args[]) {
		executaJogo(args[0], args[1]);
	}

}
