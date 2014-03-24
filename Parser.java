
import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Parser {
	
	int myNumFloors;
	int myNumElevators;
	
	
	public void readFile(File input) {
		List<Rider> riderList = new CopyOnWriteArrayList<Rider>();
		
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		
		//This gets the building numfloors and numelevators
		String firstLine = in.nextLine();
		String[] aboutBuilding = firstLine.split(" ");
		int numFloors = Integer.parseInt(aboutBuilding[0]);
		int numElevators = Integer.parseInt(aboutBuilding[1]);
		
		//This gets all the riders
//		while (in.hasNext()) {
//			String line = in.nextLine();
//			String[] floors = line.split(" ");
//			int start = Integer.parseInt(floors[0]);
//			int destination = Integer.parseInt(floors[1]);
//			
//			Building building = new Building(1, 1);
//			
//			Rider rider = new Rider(building, start, destination);
//			riderList.add(rider);
//		}
		
	}
}
