package mc322.lab05a;

public class AppDama {

	public static String[] executaJogo(String path) {
		CSVReader csv = new CSVReader();
	    csv.setDataSource(path);
	    String commands[] = csv.requestCommands();
	    
	    String[] states = new String[commands.length+1];
	    Board board = new Board();
	    states[0] = board.getState();
	    board.show(null, null);
	    
	    for (int i = 0; i < commands.length; i++) {
	    	board.requestMove(commands[i]);
	    	states[i+1] = board.getState();
	    }
	    return states;
	}
	public static void main(String args[]) {
		executaJogo(args[0]);
	}

}
