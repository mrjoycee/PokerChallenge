package mjoyce.poker.recognizers;

import java.util.ArrayList;
import java.util.List;

import mjoyce.poker.Card;
import mjoyce.poker.Card.Rank;

/**
 * Abstract HandRecognizer subclass that recognizes a set of cards of the same rank. Only the highest-ranked set of the given length
 * in the hand will be matched, with the remaining cards passed on as kickers.
 * @author mjoyce
 */
public abstract class SetRecognizer extends HandRecognizer {
	private int mSetSize;
	private Rank mMatchedRank;
	
	/**
	 * Construct a new SetRecognizer
	 * @param setSize The number of cards in the set.
	 */
	public SetRecognizer(int setSize) {
		super();
		mSetSize = setSize;
	}
	
	/**
	 * @return The rank of the matched set.
	 */
	public Rank getMatchedRank() {
		return mMatchedRank;
	}
	
	@Override
	protected List<Card> doRecognize(List<Card> hand) {
		List<Card> kickers = new ArrayList<Card>();
		List<Card> matchingSet = new ArrayList<Card>();
		Card prevCard = null;
		int setCount = 1;
		
		// The hand is sorted, so cards of like ranks will appear consecutively.
		for (Card card : hand) {
			if (prevCard != null) {
				// Compare cards. If ranks are the same, increment the set counter.
				if (prevCard.getRank() == card.getRank() && !mIsMatch) {
					matchingSet.add(prevCard);
					setCount++;
					
					// If we have reached the specified set size, finish the matching.
					if (setCount == mSetSize) {
						mIsMatch = true;
						mMatchedRank = card.getRank();
						prevCard = null;
						matchingSet.clear();
						continue;
					}
				} else {
					setCount = 1;
					kickers.addAll(matchingSet);
					matchingSet.clear();
					kickers.add(prevCard);
				}
			}
			
			prevCard = card;
		}
		if (prevCard != null) {
			kickers.add(prevCard);
		}
		return mIsMatch ? kickers : hand;
	}
	
	@Override
	protected int compareTiebreaker(HandRecognizer otherHand) {
		// otherHand represents same hand type
		int comparisonResult = ((SetRecognizer) otherHand).getMatchedRank().ordinal() - this.getMatchedRank().ordinal();
		if (comparisonResult != 0) {
			return comparisonResult;
		}
		return super.compareTiebreaker(otherHand);
	}
}
