/*
	ArrayDataCollection.java

		This class defines and implements a collection of data items.

		The implementation is based on an array of Items.

 */

import java.awt.*; //	AWT = "Abstract Window Toolkit"
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Collection.
 */
public class Collection implements DataCollection {

	/** The head. */
	private Node head;

	/** The legal moves. */
	private DataCollection legalMoves;

	/** The current. */
	private CheckerPiece current;

	/** The king. */
	private boolean canMove, king; // Keeps track of the "selected" item

	/** The y. */
	private int x, y; // Location of the "first" item

	/** The size. */
	private int size; // in the collection

	// current player.

	//
	// The default constructor sets the collection at an arbitrary location.
	//
	/**
	 * Instantiates a new collection.
	 */
	public Collection() {
		head = null;
	}

	/**
	 * Instantiates a new collection.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Collection(int x, int y) {
		head = null;
		king = false;
		size = 0;
		this.x = x;
		this.y = y;
	}

	//
	// a d d
	// =====
	//
	// Adds the given Item to the collection.
	// That item becomes the item currently selected.
	//
	/* (non-Javadoc)
	 * @see DataCollection#add(CheckerPiece)
	 */
	public void add(CheckerPiece someItem) {
		Node holder = new Node();
		holder.setItem(someItem);
		holder.setNext(head);
		int col = someItem.getCol();
		int row = someItem.getRow();
		head = holder;
		someItem.setLocation(col, row);

	}


	//
	// r e m o v e
	// ===========
	//
	// Removes the selected item (if any).
	// No item is selected any more.
	// //update x and y coordinates for all nodes a past selected
	/* (non-Javadoc)
	 * @see DataCollection#remove(CheckerPiece)
	 */
	public void remove(CheckerPiece somePiece) {

		Node previousNode = null;
		Node currentNode = head;
		while (currentNode.getNext() != null) {

			if (currentNode.getItem() == somePiece) {
				if (currentNode.getNext() != null) {
					previousNode.setNext(currentNode.getNext());
				} else {
					previousNode.setNext(null);
				}
			}

			previousNode = currentNode;
			currentNode = currentNode.getNext();

		}

	}

	/* (non-Javadoc)
	 * @see DataCollection#findSelected(int, int)
	 */
	public CheckerPiece findSelected(int col, int row) {
		Node current = head;
		CheckerPiece temp = null;
		while (current.getNext() != null) {
			temp = current.getItem();
			if (temp.getCol() == col && temp.getRow() == row) {
				return temp;
			}
			current = current.getNext();
		}

		return null;

	}

	//	public void getLegalMovesFor(CheckerPiece somePiece,
	//			DataCollection enemyTeam) {
	//
	//		CheckerPiece legalMove = new CheckerPiece();
	//		int tempCol = somePiece.getCol();
	//		int tempRow = somePiece.getRow();
	//		if (somePiece.isKing()) {
	//
	//			if (tempRow + 2 <= 7) {
	//				if (tempCol - 2 >= 0
	//						&& doesTeamPopulate(tempCol, tempRow) == false
	//						&& enemyTeam.doesTeamPopulate(tempCol, tempRow) == false) {
	//					legalMove.setLocation(tempCol - 2, tempRow + 2);
	//					legalMoves.add(legalMove);
	//				}
	//				if (tempCol + 2 > 0
	//						&& doesTeamPopulate(tempCol, tempRow) == false
	//						&& enemyTeam.doesTeamPopulate(tempCol, tempRow) == false) {
	//					legalMove.setLocation(tempCol + 2, tempRow + 2);
	//					legalMoves.add(legalMove);
	//				}
	//			}
	//			if (tempRow - 2 >= 0) {
	//				if (tempCol - 2 >= 0
	//						&& doesTeamPopulate(tempCol, tempRow) == false
	//						&& enemyTeam.doesTeamPopulate(tempCol, tempRow) == false) {
	//					legalMove.setLocation(tempCol - 2, tempRow - 2);
	//					legalMoves.add(legalMove);
	//				}
	//				if (tempCol + 2 <= 7
	//						&& doesTeamPopulate(tempCol, tempRow) == false
	//						&& enemyTeam.doesTeamPopulate(tempCol, tempRow) == false) {
	//					legalMove.setLocation(tempCol + 2, tempRow - 2);
	//					legalMoves.add(legalMove);
	//				}
	//			}
	//		} else {
	//			if (somePiece.getColor() == Color.RED) {
	//				if (tempRow + 2 <= 7) {
	//					if (tempCol - 2 >= 0
	//							&& doesTeamPopulate(tempCol, tempRow) == false
	//							&& enemyTeam.doesTeamPopulate(tempCol, tempRow) == false) {
	//						legalMove.setLocation(tempCol - 2, tempRow + 2);
	//						legalMoves.add(legalMove);
	//					}
	//					if (tempCol + 2 > 0
	//							&& doesTeamPopulate(tempCol, tempRow) == false
	//							&& enemyTeam.doesTeamPopulate(tempCol, tempRow) == false) {
	//						legalMove.setLocation(tempCol + 2, tempRow + 2);
	//						legalMoves.add(legalMove);
	//					}
	//				}
	//			} else {
	//				if (tempCol - 2 >= 0
	//						&& doesTeamPopulate(tempCol, tempRow) == false
	//						&& enemyTeam.doesTeamPopulate(tempCol, tempRow) == false) {
	//					legalMove.setLocation(tempCol - 2, tempRow - 2);
	//					legalMoves.add(legalMove);
	//				}
	//				if (tempCol + 2 <= 7
	//						&& doesTeamPopulate(tempCol, tempRow) == false
	//						&& enemyTeam.doesTeamPopulate(tempCol, tempRow) == false) {
	//					legalMove.setLocation(tempCol + 2, tempRow - 2);
	//					legalMoves.add(legalMove);
	//				}
	//			}
	//		}
	//
	//	}

	/* (non-Javadoc)
	 * @see DataCollection#doesTeamPopulate(int, int)
	 */
	@Override
	public boolean doesTeamPopulate(int col, int row) {

		Node temp = head;
		while (temp.getNext() != null) {
			if (temp.doesPopulate(col, row) == true)

				return true;
			temp = temp.getNext();
		}

		return false;
	}



	//	public void display(Graphics pane) {
	//		System.out.println("SHould be 8");
	//		Node currentNode = head;
	//		System.out.println("SHould be 8");
	//		if (currentNode != null) {
	//			System.out.println("SHould be 8");
	//			while (currentNode.getNext() != null) {
	//
	//				CheckerPiece element = currentNode.getItem();
	//				element.display(pane);
	//
	//				currentNode = currentNode.getNext();
	//
	//			}
	//		}
	//	}

	//
	// p a i n t
	// =========
	//
	// The only "graphical" method in the class visualizes the collection.
	// face
	/* (non-Javadoc)
	 * @see DataCollection#paint(java.awt.Graphics)
	 */
	public void paint(Graphics pane) {
		Node currentNode = head;

		while (currentNode != null) {
			if (currentNode.getItem() != null) {
				CheckerPiece element = currentNode.getItem();

				element.paint(pane);
				currentNode = currentNode.getNext();

			}
		}
	}

	@Override
	public void reset() {
		head = null;

	}
}

// end DataCollection
