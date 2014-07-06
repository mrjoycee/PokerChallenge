package mjoyce.poker;

import mjoyce.poker.Card.Rank;

public class FullHouseRecognizer extends CompoundRecognizer {
	public FullHouseRecognizer() {
		super(true, new ThreeOfAKindRecognizer(), new PairRecognizer());
	}
	
	public Rank getTripsRank() {
		return ((SetRecognizer) mRecognizers[0]).getMatchedRank();
	}
	
	public Rank getPairRank() {
		return ((SetRecognizer) mRecognizers[1]).getMatchedRank();
	}
	
	@Override
	public HandType getHandType() {
		return HandType.FULL_HOUSE;
	}
	
	@Override
	protected int compareTiebreaker(HandRecognizer otherHand) {
		// otherHand represents same hand type
		FullHouseRecognizer otherFullHouse = (FullHouseRecognizer) otherHand;
		int comparisonResult = otherFullHouse.getTripsRank().ordinal() - this.getTripsRank().ordinal();
		if (comparisonResult != 0) {
			return comparisonResult;
		}
		return otherFullHouse.getPairRank().ordinal() - this.getPairRank().ordinal();
	}
}
