package mjoyce.poker.tests;

import static org.junit.Assert.*;

import java.util.List;

import mjoyce.poker.RecognizerList;
import mjoyce.poker.model.Card;
import mjoyce.poker.model.CardFactory;
import mjoyce.poker.recognizers.HandRecognizer;

import org.junit.Test;

public class TestComparison {
	@Test
	public void testEverything() {		
		List [] hands = {
				CardFactory.deserializeHand("10C AC QC JC KC"),
				CardFactory.deserializeHand("8H 6C 8S 8C 8D"),
				CardFactory.deserializeHand("7S 6H 7H 7D 6C"),
				CardFactory.deserializeHand("KH JH AH 4H 2H"),
				CardFactory.deserializeHand("KD JH AS QC 10H"),
				CardFactory.deserializeHand("10H 6C 7S 10C 10D"),
				CardFactory.deserializeHand("7S 6H 7H 4D 6C"),
				CardFactory.deserializeHand("KH AD 4S 4H 7C"),
				CardFactory.deserializeHand("KH AD 4S 8H 7C")
		};
		
		int [][] comparisonMatrix = {
				{0, 1, 1, 1, 1, 1, 1, 1, 1},
				{-1, 0, 1, 1, 1, 1, 1, 1, 1},
				{-1, -1, 0, 1, 1, 1, 1, 1, 1},
				{-1, -1, -1, 0, 1, 1, 1, 1, 1},
				{-1, -1, -1, -1, 0, 1, 1, 1, 1},
				{-1, -1, -1, -1, -1, 0, 1, 1, 1},
				{-1, -1, -1, -1, -1, -1, 0, 1, 1},
				{-1, -1, -1, -1, -1, -1, -1, 0, 1},
				{-1, -1, -1, -1, -1, -1, -1, -1, 0}
		};
		
		for (int i = 0; i < hands.length; i++) {
			HandRecognizer recognizerI = RecognizerList.matchHand(hands[i]);
			for (int j = 0; j < hands.length; j++) {
				HandRecognizer recognizerJ = RecognizerList.matchHand(hands[j]);
				int comparisonResult = recognizerJ.compareTo(recognizerI);
				int compareValue = 0;
				if (comparisonResult < 0) {
					compareValue = -1;
				} else if (comparisonResult > 0) {
					compareValue = 1;
				}
				
				assertEquals(compareValue, comparisonMatrix[i][j]);
			}
		}
	}
	
	@Test
	public void testKickerTiebreaking() {
		List<Card> hand0 = CardFactory.deserializeHand("7S 4C 10D AH JC"); // Ace high
		List<Card> hand1 = CardFactory.deserializeHand("4C 2D QH 9S 8D"); // Queen high
		
		HandRecognizer recognizer0 = RecognizerList.matchHand(hand0);
		HandRecognizer recognizer1 = RecognizerList.matchHand(hand1);
		
		int compareValue = recognizer0.compareTo(recognizer1);
		assertTrue(compareValue < 0);
		
		compareValue = recognizer1.compareTo(recognizer0);
		assertTrue(compareValue > 0);
		
		compareValue = recognizer0.compareTo(recognizer0);
		assertTrue(compareValue == 0);
	}
	
	@Test
	public void testPairTiebreaking() {
		List<Card> hand0 = CardFactory.deserializeHand("6S 7H 10C 6D JS"); // Pair of sixes
		List<Card> hand1 = CardFactory.deserializeHand("7S 7C 9C 2H AS"); // Pair of sevens, Ace kicker
		List<Card> hand2 = CardFactory.deserializeHand("JH QS 7D 3C 7H"); // Pair of sevens, Queen kicker
		
		HandRecognizer recognizer0 = RecognizerList.matchHand(hand0);
		HandRecognizer recognizer1 = RecognizerList.matchHand(hand1);
		HandRecognizer recognizer2 = RecognizerList.matchHand(hand2);
		
		int compareValue = recognizer0.compareTo(recognizer1);
		assertTrue(compareValue > 0);
		
		compareValue = recognizer1.compareTo(recognizer2);
		assertTrue(compareValue < 0);
	}
	
	@Test
	public void testTwoPairTiebreaking() {
		List<Card> hand0 = CardFactory.deserializeHand("8S 7H 7D 8C KD"); // 8's and 7's, King kicker
		List<Card> hand1 = CardFactory.deserializeHand("10S 3C AH 3D 10C"); // 10's and 3's, Ace kicker
		List<Card> hand2 = CardFactory.deserializeHand("8D 4D 8H 4S 10H"); // 8's and 4's
		List<Card> hand3 = CardFactory.deserializeHand("8H 7S 7C 8D 9S"); // 8's and 7's, 9 kicker

		HandRecognizer recognizer0 = RecognizerList.matchHand(hand0);
		HandRecognizer recognizer1 = RecognizerList.matchHand(hand1);
		HandRecognizer recognizer2 = RecognizerList.matchHand(hand2);
		HandRecognizer recognizer3 = RecognizerList.matchHand(hand3);

		int compareValue = recognizer0.compareTo(recognizer1);
		assertTrue(compareValue > 0);
		
		compareValue = recognizer0.compareTo(recognizer2);
		assertTrue(compareValue < 0);
		
		compareValue = recognizer0.compareTo(recognizer3);
		assertTrue(compareValue < 0);
	}

