import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class CheckersGame.
 */
public class CheckersGame extends Canvas implements ActionListener,
		MouseListener {

	/**
	 * The new game button. The resign game button
	 */
	Abutton newGameButton, resignButton;

	/** The game in progress. */
	boolean gameInProgress;

	/** The legal moves. */
	private DataCollection redTeam, blackTeam, legalMoves;

	/** The max pieces. */
	private final int MAX_PIECES = 12;

	/** The can move. */
	boolean isRedPlaying = false, canMove = false;

	/** The jumped piece. */
	CheckerPiece selected, jumpedPiece;

	/** The y. */
	private int x, y;

	/** The last y. */
	private int lastX, lastY;

	/**
	 * Instantiates a new checkers game Set background color and add mouse
	 * listener so user can interact display buttons and call doNewGame()
	 */
	public CheckersGame() {

		setBackground(Color.DARK_GRAY);
		addMouseListener(this);

		x = 170;
		y = 50;

		resignButton = new Abutton("Resign", Color.gray, x, y);
		y += 1.5 * Abutton.BUTTON_HEIGHT;

		newGameButton = new Abutton("New Game", Color.gray, x, y);

		redTeam = new Collection();
		blackTeam = new Collection();

		doNewGame();
	}

	/**
	 * When called test if a game is in progress, if so display message, if not
	 * create new teams
	 */
	public void doNewGame() {
		if (gameInProgress == true) {

			System.out.println("Finish the current game first!");
			return;
		}
		redTeam.reset();
		blackTeam.reset();

		for (int i = 0; i < 8; i++) { // iterate through each column
			for (int j = 0; j < 8; j++) { // iterate through each row
				if (i % 2 == j % 2 && j < 3) {// if on red side of board
					CheckerPiece redTemp = new CheckerPiece(i, j, Color.RED);
					redTeam.add(redTemp);

				}
				if (i % 2 == j % 2 && j > 4) { // if on black side of the board
					CheckerPiece blackTemp = new CheckerPiece(i, j, Color.BLACK);
					blackTeam.add(blackTemp);

				}
			}
			// after all pieces have red start game
		}
		isRedPlaying = true;
		System.out.println("Red:  Make your move.");
		gameInProgress = true;

		repaint();
	}

	/**
	 * When called the game is cancelled and based on who calls will decide
	 * winner
	 */
	public void doResign() {
		if (gameInProgress == false) {
			System.out.println("There is no game in progress!");
			return;
		}
		if (isRedPlaying == true)
			gameOver("RED resigns.  BLACK wins.");
		else
			gameOver("BLACK resigns.  RED winds.");
	}

	/**
	 * however the game ends this method is called to restart game
	 *
	 * @param str
	 *            a messaged past declaring winner
	 * 
	 */
	public void gameOver(String str) {

		System.out.println("You Won");
		gameInProgress = false;
		doNewGame();
	}

	/**
	 * The mouse event passes the col and row to doClickSquare select the piece
	 * clicked or the square clicked if a piece is selected, then store value if
	 * a square is clicked test if it is a valid move then change selected
	 * piece's location. doClickSquare has to test who is playing, what kind of
	 * piece is it and if the user selected a piece
	 *
	 * @param col
	 *            the column that was clicked
	 * @param row
	 *            the row that was clicked
	 */
	public void doClickSquare(int col, int row) {

		if (isRedPlaying) { // if red is playing
			if (selected == null) { // if no piece is selected
				if (redTeam.doesTeamPopulate(col, row)) { // if user clicked a
					// piece
					selected = redTeam.findSelected(col, row); // piece is now
					// the selected
					// piece
					System.out.println("Click the square you want to move.");
				}
			} else { // if a piece is selected
				if (isMoveValid(selected, col, row) && row == 8) {
					selected.setKing(true);
				}
				if (isMoveValid(selected, col, row)) {// if user clicked a valid
					// move
					selected.setLocation(col, row); // set piece at location
					isRedPlaying = false;
					System.out.println("Black:  Make your move.");
					selected = null;
				} else { // if not a valid move select piece
					selected = null;
					System.out.println("Try another move");
				}
			}
		} else if (isRedPlaying == false) { // if black is playing
			if (selected == null) {
				if (blackTeam.doesTeamPopulate(col, row)) { // does the
					// blackteam have a
					// piece in that
					// location
					selected = blackTeam.findSelected(col, row);

					System.out.println("Click the square you want to move.");

				}
			} else {
				if (isMoveValid(selected, col, row) && row == 0) {
					selected.setKing(true);
				}
				if (isMoveValid(selected, col, row)) {
					selected.setLocation(col, row);
					isRedPlaying = true;
					System.out.println("RED:  Make your move.");
					selected = null;
				} else {
					selected = null;
					System.out.println("Try another move");
				}

			}
		}

		repaint();

	} // end doClickSquare()

	/**
	 * Checks if is move valid. if the the move is a jump then remove jumped
	 * piece if its a move then check if its valid
	 *
	 * @param selected
	 *            the selected
	 * @param col
	 *            the col
	 * @param row
	 *            the row
	 * @return true, if is move valid
	 */
	private boolean isMoveValid(CheckerPiece selected, int col, int row) {

		if (isJump(selected, col, row)) {
			System.out.println("Good Jump.");

			return true;
		}
		if (isMove(selected, col, row)) {
			System.out.println("Good Move");

			return true; // (r3,c3) is off the board.
		}
		System.out.println("Bad Move");

		return false;
	}

	/**
	 * Checks if is move.
	 *
	 * @param piece
	 *            the piece
	 * @param toCol
	 *            the to col
	 * @param toRow
	 *            the to row
	 * @return true, if the user selected a move if is move
	 */
	private boolean isMove(CheckerPiece piece, int toCol, int toRow) {

		if (toCol < 0 || toCol >= 8 || toRow < 0 || toRow >= 8) {
			return false;
		}
		if (toCol == piece.getCol() || toRow == piece.getRow()) {
			return false;

		}

		if (blackTeam.doesTeamPopulate(toCol, toRow)
				|| redTeam.doesTeamPopulate(toCol, toRow)) {

			return false;
		}
		if (inRightDirection(piece, toCol, toRow) == false) {
			System.out.println("Wrong direction");

			return false;
		}

		return true;
	}

	/**
	 * In right direction, only a king piece and go in either directed, this
	 * will check if the selected piece is allowed to go in the desired location
	 *
	 * @param piece
	 *            the piece
	 * @param toCol
	 *            the to col
	 * @param toRow
	 *            the to row
	 * @return true, if successful
	 */
	public boolean inRightDirection(CheckerPiece piece, int toCol, int toRow) {
		if (piece.isKing()) { // if the piece is a king then any direction is
			// correct
			return true;
		}
		if (piece.getColor() == Color.RED) {
			if (piece.getRow() - toRow > 0) {// red piece can only go up the
				// board
				return false;
			}
		} else {
			if (piece.getRow() - toRow < 0) {// black piece can only go down the
				// board
				return false;
			}

		}
		return true;
	}

	/**
	 * Checks if the chosen coordinates is a jump, the method must check if
	 * someone on the other team is in the location, if so not valid, it then
	 * test if there is an enemy piece between the original location the new, if
	 * so its true.
	 *
	 * @param selected
	 *            the selected
	 * @param toCol
	 *            the to col
	 * @param toRow
	 *            the to row
	 * @return true, if is jump
	 */
	public boolean isJump(CheckerPiece selected, int toCol, int toRow) {
		// Test whether this move is a jump. It is assumed that
		// the move is legal. In a jump, the piece moves two
		// rows. (In a regular move, it only moves one row.)
		jumpedPiece = new CheckerPiece();
		int fromCol = selected.getCol();
		int fromRow = selected.getRow();
		if (selected.isKing()) {
			if (fromRow - toRow == 2) { // moving up
				if (fromCol - toCol == 2) { // move left
					if (isRedPlaying) {
						if (blackTeam.doesTeamPopulate(fromCol - toCol - 1,
								fromRow - toRow - 1)) {
							jumpedPiece = blackTeam.findSelected(fromCol
									- toCol - 1, fromRow - toRow - 1);
							blackTeam.remove(jumpedPiece);
							return true;
						}
					} else {
						if (redTeam.doesTeamPopulate(fromCol - toCol - 1,
								fromRow - toRow - 1)) {
							jumpedPiece = redTeam.findSelected(fromCol - toCol
									- 1, fromRow - toRow - 1);
							redTeam.remove(jumpedPiece);
							return true;
						}
					}
					if (fromCol - toCol == -2) { // moving right
						if (isRedPlaying) {
							if (blackTeam.doesTeamPopulate(fromCol - toCol + 1,
									fromRow - toRow - 1)) {
								jumpedPiece = blackTeam.findSelected(fromCol
										- toCol + 1, fromRow - toRow - 1);
								blackTeam.remove(jumpedPiece);
								return true;
							}
						} else {
							if (redTeam.doesTeamPopulate(fromCol - toCol + 1,
									fromRow - toRow - 1)) {
								jumpedPiece = redTeam.findSelected(fromCol
										- toCol + 1, fromRow - toRow - 1);

								redTeam.remove(jumpedPiece);
								return true;
							}
						}
					}

				}

				if (fromRow - toRow == -2) { // moving down
					if (fromCol - toCol == 2) { // move left
						if (isRedPlaying) {
							if (blackTeam.doesTeamPopulate(fromCol - toCol + 1,
									fromRow - toRow - 1)) {
								jumpedPiece = blackTeam.findSelected(fromCol
										- toCol + 1, fromRow - toRow + 1);
								blackTeam.remove(jumpedPiece);
								return true;
							}
						} else {
							if (redTeam.doesTeamPopulate(fromCol - toCol + 1,
									fromRow - toRow - 1)) {
								jumpedPiece = redTeam.findSelected(fromCol
										- toCol + 1, fromRow - toRow + 1);
								redTeam.remove(jumpedPiece);
								return true;
							}
						}
					}
					if (fromCol - toCol == -2) { // moving right
						if (isRedPlaying) {
							if (blackTeam.doesTeamPopulate(fromCol - toCol + 1,
									fromRow - toRow + 1)) {
								jumpedPiece = blackTeam.findSelected(fromCol
										- toCol + 1, fromRow - toRow + 1);
								blackTeam.remove(jumpedPiece);
								return true;
							}
						} else {
							if (redTeam.doesTeamPopulate(fromCol - toCol + 1,
									fromRow - toRow + 1)) {
								jumpedPiece = redTeam.findSelected(fromCol
										- toCol + 1, fromRow - toRow + 1);
								redTeam.remove(jumpedPiece);
								return true;
							}
						}
					}
				}
			}
		} else {
			if (isRedPlaying) {
				if (fromCol - toCol == 2) { // move left
					if (blackTeam.doesTeamPopulate(fromCol - 1, fromRow + 1)) {
						jumpedPiece = blackTeam.findSelected(fromCol - 1, fromRow + 1);
						blackTeam.remove(jumpedPiece);

						return true;
					}
				}
				if (fromCol - toCol == -2) { // moving right
					if (blackTeam.doesTeamPopulate(fromCol + 1, fromRow + 1)) {
						jumpedPiece = blackTeam.findSelected(fromCol + 1, fromRow + 1);
						blackTeam.remove(jumpedPiece);

						return true;
					}
				}
			} else {
				if (fromCol - toCol == 2) { // move left
					if (redTeam.doesTeamPopulate(fromCol - 1, fromRow - 1)) {
						jumpedPiece = redTeam.findSelected(fromCol - 1, fromRow - 1);
						redTeam.remove(jumpedPiece);

						return true;
					}
				}
				if (fromCol - toCol == -2) { // moving right
					if (redTeam.doesTeamPopulate(fromCol + 1, fromRow - 1)) {
						jumpedPiece = redTeam.findSelected(fromCol + 1,fromRow - 1);
						redTeam.remove(jumpedPiece);

						return true;
					}
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();
		if (src == newGameButton)
			doNewGame();
		else if (src == resignButton)
			doResign();
	}

	/**
	 * Checks if either the resign or new game button was clicked
	 */
	private void check() {

		if (resignButton.isInside(lastX, lastY)) {
			doResign();
		}

		else if (newGameButton.isInside(lastX, lastY)) {
			doNewGame();
		}

		repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	public void paint(Graphics pane) {

		pane.setColor(Color.black);
		pane.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
		pane.drawRect(1, 1, getSize().width - 3, getSize().height - 3);

		if (newGameButton != null)
			newGameButton.paint(pane);

		if (resignButton != null)
			resignButton.paint(pane);

		/* Draw the squares of the checkerboard and the checkers. */

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (row % 2 == col % 2)
					pane.setColor(Color.lightGray);
				else
					pane.setColor(Color.gray);
				pane.fillRect(2 + col * 20, 2 + row * 20, 20, 20);

			}
		}

		if (gameInProgress) { // paint teams

			redTeam.paint(pane);
			blackTeam.paint(pane);
		}

	} // end paint()

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent event) {
		check();
		lastX = event.getX();
		lastY = event.getY();
		if (redTeam == null || blackTeam == null) {
			doNewGame();
		}
		if (gameInProgress == false)
			System.out.println("Click \"New Game\" to start a new game.");
		else {
			int col = (lastX - 2) / 20;
			int row = (lastY - 2) / 20;
			if (col >= 0 && col < 8 && row >= 0 && row < 8) {
				doClickSquare(col, row);
			}

		}
		repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent evt) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent evt) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent evt) {
	}

}