package mjoyce.poker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import mjoyce.poker.recognizers.HandRecognizer;


public class PokerChallenge {
	public static void main(String[] args) {
		List<Card> hand1 = getHandInput(1);
		List<Card> hand2 = getHandInput(2);
		
		HandRecognizer recognizer1 = RecognizerList.matchHand(hand1);
		HandRecognizer recognizer2 = RecognizerList.matchHand(hand2);
		
		System.out.print("Hand 1 (" + hand1.toString() + ") ");
		System.out.println(recognizer1.getHandType().toString());
		System.out.println("vs.");
		System.out.print("Hand 2 (" + hand2.toString() + ") ");
		System.out.println(recognizer2.getHandType().toString());
		
		int comparison = recognizer1.compareTo(recognizer2);
		if (comparison < 0) {
			System.out.println("Hand 1 wins!");
		} else if (comparison > 0) {
			System.out.println("Hand 2 wins!");
		} else {
			System.out.println("Its a tie!");
		}
	}
	
	private static List<Card> getHandInput(int handNum) {
		List<Card> hand;
		
		while (true) {
			System.out.print("Enter hand " + handNum + " ('q' to quit): ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String handString;
			try {
				handString = br.readLine();
			} catch (IOException ioe) {
				return null;
			}

			if (handString.equalsIgnoreCase("q")) {
				System.exit(0);
			}

			hand = CardFactory.deserializeHand(handString.toUpperCase());
			
			if (hand == null) {
				System.out.println("Hand not valid. Please try again.");
			} else if (hand.size() != 5) {
				System.out.println("Hand must contain exactly 5 cards.");
			} else {
				return hand;
			}
		}
		
	}
}
