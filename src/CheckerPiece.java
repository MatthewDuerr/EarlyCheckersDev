/*
 * Matthew J Duerr
 * May 1st, 2016
 * Checkers Final Project
 */

import java.awt.*;

/**
 * The blueprint of a checker piece, this class holds the values of a single
 * checker piece, and has getter and setter methods to get information about an
 * individual piece or manipulate it properties in runtime
 */
public class CheckerPiece {

	/** The row. */
	private int col, row; // To define the location

	/** The color. */
	private Color color; // and the color of an item

	/** The king. */
	private boolean king; // To highlight the item

	/**
	 * Instantiates a new checker piece at a random locations with a neutral
	 * color and not a king
	 */
	public CheckerPiece() {
		col = 0;
		row = 0;
		color = Color.white;
		king = false;
	}

	/**
	 * Instantiates a new checker piece with given properties, the piece will be
	 * located at someX, someY the color or 'team' is given too, a piece doesn't
	 * start off as a king, so king = false.
	 *
	 * @param someX
	 *            the some x or the column in which it will be located
	 * @param someY
	 *            the some y or the row that will hold the piece
	 * @param someColor
	 *            the color of the piece, a piece is either Color.RED or
	 *            Color.BLACK
	 */
	public CheckerPiece(int someX, int someY, Color someColor) {
		col = someX;
		row = someY;
		color = someColor;
		king = false;
	}

	/**
	 * Sets the location if the piece to the given coordinates
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void setLocation(int x, int y) {
		this.col = x;
		this.row = y;
	}

	/**
	 * Gets the column that the CheckerPiece is located in
	 *
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Gets the roow that the CheckerPiece is located
	 *
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Sets the color.
	 *
	 * @param someColor
	 *            sets the color of the piece to a given value
	 */
	public void setColor(Color someColor) {
		color = someColor;
	}

	/**
	 * returns the color of the piece by accessing the pieces color field
	 *
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the king. Only will be used when a player reaches the opposited side
	 * of the board a value of 'true' can be passed
	 *
	 * @param someBoolean
	 *            the new king
	 */
	public void setKing(boolean someBoolean) {
		king = someBoolean;
	}

	/**
	 * Checks if piece is a king. since king is either false or true the value
	 * king can be returned which will return an either true or false value
	 *
	 * @return the boolean
	 */
	public Boolean isKing() {
		return king;

	}

	/**
	 * Paint a the contents of the piece, this paint method will create the
	 * piece based on its values For instance if the piece isa king it will
	 * place a k on it
	 *
	 * @param pane
	 *            the pane
	 */
	public void paint(Graphics pane) {

		pane.setColor((getColor()));
		pane.fillOval(2 + 20 * col, 2 + 20 * row, 20, 20);
		if (king == true)
			pane.drawString("K", 7 + col * 20, 16 + row * 20);

	}

} // end Item