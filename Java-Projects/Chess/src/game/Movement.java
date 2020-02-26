package game;

import java.util.ArrayList;

import game.ChessPiece.ChessPieceColor;
import game.ChessPiece.ChessPieceType;

public class Movement {

	public static ArrayList<int[]> getPawnMoves(ChessBoard chessBoard, ChessPiece chessPiece) {
		ArrayList<int[]> lst = new ArrayList<int[]>();

		if (chessPiece.getColor() == ChessPieceColor.WHITE && chessPiece.getRow() < ChessBoard.MAXFILE) {
			ChessPiece rDiag = chessBoard.getPiece(chessPiece.getRow()+1,chessPiece.getCol()+1);
			if (rDiag != null && rDiag.getColor() != chessPiece.getColor())
				lst.add(new int[]{chessPiece.getRow()+1,chessPiece.getCol()+1});
			
			ChessPiece lDiag = chessBoard.getPiece(chessPiece.getRow()+1,chessPiece.getCol()-1);
			if (lDiag != null && lDiag.getColor() != chessPiece.getColor())
				lst.add(new int[]{chessPiece.getRow()+1,chessPiece.getCol()-1});
			
			if (chessBoard.getPiece(chessPiece.getRow()+1, chessPiece.getCol()) == null) {
				lst.add(new int[] {chessPiece.getRow()+1,chessPiece.getCol()});
				
				boolean moveTwo = chessPiece.getMoveCount() == 0 && chessBoard.getPiece(chessPiece.getRow()+2, chessPiece.getCol()) == null;
				if (moveTwo)
					lst.add(new int[] {chessPiece.getRow()+2,chessPiece.getCol()});
			}
		}
		else if (chessPiece.getRow() > 0){ 
			ChessPiece rDiag = chessBoard.getPiece(chessPiece.getRow()-1,chessPiece.getCol()+1);
			if (rDiag != null && rDiag.getColor() != chessPiece.getColor())
				lst.add(new int[]{chessPiece.getRow()-1,chessPiece.getCol()+1});
			
			ChessPiece lDiag = chessBoard.getPiece(chessPiece.getRow()-1,chessPiece.getCol()-1);
			if (lDiag != null && lDiag.getColor() != chessPiece.getColor())
				lst.add(new int[]{chessPiece.getRow()-1,chessPiece.getCol()-1});
			
			if (chessBoard.getPiece(chessPiece.getRow()-1, chessPiece.getCol()) == null) {
			lst.add(new int[] {chessPiece.getRow()-1,chessPiece.getCol()});
			
			boolean moveTwo = chessPiece.getMoveCount() == 0 && chessBoard.getPiece(chessPiece.getRow()-2, chessPiece.getCol()) == null;
			
			if (moveTwo)
				lst.add(new int[] {chessPiece.getRow()-2,chessPiece.getCol()});
			}
		}
		
		boolean enPassantCapture = chessBoard.enPassantPawn != null && chessBoard.enPassantPawn.getColor() != chessPiece.getColor();
		
				
		if (enPassantCapture) {
			if (chessPiece.getColor() == ChessPieceColor.WHITE) { 
				if (chessBoard.enPassantCoors[0] == chessPiece.getRow()+1 && 
						(chessBoard.enPassantCoors[1] == chessPiece.getCol()+1 || chessBoard.enPassantCoors[1] == chessPiece.getCol()-1))
					lst.add(chessBoard.enPassantCoors);
			}
			else {
				if (chessBoard.enPassantCoors[0] == chessPiece.getRow()-1 && 
						(chessBoard.enPassantCoors[1] == chessPiece.getCol()+1 || chessBoard.enPassantCoors[1] == chessPiece.getCol()-1))
					lst.add(chessBoard.enPassantCoors);
			}
		}
		
		return lst;
	}

