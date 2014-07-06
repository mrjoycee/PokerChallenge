package mjoyce.poker;

import java.util.ArrayList;
import java.util.List;

import mjoyce.poker.Card.Rank;

public abstract class SetRecognizer extends HandRecognizer {
	private int mSetSize;
	private Rank mMatchedRank;
	
	public SetRecognizer(int setSize) {
		super();
		mSetSize = setSize;
	}
	
	public Rank getMatchedRank() {
		return mMatchedRank;
	}
	
	@Override
	protected List<Card> doRecognize(List<Card> hand) {
		List<Card> kickers = new ArrayList<Card>();
		List<Card> matchingSet = new ArrayList<Card>();
		Card prevCard = null;
		int setCount = 1;
		for (Card card : hand) {
			if (prevCard != null) {
				if (prevCard.getRank() == card.getRank() && !mIsMatch) {
					matchingSet.add(prevCard);
					setCount++;
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
