package mjoyce.poker.recognizers.tests;

import static org.junit.Assert.*;

import java.util.List;

import mjoyce.poker.model.Card;
import mjoyce.poker.model.Card.Rank;
import mjoyce.poker.model.Card.Suit;
import mjoyce.poker.model.CardFactory;
import mjoyce.poker.recognizers.FlushRecognizer;
import mjoyce.poker.recognizers.FourOfAKindRecognizer;
import mjoyce.poker.recognizers.FullHouseRecognizer;
import mjoyce.poker.recognizers.PairRecognizer;
import mjoyce.poker.recognizers.StraightFlushRecognizer;
import mjoyce.poker.recognizers.StraightRecognizer;
import mjoyce.poker.recognizers.ThreeOfAKindRecognizer;
import mjoyce.poker.recognizers.TwoPairRecognizer;

import org.junit.Test;

public class TestRecognizers {

	@Test
	public void testPair() {
		PairRecognizer recognizer = new PairRecognizer();

		List<Card> hand0 = CardFactory.deserializeHand("KH AD 4S 4H 7C");
		recognizer.recognizeHand(hand0);
		assertTrue(recognizer.isMatch());
		assertEquals(Rank.FOUR, recognizer.getMatchedRank());
		Card [] kickers0 = {new Card(Suit.DIAMONDS, Rank.ACE), new Card(Suit.HEARTS, Rank.KING), new Card(Suit.CLUBS, Rank.SEVEN)};
		assertArrayEquals(kickers0, recognizer.getKickers().toArray());
		
		List<Card> hand1 = CardFactory.deserializeHand("KH AD 3S KC 4H");
		recognizer.recognizeHand(hand1);
		assertTrue(recognizer.isMatch());
		assertEquals(Rank.KING, recognizer.getMatchedRank());
		Card [] kickers1 = {new Card(Suit.DIAMONDS, Rank.ACE), new Card(Suit.HEARTS, Rank.FOUR), new Card(Suit.SPADES, Rank.THREE)};
		assertArrayEquals(kickers1, recognizer.getKickers().toArray());
	}
	
	@Test
	public void testTwoPair() {
		TwoPairRecognizer recognizer = new TwoPairRecognizer();
		
		List<Card> hand0 = CardFactory.deserializeHand("7S 6H 7H 4D 6C");
		recognizer.recognizeHand(hand0);
		assertTrue(recognizer.isMatch());
		assertEquals(Rank.SEVEN, recognizer.getTopPairRank());
		assertEquals(Rank.SIX, recognizer.getSecondPairRank());
		Card [] kickers0 = {new Card(Suit.DIAMONDS, Rank.FOUR)};
		assertArrayEquals(kickers0, recognizer.getKickers().toArray());
		
		List<Card> hand1 = CardFactory.deserializeHand("7S 6H 7H 4D AC");
		recognizer.recognizeHand(hand1);
		assertFalse(recognizer.isMatch());
		assertArrayEquals(hand1.toArray(), recognizer.getKickers().toArray());
	}
	
	@Test
	public void testThreeOfAKind() {
		ThreeOfAKindRecognizer recognizer = new ThreeOfAKindRecognizer();
		
		List<Card> hand0 = CardFactory.deserializeHand("10H 6C 7S 10C 10D");
		recognizer.recognizeHand(hand0);
		assertTrue(recognizer.isMatch());
		assertEquals(Rank.TEN, recognizer.getMatchedRank());
		Card [] kickers0 = {new Card(Suit.SPADES, Rank.SEVEN), new Card(Suit.CLUBS, Rank.SIX)};
		assertArrayEquals(kickers0, recognizer.getKickers().toArray());
		
		List<Card> hand1 = CardFactory.deserializeHand("10H KC AS 10C 10D");
		recognizer.recognizeHand(hand1);
		assertTrue(recognizer.isMatch());
		assertEquals(Rank.TEN, recognizer.getMatchedRank());
		Card [] kickers1 = {new Card(Suit.SPADES, Rank.ACE), new Card(Suit.CLUBS, Rank.KING)};
		assertArrayEquals(kickers1, recognizer.getKickers().toArray());
		
		List<Card> hand2 = CardFactory.deserializeHand("10H 6C 7S 10C 9D");
		recognizer.recognizeHand(hand2);
		assertFalse(recognizer.isMatch());
		assertArrayEquals(hand2.toArray(), recognizer.getKickers().toArray());
		
		List<Card> hand3 = CardFactory.deserializeHand("7S 6H 7H 4D 6C");
		recognizer.recognizeHand(hand3);
		assertFalse(recognizer.isMatch());
		assertArrayEquals(hand3.toArray(), recognizer.getKickers().toArray());
	}
	
