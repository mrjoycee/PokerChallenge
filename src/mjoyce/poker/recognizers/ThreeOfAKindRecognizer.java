package mjoyce.poker.recognizers;


public class ThreeOfAKindRecognizer extends SetRecognizer {
	public ThreeOfAKindRecognizer() {
		super(3);
	}
	
	@Override
	public HandType getHandType() {
		return HandType.THREE_OF_A_KIND;
	}
}
