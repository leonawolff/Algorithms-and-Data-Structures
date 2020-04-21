import java.util.*;

public class Intersection {
	
	ArrayList<Road> outgoingRoads;

	public Intersection() {
		
		outgoingRoads = new ArrayList<Road>();		
			
	}
	
    public void addOutgoingRoads(Road road) {
        
    	outgoingRoads.add(road);
    }
    
    public Road getOutgoingRoads(int index) {
        
    	return (outgoingRoads.get(index));
    }
    
    public int numOutgoingRoads() {
        
    	return (outgoingRoads.size());
    }

}