	@Test
	public void testStraight() {
		StraightRecognizer recognizer = new StraightRecognizer();
		Card [] kickers = {};
		
		List<Card> hand0 = CardFactory.deserializeHand("KD JH AS QC 10H");
		recognizer.recognizeHand(hand0);
		assertTrue(recognizer.isMatch());
		assertEquals(Rank.ACE, recognizer.getHighCard());
		assertArrayEquals(kickers, recognizer.getKickers().toArray());
		
		List<Card> hand1 = CardFactory.deserializeHand("KD 6H AS QC 10H");
		recognizer.recognizeHand(hand1);
		assertFalse(recognizer.isMatch());
		assertArrayEquals(hand1.toArray(), recognizer.getKickers().toArray());
		
		List<Card> hand2 = CardFactory.deserializeHand("2D 5H 3S 4C 6H");
		recognizer.recognizeHand(hand2);
		assertTrue(recognizer.isMatch());
		assertEquals(Rank.SIX, recognizer.getHighCard());
		assertArrayEquals(kickers, recognizer.getKickers().toArray());
	}
	
	@Test
	public void testFlush() {
		FlushRecognizer recognizer = new FlushRecognizer();
		Card [] kickers = {};
		
		List<Card> hand0 = CardFactory.deserializeHand("KH JH AH 4H 2H");
		recognizer.recognizeHand(hand0);
		assertTrue(recognizer.isMatch());
		Rank [] sortedRanks = {Rank.ACE, Rank.KING, Rank.JACK, Rank.FOUR, Rank.TWO};
		assertArrayEquals(sortedRanks, recognizer.getSortedRanks());
		assertArrayEquals(kickers, recognizer.getKickers().toArray());
		
		List<Card> hand1 = CardFactory.deserializeHand("KD 6H AS QC 10H");
		recognizer.recognizeHand(hand1);
		assertFalse(recognizer.isMatch());
		assertArrayEquals(hand1.toArray(), recognizer.getKickers().toArray());
	}
	
	@Test
	public void testFullHouse() {
		FullHouseRecognizer recognizer = new FullHouseRecognizer();
		Card [] kickers = {};
		
		List<Card> hand0 = CardFactory.deserializeHand("7S 6H 7H 7D 6C");
		recognizer.recognizeHand(hand0);
		assertTrue(recognizer.isMatch());
		assertEquals(Rank.SEVEN, recognizer.getTripsRank());
		assertEquals(Rank.SIX, recognizer.getPairRank());
		assertArrayEquals(kickers, recognizer.getKickers().toArray());
		
		List<Card> hand1 = CardFactory.deserializeHand("7S 6H 7H 7D AC");
		recognizer.recognizeHand(hand1);
		assertFalse(recognizer.isMatch());
		assertArrayEquals(hand1.toArray(), recognizer.getKickers().toArray());
		
		List<Card> hand2 = CardFactory.deserializeHand("7S 6H 7H 6D AC");
		recognizer.recognizeHand(hand2);
		assertFalse(recognizer.isMatch());
		assertArrayEquals(hand2.toArray(), recognizer.getKickers().toArray());
	}
	
	@Test
	public void testFourOfAKind() {
		FourOfAKindRecognizer recognizer = new FourOfAKindRecognizer();
		
		List<Card> hand0 = CardFactory.deserializeHand("8H 6C 8S 8C 8D");
		recognizer.recognizeHand(hand0);
		assertTrue(recognizer.isMatch());
		assertEquals(Rank.EIGHT, recognizer.getMatchedRank());
		Card [] kickers0 = {new Card(Suit.CLUBS, Rank.SIX)};
		assertArrayEquals(kickers0, recognizer.getKickers().toArray());
		
		List<Card> hand1 = CardFactory.deserializeHand("8H 10C 8S 8C 8D");
		recognizer.recognizeHand(hand1);
		assertTrue(recognizer.isMatch());
		assertEquals(Rank.EIGHT, recognizer.getMatchedRank());
		Card [] kickers1 = {new Card(Suit.CLUBS, Rank.TEN)};
		assertArrayEquals(kickers1, recognizer.getKickers().toArray());
		
		List<Card> hand2 = CardFactory.deserializeHand("8H 6C 3S 8C 8D");
		recognizer.recognizeHand(hand2);
		assertFalse(recognizer.isMatch());
		assertArrayEquals(hand2.toArray(), recognizer.getKickers().toArray());
		
		List<Card> hand3 = CardFactory.deserializeHand("7S 6H 7H 7D 6C");
		recognizer.recognizeHand(hand3);
		assertFalse(recognizer.isMatch());
		assertArrayEquals(hand3.toArray(), recognizer.getKickers().toArray());
	}
	
	@Test
	public void testStraightFlush() {
		StraightFlushRecognizer recognizer = new StraightFlushRecognizer();
		Card [] kickers = {};
		
		List<Card> hand0 = CardFactory.deserializeHand("10C AC QC JC KC");
		recognizer.recognizeHand(hand0);
		assertTrue(recognizer.isMatch());
		assertEquals(Rank.ACE, recognizer.getHighCard());
		assertArrayEquals(kickers, recognizer.getKickers().toArray());
		
		List<Card> hand1 = CardFactory.deserializeHand("10C AC QD JC KC");
		recognizer.recognizeHand(hand1);
		assertFalse(recognizer.isMatch());
		assertArrayEquals(hand1.toArray(), recognizer.getKickers().toArray());
		
		List<Card> hand2 = CardFactory.deserializeHand("10C AC 3C JC KC");
		recognizer.recognizeHand(hand2);
		assertFalse(recognizer.isMatch());
		assertArrayEquals(hand2.toArray(), recognizer.getKickers().toArray());
	}

}
