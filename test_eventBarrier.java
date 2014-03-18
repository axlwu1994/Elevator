
public class test_eventBarrier {

	
	public static void main(String[] args){
		Minstrels m1 = new Minstrels();
		Minstrels m2 = new Minstrels();
		Minstrels m3 = new Minstrels();
		Minstrels m4 = new Minstrels();
		Minstrels m5 = new Minstrels();
		
		GateKeeper myGK = new GateKeeper();
		
		m1.run();
		m2.run();
		m3.run();
		
		myGK.myBarrier.raise();
		
		m4.run();
		
		
		
		
	}
	
	
}
