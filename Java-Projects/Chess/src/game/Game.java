package game;

import java.util.ArrayList;
import java.util.Scanner;

import game.ChessPiece.ChessPieceColor;
import game.ChessPiece.ChessPieceType;

public class Game {
	ArrayList<ChessBoard> boardStates;
	int moveNum;
	//Player whitePlayer;
	//Player blackPlayer;
	
	public Game() {
		boardStates = new ArrayList<ChessBoard>();
		moveNum = 0;
		boardStates.add(new ChessBoard());
		//whitePlayer = new Player(ChessPieceColor.WHITE);
		//blackPlayer = new Player(ChessPieceColor.BLACK);
	}
	
	public ChessBoard move(int targetRow, int targetCol, int currRow, int currCol, ChessBoard chessBoard) {
		ChessPieceColor turnColor = getTurnColor();
		ChessBoard nextBoard = new ChessBoard(chessBoard);
		ChessPiece nextPiece = nextBoard.getPiece(currRow, currCol);
		int moveType = moveType(targetRow, targetCol, nextBoard, nextPiece);
		if (moveType == 0)
			return null;
		
		if (moveType == 1) {
			normalCapture(targetRow, targetCol, nextBoard, nextPiece);
			if (nextBoard.inCheck(turnColor)) 
				return null;
			
			if (nextPiece.getType() == ChessPieceType.PAWN && Math.abs(targetRow-currRow) > 1) {
				nextBoard.enPassantPawn = nextPiece;
				nextBoard.enPassantCoors = nextPiece.getColor() == ChessPieceColor.WHITE ? new int[] {targetRow-1, targetCol} :
					new int[] {targetRow+1, targetCol};
			}
			else {
				nextBoard.enPassantPawn = null;
				nextBoard.enPassantCoors = null;
			}
		}
			
		else if (moveType == 2) {
			castleKing(targetRow, targetCol, nextBoard, nextPiece);
			if (nextBoard.inCheck(turnColor)) 
				return null;
		}
		else if (moveType == 3) {
			enPassantCapture(targetRow, targetCol, nextBoard, nextPiece);
			if (nextBoard.inCheck(turnColor)) 
				return null;
		}
		
		return nextBoard;
	}
	
	public static ChessPiece normalCapture(int targetRow, int targetCol, ChessBoard chessBoard, ChessPiece chessPiece) {
		ChessPiece targetPiece = chessBoard.getPiece(targetRow, targetCol);
		chessBoard.setPiece(chessPiece.getRow(), chessPiece.getCol(), null);
		chessBoard.setPiece(targetRow, targetCol, chessPiece);
		chessPiece.setRow(targetRow);
		chessPiece.setCol(targetCol);
		chessPiece.incMove();
		return targetPiece;
	}
	
	public static ChessPiece enPassantCapture(int targetRow, int targetCol, ChessBoard chessBoard, ChessPiece chessPiece) {
		ChessPiece targetPiece = chessBoard.enPassantPawn;
		chessBoard.setPiece(targetPiece.getRow(), targetPiece.getCol(), null);
		chessBoard.setPiece(chessPiece.getRow(), chessPiece.getCol(), null);
		chessBoard.setPiece(targetRow, targetCol, chessPiece);
		chessPiece.setRow(targetRow);
		chessPiece.setCol(targetCol);
		chessPiece.incMove();
		return targetPiece;
	}
	
	public static ChessPiece castleKing(int targetRow, int targetCol, ChessBoard chessBoard, ChessPiece chessPiece) {
		int dir = targetCol - chessPiece.getCol();
		ChessPiece rook = dir > 0 ? chessBoard.getPiece(targetRow, ChessBoard.MAXRANK-1) : 
			chessBoard.getPiece(targetRow, 0);
		
		chessBoard.setPiece(chessPiece.getRow(), chessPiece.getCol(), null);
		chessBoard.setPiece(targetRow, targetCol, chessPiece);
		chessPiece.setRow(targetRow);
		chessPiece.setCol(targetCol);
		chessPiece.incMove();
		
		targetCol = dir > 0 ? targetCol-1 : targetCol+1;
		
		chessBoard.setPiece(rook.getRow(), rook.getCol(), null);
		chessBoard.setPiece(targetRow, targetCol, rook);
		chessPiece.setRow(targetRow);
		chessPiece.setCol(targetCol);
		rook.incMove();
		
		return null;
	}
	
	public static int moveType(int targetRow, int targetCol, ChessBoard chessBoard, ChessPiece chessPiece) {
		int moveType = 0;
		ArrayList<int[]> lst = getPotentialMoves(chessBoard, chessPiece);
		if (containsCoors(lst, targetRow, targetCol)) {
			moveType = 1;
			boolean isEnPassant = chessPiece.getType() == ChessPieceType.PAWN && chessBoard.enPassantPawn != null &&
					chessBoard.enPassantPawn.getColor() != chessPiece.getColor() && chessBoard.enPassantCoors[0] == targetRow &&
					chessBoard.enPassantCoors[1] == targetCol;
			boolean isCastle = chessPiece.getType() == ChessPieceType.KING && Math.abs(chessPiece.getCol() - targetCol) > 1;
			if (isEnPassant) {
				moveType = 2;
			}
			else if (isCastle) {
				moveType = 3;
			}

		}
		return moveType;
	}
	
