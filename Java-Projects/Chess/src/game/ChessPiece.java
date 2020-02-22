package game;

public abstract class ChessPiece {
	public enum ChessPieceColor {
		WHITE, BLACK
	}
	
	private int file;
	private int rank;
	private ChessPieceColor color;
	private ChessBoard chessBoard;
	
	public ChessPiece(int file, int rank, ChessPieceColor color, ChessBoard chessBoard) {
		this.file = file;
		this.rank = rank;
		this.color = color;
		this.chessBoard = chessBoard;
	}
	
	public boolean move(int targetFile, int targetRank) {
		if (validMove(targetFile, targetRank)) {
			capture(targetFile, targetRank);
			return true;
		}
		
		return false;
	}
	
	public ChessPiece capture(int targetFile, int targetRank) {
		Square currSquare = this.chessBoard.getSquare(this.file, this.rank);
		Square targetSquare = this.chessBoard.getSquare(targetFile, targetRank);
		ChessPiece targetPiece = targetSquare.getPiece();
		
		targetSquare.setPiece(this);
		currSquare.setPiece(null);
		
		this.file = targetFile;
		this.rank = targetRank;
		
		return targetPiece;
	}
	
	public  boolean validMove(int targetFile, int targetRank) {
		return inBounds(targetFile, targetRank) && inRange(targetFile, targetRank) 
				&& !occupiedBySameColor(targetFile, targetRank);
	}

	protected abstract boolean inRange(int targetFile, int targetRank);

	private boolean inBounds(int targetFile, int targetRank) {
		return targetFile >= 0 && targetFile < this.chessBoard.MAXFILE && 
				targetRank >= 0 && targetRank < this.chessBoard.MAXRANK;
	}
	

	private boolean occupiedBySameColor(int targetFile, int targetRank) {
		Square targetSquare = this.chessBoard.getSquare(targetFile, targetRank);
		ChessPiece targetPiece = targetSquare.getPiece();
		
		return targetPiece.getChessPieceColor() != this.getChessPieceColor();
	}

	public ChessPieceColor getChessPieceColor() {
		return this.color;
	}
	
}
