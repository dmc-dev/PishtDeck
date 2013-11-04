
public class PishtGame {

	PishtDeck cards = null;	
	PishtDeck floor = null;
	
	PishtPlayer a = null;
	PishtPlayer b = null;
	
	public PishtGame(){	
		cards = new PishtDeck(true);		
		floor = new PishtDeck(false);
		
		a = new PishtPlayer(floor);
		a.name="A";
		b = new PishtPlayer(floor);
		b.name="B";
	}

	public void initGame(){	
		cards.Shuffel();
		initFloor();
		initPlayers();
	}

	public void initFloor(){
		floor.clearAndNull();
		for(int i=0; i<3; i++){
			Card c = cards.dealTop();
			c.setFaceUp(false);
			floor.add(c);
		}
		floor.add(cards.dealTop());
	}
	
	public void initPlayers(){
		a.initPlayer(cards);
		b.initPlayer(cards);
	}
	
	
	public void playGame(){		
		initGame();
		while(cards.hasCards()){
			a.newHand(cards);
			b.newHand(cards);
			playHand();
		}
	}
	
	public void playHand(){		
		for(int i=0; i<4; i++){						
			while(simplePlay(a,b)) 
				;			
			while(simplePlay(b,a)) 
				;			
		}		
	}
	
	public boolean simplePlay(PishtPlayer p, PishtPlayer opp){
		
		if(floor.isPishtToCall()){
			if(p.callAPisht()){				
				if(floor.isPisht()){
					addPointsTakeFloor(opp, 20);
					System.out.println(p.name+" Calls pishty finds True "+opp.name+" wins");
					
				}else{
					addPointsTakeFloor(p, 20);
					System.out.println(p.name+" Calls pishty finds Bluff "+p.name+" wins");
				}
			}else{
				addPointsTakeFloor(opp, 10);
				System.out.println(p.name+" conseads "+opp.name+" wins pishty");
			}
						
			//System.out.println(this+"\n");			
			return true;
		}		
		p.playRand();											
		//System.out.println(this+"\n");		
		return false;
	}
	
	private void addPointsTakeFloor(PishtPlayer p, int points){
		p.pishtPoints+=points;
		p.takesFloor();
	}
	
	public String toString(){
		return 	"cards = "+cards+"\n"+
				"floor = "+floor+" points="+floor.getPoints()+"\n"+
				a+"\n"+
				b;
	}
}