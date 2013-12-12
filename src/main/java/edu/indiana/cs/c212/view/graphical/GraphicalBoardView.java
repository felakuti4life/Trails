package edu.indiana.cs.c212.view.graphical;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import edu.indiana.cs.c212.gameMechanics.GameRunner;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class GraphicalBoardView extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 8269507648751021974L;
	private GameRunner game;
	private static GameRunner currentGame = new GameRunner(6, "Random", "Random", "Standard");
	
	private static String redPlayerName = "Red Player";
	private static String bluePlayerName = "Blue Player";
	
	private static TurnViewer turnPanel = new TurnViewer(PlayerColor.RED, redPlayerName, bluePlayerName, currentGame);
	private static PlayerChoicePanel redPanel = new PlayerChoicePanel(redPlayerName, PlayerColor.RED);
	private static PlayerChoicePanel bluePanel = new PlayerChoicePanel(bluePlayerName, PlayerColor.BLUE);
	private static BoardPanel mainPanel = new BoardPanel(currentGame.getBoard());
	
	private static BoardSetupPanel boardSetupPanel = new BoardSetupPanel();
	private static RulesPanel rulesPanel = new RulesPanel();
	private static JButton startButton = new JButton("Start");
	protected void prepareGame(){
		game = new GameRunner(boardSetupPanel.getBoardSize(), 
					redPanel.getPlayerType(),
					bluePanel.getPlayerType(), 
					rulesPanel.getRules());
		game.addObserver(mainPanel);
		currentGame = game;
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
            	startButton.addActionListener(null);
                createAndShowGUI();
            }
           };
	}
	
	protected static void createAndShowGUI(){
		JFrame frame = new JFrame("Trails");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//FlowLayout flow = new FlowLayout();
		//frame.getContentPane().setLayout(flow);
		
		
		mainPanel.setSize(3000,3000);
		turnPanel.setSize(800,300);
		boardSetupPanel.setSize(350, 150);
		rulesPanel.setSize(200,100);
		startButton.setSize(100, 100);
		JLabel label = new JLabel("TILES");
		frame.getContentPane().add(label);
		//frame.getContentPane().add(Box.createVerticalStrut(300));
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        //frame.getContentPane().add(Box.createVerticalStrut(300));
        frame.getContentPane().add(turnPanel);
        frame.getContentPane().add(boardSetupPanel);
        frame.getContentPane().add(rulesPanel);
        //frame.getContentPane().add(Box.createVerticalStrut(150));
        frame.getContentPane().add(startButton, BorderLayout.PAGE_END);
        
        frame.pack();
        frame.setVisible(true);
	}

}
