package edu.indiana.cs.c212.view.graphical;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Polygon;
import java.util.Observable;
import java.util.Observer;

//import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.indiana.cs.c212.gameMechanics.GameRunner;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class TurnViewer extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5074459066652373894L;
	private PlayerColor player;
	private GameRunner game;
	private JLabel label = new JLabel("RED:");
	
	public TurnViewer(PlayerColor player, String playerName, GameRunner game){
		this.player = player;
		this.game = game;
		label.setText(playerName);
		this.add(label);
		game.notifyObservers();
	}
	
	public void paintComponent(Graphics g){
		
		//BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		//this.setLayout(layout);
		label.setText(game.getPlayer(player).getName());
		Graphics2D temp = (Graphics2D) g;
		
		boolean isRedsTurn = game.getCurrentPlayer().getColor().equals(PlayerColor.RED);
		if(isRedsTurn && player.equals(PlayerColor.RED)){
			temp.setColor(Color.RED);
			temp.fillPolygon(new int[]{0, 90, 0}, new int[]{0, 90, 180}, 3);
		}
		else{
			temp.setColor(Color.BLUE);
			temp.fillPolygon(new int[]{0, 90, 0}, new int[]{0, 90, 180}, 3);
		}
		
		temp.drawPolygon(new int[]{0, 90, 0}, new int[]{0, 90, 180}, 3);
		if(game.getCurrentPlayer().getColor().equals(player))
			label.setText(game.getCurrentPlayer().getName() + "'s turn!");
		super.paintComponent(g);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	

}
