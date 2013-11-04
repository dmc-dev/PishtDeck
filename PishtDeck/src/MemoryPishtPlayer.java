

public class MemoryPishtPlayer extends PishtPlayer {
	
	PishtMemory memory = new PishtMemory();
	
	public MemoryPishtPlayer(PishtDeck floor){
		super(floor);
		
	}
	
	public void newHand(PishtDeck cards){
		newHand(cards);
		memory.add(hand);
	}
	
	public void play(){
		
		if(floor.hasCards()){
		
			memory.add(floor.showTop());
			
			if(floor.isPishtable()){
			
				Card played = getMatchingCard();
				
				if(played!=null){
					played.setFaceUp(false);			
				}else{
					played = hand.dealRandomCard();
					if(random.nextDouble() < 0.33)
						played.setFaceUp(false);
				}
				playCard(played);
				
			}
						
		}
		
	}
	
	public boolean callAPisht(){
		Card c = floor.show2ndFromTop();		
		return memory.probOfCardValue(c) < 0.2;		
	}
}
