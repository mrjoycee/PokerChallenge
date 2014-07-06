package mjoyce.poker;

import java.util.ArrayList;
import java.util.List;

import mjoyce.poker.Card.Rank;

public class FlushRecognizer extends HandRecognizer {
	
	private Rank [] mSortedRanks;
	
	public Rank [] getSortedRanks() {
		return mSortedRanks;
	}
	
	@Override
	public HandType getHandType() {
		return HandType.FLUSH;
	}

	@Override
	protected List<Card> doRecognize(List<Card> hand) {
		Rank [] sortedRanks = new Rank [hand.size()];
		int rankIndex = 0;
		Card prevCard = null;
		for (Card card : hand) {
			if (prevCard != null) {
				if (card.getSuit() != prevCard.getSuit()) {
					// a break in the sequence
					return hand;
				}
			}
			prevCard = card;
			sortedRanks[rankIndex] = card.getRank();
			rankIndex++;
		}
		
		// if we end up here the straight has been recognized
		mIsMatch = true;
		mSortedRanks = sortedRanks;
		return new ArrayList<Card>();
	}
	
	protected int compareTiebreaker(HandRecognizer otherHand) {
		// otherHand represents same hand type
		FlushRecognizer otherFlushHand = (FlushRecognizer) otherHand;
		for (int i = 0; i < this.getSortedRanks().length; i++) {
			Rank card1 = this.getSortedRanks()[i];
			Rank card2 = otherFlushHand.getSortedRanks()[i];
			int comparisonResult = card2.ordinal() - card1.ordinal();
			if (comparisonResult != 0) {
				return comparisonResult;
			}
		}
		
		// if we get to here, the hands are considered tied
		return 0;
	}
}
