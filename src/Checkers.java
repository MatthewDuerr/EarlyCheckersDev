/*
 * Matthew J Duerr
 * May 1st, 2016
 * Checkers Final Project
 */

import java.awt.*;
import java.awt.event.*;

/**
 * The checkers class is where the main function is located as well as the
 * constructor for a game of checkers.
 */
public class Checkers extends Frame {

	/**
	 * The main method. When the program is started the main method is the first
	 * to be called, meaning the main should call the constructor and display
	 * frame
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {

		Checkers game = new Checkers();
		game.setSize(400, 400);
		game.setVisible(true);
		game.setLayout(new FlowLayout());
		game.addWindowListener(new WindowAdapter() { // to click out
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});

	}

	/**
	 * Instantiates a new checkers game, bounds are set and the game is added
	 */
	public Checkers() {

		CheckersGame game = new CheckersGame();
		add(game);
		game.setBounds(20, 20, 164, 164);
	}

}
