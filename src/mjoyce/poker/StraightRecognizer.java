package mjoyce.poker;

import java.util.ArrayList;
import java.util.List;

import mjoyce.poker.Card.Rank;

public class StraightRecognizer extends HandRecognizer {
	
	private Rank mHighCard;
	
	public Rank getHighCard() {
		return mHighCard;
	}

	@Override
	protected List<Card> doRecognize(List<Card> hand) {
		Card prevCard = null;
		for (Card card : hand) {
			if (prevCard != null) {
				if (card.getRank().ordinal() != prevCard.getRank().ordinal() - 1) {
					// a break in the sequence
					return hand;
				}
			}
			prevCard = card;
		}
		
		// if we end up here the straight has been recognized
		mIsMatch = true;
		mHighCard = hand.get(0).getRank();
		return new ArrayList<Card>();
	}

	@Override
	public HandType getHandType() {
		return HandType.STRAIGHT;
	}
	
	@Override
	protected int compareTiebreaker(HandRecognizer otherHand) {
		// otherHand represents same hand type
		return ((StraightRecognizer) otherHand).getHighCard().ordinal() - this.getHighCard().ordinal();
	}
}
