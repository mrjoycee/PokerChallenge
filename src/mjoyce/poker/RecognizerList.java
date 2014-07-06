package mjoyce.poker;

import java.util.List;

import mjoyce.poker.model.Card;
import mjoyce.poker.recognizers.FlushRecognizer;
import mjoyce.poker.recognizers.FourOfAKindRecognizer;
import mjoyce.poker.recognizers.FullHouseRecognizer;
import mjoyce.poker.recognizers.HandRecognizer;
import mjoyce.poker.recognizers.HighCardRecognizer;
import mjoyce.poker.recognizers.PairRecognizer;
import mjoyce.poker.recognizers.StraightFlushRecognizer;
import mjoyce.poker.recognizers.StraightRecognizer;
import mjoyce.poker.recognizers.ThreeOfAKindRecognizer;
import mjoyce.poker.recognizers.TwoPairRecognizer;

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
