
public class GateKeeper extends Thread {

	EventBarrier myBarrier = new EventBarrier();
	public GateKeeper(EventBarrier barrier){
		myBarrier = barrier;
	}
	public void run() {
		myBarrier.raise();
	}
}
