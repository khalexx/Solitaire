package solitare;

public abstract class PlayablePile extends CardPile {

	PlayablePile(int xl, int yl) {
		super(xl, yl);
	}

	@Override
	public void select(int tx, int ty) {
		
		if (empty()) {
			return;
		}
		
		if (Solitaire.lastChosenPileId >= 0) {
				Card tmp = Solitaire.allPiles[Solitaire.lastChosenPileId].pop();
				if (this.canTake(tmp)) {
					tmp.chosen = false;
					this.addCard(tmp);
				} else {
					tmp.chosen = false;
					Solitaire.allPiles[Solitaire.lastChosenPileId].addCard(tmp);
				}
				Solitaire.lastChosenPileId=-1;
				return;
			}
		
		Card topCard = pop();
		
		if (!topCard.isFaceUp()) {
			topCard.flip();
		} else if (topCard.chosen) {
			topCard.chosen = false;
			Solitaire.lastChosenPileId = -1;
//			Solitaire.lastChosen = null;
		} else {
			topCard.chosen = true;
			for (int i = 0; i < Solitaire.allPiles.length; i++) {
				if (this == Solitaire.allPiles[i]) {
					Solitaire.lastChosenPileId = i;
				}
			}
//			Solitaire.lastChosen = topCard;
		}
		super.addCard(topCard);
	}

	@Override
	public void autoMove(final int tx, final int ty) {

		if (empty()) {
			return;
		}

		Card topCard = top();
		
		if (!topCard.isFaceUp()) {
			topCard.flip();
			return;
		}

		// else see if any suit pile can take card
		topCard = pop();
		for (int i = 0; i < 4; i++) {
			if (Solitaire.suitPile[i].canTake(topCard)) {
				Solitaire.suitPile[i].addCard(topCard);
				return;
			}
		}
		// else see if any other table pile can take card
		for (int i = 0; i < 7; i++) {
			if (Solitaire.tableau[i].canTake(topCard)) {
				Solitaire.tableau[i].addCard(topCard);
				return;
			}
		}
		// else put it back on our pile
		addCard(topCard);
	}

}
