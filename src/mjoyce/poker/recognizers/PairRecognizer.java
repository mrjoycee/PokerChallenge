package mjoyce.poker.recognizers;

/**
 * HandRecognizer subclass that recognizes a Pair.
 * @author mjoyce
 */
public class PairRecognizer extends SetRecognizer {
	public PairRecognizer() {
		super(2);
	}
	
	@Override
	public HandType getHandType() {
		return HandType.PAIR;
	}
}