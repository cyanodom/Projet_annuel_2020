package steinerGraphJava.graph.graphFile;


import java.io.BufferedReader;
import java.io.File;

import steinerGraphJava.graph.Graph;;

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

	// COMMANDE

	/*
	 * Permet de remplir la structure
	 * @pre
	 * 		br2 != null
	 * 		struct.getNbSommets() == premi�re ligne
	 * 		struct.getNomSommets() == deuxi�me ligne
	 * 		struct.getNbSommetsRdv() == troisi�me ligne
	 *   	struct.getNomSommetsRdv() == quatri�me ligne
	 *   	struct.getNomSommetsIni() == cinqui�me ligne
	 *		struct.getBase() == fromArrayToDist(ligne 6 et plus)
	 */
	void trans(File f, Graph graph);
}
