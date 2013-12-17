package Chess;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ChessView extends JPanel
{
	private Controller controller;
	private Graphics g;
	private final int boardSize = 8;
	private final ArrayList<Position> possibleMovesForPiece = new ArrayList<Position>();
	
	public int getBoardSize() 
	{
		return boardSize;
	}

	public ChessView(Controller controller)
	{
		this.controller = controller;
		this.addMouseListener(new ChessMouseListener(this, controller));
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		this.g = g;
		int imageWidth = getWidth()/boardSize;
		int imageHeight = getHeight()/boardSize;
		
		drawTiles(imageWidth, imageHeight, g);
		drawPossibleMoves(imageWidth, imageHeight, g);
		drawPieces(imageWidth, imageHeight, g);
		
	}
	
	public void getPossibleMovesToDraw(Piece piece, Position position)
	{
		possibleMovesForPiece.clear();
		
		controller.possibleMovesForPiece(piece, position);
		
		for(int i = 0; i < piece.getPossibleMoves().size(); i++)
		{
			possibleMovesForPiece.add(piece.getPossibleMoves().get(i));
		}
	}
	
	public void drawBoard(Graphics g)
	{
		this.g = g;
		int imageWidth = getWidth()/boardSize;
		int imageHeight = getHeight()/boardSize;
		
		drawTiles(imageWidth, imageHeight, g);
		drawPieces(imageWidth, imageHeight, g);
	}
	
	public void clearPossibleMoves()
	{
		possibleMovesForPiece.clear();
	}
	
	private void drawPossibleMoves(int imageWidth, int imageHeight, Graphics g)
	{
		for(int i = 0; i < possibleMovesForPiece.size(); i++)
		{
			g.setColor(Color.red);
			int x = possibleMovesForPiece.get(i).getPositionX();
			int y = 7-possibleMovesForPiece.get(i).getPositionY();
			
			g.fillRect((getWidth()*x/boardSize)+5, (getHeight()*y/boardSize)+5, (getWidth()/boardSize)-10, (getHeight()/boardSize)-10);
		}
	}
	
	private void drawPieces(int imageWidth, int imageHeight, Graphics g) 
	{	  
		for(int y = 0; y < boardSize; y++)
        {
			for(int x = 0; x < boardSize; x++)
	        {				
                Piece piece = controller.getPieceFromBoard(x, y);
                
                String pieceName = piece.getPieceName();
                String pieceColor = piece.getPieceColor();
                String pieceFileName = pieceColor + pieceName + ".png";
                
                if(piece.getPieceType() != "-")
                {
	                Image pieceImage = null;
	        		try 
	        		{
	        			pieceImage = ImageIO.read(new File(pieceFileName));
	        		} 
	        		catch (IOException e) 
	        		{
	        			System.out.println("Please input a proper image file!");
	        		}
	        		
	        		g.drawImage(pieceImage, (getWidth()*x/boardSize), (getHeight()*(7-y)/boardSize), imageWidth, imageHeight, null);
                }
            }
        }
	}

	private void drawTiles(int imageWidth, int imageHeight, Graphics g)
	{
		char Letter = 'A';
		
		// Draws the board squares onto the panel
		for(int i = 0; i < boardSize; i++, Letter++)
		{		
			for(int j = (boardSize-1); j >= 0; j--)
			{
				if(j % 2 == i % 2)
				{
					g.setColor(Color.blue);
					g.fillRect((getWidth()*i/boardSize), (getHeight()*j/boardSize), getWidth()/boardSize, getHeight()/boardSize);
				}
				else
				{
					if(controller.whitePlayerTurn())
					{
						g.setColor(Color.white);
					}
					else
					{
						g.setColor(Color.black);
					}
					g.fillRect((getWidth()*i/boardSize), (getHeight()*j/boardSize), getWidth()/boardSize, getHeight()/boardSize);
				}
				g.setFont(new Font("Times New Roman", Font.BOLD, 20));
				g.setColor(Color.RED);
				
				g.drawString(" " + Letter + (boardSize-j), i * imageWidth, j * imageHeight + imageHeight);
			}
		}
		controller.isKingInCheckNotifier();
	}
}