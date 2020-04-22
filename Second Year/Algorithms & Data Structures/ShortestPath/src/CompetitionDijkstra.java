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
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

	private int sA, sB, sC;
	private Intersection[] city;

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA, sB, sC: speeds for 3 contestants
	 */
	CompetitionDijkstra (String filename, int sA, int sB, int sC){

		int numIntersections = -1;
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
			numIntersections = Integer.parseInt(line);
			this.city = new Intersection[numIntersections];
			for(int i = 0; i < city.length; i++) {
				city[i] = new Intersection();
			}
			line = in.readLine();
			numRoads = Integer.parseInt(line);
			for(int i = 0; i < numRoads; i++) {
				line = in.readLine();
				if(line != null) {

					String[] roadDetails = line.split("\\s+");
					int intA = Integer.parseInt(roadDetails[0]);
					int intB = Integer.parseInt(roadDetails[1]);
					double length = Double.parseDouble(roadDetails[2]);

					city[intA].outgoingRoads.add(new Road(intA, intB, length));
				}
			}
			in.close();
		} catch (Exception e) {
			this.city = new Intersection[0];
		}
	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 */
	public int timeRequiredforCompetition(){

		if(sA < 50 || sB < 50 || sC < 50 || sA > 100 || sB > 100 || sC > 100)
			return -1;
		int slowest = Math.min(sA, Math.min(sB, sC));
		double longestShortest = -1;

		for (int i = 0; i < city.length; i++) {

			double tempLongest = dijkstra(i);  // hmmmmm
			if(tempLongest == -1)
				return -1;

			if (tempLongest > longestShortest)
				longestShortest = tempLongest;			
		}
		if (longestShortest == Double.MAX_VALUE)
			return -1;

		longestShortest = longestShortest * 1000; // converted to meters

		double result = longestShortest/slowest;

		result = Math.ceil(result);
		int minutes = (int) result;

		return minutes;
	}

	public double dijkstra(int source) {


		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<Integer> unvisited = new ArrayList<Integer>();

		double[] distance = new double[city.length];
		int[] previous = new int[city.length];


		for (int i = 0; i < city.length; i++) {

			unvisited.add(i);
		}
		for (int i = 0; i < city.length; i++) {
			distance[i] = Integer.MAX_VALUE;
			previous[i] = -1;
		}

		distance[source] = 0;
		previous[source] = 0;
		int current = source;

		do {

			double shortest = Double.MAX_VALUE;
			int next = -1;

			for (int i = 0; i < unvisited.size(); i++) {		// current = intersection in unvisited with min dist[] 

				if (distance[unvisited.get(i)] < shortest) {
					shortest = distance[unvisited.get(i)];
					next = unvisited.get(i);
				}
			}
			if (next != -1)
				current = next;
			else 
				break;

			if(!visited.contains(current)) {					
				visited.add(current);
			}

			for(int j = 0; j < (unvisited.size()); j++) {
				if(unvisited.get(j) == current) {
					unvisited.remove(j);
					break;
				}
			}

//			System.out.println("Entering the bastard");
//			System.out.println("Current = " + current);
//			System.out.println("num outgoing roads but it's b4 the 4 loop " +city[current].numOutgoingRoads());

			if((current != -1) && (city[current].numOutgoingRoads() > 0)) {
				for(int i = 0; i < (city[current].numOutgoingRoads()); i++) {  // u are here. shoot me

//					System.out.println(city[current].numOutgoingRoads() + " " + i);

					Road road = city[current].getOutgoingRoads(i);

					if (unvisited.contains(road.getIntB())) {
						int nextInt = road.getIntB();
						double tempDist = distance[current] + road.getLength();
						if(tempDist < distance[nextInt]) {
							distance[nextInt] = tempDist;
							previous[nextInt] = current;
						}
					}
				}
			}

		} while(unvisited.size() > 0);

		double longestShort = distance[0];		
		for (int i = 1; i < distance.length; i++){
			if (distance[i] > longestShort){
				longestShort = distance[i];
//				System.out.println("longest short" + longestShort);
			}
		}
		return longestShort;
	}

}