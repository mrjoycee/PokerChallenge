package mjoyce.poker.model;

import java.util.ArrayList;
import java.util.List;

import mjoyce.poker.model.Card.Rank;
import mjoyce.poker.model.Card.Suit;

public class CardFactory {
	public static List<Card> deserializeHand(String handString) {
		String [] cardStrs = handString.split(" ");
		List<Card> result = new ArrayList<Card>(cardStrs.length);
		for (String cardStr : cardStrs) {
			Card card = deserializeCard(cardStr);
			if (card != null) {
				result.add(card);
			}
		}
		return result;
	}
	
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
