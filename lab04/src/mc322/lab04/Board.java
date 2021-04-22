package lab04;

public class Board {
	String state;
	Piece[] pieces = new Piece[49];
	
	public Board() {
		for (int i = 0; i < 49; i++) {
			pieces[i] = new Piece();
			if (i == 24) {
				pieces[i].piece = '-';
			}
			else if ((i >= 14 && i <= 34) || ((i%7 >= 2) && (i%7 <= 4))) {
				pieces[i].piece = 'P';
			}
			else {
				pieces[i].piece = ' ';
			}
		}
		updateState();
		show(null, null);
	}
	
	public void updateState() {
		state = "";
		int n = 6;
		for (int i = 0; i < 49; i++) {
			state += pieces[i].piece;
			if (i == n) {
				state += '\n';
				n += 7;
			}
		}
	}
	
	public void show(String source, String target) {
		if (source == null && target == null) {
			System.out.println("Tabuleiro inicial: ");
		}
		else {
			System.out.println("Source: " + source);
			System.out.println("Target: " + target);
		}
		int n = 7;
		System.out.print(n);
		n--;
		for (int i = 0; i < state.length(); i++) {
			System.out.print(" " + state.charAt(i));
			if (state.charAt(i) == '\n' && n > 0) {
				System.out.print(n);
				n--;
			}
		}
		System.out.print("  a b c d e f g\n\n");
	}
	
	public static int convertCoordinates(String coord) {
		int n = Integer.parseInt(coord.substring(1, 2));
		n = (7-n) * 7;
		int l = (int)coord.charAt(0) - 97;
		return n+l;
	}
	
	public boolean isNotInVector(int n, int[] vector) {
		for (int i = 0; i < vector.length; i++) {
			if (vector[i] == n) {
				return false;
			}
		}
		return true;
	}
	
	public boolean validMove(int src, int victim, int trg, int dist) {
		int[] leftColumn = {0, 7, 14, 21, 28, 35, 42};
		int[] rightColumn = {6, 13, 20, 27, 34, 41, 48};
		if (src < 48 && trg < 48 && (pieces[src].piece == 'P' && pieces[victim].piece == 'P' 
				&& pieces[trg].piece == '-' )) {
			if (Math.abs(dist) == 2) {
				if (isNotInVector(trg, leftColumn) && isNotInVector(trg, rightColumn)) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}
	
	public void makeMove(String move) {
		String source = move.substring(0, 2);
		String target = move.substring(3, 5);
		int src = convertCoordinates(source);
		int trg = convertCoordinates(target);
		int dist = trg - src, victim = 0;
		if (dist == 2) {
			victim = src+1;
		}
		else if (dist == -2) {
			victim = src-1;
		}
		else if (dist == 14) {
			victim = src+7;
		}
		else if (dist == -14) {
			victim = src-7;
		}
		if (validMove(src, victim, trg, dist)) {
			pieces[src].piece = '-';
			pieces[victim].piece = '-';
			pieces[trg].piece = 'P';
			updateState();
			show(source, target);
		}
		else {
			System.out.println("Source: " + source);
			System.out.println("Target: " + target);
			System.out.println("Invalid move!");
		}
	}
	
}
