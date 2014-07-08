package mjoyce.poker.recognizers;

import mjoyce.poker.Card.Rank;

/**
 * HandRecognizer subclass that recognizes a Straight Flush.
 * @author mjoyce
 */
public class StraightFlushRecognizer extends CompoundRecognizer {
	public StraightFlushRecognizer() {
		// Match both a Straight and a Flush
		super(false, new StraightRecognizer(), new FlushRecognizer());
	}
	
	/**
	 * @return The highest ranked card of this Straight Flush.
	 */
	public Rank getHighCard() {
		return ((StraightRecognizer) mRecognizers[0]).getHighCard();
	}
	
	@Override
	public HandType getHandType() {
		// Extra logic to use the Royal Flush type if the high card is an ace.
		if (getHighCard() == Rank.ACE) {
			return HandType.ROYAL_FLUSH;
		}
		
		return HandType.STRAIGHT_FLUSH;
	}
	
	@Override
	protected int compareTiebreaker(HandRecognizer otherHand) {
		// otherHand represents same hand type
		return ((StraightFlushRecognizer) otherHand).getHighCard().ordinal() - this.getHighCard().ordinal();
	}
}
