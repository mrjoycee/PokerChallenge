package mjoyce.poker.recognizers;

import mjoyce.poker.model.Card.Rank;

public class StraightFlushRecognizer extends CompoundRecognizer {
	public StraightFlushRecognizer() {
		super(false, new StraightRecognizer(), new FlushRecognizer());
	}
	
	public Rank getHighCard() {
		return ((StraightRecognizer) mRecognizers[0]).getHighCard();
	}
	
	@Override
	public HandType getHandType() {
		return HandType.STRAIGHT_FLUSH;
	}
	
	@Override
	protected int compareTiebreaker(HandRecognizer otherHand) {
		// otherHand represents same hand type
		return ((StraightFlushRecognizer) otherHand).getHighCard().ordinal() - this.getHighCard().ordinal();
	}
}
