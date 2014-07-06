package mjoyce.poker.tests;

import static org.junit.Assert.*;

import java.util.List;

import mjoyce.poker.Card;
import mjoyce.poker.CardFactory;
import mjoyce.poker.HandRecognizer;
import mjoyce.poker.RecognizerList;
import mjoyce.poker.HandRecognizer.HandType;

import org.junit.Before;
import org.junit.Test;

public class TestRecognizerList {	
	@Test
	public void testPair() {
		List<Card> hand0 = CardFactory.deserializeHand("KH AD 4S 4H 7C");
		HandRecognizer match = RecognizerList.matchHand(hand0);
		assertEquals(HandType.PAIR, match.getHandType());
		
		List<Card> hand1 = CardFactory.deserializeHand("KH AD 4S 8H 7C");
		match = RecognizerList.matchHand(hand1);
		assertEquals(HandType.HIGH_CARD, match.getHandType());
	}
	
	@Test
	public void testTwoPair() {
		List<Card> hand0 = CardFactory.deserializeHand("7S 6H 7H 4D 6C");
		HandRecognizer match = RecognizerList.matchHand(hand0);
		assertEquals(HandType.TWO_PAIR, match.getHandType());
		
		List<Card> hand1 = CardFactory.deserializeHand("7S KH JH 4D 6C");
		match = RecognizerList.matchHand(hand1);
		assertEquals(HandType.HIGH_CARD, match.getHandType());
	}
	
	@Test
	public void testThreeOfAKind() {
		List<Card> hand0 = CardFactory.deserializeHand("10H 6C 7S 10C 10D");
		HandRecognizer match = RecognizerList.matchHand(hand0);
		assertEquals(HandType.THREE_OF_A_KIND, match.getHandType());
		
		List<Card> hand1 = CardFactory.deserializeHand("2H 6C 7S 10C 9D");
		match = RecognizerList.matchHand(hand1);
		assertEquals(HandType.HIGH_CARD, match.getHandType());
	}
	
	@Test
	public void testStraight() {
		List<Card> hand0 = CardFactory.deserializeHand("KD JH AS QC 10H");
		HandRecognizer match = RecognizerList.matchHand(hand0);
		assertEquals(HandType.STRAIGHT, match.getHandType());
		
		List<Card> hand1 = CardFactory.deserializeHand("KD 3H AS QC 10H");
		match = RecognizerList.matchHand(hand1);
		assertEquals(HandType.HIGH_CARD, match.getHandType());
	}
	
	@Test
	public void testFlush() {
		List<Card> hand0 = CardFactory.deserializeHand("KH JH AH 4H 2H");
		HandRecognizer match = RecognizerList.matchHand(hand0);
		assertEquals(HandType.FLUSH, match.getHandType());
		
		List<Card> hand1 = CardFactory.deserializeHand("KH JH AD 4H 2H");
		match = RecognizerList.matchHand(hand1);
		assertEquals(HandType.HIGH_CARD, match.getHandType());
	}
	
	@Test
	public void testFullHouse() {
		List<Card> hand0 = CardFactory.deserializeHand("7S 6H 7H 7D 6C");
		HandRecognizer match = RecognizerList.matchHand(hand0);
		assertEquals(HandType.FULL_HOUSE, match.getHandType());
		
		List<Card> hand1 = CardFactory.deserializeHand("7S JH QH 4D 6C");
		match = RecognizerList.matchHand(hand1);
		assertEquals(HandType.HIGH_CARD, match.getHandType());
	}
	
	@Test
	public void testFourOfAKind() {
		List<Card> hand0 = CardFactory.deserializeHand("8H 6C 8S 8C 8D");
		HandRecognizer match = RecognizerList.matchHand(hand0);
		assertEquals(HandType.FOUR_OF_A_KIND, match.getHandType());
		
		List<Card> hand1 = CardFactory.deserializeHand("2H 6C JS QC 8D");
		match = RecognizerList.matchHand(hand1);
		assertEquals(HandType.HIGH_CARD, match.getHandType());
	}
	
	@Test
	public void testStraightFlush() {
		List<Card> hand0 = CardFactory.deserializeHand("10C AC QC JC KC");
		HandRecognizer match = RecognizerList.matchHand(hand0);
		assertEquals(HandType.STRAIGHT_FLUSH, match.getHandType());
		
		List<Card> hand1 = CardFactory.deserializeHand("10C AC 4D JC KC");
		match = RecognizerList.matchHand(hand1);
		assertEquals(HandType.HIGH_CARD, match.getHandType());
	}

}
