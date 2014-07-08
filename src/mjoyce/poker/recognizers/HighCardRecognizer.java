package mjoyce.poker.recognizers;

import java.util.List;

import mjoyce.poker.Card;

/**
 * HandRecognizer subclass that matches all hands that are not other poker hand types. This is used to stub out
 * all the matching logic and invoke the tiebreaking mechanisms in the parent class.
 * @author mjoyce
 */
public class HighCardRecognizer extends HandRecognizer {

	@Override
	protected List<Card> doRecognize(List<Card> hand) {
		mIsMatch = true;
		return hand;
	}
	
	@Override
	public HandType getHandType() {
		return HandType.HIGH_CARD;
	}
}
