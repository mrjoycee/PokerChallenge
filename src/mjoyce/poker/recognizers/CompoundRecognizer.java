package mjoyce.poker.recognizers;

import java.util.List;

import mjoyce.poker.model.Card;

public abstract class CompoundRecognizer extends HandRecognizer {
	private boolean mTestKickers;
	protected HandRecognizer [] mRecognizers;
	
	// If testKickers == true, only the unmatched cards from the first recognizer are passed to the next, 
	// and so on. If testKickers == false, the entire hand is tested against all recognizers.
	public CompoundRecognizer(boolean testKickers, HandRecognizer... recognizers) {
		mTestKickers = testKickers;
		mRecognizers = recognizers;
	}
	
	@Override
	protected List<Card> doRecognize(List<Card> hand) {
		List<Card> kickers = hand;
		mIsMatch = true;
		for (HandRecognizer recognizer : mRecognizers) {
			recognizer.recognizeHand(mTestKickers ? kickers : hand);
			mIsMatch &= recognizer.isMatch();
			kickers = recognizer.getKickers();
		}
		return mIsMatch ? kickers : hand;
	}
}
