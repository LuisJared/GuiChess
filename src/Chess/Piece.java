package Chess;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Piece 
{
	private String pieceType = "";
	private String pieceColor = "";
	private String pieceName = "";
	private ArrayList<Position> possibleMoves = new ArrayList<Position>();
	protected double minBoardSize = 0;
	protected double maxBoardSize = 8;
	protected boolean hasMoved = false;
	
	public Piece(String pieceType)
	{
		this.pieceType = pieceType;
		this.pieceName = pieceType;
	}

	public String getPieceName() 
	{
		return pieceName;
	}

	public String getPieceType() 
	{
		return pieceType;
	}

	public void setPieceType(String pieceType)
	{
		this.pieceType = pieceType;
	}
	
	public String getPieceColor()
	{
		return pieceColor;
	}
	
	public void setPieceColor(String pieceColor)
	{
		this.pieceColor = pieceColor;
	}
	
	public boolean validMovement(double x1, double y1, double x2, double y2)
	{
		boolean move = false;
		
		return move;
	}

	public ArrayList<Position> getPossibleMoves() 
	{
		return possibleMoves;
	}

	public void setPossibleMoves(Position possibleMove)
	{
		possibleMoves.add(possibleMove);
	}
	
	public void resetPossibleMoves()
	{
		possibleMoves.clear();
	}

	public boolean getHasMoved()
	{
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) 
	{
		this.hasMoved = hasMoved;
	}
}