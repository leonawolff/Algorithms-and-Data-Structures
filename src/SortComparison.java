// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Leona Wolff 
 *  @version HT 2020
 */
import java.util.Arrays; 

class SortComparison {

	static double [] insertionSort (double a[]){

		for (int i = 1; i < a.length; ++i) {
			double temp = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > temp) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = temp;
		}
		return a;
	}

	static double [] selectionSort (double a[]){
		for(int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {

				if (a[j] < a[i]) {
					swap(a, j, i);
				}
			}
		}
		return a;
	}

	static double[] quickSort (double a[], int low, int high){
		if (low < high) {
			int i = partition(a, low, high);
			quickSort(a, low, i-1);
			quickSort(a, i+1, high);
		}
		return a;
	}

	static int partition(double a[], int low, int high) { 
		double pivot = a[high];  
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (a[j] < pivot) { 
				i++; 
				swap(a, i, j);
			} 
		} 
		swap(a, (i+1), high);
		return i+1; 
	} 

	/**
	 * Sorts an array of doubles using Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted order.
	 */

	// Merge two sorted sub-arrays A[from .. mid] and A[mid + 1 .. to]
	static double[] mergeSortIterative(double[] a) {

		return a;
	}



	/**
	 * Sorts an array of doubles using recursive implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted order.
	 */
	static double[] mergeSortRecursive (double a[]) {

		if(a.length > 1) { 
			int mid = a.length / 2; 

			double[] left = new double[mid];
			for(int i = 0; i < mid; i++) {
				left[i] = a[i];
			}
			double[] right = new double[a.length - mid];
			for(int i = mid; i < a.length; i++) {
				right[i - mid] = a[i];
			}
			mergeSortRecursive(left);
			mergeSortRecursive(right);

			int i, j, k;
			i = j = k = 0;

			while(i < left.length && j < right.length) { 
				if(left[i] < right[j]) { 
					a[k] = left[i]; 
					i++; 
				} 
				else { 
					a[k] = right[j]; 
					j++; 
				} 
				k++; 
			} 
			while(i < left.length) { 
				a[k] = left[i]; 
				i++; 
				k++; 
			} 
			while(j < right.length) { 
				a[k] = right[j]; 
				j++; 
				k++; 
			} 
		} 
		return a;
	}

	public static void main(String[] args) {

		double a[] = {6.7, 1.32, 5.4, 9.9, 100.0, 3.1};
		double expected[] = {1.32, 3.1, 5.4, 6.7, 9.9, 100.0};

		if(Arrays.equals(mergeSortRecursive(a), expected))
			System.out.println("Working");
		else {
			double[] result = mergeSortRecursive(a);
			for(int i = 0; i < a.length; i++) {
				System.out.println(result[i]);	
			}
		}
	}

	static double[] swap(double a[], int b, int c) {

		double temp = a[b];
		a[b] = a[c];
		a[c] = temp;

		return a;
	}

}