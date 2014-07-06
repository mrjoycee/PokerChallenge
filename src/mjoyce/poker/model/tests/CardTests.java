package mjoyce.poker.model.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import mjoyce.poker.model.Card;
import mjoyce.poker.model.Card.Rank;
import mjoyce.poker.model.Card.Suit;
import mjoyce.poker.model.CardFactory;

import org.junit.Test;

public class CardTests {

	private static final Card CARD_ACE_OF_SPADES = new Card(Suit.SPADES, Rank.ACE);
	private static final Card CARD_JACK_OF_HEARTS = new Card(Suit.HEARTS, Rank.JACK);
	private static final Card CARD_TEN_OF_DIAMONDS = new Card(Suit.DIAMONDS, Rank.TEN);
	private static final Card CARD_TWO_OF_CLUBS = new Card(Suit.CLUBS, Rank.TWO);
	private static final Card CARD_SIX_OF_HEARTS = new Card(Suit.HEARTS, Rank.SIX);

	@Test
	public void testDeserializeCard() {
		Card card0 = CardFactory.deserializeCard("6H");
		assertEquals(CARD_SIX_OF_HEARTS, card0);
		
		Card card1 = CardFactory.deserializeCard("2C");
		assertEquals(CARD_TWO_OF_CLUBS, card1);
		
		Card card2 = CardFactory.deserializeCard("10D");
		assertEquals(CARD_TEN_OF_DIAMONDS, card2);
		
		Card card3 = CardFactory.deserializeCard("JH");
		assertEquals(CARD_JACK_OF_HEARTS, card3);
		
		Card card4 = CardFactory.deserializeCard("AS");
		assertEquals(CARD_ACE_OF_SPADES, card4);
		
		Card card5 = CardFactory.deserializeCard("SIXOFSPADES");
		assertNull(card5);
		
		Card card6 = CardFactory.deserializeCard("1H");
		assertNull(card6);
		
		Card card7 = CardFactory.deserializeCard("GD");
		assertNull(card7);
		
		Card card8 = CardFactory.deserializeCard("5Y");
		assertNull(card8);
	}
	
	@Test
	public void testDeserializeHand() {
		List<Card> hand = CardFactory.deserializeHand("AS JH 10D 2C 6H");
		Card [] testCards = {CARD_ACE_OF_SPADES, CARD_JACK_OF_HEARTS, CARD_TEN_OF_DIAMONDS, CARD_TWO_OF_CLUBS, CARD_SIX_OF_HEARTS};
		int count = 0;
		for (Card card : hand) {
			assertEquals(testCards[count], card);
			count++;
		}
	}

}
