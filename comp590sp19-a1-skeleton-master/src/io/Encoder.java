package io;
//worked with tony ma
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Encoder {
		
	public static void main(String[] args) throws IOException {
		
		//input 
		String decodedfile = "decoded.txt";
		InputStream inputstream = new FileInputStream(decodedfile);
		InputStreamBitSource inputbitsource = new InputStreamBitSource(inputstream);
		
		//output 
		OutputStream outstream = new FileOutputStream("compressedFileAfterEncoding.dat");
		OutputStreamBitSink outbitsource = new OutputStreamBitSink(outstream);
		
		ArrayList<PairsEncoder> lengths = new ArrayList<PairsEncoder>();
		Map<Character, Integer> timesappeared = new HashMap<Character, Integer>(); //only used once
		
		for (int i = 0; i < 256; i++) {
			timesappeared.put((char)i, 0);
		}
		
		
		int total = 0;
		BufferedReader bR = new BufferedReader(new FileReader(decodedfile));
		char curr;
		while ((curr = (char) bR.read())!= '\u0000') { //doesnt work
			total++;
			
		}
		
		
		
		for(Map.Entry<Character, Integer> entry : timesappeared.entrySet()) { //foreach loop
			lengths.add(new PairsEncoder(entry.getValue(), entry.getKey()));
		}
		Collections.sort(lengths); //need this to sort the list of frequencies
		
		double[] probabil = new double[256];
		for (int i = 0; i < 256; i++) { //for Part 3.2
			probabil[i] = (double)lengths.get(i).getFreq() / (double)total;
		}
		double entropy = 0.0;
		for (int i = 0; i < 256; i++) { //for Part 3.3
			double prob = probabil[i];
			if (prob > 0.0000000000000000000000001) {
				entropy += (double)(-1)*prob*Math.log(prob);
			}
		}
		System.out.println("ques 3: " + entropy);
		
		double compressedEntropy = 4599968/574992; //for Part 3.4
		System.out.println(compressedEntropy);
		
		VarianceTree vt = new VarianceTree(lengths); //not creating properly
		vt.printLeafNodes(vt.getRoot()); //debugging
		System.out.println("fucked up: " + vt.getLeafArray().size());
		ArrayList<NodeE> leafNodes = vt.getLeafArray();
		//turn the variancetree into a huffman tree.

		ArrayList<Pairs> lengthsForCT = new ArrayList<Pairs>();
		
		for (int i = 0; i < 256; i++) { //debugging
			lengthsForCT.add(new Pairs(leafNodes.get(i).getLength(), leafNodes.get(i).getChar()));
//			System.out.println("lengths!!!!!!!!!!!!!: " + leafNodes.get(i).getLength());
		}
		Collections.sort(lengthsForCT);
		for (int i = 0; i < 256; i++) { //debugging after the sort
			System.out.println(lengthsForCT.get(i).getCharacter());
			System.out.println("LENGTHS!!!!!!!!!!!!!: " + lengthsForCT.get(i).getLength());
			
		}
		System.out.println("hello?: " + lengthsForCT.size()); //debugging
		
		CanonicalTree newCT = new CanonicalTree(lengthsForCT);
		newCT.printLeafNodes(newCT.getRoot());
		
		
		//first 256 bytes
		for (int i = 0; i < 256; i++) {
			outbitsource.write(lengthsForCT.get(i).getLength(), 8); //could this be wrong?

		}
		//output next 4 bytes
		outbitsource.write(total, 32);
		
		//output the rest of the file
		HashMap<Character, String> chart = newCT.getStrings(); //gives values of each key

		BufferedReader bR2 = new BufferedReader(new FileReader(decodedfile));
		char currentCharacter2;
		
		
		//pad
		outbitsource.padToWord();
	}
	
}
