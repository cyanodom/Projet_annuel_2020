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
		// FICHIER TXT qui contient le graphe
		file = new File("C:\\Users\\tisda\\eclipse-workspace2\\Steiner\\src\\SteinerGraphe\\text.txt");
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
		
		gene = new Genetique(struct);
		gene.AlgoGene();
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
