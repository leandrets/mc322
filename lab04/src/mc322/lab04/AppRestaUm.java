package mc322.lab04;

public class AppRestaUm {
	
	public static String[] executaJogo(String path) {
		CSVReader csv = new CSVReader();
	    csv.setDataSource(path);
	    String commands[] = csv.requestCommands();
	    
	    String[] states = new String[commands.length+1];
	    Board board = new Board();
	    states[0] = board.state;
	    for (int i = 0; i < commands.length; i++) {
	    	board.makeMove(commands[i]);
	    	states[i+1] = board.state;
	    }
	    return states;
	}
	
	public static void main(String args[]) {
		executaJogo(args[0]);
	}
}
