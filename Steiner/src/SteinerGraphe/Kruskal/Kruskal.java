package SteinerGraphe.Kruskal;


import SteinerGraphe.StructFile;

public class Kruskal {
	// CONSTANTE
	
	private final static int NB_ELEMENT = 2;
	
	// ATTRIBUTS
	
	private StructFile struct;
	private QuickSort quickSort;
	private Cycle cycle;
	private int length;
	
	
	// CONSTRUCTEUR
	
	public Kruskal (StructFile struct, int length) {
		this.struct = struct;
		this.quickSort = new QuickSort();
		this.cycle = new Cycle(length - 3, this.struct);
		this.length = length;
	}
	
	
	// REQUETES
	
	public int getLength() {
		return length;
	}
	
	
	// COMMANDES
	
	public int[][] trierList(int pos) {
		return quickSort.sort(struct.getList(), 0, getLength() - 4, pos);
	}

	public int[][] kruskal() {
		int n = 0; // Controle pour ne pas avoir des tailles trop grandes de tableau avec des cases vides
		int res[][] = trierList(NB_ELEMENT);
		quickSort.printArray(res);
		int kara[][] = new int[getLength() - 3][NB_ELEMENT];
		for (int i = 0; i < getLength() - 3; i++) {
			kara[i + n] = res[i];
			System.out.println(kara[i + n][0] + "," + kara[i + n][1] + "," + kara[i + n][2]);
			if (cycle.findCycle(kara, i + 1 + n)) {
				System.out.println("CYCLE");
				vide(kara[i + n]);
				n--;
			} else {
				System.out.println("Not a CYCLE");
			}	
		}
		return kara;
	}
	
	public void vide(int[] tab) {
		tab[0] = 0;
		tab[1] = 0;
		tab[2] = 0;
	}
}
