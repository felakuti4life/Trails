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
	
	private static final long serialVersionUID = 8269507648751021974L;
	private GameRunner game;
	private static TurnViewer turnPanel;
	private static String redPlayerName;
	private static String bluePlayerName;
	
	private static PlayerChoicePanel redPanel = new PlayerChoicePanel(redPlayerName, PlayerColor.RED);
	private static PlayerChoicePanel bluePanel = new PlayerChoicePanel(bluePlayerName, PlayerColor.BLUE);
	private static BoardPanel mainPanel;
	
	private static BoardSetupPanel boardSetupPanel;
	private static RulesPanel rulesPanel;
	private static JButton startButton = new JButton("Start");
	protected void prepareGame(){
		game = new GameRunner(boardSetupPanel.getBoardSize(), 
					redPanel.getPlayerType(),
					bluePanel.getPlayerType(), 
					rulesPanel.getRules());
		mainPanel = new BoardPanel(game.getBoard());
	}

	
	//TODO: fix
	@Override
	public void actionPerformed(ActionEvent e){
		if (game != null) game.stopGame();
		
		this.prepareGame();
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
        frame.getContentPane().add(turnPanel, BorderLayout.PAGE_START);
        frame.getContentPane().add(redPanel, BorderLayout.LINE_START);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.getContentPane().add(bluePanel, BorderLayout.LINE_END);
        frame.getContentPane().add(boardSetupPanel, BorderLayout.PAGE_END);
        frame.getContentPane().add(rulesPanel, BorderLayout.PAGE_END);
        frame.getContentPane().add(startButton, BorderLayout.PAGE_END);
        
        frame.pack();
        frame.setVisible(true);
	}

}
