package SteinerGraphe.Lecture;


import util.Contract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import SteinerGraphe.StructFile;;

public class Reader implements IReader {
	
	// ATTRIBUTS
	
	private BufferedReader br1;
	private BufferedReader br2;
	
	private Translator translate;
	
	private StructFile struct;
	
	private int length;
	
	
	// CONSTRUCTEUR
	
    public Reader(StructFile struct, File file) throws FileNotFoundException {
    	Contract.checkCondition(struct != null);
    	
    	this.br1 = new BufferedReader(new FileReader(file));
    	this.br2 = new BufferedReader(new FileReader(file));
    	
    	this.struct = struct;
    }
    
    // REQUETES
    
    public BufferedReader getBr1() {
    	return br1;
    }
    
    public BufferedReader getBr2() {
    	return br2;
    }
    
    public Translator getTranslate() {
    	Contract.checkCondition(translate != null);
    	return translate;
    }
    
    public int getLength() {
    	return length;
    }
    
    public StructFile getStruct() {
    	return struct;
    }
    
    
    // COMMANDES
    
    public void translate() throws Exception {
    	translate = new Translator(getBr1(), getBr2(), getStruct());
    	length = getTranslate().count();
    }
    
    public void read() throws Exception {
    	Contract.checkCondition(getLength() >= 4);
   		getTranslate().trans(getLength());
   		
    	close();
    }
    
    public void close() throws Exception {
    	getBr1().close();
    	getBr2().close();
    }
}