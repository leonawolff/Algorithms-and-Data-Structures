import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CompetitionTests {
	
	String fileName1 = "C:\\Users\\leona\\eclipse-workspace\\ShortestPath\\src\\tinyEWD.txt";
	String fileName2 = "C:\\Users\\leona\\eclipse-workspace\\ShortestPath\\src\\1000EWD.txt";
	String fileName3 = "kdsdbfl";
	String fileName4 = "";

    @Test
    public void testDijkstraConstructor() {

    }

    @Test
    public void testFWConstructor() {
    	
    	int sA = 60;
    	int sB = 70;
    	int sC = 80;
    	
    	CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall(fileName1, sA, sB, sC);
        assertEquals("Checking with tinyEWD", 8, floydWarshall.getNumInts());
        assertEquals("Checking with tinyEWD", 60, floydWarshall.getSA());
        assertEquals("Checking with tinyEWD", 70, floydWarshall.getSB());
        assertEquals("Checking with tinyEWD", 80, floydWarshall.getSC());
        
        CompetitionFloydWarshall floydWarshall2 = new CompetitionFloydWarshall(fileName2, sA, sB, sC);
        assertEquals("Checking with tinyEWD", 1000, floydWarshall2.getNumInts());
        assertEquals("Checking with tinyEWD", 60, floydWarshall2.getSA());
        assertEquals("Checking with tinyEWD", 70, floydWarshall2.getSB());
        assertEquals("Checking with tinyEWD", 80, floydWarshall2.getSC());
    	    	
    }
    
    @Test
    public void testFWCompetitionTimeRequired() {
    	
//    	CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall(fileName1, 60, 70, 80); //
//    	assertEquals(floydWarshall.timeRequiredforCompetition(), 31);

    	
    }
    
    @Test
    public void testDijkstraCompetitionTimeRequired() {
    	
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra(fileName1, 60, 70, 80); // hmmmmm
    	assertEquals(dijkstra.timeRequiredforCompetition(), 31);
    	
    	dijkstra = new CompetitionDijkstra(fileName1, 10, 70, 80);
    	assertEquals(dijkstra.timeRequiredforCompetition(), -1);
    	
    }
    



    
}