package mc322.lab05a;

public class Board {
	private String state;
	private Pawn[][] pawns;
	private King[][] kings;
	
	public Board() {
		state = "";
		pawns = new Pawn[8][8];
		kings = new King[8][8];
		
		boolean presence = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {	
				
				if (presence) {
					if (i < 3) {
						pawns[i][j] = new Pawn(i, j, 'b');
					}
					else {
						pawns[i][j] = new Pawn(i, j, 'p');
					}
				}
				else {
					pawns[i][j] = null;
				}
				
				kings[i][j] = null;
				
				
				if (j == 7 || i == 3 || i == 4) {
					continue;
				}
				else {
					presence = (presence) ? false : true;
					continue;
				}
			}
		}
		updateState();
	}
	
	public void updateState() {
		state = "";
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j <= 7; j++) {
				if (pawns[i][j] != null) {
					state += pawns[i][j].getColor();
				}
				else if (kings[i][j] != null) {
					state += kings[i][j].getColor();
				}
				else {
					state += '-';
				}
			}
			state += '\n';
		}
	}
	
	public String getState() {
		return state;
	}
	
	public boolean validCoordinates(int i, int j) {
		if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
			return true;
		}
		return false;
	}
	
	public char[] buildPath(int iSrc, int jSrc, int vertDist, int horizDist) {
		int iStep = vertDist/Math.abs(vertDist);
		int jStep = horizDist/Math.abs(horizDist);
		int iMid = iSrc + iStep, jMid = jSrc + jStep;
		char[] path = new char[Math.abs(vertDist)]; 
		int index = 0;
		while (index < path.length) {	
			if (pawns[iMid][jMid] != null) {
				path[index] = pawns[iMid][jMid].getColor();
			}
			else if (kings[iMid][jMid] != null) {
				path[index] = kings[iMid][jMid].getColor();
			}
			else {
				path[index] = '-';
			}
			iMid += iStep;
			jMid += jStep;
			index++;
		}
		return path;
	}
	
	public void requestMove(String command) {
		String source = command.substring(0, 2);
		String target = command.substring(3, 5);
		int iSrc = Integer.parseInt(source.substring(1, 2)) - 1;
		int jSrc = (int)source.charAt(0) - 97;
		int iTrg = Integer.parseInt(target.substring(1, 2)) - 1;
		int jTrg = (int)target.charAt(0) - 97;
		boolean valid = false;
		
		// Checks if coordinates are inside board
		if (validCoordinates(iTrg, jTrg) && validCoordinates(iSrc, jSrc)) {
			int vertDist = iTrg - iSrc;
			int horizDist = jTrg - jSrc;
			
			// Checks if move is diagonal
			if (Math.abs(horizDist) == Math.abs(vertDist)) {
				char[] path = buildPath(iSrc, jSrc, vertDist, horizDist);
				Pawn pSource = pawns[iSrc][jSrc];
				King kSource = kings[iSrc][jSrc];
				
				// Checks if source house is empty and asks piece if move is valid
				if (pSource != null && pSource.validMove(vertDist, path)) {
					if (iTrg == 7 || iTrg == 0) { // Checks if there's piece transformation
						kings[iTrg][jTrg] = new King(iTrg, jTrg, Character.toUpperCase(pSource.getColor()));
					}
					else {
						pawns[iTrg][jTrg] = new Pawn(iTrg, jTrg, pSource.getColor());
					}				
					pawns[iSrc][jSrc] = null;
					valid = true;
				}
				else if (kSource != null && kSource.validMove(path)) {
					kings[iTrg][jTrg] = new King(iTrg, jTrg, kSource.getColor());
					kings[iSrc][jSrc] = null;
					valid = true;
				}
				
				// If move was a capture attempt and is valid, captures the victim 
				if (valid) {
					int iVic = iSrc + (vertDist/2);
					int jVic = jSrc + (horizDist/2);
					if (path[0] == 'b' || path[0] == 'p') {
						pawns[iVic][jVic] = null;
					}
					else if (path[0] == 'B' || path[0] == 'P') {
						kings[iVic][jVic] = null;
					}
				}
			}
		}
		
		if (!valid) {
			System.out.println("Source: " + source);
			System.out.println("Target: " + target);
			System.out.println("Comando Inválido!\n");
			return;
		}
		updateState();
		show(source, target);
	}
	
	public void show(String source, String target) {
		if (source == null && target == null) {
			System.out.println("Tabuleiro inicial: ");
		}
		else {
			System.out.println("Source: " + source);
			System.out.println("Target: " + target);
		}
		int n = 8;
		int i = 0;
		do {
			System.out.print(n);
			n--;			
			for (int j = i; j < i + 9; j++) {
				System.out.print(" " + state.charAt(j));
			}
			i += 9;
		} while (i < state.length());
		System.out.println("  a b c d e f g h\n");
	}
}