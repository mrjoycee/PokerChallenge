package mjoyce.poker;


public class FourOfAKindRecognizer extends SetRecognizer {
	public FourOfAKindRecognizer() {
		super(4);
	}
	
	@Override
	public HandType getHandType() {
		return HandType.FOUR_OF_A_KIND;
	}
}
