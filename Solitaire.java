package solitare;

/*
 Simple Solitaire Card Game in Java
 Written by Tim Budd, Oregon State University, 1996
 */

import java.awt.*;
import java.applet.*;

public class Solitaire extends Applet {
	static DeckPile deckPile;
	static PlayablePile discardPile;
	static TablePile tableau[];
	static SuitPile suitPile[];
	static CardPile allPiles[];
	static int lastChosenPileId = -1;

	@Override
	public void init() {
		// first allocate the arrays
		allPiles = new CardPile[13];
		suitPile = new SuitPile[4];
		tableau = new TablePile[7];
		// then fill them in
		allPiles[0] = deckPile = new DeckPile(335, 5);
		allPiles[1] = discardPile = new DiscardPile(268, 5);
		for (int i = 0; i < 4; i++) {
			allPiles[2 + i] = suitPile[i] = new SuitPile(15 + 60 * i, 5);
		}
		for (int i = 0; i < 7; i++) {
			allPiles[6 + i] = tableau[i] = new TablePile(5 + 55 * i, 80, i + 1);
		}
	}

	@Override
	public void paint(final Graphics g) {
		for (int i = 0; i < 13; i++) {
			allPiles[i].display(g);
		}
	}

	@Override
	public boolean mouseDown(final Event evt, final int x, final int y) {

		if (evt.clickCount == 2) {
			for (int i = 0; i < 13; i++) {
				if (allPiles[i].includes(x, y)) {
					if (allPiles[i] instanceof PlayablePile) {
						allPiles[i].select(x, y);
						allPiles[i].autoMove(x, y);
					}
					repaint();
				}
			}

		} else {
			for (int i = 0; i < 13; i++) {
				if (allPiles[i].includes(x, y)) {
					allPiles[i].select(x, y);
					repaint();
				}
			}
		}

		return true;
	}
}