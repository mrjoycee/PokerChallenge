package mjoyce.poker.recognizers;


public class PairRecognizer extends SetRecognizer {
	public PairRecognizer() {
		super(2);
	}
	
	@Override
	public HandType getHandType() {
		return HandType.PAIR;
	}
}