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
		
		int row = chessPiece.getRow();
		int col = chessPiece.getCol();
		
		//upperleft
		lst.add(new int[] {row+1, col-2});
		lst.add(new int[] {row+2, col-1});
		
		//upperright
		lst.add(new int[] {row+1, col+2});
		lst.add(new int[] {row+2, col+1});
		
		//lowerleft
		lst.add(new int[] {row-1, col-2});
		lst.add(new int[] {row-2, col-1});
		
		//lowerright
		lst.add(new int[] {row-1, col+2});
		lst.add(new int[] {row-2, col+1});
		
		for(int i = 0; i < lst.size(); i++) {
			int[] arr = lst.get(i);
			if(!inBounds(arr[0],arr[1]))
				lst.remove(i);
			else if (chessBoard.getPiece(arr[0], arr[1]) != null && 
					chessBoard.getPiece(arr[0], arr[1]).getColor() == chessPiece.getColor())
				lst.remove(i);
		}
		
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
		int row = chessPiece.getRow();
		int col = chessPiece.getCol();
		
		//diags
		lst.add(new int[] {row+1, col+1});
		lst.add(new int[] {row+1, col-1});
		lst.add(new int[] {row-1, col-1});
		lst.add(new int[] {row-2, col-1});
		
		//lats
		lst.add(new int[] {row+1, col});
		lst.add(new int[] {row-1, col});
		lst.add(new int[] {row, col+1});
		lst.add(new int[] {row, col-1});
		
		for(int i = 0; i < lst.size(); i++) {
			int[] arr = lst.get(i);
			if(!inBounds(arr[0],arr[1]))
				lst.remove(i);
			else if (chessBoard.getPiece(arr[0], arr[1]) != null && 
					chessBoard.getPiece(arr[0], arr[1]).getColor() == chessPiece.getColor())
				lst.remove(i);
		}
		
		if (chessPiece.getMoveCount() == 0) {
			castle(chessBoard, chessPiece, lst);
		}
			
			
		return lst;
	}
	
	private static void castle(ChessBoard chessBoard, ChessPiece chessPiece, ArrayList<int[]> lst) {
		ChessPieceColor color = chessPiece.getColor();
		int row = chessPiece.getRow();
		for (int c = 1; c < ChessBoard.MAXRANK-2; c++) {
			ChessPiece tempPiece = chessBoard.getPiece(row, c); 
			if (tempPiece != chessPiece || chessBoard.squareAttacked(row, c, color))
				return;
		}
		
		ChessPiece leftCorner = chessBoard.getPiece(row, 0);
		ChessPiece rightCorner = chessBoard.getPiece(row, ChessBoard.MAXRANK-1);
		
		if (leftCorner != null && leftCorner.getType() == ChessPieceType.ROOK && leftCorner.getMoveCount() == 0)
			lst.add(new int[]{row,chessPiece.getCol()-2});
		
		if (rightCorner != null && rightCorner.getType() == ChessPieceType.ROOK && rightCorner.getMoveCount() == 0)
			lst.add(new int[]{row,chessPiece.getCol()+2});
	}
	
	private static boolean inBounds(int row, int col) {
		return row >= 0 && row < ChessBoard.MAXFILE && 
				col >= 0 && col < ChessBoard.MAXRANK;
	}
}
