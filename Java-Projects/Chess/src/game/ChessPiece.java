package game;

public abstract class ChessPiece {
	public enum ChessPieceColor {
		WHITE, BLACK
	}
	
	private int file;
	private int rank;
	private ChessPieceColor color;
	private ChessBoard chessBoard;
	
	public boolean move(int targetFile, int targetRank) {
		if (validMove(targetFile, targetRank)) {
			capture(targetFile, targetRank);
			return true;
		}
		
		return false;
	}
	
	public ChessPiece capture(int targetFile, int targetRank) {
		Square currSquare = this.chessBoard.getSquare(this.file, this.rank);
		Square targetSquare = this.chessBoard.getSquare(targetFile, targetFile);
		ChessPiece targetPiece = targetSquare.getPiece();
		
		targetSquare.setPiece(this);
		currSquare.setPiece(null);
		
		this.file = targetFile;
		this.rank = targetRank;
		
		return targetPiece;
	}
	
	public abstract boolean validMove(int targetFile, int targetRank);
	
}
