package mjoyce.poker.recognizers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mjoyce.poker.Card;

/**
 * Base class for all HandRecognizers. Calling recognizeHand will perform the match and populate all state
 * within the object.
 * @author mjoyce
 *
 */
public abstract class HandRecognizer implements Comparable<HandRecognizer> {
	
	/**
	 * Enum used in specifying the poker ranking of a hand. Call getHandType after recognizeHand to determine the ranking of
	 * this hand.
	 */
	public enum HandType {
		HIGH_CARD("High Card"),
		PAIR("Pair"),
		TWO_PAIR("Two Pair"),
		THREE_OF_A_KIND("Three of a Kind"),
		STRAIGHT("Straight"),
		FLUSH("Flush"),
		FULL_HOUSE("Full House"),
		FOUR_OF_A_KIND("Four of a Kind"),
		STRAIGHT_FLUSH("Straight Flush"),
		ROYAL_FLUSH("Royal Flush");
		
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
	
	/**
	 * @return The HandType that this recognizer matches.
	 */
	public abstract HandType getHandType();

	/**
	 * Returns true if the last execution of recognizeHand produced a match. Value is undefined if recognizeHand has not
	 * been called.
	 * @return True if the last execution of recognizeHand produced a match.
	 */
	public boolean isMatch() {
		return mIsMatch;
	}
	
	/**
	 * Returns a list of Kicker cards that are part of the hand but did not participate in the matching.
	 * (e.g if the hand [AS, AC, AD, 6C, 9S] is sent to recognizeHand, the kickers will contain [9S, 6C].) Value is undefined 
	 * if recognizeHand has not been called.
	 * @return List of Kicker cards that are part of the hand but did not participate in the matching.
	 */
	public List<Card> getKickers() {
		return mKickers;
	}
	
	/**
	 * Call to invoke the recognizer on the given hand. State values on this object are only valid after recognizeHand has
	 * been called.
	 * @param hand The hand to recognize.
	 */
	public final void recognizeHand(List<Card> hand) {
		mIsMatch = false;
		mKickers.clear();
		Collections.sort(hand);
		List<Card> kickers = doRecognize(hand);
		mKickers.addAll(kickers);
	}
	
	/**
	 * Internal method for recognizing hands. Subclasses must implement to provide matching logic for their specific hand types.
	 * @param hand The poker hand to recognize.
	 * @return A list of Cards that did not participate in the recognizing (kickers). Or the hand itself if there was
	 * no match.
	 */
	protected abstract List<Card> doRecognize(List<Card> hand);

	/**
	 * Standard compareTo method compares the given HandRecognizer to this HandRecognizer to determine which represents the
	 * higher-ranking poker hand.
	 * @param otherHand The HandRecognizer representing the hand to compare to this hand.
	 * @return Negative if otherHand is less than this Hand, positive if otherHand is greater than this Hand, and
	 * 	0 if they are equivalent.
	 */
	@Override
	public final int compareTo(HandRecognizer otherHand) {
		if (this.getHandType() == otherHand.getHandType()) {
			return compareTiebreaker(otherHand);
		}
		return otherHand.getHandType().ordinal() - this.getHandType().ordinal();
	}

	/**
	 * Internal tiebreaking method used when comparing poker hands. Subclasses should override to provide hand-specific
	 * tiebreaking, then call super to compare the kickers (if any).
	 * @param otherHand The HandRecognizer representing the hand to compare to this hand.
	 * @return Negative if otherHand is less than this Hand, positive if otherHand is greater than this Hand, and
	 * 	0 if they are equivalent.
	 */
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
}
