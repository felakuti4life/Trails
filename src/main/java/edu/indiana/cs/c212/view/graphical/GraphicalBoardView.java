package edu.indiana.cs.c212.view.graphical;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		JFrame frame = new JFrame("mainWindow");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*FlowLayout flow = new FlowLayout();
		frame.getContentPane().setLayout(flow);*/
		
		
		
		JLabel label = new JLabel("TILES");
		
        frame.getContentPane().add(turnPanel);
        frame.getContentPane().add(mainPanel, BorderLayout.PAGE_START);
        frame.getContentPane().add(boardSetupPanel, BorderLayout.LINE_START);
        frame.getContentPane().add(rulesPanel, BorderLayout.CENTER);
        frame.getContentPane().add(startButton, BorderLayout.PAGE_END);
        
        frame.pack();
        frame.setVisible(true);
	}

}
