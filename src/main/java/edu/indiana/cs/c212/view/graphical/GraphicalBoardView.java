package edu.indiana.cs.c212.view.graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import edu.indiana.cs.c212.gameMechanics.GameRunner;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class GraphicalBoardView extends JFrame implements ActionListener{
	
	private static String redPlayerName;
	private GameRunner game;
	private String playerName;
	private TurnViewer turnPanel;
	private static PlayerChoicePanel redPanel = new PlayerChoicePanel(redPlayerName, PlayerColor.RED);
	private static String bluePlayerName;
	private static PlayerChoicePanel bluePanel = new PlayerChoicePanel(bluePlayerName, PlayerColor.BLUE);
	private static BoardSetupPanel boardPanel;
	private static RulesPanel rulesPanel;
	private static JButton startButton = new JButton("Start");
	private static Runnable runtime;
	
	protected void prepareGame(){
		this.game = new GameRunner(this.boardPanel.getBoardSize(), 
					redPanel.getPlayerType(),
					bluePanel.getPlayerType(), 
					rulesPanel.getRules());
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e){
		Thread gameThread = new Thread(game); 
		 gameThread.start();
		
	}
	
	public static Runnable setup(){
		return runtime;
	}
	
	protected static void createAndShowGUI(){
		getContentPane().add(redPanel);
	}

}
