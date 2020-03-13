package SteinerGraphe.Kruskal;


class QuickSort {

	// CONSTRUCTEUR

	public QuickSort() {

	}


	// COMMANDES

	public int partition(int arr[][], int low, int high, int pos) {
		int pivot = arr[high][pos]; // DERNIER ELEMENT
		int i = (low-1);
		for (int j = low; j < high; j++) {
			if (arr[j][pos] < pivot) {
				i++;

				int temp[] = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		int temp[] = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;

		return i+1;
	}


	public int[][] sort(int list[][], int low, int high, int pos) {
		int [][] arr = list;
		if (low < high) {
			int pi = partition(arr, low, high, pos);

			sort(arr, low, pi-1, pos);
			sort(arr, pi+1, high, pos);
		}
		return arr;
	}


	public void printArray(int[][] js) {
		int n = js.length;
		for (int i=0; i<n; ++i) {
			System.out.print("(" + js[i][0] + "," + js[i][1] + "," + js[i][2] + ")");
			System.out.println();
		}
	}
}
