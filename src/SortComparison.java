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

		if (a != null) {

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
		else
			return null;
	}

	static double [] selectionSort (double a[]){

		if (a != null) {

			for(int i = 0; i < a.length; i++) {
				for (int j = i + 1; j < a.length; j++) {

					if (a[j] < a[i]) {
						swap(a, j, i);
					}
				}
			}
			return a;
		}
		else 
			return null;
	}

	static double[] quickSort(double a[]) {
		if (a != null) {
			quickSort(a, 0, a.length - 1);
			return a;
		} else 
			return null;
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

	static double[] mergeSortIterative(double[] a) {
		if (a != null) {
			int low = 0;
			int high = a.length - 1;

			double[] temp = Arrays.copyOf(a, a.length);

			for (int m = 1; m <= high - low; m = 2 * m) {
				for (int bottom = low; bottom < high; bottom += 2 * m) {
					int mid = bottom + m - 1;
					int top = bottom + 2 * m - 1;

					merge(a, temp, bottom, mid, (top < high) ? top : high);
				}
			}
			return a;
		} 
		else
			return null;
	}

	static void merge(double[] a, double[] temp, int bottom, int mid, int top) {
		int k = bottom, i = bottom, j = mid + 1;
		while (i <= mid && j <= top) {
			if (a[i] < a[j]) {
				temp[k++] = a[i++];
			} else {
				temp[k++] = a[j++];
			}
		}
		while (i <= mid && i < a.length) {
			temp[k++] = a[i++];
		}
		for (i = bottom; i <= top; i++) {
			a[i] = temp[i];
		}
	}

	static double[] mergeSortRecursive (double a[]) {

		if (a != null) {

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
		else 
			return null;
	}

	//	static void main(String[] args) {
	//
	//		double a[] = {6.7, 1.32, 5.4, 9.9, 100.0, 3.1};
	//		double expected[] = {1.32, 3.1, 5.4, 6.7, 9.9, 100.0};
	//
	//		if(Arrays.equals(selectionSort(a), expected))
	//			System.out.println("Working");
	//		else {
	//			double[] result = selectionSort(a);
	//			for(int i = 0; i < a.length; i++) {
	//				System.out.println(result[i]);	
	//			}
	//		}
	//	}

	static double[] swap(double a[], int b, int c) {

		double temp = a[b];
		a[b] = a[c];
		a[c] = temp;

		return a;
	}

}