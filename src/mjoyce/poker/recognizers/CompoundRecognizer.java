package mjoyce.poker.recognizers;

import java.util.List;

import mjoyce.poker.Card;

/**
 * Recognizer subclass that uses an array of recognizers to determine a match. All sub-recognizers much match the
 * hand in order for the CompoundRecognizer to indicate a match.
 * @author mjoyce
 *
 */
public abstract class CompoundRecognizer extends HandRecognizer {
	private boolean mTestKickers;
	protected HandRecognizer [] mRecognizers;
	
	/**
	 * Construct a new CompoundRecognizer.
	 * @param testKickers If true, only the unmatched cards from the first recognizer are passed to the next, and so on.
	 * 		If false, the entire hand is tested against all recognizers.
	 * @param recognizers A list of sub-recognizers.
	 */
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
