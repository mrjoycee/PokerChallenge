package mjoyce.poker;

/**
 * Basic immutable model object which represents a card. A card is comprised of a rank and a suit.
 * @author mjoyce
 *
 */
public class Card implements Comparable<Card> {
	
	/**
	 * Suit enum used in specifying the suit of a given card.
	 */
	public enum Suit {
		CLUBS("Clubs"),
		DIAMONDS("Diamonds"),
		HEARTS("Hearts"),
		SPADES("Spades");
		
		private String mDescription;
		private Suit(String description) {
			mDescription = description;
		}
		
		@Override
		public String toString() {
			return mDescription;
		}
	}
	
	/**
	 * Rank enum used in specifying the rank of a given card. Ordering in this enum is important,
	 * as ordinal values are used to compare cards.
	 */
	public enum Rank {
		TWO("Two"),
		THREE("Three"),
		FOUR("Four"),
		FIVE("Five"),
		SIX("Six"),
		SEVEN("Seven"),
		EIGHT("Eight"),
		NINE("Nine"),
		TEN("Ten"),
		JACK("Jack"),
		QUEEN("Queen"),
		KING("King"),
		ACE("Ace");
		
		private String mDescription;
		private Rank(String description) {
			mDescription = description;
		}
		
		public String toString() {
			return mDescription;
		}
	}
	
	private Suit mSuit;
	private Rank mRank;
	
	public Card(Suit suit, Rank rank) {
		mSuit = suit;
		mRank = rank;
	}
	
	public Suit getSuit() {
		return mSuit;
	}
	
	public Rank getRank() {
		return mRank;
	}
	
	/**
	 * Standard equals method determines if the given object is equal to this Card.
	 * @param obj The object to compare to this Card.
	 * @return True if obj is a Card instance and has the same rank and suit as this Card.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			Card card = (Card)obj;
			return this.mRank == card.mRank && this.mSuit == card.mSuit;
		}
		return false;
	}
	
	/**
	 * Standard compareTo method compares the given Card to this Card. Used for sorting of hands as well as for
	 * hand comparison. Note that only the rank is used for comparison.
	 * @param otherCard The Card to compare to this Card.
	 * @return Negative if otherCard is less than this Card, positive if otherCard is greater than this Card, and
	 * 	0 if they are equivalent.
	 */
	@Override
	public int compareTo(Card otherCard) {
		return otherCard.mRank.ordinal() - this.mRank.ordinal();
	}
	
	/**
	 * Returns a human-readable string representation of this Card.
	 * @return String representation of this Card.
	 */
	@Override
	public String toString() {
		return mRank.toString() + " of " + mSuit.toString();
	}
}
