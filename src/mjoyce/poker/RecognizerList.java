package mjoyce.poker;

import java.util.List;

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
	
	/**
	 * Determines the poker ranking of a specified hand (list of Cards).
	 * @param hand A list of Cards representing the hand to be matched.
	 * @return A HandRecognizer object determining the poker ranking of the hand.
	 */
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
	
	// Builds a list of HandRecognizers in their correct ordering.
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
