package SteinerGraphe;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import SteinerGraphe.Kruskal.Kruskal;
import SteinerGraphe.Lecture.Reader;

public class Graphe {
	
	// ATTRIBUTS
	
	private Reader reader;
	
	private StructFile struct;
	
	private File file;
	
	private Genetique gene;
	
	
	// CONSTRUCTEUR
	
	public Graphe () {
		runAlgo();
	}
	
	
	// COMMANDES
	
	public void runAlgo() {
		file = new File("C:\\Users\\tisda\\eclipse-workspace2\\Steiner\\src\\SteinerGraphe\\text.txt");
		
		// Partie Reader
		this.struct = new StructFile();
		
		try {
			reader = new Reader(struct, file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			reader.translate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			reader.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// PARTIE g�n�tique
		
		switch(struct.getNomSommetsT().length) {
		case 1 :
			System.out.println(getNomSommetsT()[0]);
			System.out.println("Poids de 0");
			break;
		case 2 :
			
			break;
		default :
			if (struct.getNomSommetsT().length == struct.getNbSommets()) {  
				// SI S = T
				Kruskal kruskal = new Kruskal(struct, reader.getLength());
				int[][] tab = kruskal.kruskal();
			} else {
				gene = new Genetique(struct);
				gene.AlgoGene();
			}
			break;
		}
	}
	
	// MAIN
	
	public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Graphe();
            }
        });
    }
}
