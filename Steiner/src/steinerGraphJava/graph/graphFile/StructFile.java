package steinerGraphJava.graph.graphFile;

import util.Contract;

public class StructFile implements IStructFile {

	// ATTRIBUTS

	private int nbSommets;
	private String[] nomSommets;
	private String[] nomSommetsT;
	private int[][] base;
	private int[][] list;

	// CONSTRUCTEUR

	public StructFile() {

	}

	// REQUETES

    public int getNbSommets() {
		return nbSommets;
	}

	public String[] getNomSommets() {
		return nomSommets;
	}

	public String[] getNomSommetsT() {
		return nomSommetsT;
	}

	public int[][] getBase() {
    	return base;
    }

	public int[][] getList() {
    	return list;
    }


    // COMMANDES

    public void addNbSommets(int nbSommets) {
    	Contract.checkCondition(nbSommets >= 0);
    	this.nbSommets = nbSommets;
    }

    public void addNomSommets(String[] nomSommets) {
    	Contract.checkCondition(nomSommets.length >= 0);
    	this.nomSommets = nomSommets;
    }

    public void addNomSommetsT(String[] nomSommetsT) {
    	Contract.checkCondition(nomSommetsT.length > 0);
    	this.nomSommetsT = nomSommetsT;
    }

    public void addBase(int[][] storage) {
    	Contract.checkCondition(storage != null);
    	this.base = storage;
    }

    public void addList(int[][] storage) {
    	Contract.checkCondition(storage != null);
    	this.list = storage;
    }
}
