package edu.indiana.cs.c212.view.graphical;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import edu.indiana.cs.c212.gameMechanics.GameRunner;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class GraphicalBoardView extends JFrame implements ActionListener{
	

	private GameRunner game;
	private String playerName;
	private TurnViewer turnPanel;
	private static String redPlayerName;
	private static String bluePlayerName;
	
	private static PlayerChoicePanel redPanel = new PlayerChoicePanel(redPlayerName, PlayerColor.RED);
	private static PlayerChoicePanel bluePanel = new PlayerChoicePanel(bluePlayerName, PlayerColor.BLUE);
	
	private static BoardSetupPanel boardPanel;
	private static RulesPanel rulesPanel;
	private static JButton startButton = new JButton("Start");
	
	protected void prepareGame(){
		this.game = new GameRunner(boardPanel.getBoardSize(), 
					redPanel.getPlayerType(),
					bluePanel.getPlayerType(), 
					rulesPanel.getRules());
	}

	
	//TODO: fix
	@Override
	public void actionPerformed(ActionEvent e){
		Thread gameThread = new Thread(game); 
		 gameThread.start();
		
	}
	
	public static Runnable setup(){
		return new Runnable() {
            public void run() {
                createAndShowGUI();
            }
            };
	}
	
	protected static void createAndShowGUI(){
		JFrame frame = new JFrame("mainWindow");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("TILES");
		
        frame.getContentPane().add(label, BorderLayout.PAGE_START);
        frame.getContentPane().add(redPanel, BorderLayout.LINE_START);
        frame.getContentPane().add(bluePanel, BorderLayout.LINE_END);
        frame.getContentPane().add(boardPanel, BorderLayout.PAGE_END);
        frame.getContentPane().add(rulesPanel, BorderLayout.PAGE_END);
        frame.getContentPane().add(startButton, BorderLayout.PAGE_END);
        
        frame.pack();
        frame.setVisible(true);
	}

}
