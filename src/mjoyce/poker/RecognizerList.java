package mjoyce.poker;

import java.util.List;


public class RecognizerList {
	public static HandRecognizer matchHand(List<Card> hand) {
		for (HandRecognizer recognizer : buildRecognizerList()) {
			recognizer.recognizeHand(hand);
			if (recognizer.isMatch()) {
				return recognizer;
			}
		}
		
		// something went wrong if we end up down here
		return null;
	}
	
	private static HandRecognizer [] buildRecognizerList() {
		HandRecognizer [] recognizers = {
				new StraightFlushRecognizer(),
				new FourOfAKindRecognizer(),
				new FullHouseRecognizer(),
				new FlushRecognizer(),
				new StraightRecognizer(),
				new ThreeOfAKindRecognizer(),
				new TwoPairRecognizer(),
				new PairRecognizer(),
				new HighCardRecognizer()
		};
		return recognizers;
	}
}
