package mjoyce.poker;

import mjoyce.poker.Card.Rank;

public class TwoPairRecognizer extends CompoundRecognizer {
	public TwoPairRecognizer() {
		super(true, new PairRecognizer(), new PairRecognizer());
	}

	public Rank getTopPairRank() {
		return ((SetRecognizer) mRecognizers[0]).getMatchedRank();
	}
	
	public Rank getSecondPairRank() {
		return ((SetRecognizer) mRecognizers[1]).getMatchedRank();
	}
	
	@Override
	public HandType getHandType() {
		return HandType.TWO_PAIR;
	}
	
	@Override
	protected int compareTiebreaker(HandRecognizer otherHand) {
		// otherHand represents same hand type
		TwoPairRecognizer otherFullHouse = (TwoPairRecognizer) otherHand;
		int comparisonResult = otherFullHouse.getTopPairRank().ordinal() - this.getTopPairRank().ordinal();
		if (comparisonResult != 0) {
			return comparisonResult;
		}
		
		comparisonResult = otherFullHouse.getSecondPairRank().ordinal() - this.getSecondPairRank().ordinal();
		if (comparisonResult != 0) {
			return comparisonResult;
		}
		
		return super.compareTiebreaker(otherHand);
	}
}
