package edu.indiana.cs.c212.gameMechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.SimpleGameBoard;
import edu.indiana.cs.c212.players.EjgellerBasicTrailsPlayer;
import edu.indiana.cs.c212.players.Player;
import edu.indiana.cs.c212.players.PointAndClickPlayer;
import edu.indiana.cs.c212.players.SimpleRandom;
import edu.indiana.cs.c212.view.graphical.GraphicalBoardView;
import edu.indiana.cs.c212.view.textual.CommandLineView;


public class GameRunner extends Observable implements Runnable {
	
	private SimpleGameBoard board;
	private Player red, blue;
	private StandardRules  rules;
	private static ArrayList<String> playersList;
	//private boolean redTurn;
	private boolean gameStopped;
	private static ArrayList<String> ruleSets = new ArrayList<String>(3);
	
	
	public GameRunner(int boardSize, String red, String blue, String ruleSet){
		GameRunner.playersList = new ArrayList<String>();
		playersList.add(red);
		playersList.add(blue);

		this.board = new SimpleGameBoard(boardSize);
		this.red = createPlayer(red, PlayerColor.RED);
		this.blue = createPlayer(blue, PlayerColor.BLUE);
		
		if(ruleSet == "Standard Rules")
			this.rules = new StandardRules(this.board, this.red, this.blue);
		if(ruleSet == "Lose By Connecting Rules")
			this.rules = new LoseByConnectingRules(this.board, this.red, this.blue);
		if(ruleSet == "Random Move");
		else this.rules = new StandardRules(this.board, this.red, this.blue);
		
		//this.redTurn = true;
	}
	
	public static List<String> getPlayersList(){
		return playersList;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public Player getPlayer(PlayerColor color){
		if (color.equals(PlayerColor.RED)) return red;
		
		else return blue;
	}
	
	@Override
public void run() {
		
		this.gameStopped = false;
		
		while (!gameStopped) {
			
			System.out.println(getCurrentPlayer().toString());
			System.out.println(rules.toString());
			Move move = getCurrentPlayer().getMove(new SimpleGameBoard(board), rules.getLegalMoves(getCurrentPlayer()));
			boolean isLegalMove = rules.isLegalMove(move);
			
			if (!isLegalMove) {
				System.out.println("Gah! You cheated! Do you have any idea what you have done?!");
				stopGame();
				break;
			} 
			
			else {
				Player currentPlayer = getCurrentPlayer();
				System.out.println(CommandLineView.boardToString(board));
				System.out.println(currentPlayer + "'s move: " + move);
				board.makeMove(move, getCurrentPlayer().getColor());
				setChanged();
				notifyObservers(board);
				PlayerColor winnerColor = rules.checkForWins();
				if (winnerColor == null) {
					rules.nextTurn();
				} else {
					stopGame();
				}
			}
		}
	}

	public Player getCurrentPlayer() {
		return rules.getPlayers().peek();
	}
	
	public static List<String> getRuleSets(){
		ruleSets.add("Standard Rules");
		ruleSets.add("Lose by Connecting Rules");
		ruleSets.add("Overwrite Rules");
		ruleSets.add("Random Move");
		return ruleSets;
	}
	
	protected Player createPlayer(String playerType, PlayerColor color){
		if(playerType == "Ethan's Basic Player")
			return new EjgellerBasicTrailsPlayer(color);
		
		else if(playerType =="Point And Click Player")
			return new PointAndClickPlayer(color);
			
		else if(playerType=="Random Player")
			return new SimpleRandom(color);
		
		else{
			System.out.println("invalid player type. I guess I will just have to play for you.");
			return new SimpleRandom(color);
		}
	}

	public void stopGame(){
		this.gameStopped = true;
	}
	
	public static void main(String[] args){
		if (args.length == 1 && args[0].equals("text")) {
	        Scanner scanner = new Scanner(System.in);
	        CommandLineView.setup(scanner);
	    } else
	        SwingUtilities.invokeLater(GraphicalBoardView.setup());
	}
}
