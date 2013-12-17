package Chess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChessMouseListener implements MouseListener
{
	private ChessView view;
	private Controller controller;
	private int pressedX;
	private int pressedY;
	private int releasedX;
	private int releasedY;
	private int cellWidth;
	private int cellHeight;
	
	public ChessMouseListener(ChessView view, Controller controller)
	{
		this.view = view;
		this.controller = controller;
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		cellWidth = view.getWidth() / view.getBoardSize();
		cellHeight = view.getHeight() / view.getBoardSize();
		
		pressedX = e.getX() / cellWidth;
		pressedY = e.getY() / cellHeight;
		
		if(pressedY == 0)
		{
			pressedY = 7;
		}
		else if(pressedY == 1)
		{
			pressedY = 6;
		}
		else if(pressedY == 2)
		{
			pressedY = 5;
		}
		else if(pressedY == 3)
		{
			pressedY = 4;
		}
		else if(pressedY == 4)
		{
			pressedY = 3;
		}
		else if(pressedY == 5)
		{
			pressedY = 2;
		}
		else if(pressedY == 6)
		{
			pressedY = 1;
		}
		else if(pressedY == 7)
		{
			pressedY = 0;
		}
		
		Piece piece = controller.getPieceFromBoard(pressedX, pressedY);
		Position piecePosition = new Position(pressedX, pressedY);
		
		view.getPossibleMovesToDraw(piece, piecePosition);
		
		view.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		cellWidth = view.getWidth() / view.getBoardSize();
		cellHeight = view.getHeight() / view.getBoardSize();
		
		releasedX = e.getX() / cellWidth;
		releasedY = e.getY() / cellHeight;
		
		releasedY = 7 - releasedY;
		
		System.out.println("\nCLICKING: "+controller.coordinateToPosition(pressedX, pressedY) + " " + controller.coordinateToPosition(releasedX, releasedY));
		
		String startClick = controller.coordinateToPosition(pressedX, pressedY);
		String endClick = controller.coordinateToPosition(releasedX, releasedY);		
		String command = startClick + " " + endClick;
		
		Position pieceStart = new Position(pressedX, pressedY);
		Position pieceEnd = new Position(releasedX, releasedY);
		
		controller.movePieceOnBoard(command, pieceStart, pieceEnd);
		view.clearPossibleMoves();

		view.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}