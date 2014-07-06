package mjoyce.poker.recognizers;

import java.util.List;

import mjoyce.poker.model.Card;

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
