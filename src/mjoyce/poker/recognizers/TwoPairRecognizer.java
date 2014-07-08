package mjoyce.poker.recognizers;

import mjoyce.poker.Card.Rank;

/**
 * HandRecognizer subclass that recognizes a Two Pair.
 * @author mjoyce
 */
public class TwoPairRecognizer extends CompoundRecognizer {
	public TwoPairRecognizer() {
		// Match two pairs in the hand. The first recognizer will always match the higher-ranked pair.
		super(true, new PairRecognizer(), new PairRecognizer());
	}

	/**
	 * @return The rank of the top pair.
	 */
	public Rank getTopPairRank() {
		return ((SetRecognizer) mRecognizers[0]).getMatchedRank();
	}
	
	/**
	 * @return The rank of the second pair.
	 */
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
		
		// Compare both sets of pairs, starting with the higher-ranked one. May have to use the remaining kicker card to break the tie.
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
