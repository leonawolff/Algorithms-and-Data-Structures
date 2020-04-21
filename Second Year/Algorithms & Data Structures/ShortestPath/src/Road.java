
public class Road {
	
	private int intA;					// intersection A = start of road
	private int intB;					// intersection B = end of road
	private double length;
	
	public Road(int intA, int intB, double length) {
		
		this.intA = intA;
		this.intB = intB;
		this.length = length;
			
	}

    public int getIntA() {
        return intA;
    }
    
    public int getIntB() {
        return intB;
    }    
    
    public double getLength() {
        return length;
    }
}