package mjoyce.poker.recognizers;

import mjoyce.poker.Card.Rank;

/**
 * HandRecognizer subclass that recognizes a Flush.
 * @author mjoyce
 */
public class FullHouseRecognizer extends CompoundRecognizer {
	public FullHouseRecognizer() {
		// Look for a Three of a Kind and a Pair to match a Full House.
		super(true, new ThreeOfAKindRecognizer(), new PairRecognizer());
	}
	
	/**
	 * @return The rank of the Three of a Kind contained in the Full House.
	 */
	public Rank getTripsRank() {
		return ((SetRecognizer) mRecognizers[0]).getMatchedRank();
	}
	
	/**
	 * @return The rank of the Pair contained in the Full House.
	 */
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
		
		// For tiebreaking a Full House, the hand with the higher Three of a Kind is the winner.
		int comparisonResult = otherFullHouse.getTripsRank().ordinal() - this.getTripsRank().ordinal();
		if (comparisonResult != 0) {
			return comparisonResult;
		}
		return otherFullHouse.getPairRank().ordinal() - this.getPairRank().ordinal();
	}
}
