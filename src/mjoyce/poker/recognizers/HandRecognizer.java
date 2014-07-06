package mjoyce.poker.recognizers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mjoyce.poker.model.Card;

public abstract class HandRecognizer implements Comparable<HandRecognizer> {
	
	public enum HandType {
		HIGH_CARD("High Card"),
		PAIR("Pair"),
		TWO_PAIR("Two Pair"),
		THREE_OF_A_KIND("Three of a Kind"),
		STRAIGHT("Straight"),
		FLUSH("Flush"),
		FULL_HOUSE("Full House"),
		FOUR_OF_A_KIND("Four of a Kind"),
		STRAIGHT_FLUSH("Straight Flush");
		
		private String mDescription;
		private HandType(String description) {
			mDescription = description;
		}
		
		@Override
		public String toString() {
			return mDescription;
		}
	};
	
	private List<Card> mKickers;
	protected boolean mIsMatch;
	
	public HandRecognizer() {
		mKickers = new ArrayList<Card>();
	}

	public boolean isMatch() {
		return mIsMatch;
	}
	
	public List<Card> getKickers() {
		return mKickers;
	}
	
	public final void recognizeHand(List<Card> hand) {
		mIsMatch = false;
		mKickers.clear();
		Collections.sort(hand);
		List<Card> kickers = doRecognize(hand);
		mKickers.addAll(kickers);
	}
	
	public final int compareTo(HandRecognizer otherHand) {
		if (this.getHandType() == otherHand.getHandType()) {
			return compareTiebreaker(otherHand);
		}
		return otherHand.getHandType().ordinal() - this.getHandType().ordinal();
	}

	// subclasses can override to provide hand-specific tiebreaking, then call super to compare kickers
	protected int compareTiebreaker(HandRecognizer otherHand) {
		// kickers should be sorted at this point, and both arrays should be the same length
		for (int i = 0; i < this.getKickers().size(); i++) {
			Card card1 = this.getKickers().get(i);
			Card card2 = otherHand.getKickers().get(i);
			int comparisonResult = card1.compareTo(card2);
			if (comparisonResult != 0) {
				return comparisonResult;
			}
		}
		
		// if we get to here, the hands are considered tied
		return 0;
	}
	
	protected abstract List<Card> doRecognize(List<Card> hand);
	public abstract HandType getHandType();
}
