package game;

import java.util.ArrayList;

public class King extends ChessPiece {

	public King(int file, int rank, ChessPieceColor color) {
		super(file, rank, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean inRange(int targetFile, int targetRank, ChessBoard chessBoard) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected ChessPiece duplicate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ArrayList<int[]> potentialMoves() {
		// TODO Auto-generated method stub
		return null;
	}

}