	public static ArrayList<int[]> getRookMoves(ChessBoard chessBoard, ChessPiece chessPiece) {
		ArrayList<int[]> lst = new ArrayList<int[]>();
		boolean blocked = false;
		
		for (int r = chessPiece.getRow()-1; !blocked && r >= 0; r--) {
			int currCol = chessPiece.getCol();
			if (chessBoard.getPiece(r, currCol) != null) {
				blocked = true;
				if (chessBoard.getPiece(r, currCol).getColor() != chessPiece.getColor())
					lst.add(new int[] {r, currCol}); 
			}
			else {
				lst.add(new int[] {r, currCol}); 
			}
		}
		
		blocked = false;
		
		for (int r = chessPiece.getRow()+1; !blocked && r <= ChessBoard.MAXFILE; r++) {
			int currCol = chessPiece.getCol();
			if (chessBoard.getPiece(r, currCol) != null) {
				blocked = true;
				if (chessBoard.getPiece(r, currCol).getColor() != chessPiece.getColor())
					lst.add(new int[] {r, currCol}); 
			}
			else {
				lst.add(new int[] {r, currCol}); 
			}
		}
		
		blocked = false;
		
		for (int c = chessPiece.getCol()-1;!blocked && c >= 0; c--) {
			int currRow = chessPiece.getRow();
			if (chessBoard.getPiece(currRow, c) != null) {
				blocked = true;
				if (chessBoard.getPiece(currRow, c).getColor() != chessPiece.getColor())
					lst.add(new int[] {currRow, c}); 
			}
			else {
				lst.add(new int[] {currRow, c}); 
			}
		}
		
		blocked = false;
		
		for (int c = chessPiece.getCol()+1;!blocked && c <= ChessBoard.MAXRANK; c++) {
			int currRow = chessPiece.getRow();
			if (chessBoard.getPiece(currRow, c) != null) {
				blocked = true;
				if (chessBoard.getPiece(currRow, c).getColor() != chessPiece.getColor())
					lst.add(new int[] {currRow, c}); 
			}
			else {
				lst.add(new int[] {currRow, c}); 
			}
		}
		
		return lst;
	}

	public static ArrayList<int[]> getBishopMoves(ChessBoard chessBoard, ChessPiece chessPiece) {
		ArrayList<int[]> lst = new ArrayList<int[]>();
		
		int currRow = chessPiece.getRow()+1;
		int currCol = chessPiece.getCol()+1;
		boolean blocked = false;
		
		while (!blocked && inBounds(currRow,currCol)) {
			ChessPiece tempPiece = chessBoard.getPiece(currRow, currCol);
			if (tempPiece != null) {
				blocked = true;
				if (tempPiece.getColor() != chessPiece.getColor())
					lst.add(new int[]{currRow,currCol});
			}
			else {
				lst.add(new int[]{currRow,currCol});
			}
			currRow++;
			currCol++;
		}
		
		currRow = chessPiece.getRow()-1;
		currCol = chessPiece.getCol()+1;
		blocked = false;
		
		while (!blocked && inBounds(currRow,currCol)) {
			ChessPiece tempPiece = chessBoard.getPiece(currRow, currCol);
			if (tempPiece != null) {
				blocked = true;
				if (tempPiece.getColor() != chessPiece.getColor())
					lst.add(new int[]{currRow,currCol});
			}
			else {
				lst.add(new int[]{currRow,currCol});
			}
			currRow--;
			currCol++;
		}
		
		currRow = chessPiece.getRow()+1;
		currCol = chessPiece.getCol()-1;
		blocked = false;
		
		while (!blocked && inBounds(currRow,currCol)) {
			ChessPiece tempPiece = chessBoard.getPiece(currRow, currCol);
			if (tempPiece != null) {
				blocked = true;
				if (tempPiece.getColor() != chessPiece.getColor())
					lst.add(new int[]{currRow,currCol});
			}
			else {
				lst.add(new int[]{currRow,currCol});
			}
			currRow++;
			currCol--;
		}
		
		currRow = chessPiece.getRow()-1;
		currCol = chessPiece.getCol()-1;
		blocked = false;
		
		while (!blocked && inBounds(currRow,currCol)) {
			ChessPiece tempPiece = chessBoard.getPiece(currRow, currCol);
			if (tempPiece != null) {
				blocked = true;
				if (tempPiece.getColor() != chessPiece.getColor())
					lst.add(new int[]{currRow,currCol});
			}
			else {
				lst.add(new int[]{currRow,currCol});
			}
			currRow--;
			currCol--;
		}
		
		return lst;
	}

	public static ArrayList<int[]> getKnightMoves(ChessBoard chessBoard, ChessPiece chessPiece) {
		ArrayList<int[]> lst = new ArrayList<int[]>();
		
		return lst;
	}

	public static ArrayList<int[]> getQueenMoves(ChessBoard chessBoard, ChessPiece chessPiece) {
		 ArrayList<int[]> lst1 = getRookMoves(chessBoard, chessPiece);
		 ArrayList<int[]> lst2 = getBishopMoves(chessBoard, chessPiece);
		 lst1.addAll(lst2);
		 return lst1;
	}

	public static ArrayList<int[]> getKingMoves(ChessBoard chessBoard, ChessPiece chessPiece) {
		ArrayList<int[]> lst = new ArrayList<int[]>();
		
		return lst;
	}
	
	private static boolean inBounds(int row, int col) {
		return row >= 0 && row < ChessBoard.MAXFILE && 
				col >= 0 && col < ChessBoard.MAXRANK;
	}
}
