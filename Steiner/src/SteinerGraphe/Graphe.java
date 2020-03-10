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
	
	private Kruskal kruskal;
	
	
	// CONSTRUCTEUR
	
	public Graphe () {
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
		
		kruskal = new Kruskal(struct, reader.getLength());
		kruskal.kruskal();
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
