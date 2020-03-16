package steinerGraphJava.graph.graphFile;


import java.io.BufferedReader;

import util.Contract;

public class Translator implements ITranslator {
	// CONSTANTES

	private static final int NB_ELEMENT = 3;


	// ATTRIBUTS

	private BufferedReader br1;
	private BufferedReader br2;
	private StructFile struct;

	// CONSTRUCTEUR

	public Translator(BufferedReader br1, BufferedReader br2, StructFile struct) {
		Contract.checkCondition(br1 != null && br2 != null && struct != null);
		this.br1 = br1;
		this.br2 = br2;
		this.struct = struct;
	}


	// REQUETES

	public BufferedReader getBr1() {
		return br1;
	}

	public BufferedReader getBr2() {
		return br2;
	}

	public StructFile getStruct() {
		return struct;
	}

	// COMMANDES

	public int count() throws Exception {
		Contract.checkCondition(getBr1() != null);
		int i = 0;
		while ((getBr1().readLine()) != null) {
	        i++;
		}
		return i;
	}

	public void trans(int i) throws Exception {
		Contract.checkCondition(getBr2() != null);
		int n = 0;
		String[] tab = new String[i - 3];
		while (n < i) {
			if (n== 0) {
				struct.addNbSommets(Integer.parseInt(getBr2().readLine()));
			}
			else if (n == 1) {
				struct.addNomSommets(getBr2().readLine().split(" "));
			}
			else if (n == 2) {
				struct.addNomSommetsT(getBr2().readLine().split(" "));
			}
			else if (n >= 3) {
				tab[n - 3] = getBr2().readLine();
			}
			n++;
		}
		struct.addList(fromArrayToList(tab, i - 3));
	}

	public int[][] fromArrayToList(String[] tab, int n) {
		Contract.checkCondition(tab[0] != null);
		int[][] res = new int[n][NB_ELEMENT];
		for (int i = 0; i < n; i++) {
			String[] temp = new String[3];
			tab[i].trim();
			temp = tab[i].split(",");
			temp[0] = temp[0].replace('(', ' ').trim();
			temp[2] = temp[2].replace(')', ' ').trim();
			res[i][0] = fromPredToInt(temp[0]);
			res[i][1] = fromPredToInt(temp[1]);
			res[i][2] = fromPredToInt(temp[2]);
		}
		return res;
	}


	public int[][] fromArrayToDist(String[] tab) {
		Contract.checkCondition(tab[0] != null);
		int[][] res = new int[struct.getNbSommets()][struct.getNbSommets()];
		for (int i = 0; i < struct.getNbSommets(); i++) {
			for (int j = 0; j < struct.getNbSommets(); j++) {
				if (i == j) {
					res[i][j] = 0;
				} else {
					res[i][j] = I;
				}
			}
		}

		for (int i = 0; i < tab.length; i++) {
			String[] temp = new String[3];
			tab[i].trim();
			temp = tab[i].split(",");
			temp[0] = temp[0].replace('(', ' ').trim();
			temp[2] = temp[2].replace(')', ' ').trim();
			res[fromPredToInt(temp[0])][fromPredToInt(temp[1])] = Integer.parseInt(temp[2]);
		}
		return res;
	}


	// OUTILS

	public int fromPredToInt(String car) {
		Contract.checkCondition(car != null);
		return Integer.parseInt(car);
	}
}
