
public class Minstrels extends Thread{

	boolean enteredCastle;
	
	EventBarrier myBarrier;
	
	Minstrels(EventBarrier barrier){
		enteredCastle = false;
		myBarrier = barrier;
	}
	
	public void run(){
		myBarrier.arrive();
		this.getToCastle();
		myBarrier.complete();
		
	}
	
	private void getToCastle(){
		enteredCastle = true;
	}
	
	public boolean getIntoCastle() {
		return enteredCastle;
	}
	
}
