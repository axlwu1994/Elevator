
public class Minstrels extends Thread{

	boolean enteredCastle;
	
	EventBarrier myBarrier;
	
	Minstrels(){
		enteredCastle = false;
	}
	
	public void run(){
		myBarrier.arrive();
		this.getToCastle();
		myBarrier.complete();
		
	}
	
	private void getToCastle(){
		enteredCastle = true;
	}
	
}
