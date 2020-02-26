package game;

import game.ChessPiece.ChessPieceColor;
import game.ChessPiece.ChessPieceType;

public class ChessBoard {
	private ChessPiece[][] board;
	static final int MAXFILE = 8; //row
	static final int MAXRANK = 8; //col
	public ChessPiece enPassantPawn;
	public int[] enPassantCoors;
	public ChessBoard() {
		board = new ChessPiece[MAXFILE][MAXRANK];
		
		for (int c = 0; c < board[0].length; c++)
			for (int r = 0; r < board.length; r++) {
				
			}
	}
	
	public ChessBoard(ChessBoard cb) {
		board = new ChessPiece[MAXFILE][MAXRANK];
		
		for (int c = 0; c < board[0].length; c++)
			for (int r = 0; r < board.length; r++) {
				if (cb.getPiece(r, c) == null)
					this.board[r][c] = null;
				else
					this.board[r][c] = new ChessPiece(cb.getPiece(r, c));
			}
	}
	
	
	public ChessPiece getPiece(int row, int col) {
		return board[row][col];
	}
	
	public void setPiece(int row, int col, ChessPiece piece) {
		board[row][col] = piece;
	}
	
	public ChessPiece[][] getBoard() {
		return this.board;
	}
	
	public boolean inCheck(ChessPieceColor color) {
		ChessPiece king = findKing(color);
		
		for (int c = 0; c < board[0].length; c++)
			for (int r = 0; r < board.length; r++) {
				ChessPiece chessPiece = this.getPiece(r,c);
				if (chessPiece != null && chessPiece.getColor() != color && chessPiece != king
						&& Game.validMove(king.getRow(), king.getCol(), this, chessPiece))
					return true;
			}
			
		return false;
	}

	private ChessPiece findKing(ChessPieceColor color) {
		for (int c = 0; c < board[0].length; c++)
			for (int r = 0; r < board.length; r++) {
				ChessPiece piece = this.getPiece(r,c);
				if (piece != null && piece.getColor() == color && piece.getType() == ChessPieceType.KING)
					return piece;
			}
		return null;
	}
	
	
}
