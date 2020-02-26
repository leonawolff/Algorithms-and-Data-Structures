import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import org.junit.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/*
 RUNNING TIMES COMPARISON :
 

numbers10

	insertionSort with numbers10.txt = 0.0128 ms
	selectionSort with numbers10.txt = 0.0048 ms
	quickSort with numbers10.txt = 0.0252 ms
	mergeSortIterative with numbers10.txt = 0.059 ms
	mergeSortRecursive with numbers10.txt = 0.0661 ms

numbers100

	insertionSort with numbers100.txt = 0.0618 ms
	selectionSort with numbers100.txt = 0.2873 ms
	quickSort with numbers100.txt = 0.299 ms
	mergeSortIterative with numbers100.txt = 0.2814 ms
	mergeSortRecursive with numbers100.txt = 0.202 ms

numbers1000

	insertionSort with numbers1000.txt = 0.2472 ms
	selectionSort with numbers1000.txt = 8.1486 ms
	quickSort with numbers1000.txt = 7.1015 ms
	mergeSortIterative with numbers1000.txt = 1.2157 ms
	mergeSortRecursive with numbers1000.txt = 1.6014 ms

numbers1000Duplicates

	insertionSort with numbers1000Duplicates.txt = 0.1802 ms
	selectionSort with numbers1000Duplicates.txt = 0.9331 ms
	quickSort with numbers1000Duplicates.txt = 9.7679 ms
	mergeSortIterative with numbers1000Duplicates.txt = 0.5178 ms
	mergeSortRecursive with numbers1000Duplicates.txt = 0.562 ms

numbersNearlyOrdered1000

	insertionSort with numbersNearlyOrdered1000.txt = 0.1828 ms
	selectionSort with numbersNearlyOrdered1000.txt = 6.9646 ms
	quickSort with numbersNearlyOrdered1000.txt = 9.5527 ms
	mergeSortIterative with numbersNearlyOrdered1000.txt = 0.523 ms
	mergeSortRecursive with numbersNearlyOrdered1000.txt = 0.541 ms

numbersReverse1000
	
	insertionSort with numbersReverse1000.txt = 0.1881 ms
	selectionSort with numbersReverse1000.txt = 6.9963 ms
	quickSort with numbersReverse1000.txt = 3.6148 ms
	mergeSortIterative with numbersReverse1000.txt = 0.2841 ms
	mergeSortRecursive with numbersReverse1000.txt = 0.303 ms

numbersSorted1000

	insertionSort with numbersSorted1000.txt = 0.1071 ms
	selectionSort with numbersSorted1000.txt = 4.1726 ms
	quickSort with numbersSorted1000.txt = 0.9238 ms
	mergeSortIterative with numbersSorted1000.txt = 0.2735 ms
	mergeSortRecursive with numbersSorted1000.txt = 0.5468 ms

	a. Which of the sorting algorithms does the order of input have an impact on? Why?
	
		Insertion sort performs better when the array is nearly sorted as there are only a few elements of the array that need to be shifted into the correct position. 
		It does not have to go through the sorted elements of the array, resulting in a faster running time. 
		
		Quicksort performs poorly on a nearly sorted array. If the pivot is the first element, we need to increment through evbery pivot position and every element 
		in the array to determine if they need to be swapped. 	
	
	b. Which algorithm has the biggest difference between the best and worst performance, based
	on the type of input, for the input of size 1000? Why?
	
		Insertion sort, as when the array is nearly sorted, the running time is very short as it does not need to carry out very many operations.
		However, when the array is reversed for example, every element must be swapped, resulting in a lot of operations and a high running time. 	
	
	c. Which algorithm has the best/worst scalability, i.e., the difference in performance time
	based on the input size? Please consider only input files with random order for this answer.
	
		insertionSort has the worst scalability, as when the file size increases, it slows down more than the other algorithms. 
		
		mergeSort seems to have the best scalability as the results changed very little when using the larger files vs the smaller files, compared to the other algorithms.		
		
	
	d. Did you observe any difference between iterative and recursive implementations of merge
	sort?
		
		Most of the time, the results were quite similar. The iterative version was slightly faster on average. 
	
	
	e. Which algorithm is the fastest for each of the 7 input files?
	
		In my tests, insertion sort seems to be winning nearly every time. However, I have researched the various algorithms in this assignment and 
		have found that the correct answer is quickSort, as quickSort has the best average time complexity.

 */


//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Leona Wolff
 *  @version HT 2020
 */
@RunWith(JUnit4.class)

