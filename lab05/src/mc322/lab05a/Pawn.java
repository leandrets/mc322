package mc322.lab05a;

public class Pawn {
	private char color;
	public int i, j;
	
	public Pawn(int i, int j, char color) {
		this.i = i;
		this.j = j;
		this.color = color;
	}
	
	public char getColor() {
		return color;
	}
	
	public boolean validMove(int vertDist, char[] path) {
		
		// Checks if move respects size and direction
		if (path.length != 1 && path.length != 2) {
			return false;
		}
		if (this.color == 'b' && vertDist < 0) {
			return false;
		}
		else if (this.color == 'p' && vertDist > 0) {
			return false;
		}
		
		// If move is a capture, checks if victim house has enemy piece
		if (path[0] == this.color) {
			return false;
		}
		
		return true;
	}
	
}