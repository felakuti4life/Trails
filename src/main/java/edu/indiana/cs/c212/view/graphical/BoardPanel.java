package edu.indiana.cs.c212.view.graphical;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.Tile;

public class BoardPanel extends JPanel implements ActionListener, Observer {
	
	private static final long serialVersionUID = -2590536940878831526L;
	private static int TILES_X_OFFSET = 40;
	private static int TILES_Y_OFFSET = 30;
	protected Board board;
	protected Point chosenXY;
	private int radius = 40;
	
	private List<HexTile> hexTileList = new ArrayList<HexTile>();
	
	public BoardPanel(Board board){
		resetTiles(board);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		HexTile tile = (HexTile) e.getSource();
		
		int x = tile.getBoardX();
		int y = tile.getBoardY();
		Point point = new Point(x,y);
		
		MoveEvent moveEvent = new MoveEvent (point, e.getID());
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		for (AWTEventListener listener : toolkit.getAWTEventListeners()) {
		    listener.eventDispatched(moveEvent);
		}
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		Board currentBoard = (Board) arg1;
		resetTiles(currentBoard);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		for (HexTile tile : hexTileList) {
			tile.paint(g);
		}
		
	}
	
	public void resetTiles(Board board){
		this.board = board;

		for (HexTile tile:hexTileList)
			tile.removeActionListener(this);

		hexTileList.clear();
		int boardSize = board.getSize();
		
		for (int x = 0; x < boardSize; x++) {
			for (int y = 0; y < boardSize; y++) {
				Tile tile = board.getTileAt(x, y);
				double xCoord = TILES_X_OFFSET + y*Math.sqrt(3)*0.5*this.radius+ x*Math.sqrt(3)*this.radius;
				double yCoord = TILES_Y_OFFSET + y*1.5*this.radius;
				
				HexTile hexTile = new HexTile(xCoord, yCoord, this.radius, tile);
				hexTile.addActionListener(this);

				hexTileList.add(hexTile);
			}
		}
		
	}
}
