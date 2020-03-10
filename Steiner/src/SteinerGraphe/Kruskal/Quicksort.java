package SteinerGraphe.Kruskal;

class QuickSort {

	// CONSTRUCTEUR
	
	public QuickSort() {
		
	}
	
	
	// COMMANDES
	
	/* This function takes last element as pivot, 
	places the pivot element at its correct 
	position in sorted array, and places all 
	smaller (smaller than pivot) to left of 
	pivot and all greater elements to right 
	of pivot */
	public int partition(int arr[][], int low, int high, int pos) { 
		int pivot = arr[high][pos]; // DERNIER ELEMENT
		int i = (low-1); // index of smaller element 
		for (int j = low; j < high; j++) { 
			// If current element is smaller than the pivot 
			if (arr[j][pos] < pivot) { 
				i++; 

				// swap arr[i] and arr[j] 
				int temp[] = arr[i]; 
				arr[i] = arr[j]; 
				arr[j] = temp; 
			} 
		} 

		// swap arr[i+1] and arr[high] (or pivot) 
		int temp[] = arr[i+1]; 
		arr[i+1] = arr[high]; 
		arr[high] = temp; 

		return i+1; 
	} 


	/* The main function that implements QuickSort() 
	arr[] --> Array to be sorted, 
	low --> Starting index, 
	high --> Ending index */
	public int[][] sort(int list[][], int low, int high, int pos) {
		int [][] arr = list; 
		if (low < high) { 
			/* pi is partitioning index, arr[pi] is 
			now at right place */
			int pi = partition(arr, low, high, pos); 

			// Recursively sort elements before 
			// partition and after partition 
			sort(arr, low, pi-1, pos); 
			sort(arr, pi+1, high, pos); 
		}
		return arr;
	} 

	/* A utility function to print array of size n */
	public void printArray(int[][] js) { 
		int n = js.length; 
		for (int i=0; i<n; ++i) { 
			System.out.print("(" + js[i][0] + "," + js[i][1] + "," + js[i][2] + ")"); 
			System.out.println(); 
		}
	}
} 

/**
 * 
(2,5,5)
(2,6,2)
(2,9,8)
(3,4,8)
(3,7,4)
(3,8,8)
(4,7,3)
(5,7,5)
(5,9,8)
(6,7,1)
*/