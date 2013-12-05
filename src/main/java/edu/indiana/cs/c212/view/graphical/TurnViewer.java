package edu.indiana.cs.c212.view.graphical;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import java.awt.geom.Ellipse2D;

import edu.indiana.cs.c212.gameMechanics.GameRunner;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class TurnViewer extends JPanel implements Observer {
	private PlayerColor player;
	private GameRunner game;
	private Image turnTriangle;
	private Image nonTurnTriangle;
	
	public TurnViewer(PlayerColor player, GameRunner game){
		this.player = player;
		this.game = game;
		//TODO: create path for triangle images
	}
	
	public void paintComponent(Graphics g){
		if (game.getCurrentPlayer().equals(player)){
			//TODO: set dimensions
			this.imageUpdate(turnTriangle, infoflags, x, y, w, h)
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.paintComponent(g);
		
	}
	
	

}
