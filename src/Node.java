/**
 * Matthew J. Duerr
 * May 1st, 2016
 * Checkers Final Project
 */
import java.awt.*;

/**
 * The Class Node is the programs data structure
 */
public class Node {

	/** The item. */
	private CheckerPiece item;

	/** The next. */
	private Node next;

	// basic constructor for a node
	/**
	 * Instantiates a new node.
	 */
	public Node() {
		item = null;
		next = null;
	}

	/**
	 * Sets the next.
	 *
	 * @param next
	 *            the new next
	 */
	public void setNext(Node next) {
		this.next = next;
	}

	/**
	 * Does populate is a boolean value, it returns true if a piece is located
	 * at the given coordinate
	 *
	 * @param someCol
	 *            the some col
	 * @param someRow
	 *            the some row
	 * @return true, if piece is located
	 */
	public boolean doesPopulate(int someCol, int someRow) {

		if (someRow == item.getRow() && someCol == item.getCol())
			return true;

		return false;
	}

	// retrieves next node
	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public Node getNext() {
		return next;
	}

	// sets element of node, not reference
	/**
	 * Sets the item.
	 *
	 * @param item
	 *            the new item
	 */
	public void setItem(CheckerPiece item) {
		this.item = item;
	}

	// retrieves item from node
	/**
	 * Gets the item.
	 *
	 * @return the item
	 */
	public CheckerPiece getItem() {
		return item;
	}

}