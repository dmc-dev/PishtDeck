import java.util.Vector;




public class PishtMemory {

	private Vector<CardCount> memory = new Vector<CardCount>();
	
	class CardCount{
		public Card card=null;
		public int count=0;		
		CardCount(Card card, int count){this.card=card; this.count=count;}
				
		public boolean equals(Object obj) {			
			Card c = (Card)obj;
			return card.isSameValue(c);
		}
	}
	
	int memorySZ(){
		int total=0;
		for(CardCount c :memory)
			total+=c.count;		
		return total;
	}
	
	void add(Deck d){		
		DeckItterator i = d.getItterator();		
		while(i.hasMore()){
			add(i.getNext());			
		}		
	}
	
	void add(Card c){								
		int idx = memory.indexOf(c);
			
		if(idx!=-1){
			CardCount counter = memory.get(idx);
			counter.count++;
		}else{
			memory.add(new CardCount(c, 1));
		}					
	}
		
	int count(Card c){
		int idx = memory.indexOf(c);
		if(idx!=-1)
			return memory.get(idx).count;		
		return 0;
	}
	
	int countRemaining(Card c){		
		return 4 - count(c);
	}
	
	int totalRemaining(){
		return Deck.BUFSZ - this.memorySZ();
	}
	
	double probOfCardValue(Card c){		
		return countRemaining(c) / (double)totalRemaining();		
	}
	
	Card dealMostImproable(Deck d){
		DeckItterator i = d.getItterator();
		Card res = null; 
		while(i.hasMore()){
			Card c = i.getNext();			
			int count = countRemaining(c);
		}
		return d.dealCardValue(res.getValue());
	}
	
}
