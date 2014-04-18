import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


/**
 * Change the filename variable and run main if you would like to
 * run a input file (make sure that your input file is in the 
 * working directory). This will make an output file called 
 * "output.txt" which will list the riders id and current floor.
 * @author carlosreyes
 *
 */

public class Main {
	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.readFile(args[0]);
		
		for (Rider rider : parser.getMyRiderList()) {
			rider.start();
		}
		for (Elevator elevator : parser.getMyElevatorList()) {
			Thread elevatorThread = new Thread(elevator);
			elevatorThread.start();
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Parser.writer.close();
//		for (Rider r : parser.getMyRiderList()) {
//			writer.println(r.getMyID() + " " + r.getCurrentFloor());
//		}
		
	}
}
