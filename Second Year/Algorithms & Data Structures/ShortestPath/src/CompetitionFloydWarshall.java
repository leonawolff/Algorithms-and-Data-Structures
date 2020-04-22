import java.io.*; 
import java.util.*;
import java.lang.*; 

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {

	private int sA, sB, sC;
	private ArrayList<Road> roads = new ArrayList<Road>();
	private int numInts;		// number of intersections 

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA, sB, sC: speeds for 3 contestants
	 */
	CompetitionFloydWarshall (String filename, int sA, int sB, int sC){

		int numRoads = -1;
		File cityFile;
		if(filename != null)
			cityFile = new File(filename);
		else
			cityFile = null;
		this.sA = sA;
		this.sB = sB;
		this.sC = sC;
		try {
			BufferedReader in = new BufferedReader(new FileReader(cityFile));
			String line = in.readLine();
			numInts = Integer.parseInt(line);
			//			this.city = new Intersection[numIntersections];
			//			for(int i = 0; i < city.length; i++) {
			//				city[i] = new Intersection();
			//			}
			line = in.readLine();
			numRoads = Integer.parseInt(line);
			for(int i = 0; i < numRoads; i++) {
				line = in.readLine();
				if(line != null) {

					String[] roadDetails = line.trim().split("\\s+");
					int intA = Integer.parseInt(roadDetails[0]);
					int intB = Integer.parseInt(roadDetails[1]);
					double length = Double.parseDouble(roadDetails[2]);

					roads.add(new Road(intA, intB, length));
					//					city[intA].outgoingRoads.add(new Road(intA, intB, length));
				}
			}
			in.close();
		} catch (Exception e) {
			//			this.city = new Intersection[0];
		}
	}


	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 */
	public int timeRequiredforCompetition(){

		if(sA < 50 || sB < 50 || sC < 50 || sA > 100 || sB > 100 || sC > 100)
			return -1;
		int slowest = Math.min(sA, Math.min(sB, sC));

		double distances[][] = new double[numInts][numInts];	// initialising 2D array to hold the distances from each intersection to every other intersection
		double maxDistance = Double.MIN_VALUE;

		for (int i = 0; i < numInts; i++) {
			for (int j = 0; j < numInts; j++) {
				distances[i][j] = Double.POSITIVE_INFINITY;
			}
		}
		for(int i = 0; i < numInts; i++) {
			distances[i][i] = 0.0; 								//setting distance from each intersection to itself to 0
		}

		for (int i = 0; i < roads.size(); i++) {
			Road current = roads.get(i);
			if(distances[current.getIntA()][current.getIntB()] > current.getLength()) {
				distances[current.getIntA()][current.getIntB()] = current.getLength();		// adding known distances to array
			}
		}

		for(int i = 0; i < numInts; i++) {
			for(int j = 0; j < numInts; j++) {
				for(int k = 0; k < numInts; k++) {

					if(distances[i][j] > distances[i][k] + distances[k][j]) {
						distances[i][j] = distances[i][k] + distances[k][j];
					}
				}
			}
		}

		for(int i = 0; i < distances.length; i++) {
			for(int j = 0; j < distances.length; j++) {			
				System.out.println(distances[i][j]);
			}
		}
		
		for(int i = 0; i < numInts; i++) {
			for(int j = 0; j < numInts; j++) {

				if(distances[i][j] > maxDistance) {
					maxDistance = distances[i][j];
				}
			}
		}

		if (maxDistance == Double.POSITIVE_INFINITY) {

			System.out.println("Bitchcoin");

			return -1;
		}

		maxDistance = maxDistance * 1000; // converted to meters

		double result = maxDistance * slowest;

		result = Math.ceil(result);
		int minutes = (int) result;

		return minutes;
	}

	public int getSA() { return sA;	}
	public int getSB() { return sB; }
	public int getSC() { return sC; }

	public int getNumInts() { return numInts; }
}