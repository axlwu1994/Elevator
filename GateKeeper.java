
public class GateKeeper extends Thread {

	EventBarrier myBarrier;
	public GateKeeper(EventBarrier barrier){
		myBarrier = barrier;
	}
	public void run() {
		myBarrier.raise();
	}
}
