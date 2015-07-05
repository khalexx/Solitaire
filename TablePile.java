package solitare;

import java.awt.Graphics;

class TablePile extends PlayablePile {
	
	private int lastCardY=0;

	TablePile (final int x, final int y, final int c) {
			// initialize the parent class
		super(x, y);
			// then initialize our pile of cards
		for (int i = 0; i < c; i++) {
			addCard(Solitaire.deckPile.pop());
			}
			// flip topmost card face up
		top().flip();
		}

	@Override
	public boolean canTake (final Card aCard) {
		if (empty()) {
			return aCard.isKing();
		}
		Card topCard = top();
		return (aCard.color() != topCard.color()) &&
			(aCard.getRank() == topCard.getRank() - 1);
		}

	@Override
	public boolean includes (final int tx, final int ty) {
			// don't test bottom of card
		return x <= tx && tx <= x + Card.width &&
			lastCardY <= ty && ty <= lastCardY + Card.height;
		}


	private int stackDisplay(final Graphics g, final Card aCard) {
		int localy;
		if (aCard == null) {
			return y;
		}
		localy = stackDisplay(g, aCard.link);
		aCard.draw(g, x, localy);
		lastCardY=localy;
		return localy + 35;
		}

	@Override
	public void display (final Graphics g) {
		stackDisplay(g, top());
		}

}