package SteinerGraphe.Lecture;

import java.io.BufferedReader;

import SteinerGraphe.StructFile;

/*
 * @cons
 * 		$DESC$ 	Un Reader de fichier
 * 		$ARG$	StructFile struct
 * 		$PRE$
 * 			struct != null
 * 		$POST$
 * 			getBr1 == C:\\chemin_vers_.txt
 * 			getBr2 == C:\\chemin_vers_.txt
 * 			getStruct() == struct
 */
public interface IReader {

    // REQUETES

    /*
     * BufferedReader utilis� pour connaitre length
     */
    BufferedReader getBr1();

    /*
     * BufferedReader utilis� pour la traduction
     */
    BufferedReader getBr2();

    /*
     * Le Translator actuellement en cours
     * @pre
     * 		translate != null
     */
    Translator getTranslate();

    /*
     * Le nombre de lignes du fichier
     */
    int getLength();

    /*
     * La structure
     */
    StructFile getStruct();


    // COMMANDES

    /*
     * Utilis� pour initialiser le Translator et la length du fichier
     * @post
     * 		translate == Translator(br1, br2, struct);
     *		length == translate.count();
     */
    void translate() throws Exception;

    /*
     * Lire le fichier afin de remplir la structure en utilisant le Translator
     * @pre
     * 		length >= 6
     * @post
     * 		struct != old struct
     * 		br1 == null
     * 		br2 == null
     */
    void read() throws Exception;

    /*
     * Permet de fermer les fichiers
     * @post
     * 		br1 == null
     * 		br2 == null
     */
    void close() throws Exception;
}
