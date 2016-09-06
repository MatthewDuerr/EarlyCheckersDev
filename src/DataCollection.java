/*
	DataCollection.java

		This interface defines a collection of data items.


 */

import java.awt.Graphics; //	AWT = "Abstract Window Toolkit"

// TODO: Auto-generated Javadoc
/**
 * The Interface DataCollection.
 */
public interface DataCollection {

	//
	// D a t a C o l l e c t i o n
	// ===========================
	//
	// The DataCollection interface is meant to manipulate a collection of
	// items,
	// implemented by the Item class.
	//
	// Central to the definition of that interface (and the manipulation of the
	// collection), is the notion of a 'selected item.'
	//
	// There is at most one item selected in a collection. It is the item that
	// was
	// last added, or the item that will be next handled.
	//
	// The DataCollection interface requires
	// the implementation of the following methods.
	//

	//
	// a d d
	// =====
	//
	/**
	 * Adds the.
	 *
	 * @param someItem
	 *            the some item
	 */
	public void add(CheckerPiece someItem);

	//
	// Adds the given Item to the collection.
	// That item becomes the item currently selected.

	//
	// r e m o v e
	// ===========
	//
	/**
	 * Removes the.
	 *
	 * @param somePiece
	 *            the some piece
	 */
	public void remove(CheckerPiece somePiece);

	//
	// Removes the selected item (if any).
	// No item is selected any more.

	//
	// r e s e t
	// =========
	//

	//
	// p a i n t
	// =========
	//
	/**
	 * Paint.
	 *
	 * @param pane
	 *            the pane
	 */
	public void paint(Graphics pane);

	//
	// The only "graphical" method visualizes the collection.
	// Paints all items in the collection
	// (including the selected item, if any).
	// Items are painted from left to right
	// in the order in which they were added.

	// public DataCollection displayLegalMoves(DataCollection blackTeam);

	/**
	 * Does team populate. iterates through all pieces of collection and test
	 * someone is in that location
	 *
	 * @param col
	 *            the col
	 * @param row
	 *            the row
	 * @return true, if successful
	 */
	public boolean doesTeamPopulate(int col, int row);

	//
	// N o t e :
	// =========
	//
	// The 3 methods hasNext, next, and remove make up the 3 methods required
	// for what is known and
	// defined in Java as the Iterator interface. Other than using Object's,
	// rather than Item's,
	// there is a slight difference, however, in the behavior of the remove
	// method.
	//
	// For comparison, here is the definition of the Iterator interface, which
	// requires the
	// implementation of the following methods.
	//
	// boolean hasNext(): returns true if the executing object contains one or
	// more objects that
	// have not been returned by the next method.
	//
	// Object next(): returns a reference to the next object in the Iterator.
	//
	// void remove(): removes the item most recently returned by the next method
	// from the
	// underlying collection.
	//

	/**
	 * Find selected. Given coordinates return the piece located at col,row
	 * 
	 *
	 * @param col
	 *            the col
	 * @param row
	 *            the row
	 * @return the checker piece
	 */
	public CheckerPiece findSelected(int col, int row);
//start over
	public void reset();

} // end DataCollection
