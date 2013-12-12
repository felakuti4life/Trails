package edu.indiana.cs.c212.view.graphical;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import edu.indiana.cs.c212.board.Tile;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class HexTile extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 664297970644616392L;
	private Polygon hexPoly;
	private double x;
	private double y;
	private int radius;
	
	private Tile tile;

	
	public HexTile(double x, double y, int radius, Tile tile){
		this.radius = radius;
		hexPoly = new Polygon();
		this.x = x;
		this.y = y;
		
		this.tile = tile;
	}
	
	@Override
	public void paint(Graphics g){
		Graphics2D temp = (Graphics2D) g;
		
		for(int n=0; n<6; n++){
			double theta = n*Math.PI /3+Math.PI/2;
			double xDistance = (int) (this.radius*Math.cos(theta));
			double yDistance = (int) (this.radius*Math.sin(theta));
			hexPoly.addPoint((int) (x+xDistance), (int) (y+yDistance));
		}
		
		if(this.tile.getColor().equals(PlayerColor.RED))
			temp.setColor(Color.RED);
		else if(this.tile.getColor().equals(PlayerColor.BLUE))
			temp.setColor(Color.BLUE);
		else temp.setColor(Color.LIGHT_GRAY);
		
		temp.fillPolygon(hexPoly);
		temp.drawPolygon(hexPoly);
		
		super.paint(g);
	}
	
	
	@Override
	public boolean contains(Point p)
	{
		return this.contains(p);
	}

	@Override
	public boolean contains(int x, int y)
	{
		return this.contains(x, y);
	}

	@Override
	protected void processMouseEvent(MouseEvent e)
	{
	if ( this.hexPoly.contains(e.getPoint()))
		super.processMouseEvent(e);
	}
	
	public final int getBoardX(){
		return (int) this.tile.getPoint().getX();
	}
	
	public final int getBoardY(){
		return (int) this.tile.getPoint().getY();
	}
	
	public void setRadius(int radius){
		this.radius = radius;
		repaint();
	}

	
}
