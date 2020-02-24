package game;

public abstract class ChessPiece {
	public enum ChessPieceColor {
		WHITE, BLACK
	}
	
	private int file;
	private int rank;
	private int moveCount;
	private ChessPieceColor color;

	
	public ChessPiece(int file, int rank, ChessPieceColor color) {
		this.file = file;
		this.rank = rank;
		this.color = color;
		this.moveCount = 0;
	}
	
	
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
		return inBounds(targetFile, targetRank, chessBoard) && inRange(targetFile, targetRank) 
				&& !occupiedBySameColor(targetFile, targetRank, chessBoard);
	}

	protected abstract boolean inRange(int targetFile, int targetRank);
	
	protected abstract ChessPiece duplicate();

	private boolean inBounds(int targetFile, int targetRank, ChessBoard chessBoard) {
		return targetFile >= 0 && targetFile < chessBoard.MAXFILE && 
				targetRank >= 0 && targetRank < chessBoard.MAXRANK;
	}
	

	private boolean occupiedBySameColor(int targetFile, int targetRank, ChessBoard chessBoard) {
		Square targetSquare = chessBoard.getSquare(targetFile, targetRank);
		ChessPiece targetPiece = targetSquare.getPiece();
		
		return targetPiece.getColor() != this.getColor();
	}

	public ChessPieceColor getColor() {
		return this.color;
	}
	
	public int getMoveCount() {
		return this.moveCount;
	}
	
	public int getFile() {
		return this.file;
	}
	
	public int getRank() {
		return this.rank;
	}
}
