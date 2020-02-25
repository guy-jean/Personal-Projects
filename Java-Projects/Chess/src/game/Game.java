package game;

import java.util.ArrayList;

import game.ChessPiece.ChessPieceColor;
import game.ChessPiece.ChessPieceType;

public class Game {
	ArrayList<ChessBoard> boardStates;
	int move;
	Player whitePlayer;
	Player blackPlayer;
	
	public Game() {
		boardStates = new ArrayList<ChessBoard>();
		move = 0;
		boardStates.add(new ChessBoard());
		whitePlayer = new Player(ChessPieceColor.WHITE);
		blackPlayer = new Player(ChessPieceColor.BLACK);
	}
	
	public static boolean move(int targetRow, int targetCol, ChessBoard chessBoard, ChessPiece chessPiece) {
		if (validMove(targetRow, targetCol, chessBoard, chessPiece)) {
			capture(targetRow, targetCol, chessBoard, chessPiece);
			chessPiece.incMove();
			return true;
		}
		
		return false;
	}
	
	public static ChessPiece capture(int targetRow, int targetCol, ChessBoard chessBoard, ChessPiece chessPiece) {
		ChessPiece targetPiece = chessBoard.getPiece(targetRow, targetCol);
		chessBoard.setPiece(chessPiece.getRow(), chessPiece.getCol(), null);
		chessBoard.setPiece(targetRow, targetCol, chessPiece);
		chessPiece.setRow(targetRow);
		chessPiece.setCol(targetCol);
		return targetPiece;
	}
	
	public static boolean validMove(int targetRow, int targetCol, ChessBoard chessBoard, ChessPiece chessPiece) {
		ArrayList<int[]> lst = getPotentialMoves(chessBoard, chessPiece);
		return lst.contains(new int[] {targetRow, targetCol});
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
		ChessBoard chessBoard = boardStates.get(boardStates.size());
		for (int c = 0; c < chessBoard.MAXRANK; c++)
			for (int r = 0; r < chessBoard.MAXFILE; r++) {
				ChessPiece chessPiece = chessBoard.getPiece(r, c);
				if (chessPiece.getColor() == color && allValidMoves(chessBoard,chessPiece).isEmpty())
					return false;
			}
		
		return true;
	}
	
	public ArrayList<int[]> allValidMoves(ChessBoard chessBoard, ChessPiece chessPiece) {
		ArrayList<int[]> lst = getPotentialMoves(chessBoard, chessPiece);
		
		for (int i = 0; i < lst.size(); i++) {
			ChessBoard tempBoard = new ChessBoard(chessBoard);
			move(lst.get(i)[0], lst.get(i)[1], tempBoard, tempBoard.getPiece(chessPiece.getRow(), chessPiece.getCol()));
			if (tempBoard.inCheck(chessPiece.getColor()))
				lst.remove(i);
		}
		
		return lst;
	}
}
