package mc322.lab05a;

public class King {
	private char color;
	public int i, j;
	
	public King(int i, int j, char color) {
		this.i = i;
		this.j = j;
		this.color = color;
	}
	
	public char getColor() {
		return color;
	}
	
	public boolean validMove(char[] path) {
		
		//Checks if move is more than size 0 
		if (path.length == 0) {
			return false;
		}
		
		// If move is a capture, checks if victim house has enemy piece
		if (path[0] == this.color) {
			return false;
		}

		// If move is longer than two houses, checks if middle houses are empty
		if (path.length > 2) {
			for (int i = 0; i < path.length; i++) {
				if (path[i] != '-') {
					return false;
				}
			}
		}

		return true;
	}
	
}