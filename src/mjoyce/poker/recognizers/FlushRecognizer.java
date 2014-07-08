package mjoyce.poker.recognizers;

import java.util.ArrayList;
import java.util.List;

import mjoyce.poker.Card;
import mjoyce.poker.Card.Rank;

/**
 * HandRecognizer subclass that recognizes a Flush.
 * @author mjoyce
 */
public class FlushRecognizer extends HandRecognizer {
	
	private Rank [] mSortedRanks;
	
	/**
	 * @return A list of sorted ranks, highest to lowest.
	 */
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
		
		// Search through each card and ensure all suits are the same.
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
	
	@Override
	protected int compareTiebreaker(HandRecognizer otherHand) {
		// otherHand represents same hand type
		FlushRecognizer otherFlushHand = (FlushRecognizer) otherHand;

		// We must compare each card in the Flush to determine the winning hand.
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