public class SortComparisonTest{
	//~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new SortComparison();
	}

	@Test
	public void testEmpty() {
		double[] a = {};
		assertArrayEquals("Checking empty insertionSort", a, SortComparison.insertionSort(a), 0);
		assertArrayEquals("Checking empty selectionSort", a, SortComparison.selectionSort(a), 0);
		assertArrayEquals("Checking empty quickSort", a, SortComparison.quickSort(a), 0);
		assertArrayEquals("Checking empty mergeSortIterative", a, SortComparison.mergeSortIterative(a), 0);
		assertArrayEquals("Checking empty mergeSortRecursive", a, SortComparison.mergeSortRecursive(a), 0);
	}

	@Test
	public void testInsertionSort () {
		double a[] = {6.7, 1.32, 5.4, 9.9, 100.0, 3.1};
		double b[] = null;
		double expected[] = {1.32, 3.1, 5.4, 6.7, 9.9, 100.0};

		assertArrayEquals("Checking empty insertionSort", expected, SortComparison.insertionSort(a), 0);
		assertArrayEquals("Checking empty insertionSort", null, SortComparison.insertionSort(null), 0);
	}

	@Test
	public void testSelectionSort() {
		
		double a[] = {6.7, 1.32, 5.4, 9.9, 100.0, 3.1};
		double expected[] = {1.32, 3.1, 5.4, 6.7, 9.9, 100.0};

		assertArrayEquals("Checking empty insertionSort", expected, SortComparison.selectionSort(a), 0);
		assertArrayEquals("Checking empty insertionSort", null, SortComparison.selectionSort(null), 0);
	}
	
	@Test
	public void testQuickSort() {
		
		double a[] = {6.7, 1.32, 5.4, 9.9, 100.0, 3.1};
		double expected[] = {1.32, 3.1, 5.4, 6.7, 9.9, 100.0};

		assertArrayEquals("Checking empty insertionSort", expected, SortComparison.quickSort(a), 0);
		assertArrayEquals("Checking empty insertionSort", null, SortComparison.quickSort(null), 0);
	}
	
	@Test
	public void testMergeSortIterative() {
		
		double a[] = {6.7, 1.32, 5.4, 9.9, 100.0, 3.1};
		double expected[] = {1.32, 3.1, 5.4, 6.7, 9.9, 100.0};

		assertArrayEquals("Checking empty insertionSort", expected, SortComparison.mergeSortIterative(a), 0);
		assertArrayEquals("Checking empty insertionSort", null, SortComparison.mergeSortIterative(null), 0);
	}
	
	@Test
	public void testMergeSortRecursive() {
		
		double a[] = {6.7, 1.32, 5.4, 9.9, 100.0, 3.1};
		double expected[] = {1.32, 3.1, 5.4, 6.7, 9.9, 100.0};

		assertArrayEquals("Checking empty insertionSort", expected, SortComparison.mergeSortRecursive(a), 0);
		assertArrayEquals("Checking empty insertionSort", null, SortComparison.mergeSortRecursive(null), 0);
	}
	
	
	/**
	 *  Main Method.
	 *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
	 *
	 */
	@Test
	public void main() {

		double a[] = readFile(new double[10], "numbers10.txt");
		double b[] = readFile(new double[100], "numbers100.txt");
		double c[] = readFile(new double[1000], "numbers1000.txt");
		double d[] = readFile(new double[1000], "numbers1000Duplicates.txt");
		double e[] = readFile(new double[1000], "numbersNearlyOrdered1000.txt");
		double f[] = readFile(new double[1000], "numbersReverse1000.txt");
		double g[] = readFile(new double[1000], "numbersSorted1000.txt");

		testTextFiles(a, "numbers10.txt");
		testTextFiles(b, "numbers100.txt");
		testTextFiles(c, "numbers1000.txt");
		testTextFiles(d, "numbers1000Duplicates.txt");
		testTextFiles(e, "numbersNearlyOrdered1000.txt");
		testTextFiles(f, "numbersReverse1000.txt");
		testTextFiles(g, "numbersSorted1000.txt");

	} 

	public static double[] readFile(double[] a, String fileName) {
		try {
			String line;
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			while((line = br.readLine())!= null){
				String [] r = line.split("\n");

				for(int i = 0; i < r.length; i++){
					double val = Double.parseDouble(r[i]);
					a[i] = val;
				}
			}
			br.close();
		}
		catch(Exception e){
			System.out.println("Oh no, something went wrong!");			
		}

		return a;
	}

	public static void testTextFiles(double arr[], String arrName) {
		
		double start = System.nanoTime();
		SortComparison.mergeSortRecursive(arr);
		double end = System.nanoTime();
		System.out.println("mergeSortRecursive with " + arrName + " = " + (end - start)/1000000 + " ms");


		start = System.nanoTime();
		SortComparison.insertionSort(arr); 
		end = System.nanoTime();
		System.out.println("insertionSort with " + arrName + " = " + (end - start)/1000000 + " ms");

		start = System.nanoTime();
		SortComparison.selectionSort(arr);
		end = System.nanoTime();
		System.out.println("selectionSort with " + arrName + " = " + (end - start)/1000000 + " ms");



		start = System.nanoTime();
		SortComparison.quickSort(arr);
		end = System.nanoTime();
		System.out.println("quickSort with " + arrName + " = " + (end - start)/1000000 + " ms");


		start = System.nanoTime();
		SortComparison.mergeSortIterative(arr);
		end = System.nanoTime();
		System.out.println("mergeSortIterative with " + arrName + " = " + (end - start)/1000000 + " ms");
		
		System.out.println("\n");


	}
}
