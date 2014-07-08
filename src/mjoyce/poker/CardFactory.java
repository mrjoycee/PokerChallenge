package mjoyce.poker;

import java.util.ArrayList;
import java.util.List;

import mjoyce.poker.Card.Rank;
import mjoyce.poker.Card.Suit;

/**
 * Contains static convenience methods for parsing Cards using string representations.
 * @author mjoyce
 */
public class CardFactory {
	
	/**
	 * Parses a string representation of a hand of cards and returns a list of Card objects. Cards are specified by 2-3 character strings
	 * of the order <rank><suit>. Card ranks from two to ten are represented by standard arabic numerals (2 to 10). Face cards are as follows:
	 * 		J for Jack
	 * 		Q for Queen
	 * 		K for King
	 * 		A for Ace
	 * Suits are represented by one-character strings as follows:
	 * 		C for Clubs
	 * 		D for Diamonds
	 * 		H for Hearts
	 * 		S for Spades
	 * For example, the string "AS" represents the Ace of Spades, and "2H" represents the Two of Hearts.
	 * The hand string can be made up of any number of cards, separated by spaces.
	 * @param handString A string representation of a hand of cards, separated by spaces.
	 * @return A list of Card objects.
	 */
	public static List<Card> deserializeHand(String handString) {
		String [] cardStrs = handString.split(" ");
		List<Card> result = new ArrayList<Card>(cardStrs.length);
		for (String cardStr : cardStrs) {
			Card card = deserializeCard(cardStr);
			if (card != null) {
				result.add(card);
			} else {
				// if we get an invalid card, there's no point in continuing
				return null;
			}
		}
		return result;
	}
	
	/**
	 * Used by deserializeHand to parse individual card strings into Card objects
	 * @param cardString A 2-3 character string representing an individual card.
	 * @return A Card object.
	 */
	public static Card deserializeCard(String cardString) {
		// Card string must be of the format <rank><suit> e.g. KH (King of Hearts)
		// We recognize strings of length 2 and 3. Strings of length three are always the ten of some suit
		// e.g. 10D (Ten of Diamonds)
		String rankStr;
		char suitChar;
		if (cardString.length() == 2) {
			rankStr = cardString.substring(0, 1);
			suitChar = cardString.charAt(1);
		} else if (cardString.length() == 3) {
			rankStr = cardString.substring(0, 2);
			suitChar = cardString.charAt(2);
		} else {
			return null;
		}
		
		Suit suit;
		switch (suitChar) {
			case 'C':
				suit = Suit.CLUBS;
				break;
			case 'D':
				suit = Suit.DIAMONDS;
				break;
			case 'H':
				suit = Suit.HEARTS;
				break;
			case 'S':
				suit = Suit.SPADES;
				break;
			default:
				// invalid suit
				return null;
		}
		
		Rank rank;
		try {
			int rankInt = Integer.parseInt(rankStr);
			if (rankInt >= 2 && rankInt <= 10) {
				rank = Rank.values()[rankInt - 2];
			} else {
				// invalid rank
				return null;
			}
		} catch (NumberFormatException e) {
			switch (rankStr.charAt(0)) {
				case 'J':
					rank = Rank.JACK;
					break;
				case 'Q':
					rank = Rank.QUEEN;
					break;
				case 'K':
					rank = Rank.KING;
					break;
				case 'A':
					rank = Rank.ACE;
					break;
				default:
					// invalid rank
					return null;
			}
		}
		
		return new Card(suit, rank);
	}
}
