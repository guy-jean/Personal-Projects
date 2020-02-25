package game;

import java.util.ArrayList;

public class ChessPiece {
	public enum ChessPieceColor {
		WHITE, BLACK
	}
	
	public enum ChessPieceType {
		PAWN, ROOK, BISHOP, KNIGHT, QUEEN, KING
	}
	
	private ChessPieceType type;
	private int row;
	private int col;
	private int moveCount;
	private ChessPieceColor color;

	
	public ChessPiece(int row, int col, ChessPieceColor color, ChessPieceType type) {
		this.type = type;
		this.row = row;
		this.col = col;
		this.color = color;
		this.moveCount = 0;
	}
	
	public ChessPiece(ChessPiece chessPiece) {
		this.type = chessPiece.getType();
		this.row = chessPiece.getRow();
		this.col = chessPiece.getCol();
		this.color = chessPiece.getColor();
		this.moveCount = chessPiece.getMoveCount();
	}
	
	/*
	public boolean move(int targetFile, int targetRank, ChessBoard chessBoard) {
		if (validMove(targetFile, targetRank, chessBoard)) {
			capture(targetFile, targetRank, chessBoard);
			this.moveCount++;
			return true;
		}
		
		return false;
	}
	
	public ChessPiece capture(int targetFile, int targetRank, ChessBoard chessBoard) {
		Square currSquare = chessBoard.getSquare(this.file, this.rank);
		Square targetSquare = chessBoard.getSquare(targetFile, targetRank);
		ChessPiece targetPiece = targetSquare.getPiece();
		
		targetSquare.setPiece(this);
		currSquare.setPiece(null);
		
		this.file = targetFile;
		this.rank = targetRank;
		
		return targetPiece;
	}
	
	public  boolean validMove(int targetFile, int targetRank, ChessBoard chessBoard) {
		return inBounds(targetFile, targetRank, chessBoard) && inRange(targetFile, targetRank, chessBoard) 
				&& !occupiedBySameColor(targetFile, targetRank, chessBoard);
	}

	protected abstract boolean inRange(int targetFile, int targetRank, ChessBoard chessBoard);
	*/
	protected ChessPiece duplicate() {
		return null;
	}
	
	/*
	protected abstract ArrayList<int[]> potentialMoves();

	private boolean inBounds(int targetFile, int targetRank, ChessBoard chessBoard) {
		return targetFile >= 0 && targetFile < chessBoard.MAXFILE && 
				targetRank >= 0 && targetRank < chessBoard.MAXRANK;
	}
	

	private boolean occupiedBySameColor(int targetFile, int targetRank, ChessBoard chessBoard) {
		Square targetSquare = chessBoard.getSquare(targetFile, targetRank);
		ChessPiece targetPiece = targetSquare.getPiece();
		
		return targetPiece.getColor() != this.getColor();
	}
	*/
	public ChessPieceColor getColor() {
		return this.color;
	}
	
	public int getMoveCount() {
		return this.moveCount;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public void incMove( ) {
		this.moveCount++;
	}
	
	public ChessPieceType getType() {
		return this.type;
	}
}
