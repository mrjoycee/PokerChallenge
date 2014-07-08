package mjoyce.poker.recognizers;

/**
 * HandRecognizer subclass that recognizes a Four of a Kind.
 * @author mjoyce
 */
public class FourOfAKindRecognizer extends SetRecognizer {
	public FourOfAKindRecognizer() {
		super(4);
	}
	
	@Override
	public HandType getHandType() {
		return HandType.FOUR_OF_A_KIND;
	}
}
