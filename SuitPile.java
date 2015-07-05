package solitare;

class SuitPile extends CardPile {

	SuitPile (final int x, final int y) { super(x, y); }

	@Override
	public void select(int tx, int ty) {
		
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
	}
	
	@Override
	public boolean canTake (final Card aCard) {
		if (empty()) {
			return aCard.isAce();
		}
		Card topCard = top();
		return (aCard.getSuit() == topCard.getSuit()) &&
			(aCard.getRank() == 1 + topCard.getRank());
		}
}