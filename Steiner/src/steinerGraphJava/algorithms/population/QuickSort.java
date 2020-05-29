package steinerGraphJava.algorithms.population;

import java.util.LinkedList;

import steinerGraphJava.graph.Arc;

public class QuickSort {

	// CONSTRUCTEUR

	public QuickSort() {

	}


	// COMMANDES
	
    // Tab[] QuickSort
    
	private int partition(int arr[], int low, int high) { 
        int pivot = arr[high];  
        int i = (low-1);
        for (int j=low; j<high; j++) { 
            if (arr[j] < pivot) { 
                i++;
                
                int temp = arr[i]; 
                arr[i] = arr[j];
                arr[j] = temp;
            } 
        } 
        
        int temp = arr[i+1];
        arr[i+1] = arr[high]; 
        arr[high] = temp;
  
        return i+1; 
    } 
  

    public void sort(int arr[], int low, int high) { 
        if (low < high) { 
            int pi = partition(arr, low, high);
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    }
    
    
    // Double Tab[] QuickSort

	private int partitionDouble(int arr[], int arr2[], int low, int high) { 
        int pivot = arr[high];  
        int i = (low-1);
        for (int j=low; j<high; j++) { 
            if (arr[j] < pivot) { 
                i++;
                
                int temp = arr[i]; 
                int temp2 = arr2[i];
                arr[i] = arr[j];
                arr2[i] = arr2[j];
                arr[j] = temp;
                arr2[j] = temp2;
            } 
        } 
        
        int temp = arr[i+1];
        int temp2 = arr2[i+1];
        arr[i+1] = arr[high]; 
        arr2[i+1] = arr2[high];
        arr[high] = temp;
        arr2[high] = temp2;
  
        return i+1; 
    } 
  

    public void sortDouble(int arr[], int arr2[], int low, int high) { 
        if (low < high) { 
            int pi = partitionDouble(arr, arr2, low, high);
            sortDouble(arr, arr2, low, pi-1); 
            sortDouble(arr, arr2, pi+1, high); 
        } 
    }
    
    
    // LinkedList<Arc> QuickSort
    
	private int partitionArc(LinkedList<Arc> arr, int low, int high) { 
        int pivot = arr.get(high).getWeight();  
        int i = (low-1);
        for (int j=low; j<high; j++) { 
            if (arr.get(j).getWeight() < pivot) { 
                i++;
                
                Arc temp = arr.get(i); 
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            } 
        } 
        
        Arc temp = arr.get(i+1);
        arr.set(i+1, arr.get(high)); 
        arr.set(high, temp);
  
        return i+1; 
    } 
  

    public void sortArc(LinkedList<Arc> arr, int low, int high) { 
        if (low < high) { 
            int pi = partitionArc(arr, low, high);
            sortArc(arr, low, pi-1); 
            sortArc(arr, pi+1, high); 
        } 
    }
    
    
    // Poids[] QuickSort
    
	private int partitionPoids(PoidsPenality[] arr, int low, int high) { 
        int pivot = arr[high].getPoids();
        int i = (low-1);
        for (int j=low; j<high; j++) { 
            if (arr[j].getPoids() < pivot) { 
                i++;
                
                PoidsPenality temp = arr[i]; 
                arr[i] = arr[j];
                arr[j] = temp;
            } 
        } 
        
        PoidsPenality temp = arr[i+1];
        arr[i+1] = arr[high]; 
        arr[high] = temp;
  
        return i+1; 
    } 
  

    public void sortPoids(PoidsPenality[] arr, int low, int high) { 
        if (low < high) { 
            int pi = partitionPoids(arr, low, high);
            sortPoids(arr, low, pi-1); 
            sortPoids(arr, pi+1, high); 
        } 
    }    
    
    
    // Penality[] QuickSort
    
	private int partitionPenality(PoidsPenality[] arr, int low, int high) { 
        int pivot = arr[high].getPenality();
        int i = (low-1);
        for (int j=low; j<high; j++) { 
            if (arr[j].getPenality() < pivot) { 
                i++;
                
                PoidsPenality temp = arr[i]; 
                arr[i] = arr[j];
                arr[j] = temp;
            } 
        } 
        
        PoidsPenality temp = arr[i+1];
        arr[i+1] = arr[high]; 
        arr[high] = temp;
  
        return i+1; 
    } 
  

    public void sortPenality(PoidsPenality[] arr, int low, int high) { 
        if (low < high) {
            int pi = partitionPenality(arr, low, high);
            sortPenality(arr, low, pi-1);
            sortPenality(arr, pi+1, high);
        } 
    }
}
