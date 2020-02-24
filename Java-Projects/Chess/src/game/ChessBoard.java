package game;

public class ChessBoard {
	private Square[][] board;
	static final int MAXFILE = 8;
	static final int MAXRANK = 8;
	
	public ChessBoard() {
		board = new Square[MAXFILE][MAXRANK];
		
		for (int c = 0; c < board[0].length; c++)
			for (int r = 0; r < board.length; r++) {
				
			}
	}
	
	public ChessBoard(ChessBoard cb) {
		board = new Square[MAXFILE][MAXRANK];
		
		for (int c = 0; c < board[0].length; c++)
			for (int r = 0; r < board.length; r++) {
				this.board[r][c] = new Square(cb.getSquare(r, c));
			}
	}
	
	public Square getSquare(int file, int rank) {
		return board[file][rank];
	}
	
	
}
