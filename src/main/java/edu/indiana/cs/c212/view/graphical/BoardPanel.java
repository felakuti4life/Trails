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
		Toolkit tk = Toolkit.getDefaultToolkit();
		for (AWTEventListener listener : tk.getAWTEventListeners())
		    listener.eventDispatched(e);
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
				int xCoord = 0;
				int yCoord = 0;
				if(y % 2 == 0){
					xCoord = TILES_X_OFFSET + x*2*this.radius;
					yCoord = TILES_Y_OFFSET + 2*y*this.radius;
				}
				else{
					xCoord = TILES_X_OFFSET + x*2*this.radius;
					yCoord = TILES_Y_OFFSET + 3*y*this.radius;
				}

				HexTile hexTile = new HexTile(xCoord, yCoord, this.radius, tile);
				hexTile.addActionListener(this);

				hexTileList.add(hexTile);
			}
		}
		
	}
}
