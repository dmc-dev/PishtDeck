
public class PishtDeck extends Deck{

	public static Card tenDimond = new Card(Card.DIMOND, Card.TEN);
	public static Card twoCulb = new Card(Card.CLUB, Card.TWO);
			
	public PishtDeck(boolean deepCopy) {
		super(deepCopy);
	}

	public int getPoints(){
		int points=0;
		
		if(contains(tenDimond))
			points+=3;
		
		if(contains(twoCulb))
			points+=2;
		
		points += countCard(Card.ACE);
		
		points += countCard(Card.JACK);
		
		if(this.size() > Deck.ONE_DECK_MAX_CARDS / 2)
			points+=3;
		
		return points;
	}
	
	public boolean isTakeable(){
		Card first = this.showTop();
		if(first.isSameValue(Card.JACK))
			return true;
		
		if(this.size()<2)
			return false;
		
		Card second = this.show2ndFromTop();		
		return first.isSameValue(second);
	}
	
	public boolean isPisht(){
		
		if(this.size()!=2)
			return false;
		
		Card first = this.showTop();
		Card second = this.show2ndFromTop();
		
		return first.isSameValue(second);
	}
	
	public boolean isPishtable(){
		return size()==1;
	}
	
	public boolean isPishtToCall(){		
		if(this.size()!=2)
			return false;
		Card first = this.showTop();
		return first.isFaceUp()==false;
	}
}
