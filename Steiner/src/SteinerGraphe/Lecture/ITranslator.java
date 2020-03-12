package SteinerGraphe.Lecture;


import java.io.BufferedReader;

import SteinerGraphe.StructFile;;

/*
 * @cons
 * 		$DESC$ 	Un Translator de fichier
 * 		$ARG$	StructFile struct, BufferedReader br1, BufferedReader br2
 * 		$PRE$
 * 			br1 != null
 * 			br2 != null
 * 			struct != null
 * 		$POST$
 * 			getBr1 == Reader.getBr1
 * 			getBr2 == Reader.getBr1
 * 			getStruct() == struct
 */
public interface ITranslator {
	
	// CONSTANTE
	
	/*
	 * Le nombre représentant l'infini
	 */
	static int I = 99999999;
	
	// REQUETES
	
	/*
	 * Renvoie le BufferedReader 1 permettant de count le nombre de lignes
	 */
	BufferedReader getBr1();
	
	/*
	 * Renvoie le BufferedReader 2 permettant de créer notre structure
	 */
	BufferedReader getBr2();
	
    /*
     * La structure
     */
    StructFile getStruct();
	
    
	// COMMANDE
	
	/*
	 * Compte le nombre de ligne et le renvoie
	 * @pre
	 * 		br1 != null
	 */
	int count() throws Exception;
    
	/*
	 * Permet de remplir la structure
	 * @pre
	 * 		br2 != null
	 * 		struct.getNbSommets() == première ligne
	 * 		struct.getNomSommets() == deuxième ligne
	 * 		struct.getNbSommetsRdv() == troisième ligne
	 *   	struct.getNomSommetsRdv() == quatrième ligne
	 *   	struct.getNomSommetsIni() == cinquième ligne
	 *		struct.getBase() == fromArrayToDist(ligne 6 et plus)
	 */
	void trans(int i) throws Exception;
	
	/*
	 * Créé un tableau bidimentionel représentant la situation envoyé depuis le .txt
	 *		tab[0] != null
	 */
	int[][] fromArrayToDist(String[] tab);
	
	
	// OUTILS 
	
	/*
	 * Transforme un char en un int tq a = 0, b = 1, etc
	 * @pre
	 * 		car != null
	 */
	int fromPredToInt(String car);
}