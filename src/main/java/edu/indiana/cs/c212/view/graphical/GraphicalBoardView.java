package edu.indiana.cs.c212.view.graphical;

//import java.awt.BorderLayout;
//import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





import javax.swing.Box;
//import javax.swing.Box;
//import javax.swing.AbstractButton;
//import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.indiana.cs.c212.gameMechanics.GameRunner;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class GraphicalBoardView extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 8269507648751021974L;
	private GameRunner game = new GameRunner(6, "Random Player", "Random Player", "Standard Rules");
	//private static GameRunner currentGame = new GameRunner(6, "Random", "Random", "Standard");
	
	private String redPlayerName = "Red Player";
	private String bluePlayerName = "Blue Player";
	
	private TurnViewer redTurnPanel;
	private TurnViewer blueTurnPanel;
	private PlayerChoicePanel redPanel = new PlayerChoicePanel(redPlayerName, PlayerColor.RED);
	private PlayerChoicePanel bluePanel = new PlayerChoicePanel(bluePlayerName, PlayerColor.BLUE);
	private BoardPanel mainPanel;
	
	private BoardSetupPanel boardSetupPanel = new BoardSetupPanel();
	private RulesPanel rulesPanel = new RulesPanel();
	private JButton startButton = new JButton("Start");
	
	public GraphicalBoardView(){
		JPanel content = new JPanel();
		//content.setLayout(new FlowLayout());
		
		redTurnPanel = new TurnViewer(PlayerColor.RED, redPlayerName, game);
		blueTurnPanel = new TurnViewer(PlayerColor.BLUE, bluePlayerName, game);
		mainPanel = new BoardPanel(game.getBoard());
		mainPanel.setLayout(null);
		mainPanel.setOpaque(true);
		redPanel.setOpaque(true);
		bluePanel.setOpaque(true);
		content.add(redTurnPanel);
		content.add(redPanel);
		//content.add(Box.createVerticalStrut(60));
		content.add(mainPanel, new Dimension(mainPanel.getPreferredSize().width + 500, mainPanel.getPreferredSize().height + 500), 0);add(mainPanel);
		content.add(blueTurnPanel);
		content.add(bluePanel);
		
		rulesPanel.setOpaque(true);
		content.add(rulesPanel);
		content.add(boardSetupPanel);
		
		content.add(mainPanel);
		
		startButton.addActionListener(this);
		content.add(startButton, BorderLayout.PAGE_END);
		setContentPane(content);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(mainPanel.getPreferredSize().width + 500, mainPanel.getPreferredSize().height + 500);
		setResizable(true);
	}
	
	protected void prepareGame(){
		game = new GameRunner(boardSetupPanel.getBoardSize(), 
					redPanel.getPlayerType(),
					bluePanel.getPlayerType(), 
					rulesPanel.getRules());
		game.addObserver(mainPanel);
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
            	//GraphicalBoardView viewport = new GraphicalBoardView(); moved to createAndShowGUI;
                createAndShowGUI();
            }
           };
	}
	
	protected static void createAndShowGUI(){
		new GraphicalBoardView();
	}

}
