import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
	public void readFile(File input) {
		List<Rider> riderList = new ArrayList<Rider>();
		
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		
		while (in.hasNext()) {
			String line = in.nextLine();
			String[] floors = line.split(" ");
			int start = Integer.parseInt(floors[0]);
			int destination = Integer.parseInt(floors[1]);
			
			//DONT ACTUALLY ADD THIS
			Building building = new Building(1, 1);
			
			Rider rider = new Rider(building, start, destination);
			riderList.add(rider);
		}
		
	}
}
