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
		
		for (int c = 0; c < board[0].length; c++) {
			board[1][c] = new ChessPiece(1, c, ChessPieceColor.BLACK, ChessPieceType.PAWN);
			board[MAXFILE-2][c] = new ChessPiece(MAXFILE-2, c, ChessPieceColor.WHITE, ChessPieceType.PAWN);
		}
		
		board[0][0] = new ChessPiece(0, 0, ChessPieceColor.BLACK, ChessPieceType.ROOK); 
		board[0][MAXRANK-1] = new ChessPiece(0, MAXRANK-1, ChessPieceColor.BLACK, ChessPieceType.ROOK); 
		board[MAXFILE-1][0] = new ChessPiece(MAXFILE-1, 0, ChessPieceColor.WHITE, ChessPieceType.ROOK); 
		board[MAXFILE-1][MAXRANK-1] = new ChessPiece(MAXFILE-1, MAXRANK-1, ChessPieceColor.WHITE, ChessPieceType.ROOK);
		
		board[0][1] = new ChessPiece(0, 1, ChessPieceColor.BLACK, ChessPieceType.KNIGHT); 
		board[0][MAXRANK-2] = new ChessPiece(0, MAXRANK-2, ChessPieceColor.BLACK, ChessPieceType.KNIGHT); 
		board[MAXFILE-1][1] = new ChessPiece(MAXFILE-1, 1, ChessPieceColor.WHITE, ChessPieceType.KNIGHT); 
		board[MAXFILE-1][MAXRANK-2] = new ChessPiece(MAXFILE-1, MAXRANK-2, ChessPieceColor.WHITE, ChessPieceType.KNIGHT);
		
		board[0][2] = new ChessPiece(0, 2, ChessPieceColor.BLACK, ChessPieceType.BISHOP); 
		board[0][MAXRANK-3] = new ChessPiece(0, MAXRANK-3, ChessPieceColor.BLACK, ChessPieceType.BISHOP); 
		board[MAXFILE-1][2] = new ChessPiece(MAXFILE-1, 2, ChessPieceColor.WHITE, ChessPieceType.BISHOP); 
		board[MAXFILE-1][MAXRANK-3] = new ChessPiece(MAXFILE-1, MAXRANK-3, ChessPieceColor.WHITE, ChessPieceType.BISHOP);
		
		board[0][3] = new ChessPiece(0, 3, ChessPieceColor.BLACK, ChessPieceType.QUEEN); 
		board[0][MAXRANK-4] = new ChessPiece(0, MAXRANK-4, ChessPieceColor.BLACK, ChessPieceType.KING); 
		board[MAXFILE-1][3] = new ChessPiece(MAXFILE-1, 3, ChessPieceColor.WHITE, ChessPieceType.QUEEN); 
		board[MAXFILE-1][MAXRANK-4] = new ChessPiece(MAXFILE-1, MAXRANK-4, ChessPieceColor.WHITE, ChessPieceType.KING);
	}
	
	public ChessBoard(ChessBoard cb) {
		board = new ChessPiece[MAXFILE][MAXRANK];
		
		for (int r = 0; r < board.length; r++)
			for (int c = 0; c < board[0].length; c++) {
				if (cb.getPiece(r, c) == null)
					this.board[r][c] = null;
				else
					this.board[r][c] = new ChessPiece(cb.getPiece(r, c));
			}
	}
	
	
	public ChessPiece getPiece(int row, int col) {
		if (!Movement.inBounds(row,col))
			return null;
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
		return squareAttacked(king.getRow(), king.getCol(), color);
	}
	
	public boolean squareAttacked(int row, int col, ChessPieceColor color) {
		for (int r = 0; r < board.length; r++)
			for (int c = 0; c < board[0].length; c++) {
				ChessPiece chessPiece = this.getPiece(r,c);
				if (chessPiece != null && chessPiece.getColor() != color
						&& Game.moveType(row, col, this, chessPiece) == 1)
					return true;
			}
			
		return false;
	}

	private ChessPiece findKing(ChessPieceColor color) {
		for (int r = 0; r < board.length; r++)
			for (int c = 0; c < board[0].length; c++) {
				ChessPiece piece = this.getPiece(r,c);
				if (piece != null && piece.getColor() == color && piece.getType() == ChessPieceType.KING)
					return piece;
			}
		return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		for (int r = 0; r < board.length; r++) {
			for (int i = 0; i < (3 * board.length) + 1; i++)
				str.append("-");
			str.append("\n");
			for (int c = 0; c < board[0].length; c++) {
				str.append("|");
				ChessPiece piece = getPiece(r, c);
				if (piece == null)
					str.append("  ");
				else
					str.append(piece.toString());
			}
			str.append("|\n");
		}
		for (int i = 0; i < (3 * board.length) + 1; i++)
			str.append("-");
		return str.toString();
	}
	
}
