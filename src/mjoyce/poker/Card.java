package mjoyce.poker;

public class Card implements Comparable<Card> {
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			Card card = (Card)obj;
			return this.mRank == card.mRank && this.mSuit == card.mSuit;
		}
		return false;
	}
	
//	@Override
//	public String toString() {
//		StringBuilder result = new StringBuilder();
//		// the rank portion
//		if (mRank.ordinal() <= 8) {
//			result.append(mRank.ordinal() + 2);
//		} else {
//			switch (mRank) {
//				case JACK:
//					result.append("J");
//					break;
//				case QUEEN:
//					result.append("Q");
//					break;
//				case KING:
//					result.append("K");
//					break;
//				case ACE:
//					result.append("A");
//					break;
//			}
//		}
//		
//		// the suit portion
//		switch (mSuit) {
//			case CLUBS:
//				result.append("C");
//				break;
//			case DIAMONDS:
//				result.append("D");
//				break;
//			case HEARTS:
//				result.append("H");
//				break;
//			case SPADES:
//				result.append("S");
//				break;
//		}
//		
//		return result.toString();
//	}
	
	@Override
	public int compareTo(Card otherCard) {
		return otherCard.mRank.ordinal() - this.mRank.ordinal();
	}
	
	@Override
	public String toString() {
		return mRank.toString() + " of " + mSuit.toString();
	}
}
