package edu.indiana.cs.c212.view.graphical;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Observable;
import java.util.Observer;

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
	private JLabel redLabel = new JLabel("RED:");
	private Polygon redTriangle;
	private JLabel blueLabel = new JLabel("BLUE: ");
	private Polygon blueTriangle;
	
	public TurnViewer(PlayerColor player, String redName, String blueName, GameRunner game){
		this.player = player;
		this.game = game;
		redLabel.setText(redName);
		blueLabel.setText(blueName);
		int xPoints[] = {0, 0, getWidth()};
	    int yPoints[] = {0, getHeight(), getHeight()/2};
		redTriangle = new Polygon(xPoints, yPoints, 3);
		
		blueTriangle = new Polygon(xPoints, yPoints, 3);
		game.notifyObservers();
	}
	
	public void paintComponent(Graphics g){
		
		Graphics2D temp = (Graphics2D) g;
		temp.drawPolygon(redTriangle);
		temp.drawPolygon(blueTriangle);
		if(this.player.equals(PlayerColor.RED)){
			temp.setColor(Color.RED);
			temp.fillPolygon(redTriangle);
		}
		else{
			temp.setColor(Color.BLUE);
			temp.fillPolygon(blueTriangle);
		}
		
		temp.drawPolygon(redTriangle);
		temp.drawPolygon(blueTriangle);
		super.paintComponent(g);
	}
	
	@Override
	public void update(Observable o, Object arg) {
	}
	
	

}
