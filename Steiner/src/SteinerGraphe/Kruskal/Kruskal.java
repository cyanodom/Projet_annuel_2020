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
	
	public void kruskal() {
		int res[][] = trierList(NB_ELEMENT);
		int kara[][] = new int[getLength() - 3][NB_ELEMENT];
		kara[0] = res[0];
		System.out.println(kara[0][0] + "," + kara[0][1] + "," + kara[0][2]);
		for (int i = 1; i < getLength() - 3; i++) {
			kara[i] = res[i];
			System.out.println(kara[i][0] + "," + kara[i][1] + "," + kara[i][2]);
			if (cycle.findCycle(kara, i + 1)) {
				System.out.println("CYCLE");
			} else {
				System.out.println("Not a CYCLE");
			}	
		}
	}
}