	public static boolean containsCoors(ArrayList<int[]> lst, int row, int col) {
		for (int[] arr : lst) 
			if (arr[0] == row && arr[1] == col)
				return true;

		return false;
	}
	
	public static ArrayList<int[]> getPotentialMoves(ChessBoard chessBoard, ChessPiece chessPiece) {
		ChessPieceType type = chessPiece.getType();
		switch(type)
		{
		   case PAWN :
			   return Movement.getPawnMoves(chessBoard, chessPiece);
		   
		   case ROOK :
			   return Movement.getRookMoves(chessBoard, chessPiece);
		      
		   case BISHOP :
			   return Movement.getBishopMoves(chessBoard, chessPiece);
			
		   case KNIGHT :
			   return Movement.getKnightMoves(chessBoard, chessPiece);
			      
		   case QUEEN :
			   return Movement.getQueenMoves(chessBoard, chessPiece);
		
		   case KING :
			   return Movement.getKingMoves(chessBoard, chessPiece);

		   default : 

		}
		return null;
	}
	
	public boolean checkMate(ChessPieceColor color) {
		ChessBoard chessBoard = boardStates.get(boardStates.size()-1);
		for (int c = 0; c < ChessBoard.MAXRANK; c++)
			for (int r = 0; r < ChessBoard.MAXFILE; r++) {
				ChessPiece chessPiece = chessBoard.getPiece(r, c);
				if (chessPiece != null && chessPiece.getColor() == color && allValidMoves(chessBoard,chessPiece).isEmpty())
					return false;
			}
		
		return true;
	}
	
	public ArrayList<int[]> allValidMoves(ChessBoard chessBoard, ChessPiece chessPiece) {
		ArrayList<int[]> lst = getPotentialMoves(chessBoard, chessPiece);
		System.out.println(chessPiece.getType());
		for (int i = 0; i < lst.size()-1; i++) {
			ChessBoard tempBoard = move(lst.get(i)[0], lst.get(i)[1], chessPiece.getRow(), chessPiece.getCol(), chessBoard);
			if (tempBoard == null)
				lst.remove(i);
		}
		
		return lst;
	}
	
	public ChessPieceColor getTurnColor() {
		ChessPieceColor turnColor = moveNum % 2 == 0 ? ChessPieceColor.WHITE : ChessPieceColor.BLACK;
		return turnColor;
	}
	
	public boolean turn(int targetRow, int targetCol, int currRow, int currCol) {
		ChessBoard currBoard = boardStates.get(boardStates.size()-1);
		
		if (currBoard.getPiece(currRow,currCol) == null || currBoard.getPiece(currRow,currCol).getColor() != getTurnColor())
			return false;
		
		ChessBoard nextBoard = move(targetRow, targetCol, currRow, currCol, currBoard);
		
		if (nextBoard == null)
			return false;
		
		moveNum++;
		boardStates.add(nextBoard);
		return true;
	}
	
	public void play() {
		boolean finished = false;
		Scanner scan = new Scanner(System.in);
		
		while (!finished) {
			System.out.println(toString());
			System.out.println("Enter Piece Row: ");
			int row = scan.nextInt();
			System.out.println("Enter Piece Col: ");
			int col = scan.nextInt();
			System.out.println("Enter Target Row: ");
			int targetRow = scan.nextInt();
			System.out.println("Enter Target Col: ");
			int targetCol = scan.nextInt();
			
			while (!turn(targetRow, targetCol, row, col)) {
				System.out.println("Invalid move, try again");
				System.out.println(toString());
				System.out.println("Enter Piece Row: ");
				row = scan.nextInt();
				System.out.println("Enter Piece Col: ");
				col = scan.nextInt();
				System.out.println("Enter Target Row: ");
				targetRow = scan.nextInt();
				System.out.println("Enter Target Col: ");
				targetCol = scan.nextInt();
			}
			finished = checkMate(getTurnColor());
		}
		scan.close();
		moveNum++;
		System.out.println(getTurnColor() + " wins");
	}
	
	public String toString() {
		ChessBoard currBoard = boardStates.get(boardStates.size()-1);
		StringBuilder str = new StringBuilder();
		str.append(getTurnColor() + "'s turn.\n");
		str.append(currBoard.toString() + '\n');
		if (currBoard.inCheck(getTurnColor()))
			str.append(getTurnColor() + "'s in check.\n");
		
		return str.toString();
	}
}