	@Test
	public void testTripsTiebreaking() {
		List<Card> hand0 = CardFactory.deserializeHand("6S 7H 6C 6D JS"); // Three sixes
		List<Card> hand1 = CardFactory.deserializeHand("7S 7C 9C 7H AS"); // Three sevens
		
		HandRecognizer recognizer0 = RecognizerList.matchHand(hand0);
		HandRecognizer recognizer1 = RecognizerList.matchHand(hand1);
		
		int compareValue = recognizer0.compareTo(recognizer1);
		assertTrue(compareValue > 0);
	}
	
	@Test
	public void testStraightTiebreaking() {
		List<Card> hand0 = CardFactory.deserializeHand("6S 5H 4C 7D 8S"); // Eight high
		List<Card> hand1 = CardFactory.deserializeHand("7S 8C 9C 6H 5S"); // Nine high
		
		HandRecognizer recognizer0 = RecognizerList.matchHand(hand0);
		HandRecognizer recognizer1 = RecognizerList.matchHand(hand1);
		
		int compareValue = recognizer0.compareTo(recognizer1);
		assertTrue(compareValue > 0);
	}
	
	@Test
	public void testFlushTiebreaking() {
		List<Card> hand0 = CardFactory.deserializeHand("6H 5H 10H JH 8H"); // Hearts, Jack high
		List<Card> hand1 = CardFactory.deserializeHand("2C 8C 9C 6C 4C"); // Clubs, Nine high
		List<Card> hand2 = CardFactory.deserializeHand("6S 5S 10S JS 8S"); // Spades, same as hand0
		List<Card> hand3 = CardFactory.deserializeHand("4D 8D 9D 6D 3D"); // Diamonds, low card better than hand1
		
		HandRecognizer recognizer0 = RecognizerList.matchHand(hand0);
		HandRecognizer recognizer1 = RecognizerList.matchHand(hand1);
		HandRecognizer recognizer2 = RecognizerList.matchHand(hand2);
		HandRecognizer recognizer3 = RecognizerList.matchHand(hand3);
		
		int compareValue = recognizer0.compareTo(recognizer1);
		assertTrue(compareValue < 0);
		
		compareValue = recognizer0.compareTo(recognizer2);
		assertTrue(compareValue == 0);
		
		compareValue = recognizer1.compareTo(recognizer3);
		assertTrue(compareValue > 0);
	}
	
	@Test
	public void testFullHouseTiebreaking() {
		List<Card> hand0 = CardFactory.deserializeHand("8S 7H 7D 8C 8D"); // 8's over 7's
		List<Card> hand1 = CardFactory.deserializeHand("10S 3C 3H 3D 10C"); // 3's over 10's
		List<Card> hand2 = CardFactory.deserializeHand("8S 9H 9D 8C 8D"); // 8's over 9's

		HandRecognizer recognizer0 = RecognizerList.matchHand(hand0);
		HandRecognizer recognizer1 = RecognizerList.matchHand(hand1);
		HandRecognizer recognizer2 = RecognizerList.matchHand(hand2);

		int compareValue = recognizer0.compareTo(recognizer1);
		assertTrue(compareValue < 0);
		
		compareValue = recognizer0.compareTo(recognizer2);
		assertTrue(compareValue > 0);
	}
	
	@Test
	public void testFoursTiebreaking() {
		List<Card> hand0 = CardFactory.deserializeHand("6S 6H 6C 6D JS"); // Four sixes
		List<Card> hand1 = CardFactory.deserializeHand("7S 7C 7D 7H AS"); // Four sevens
		
		HandRecognizer recognizer0 = RecognizerList.matchHand(hand0);
		HandRecognizer recognizer1 = RecognizerList.matchHand(hand1);
		
		int compareValue = recognizer0.compareTo(recognizer1);
		assertTrue(compareValue > 0);
	}
	
	@Test
	public void testStraightFlushTiebreaking() {
		List<Card> hand0 = CardFactory.deserializeHand("6S 5S 4S 7S 8S"); // Eight high
		List<Card> hand1 = CardFactory.deserializeHand("7C 8C 9C 6C 5C"); // Nine high
		
		HandRecognizer recognizer0 = RecognizerList.matchHand(hand0);
		HandRecognizer recognizer1 = RecognizerList.matchHand(hand1);
		
		int compareValue = recognizer0.compareTo(recognizer1);
		assertTrue(compareValue > 0);
	}
	
}
