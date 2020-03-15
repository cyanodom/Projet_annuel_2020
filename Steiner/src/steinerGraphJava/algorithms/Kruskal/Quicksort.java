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
				arr.set(i, arr.get(j));
				arr.set(j, temp);
			}
		}

		Arc temp = arr.get(i + 1);
		arr.set(i + 1, arr.get(high));
		arr.set(high, temp);

		return i+1;
	}


	public LinkedList<Arc> sort(LinkedList<Arc> linkedList, int low, int high) {
		System.out.println(linkedList.size());
		if (low < high) {
			int pi = partition(linkedList, low, high);

			sort(linkedList, low, pi-1);
			sort(linkedList, pi+1, high);
		}
		return linkedList;
	}


	public void printArray(LinkedList<Arc> res) {
		System.out.println("Résultat du QuickSort :");
		for (int i = 0; i < res.size(); ++i) {
			System.out.println("(" + res.get(i).getNodes()[0].getName() + "," 
								   + res.get(i).getNodes()[1].getName() + ","
								   + res.get(i).getWeight() + ")");
		}
		System.out.println("FIN résultat du QuickSort :" + res.size());
	}
}
