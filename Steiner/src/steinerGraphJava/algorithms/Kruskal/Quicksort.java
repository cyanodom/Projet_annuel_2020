package steinerGraphJava.algorithms.Kruskal;

import java.util.LinkedList;

import steinerGraphJava.graph.Arc;

class QuickSort {

	// CONSTRUCTEUR

	public QuickSort() {

	}


	// COMMANDES

	public int partition(LinkedList<Arc> arr, int low, int high) {
		int pivot = arr.get(high).getWeight(); // DERNIER ELEMENT
		int i = (low-1);
		for (int j = low; j < high; j++) {
			if (arr.get(j).getWeight() < pivot) {
				i++;

				Arc temp = arr.get(i);
				arr.add(i, arr.get(j));
				arr.add(j, temp);
			}
		}

		Arc temp = arr.get(i + 1);
		arr.add(i + 1, arr.get(high));
		arr.add(high, temp);

		return i+1;
	}


	public LinkedList<Arc> sort(LinkedList<Arc> linkedList, int low, int high) {
		LinkedList<Arc> arr = linkedList;
		if (low < high) {
			int pi = partition(arr, low, high);

			sort(arr, low, pi-1);
			sort(arr, pi+1, high);
		}
		return arr;
	}


//	public void printArray(LinkedList<Arc> res) {
//		int n = res.length;
//		for (int i=0; i<n; ++i) {
//			System.out.print("(" + res[i].getNodes()[0] + "," + res[i].getNodes()[1] + "," + res[i].getWeight() + ")");
//			System.out.println();
//		}
//	}
}
