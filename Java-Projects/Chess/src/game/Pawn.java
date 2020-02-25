package game;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

	public Pawn(int file, int rank, ChessPieceColor color) {
		super(file, rank, color);
	}

	@Override
	protected boolean inRange(int targetFile, int targetRank, ChessBoard chessBoard) {
		if (this.getMoveCount() == 0) {
			
		}
		return false;
	}

	@Override
	protected ChessPiece duplicate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ArrayList<int[]> potentialMoves() {
		ArrayList<int[]> lst = new ArrayList<int[]>();

		if (this.getColor() == ChessPieceColor.WHITE) { 
			lst.add(new int[] {this.getFile()+1,this.getRank()});
			if (this.getMoveCount() == 0)
				lst.add(new int[] {this.getFile()+2,this.getRank()});
		}
		else { 
			lst.add(new int[] {this.getFile()-1,this.getRank()});
			if (this.getMoveCount() == 0)
				lst.add(new int[] {this.getFile()-2,this.getRank()});
		}
		
		return null;
	}

}
