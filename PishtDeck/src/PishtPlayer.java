import java.util.Random;


public class PishtPlayer {
	
	public static Random random = new Random();
	
	public String name=null;
	public int pishtPoints =0 ;
	
	PishtDeck floor = null;
	
	PishtDeck hand = new PishtDeck(false);
	PishtDeck taken = new PishtDeck(false);
	
		
	public PishtPlayer(PishtDeck floor){
		this.floor = floor;
	}
	
	public void play(){
		playRand();
	}
	
	public void playRand(){		
		System.out.println(floor);
		System.out.print(name+ " hand="+hand + " playes ");
		
		Card played=null;
		
		if(floor.isPishtable()){						
			played = getMatchingCard();
			
			if(played!=null){
				played.setFaceUp(false);			
			}else{
				played = hand.dealRandomCard();
				if(random.nextDouble() < 0.33)
					played.setFaceUp(false);
			}
			playCard(played);
			
			if(played.isFaceUp())
				System.out.println("normal "+played);
			else
				System.out.println("pisht "+played);
			
			return;
		}
					
		if(floor.hasCards()){
			played = getMatchingCard();			
		}
		if(played==null){
			played = getRandomCard();			
		}
		
		System.out.println(played);
		
		playCard(played);
		
				
		if(floor.isTakeable()){
			takesFloor();
			System.out.print(name+" takes "+taken);
		}
	}
	
	public void playCard(Card c){floor.add(c);}
	
	public Card getRandomCard(){
		return hand.dealRandomCard();		
	}
	
	public Card getMatchingCard(){
		Card top = floor.showTop();			
		return  hand.dealCardValue(top.getValue());
	}
	
	public boolean playTruePisht(){
		Card played = getMatchingCard();
		if(played!=null){
			played.setFaceUp(false);
			playCard(played);
			return true;
		}
		return false;
	}
	
	public void playRandomBluffPisht(){
		Card c = hand.dealRandomCard();			
		if(random.nextDouble() < 0.33)
			c.setFaceUp(false);			
		playCard(c);
	}
	
	public void takesFloor(){
		taken.add(floor);
		floor.clearAndNull();
	}
	
	public boolean callAPisht(){		
		return random.nextBoolean();
	}
	
	public int getPoints(){
		return taken.getPoints();
	}
	
	public void initPlayer(PishtDeck cards){		
		taken.clearAndNull();		
		hand.clearAndNull();
		pishtPoints=0;
	}
	
	public void newHand(PishtDeck cards){
		hand.clearAndNull();
		for(int i=0; i<4; i++)
			hand.add(cards.dealTop());
	}
		
	
	public String toString(){
		return 	name+" hand  = "+hand+"\n"+
					"  taken = "+taken+" points="+getPoints()+"\n"+
					"  PistyPoints="+pishtPoints;
	}			
}