package Chess;

import javax.swing.JFrame;

public class ChessGame
{	
	public static Controller controller = new Controller();
	
	public static void main(String[] args)
	{	
		FileInputReader fileReader = new FileInputReader();
		try
		{
			fileReader.readFile(args[0]);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("No command line!  Please input one!");
		}
		catch(NullPointerException a)
		{
			System.out.println("Incorrect arguement! Please input a proper arguement!");
		}
		
		JFrame myGame = new JFrame();
		ChessView view = new ChessView(controller);
		
		myGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myGame.setSize(1500, 1000);
		myGame.setVisible(true);
		myGame.setTitle("Chess");
		myGame.add(view);
		myGame.repaint();
		
		controller.runGame();
	}
}