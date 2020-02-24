package game;

public class Pawn extends ChessPiece {

	public Pawn(int file, int rank, ChessPieceColor color, ChessBoard chessBoard) {
		super(file, rank, color);
	}

	@Override
	protected boolean inRange(int targetFile, int targetRank) {
		
		return false;
	}

	@Override
	protected ChessPiece duplicate() {
		// TODO Auto-generated method stub
		return null;
	}

}
