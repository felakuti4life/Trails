package edu.indiana.cs.c212.view.graphical;

import java.awt.AWTEvent;
import java.awt.Point;

public class MoveEvent extends AWTEvent {

	private static final long serialVersionUID = -2089077342079040230L;

	public MoveEvent(Point source, int id) {
	super(source, id);
    }
}