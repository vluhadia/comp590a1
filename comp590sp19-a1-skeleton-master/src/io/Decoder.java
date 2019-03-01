package io;
//worked with tony ma
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import io.CanonicalTree;
import io.InputStreamBitSource;
import io.InsufficientBitsLeftException;
import io.Node;
import io.OutputStreamBitSink;
import io.Pairs;

public class Decoder {
	
	
	public static void main(String[] args) throws InsufficientBitsLeftException, IOException {
		
		ArrayList<Pairs> lengths = new ArrayList<Pairs>();
		int totalPairs = 0; 
		
		String file = "compressedFileAfterEncoding.dat";
		InputStream iS = new FileInputStream(file);
		InputStreamBitSource iSBS = new InputStreamBitSource(iS);
		
		for(int i = 0; i < 256; i++) { //256bytes
			lengths.add(new Pairs(iSBS.next(8), Character.toString((char)i).charAt(0)));
		}
		Collections.sort(lengths);
		
		
		totalPairs = iSBS.next(32); //4bytes
		

		
		
		
		CanonicalTree canontree = new CanonicalTree(lengths);
		
		
		OutputStream outstream = new FileOutputStream("A1Decoder2.txt");
		
		
		
		OutputStreamBitSink outstreambitsink = new OutputStreamBitSink(outstream);
		
		
		
		
		int counter = 0;
		Node current = canontree.getRoot();
//		while (counter < totalPairs) {
//			while(true) {//wtf is all this shit logic i don't even know what to do
//				if (current.isLeaf()) {
//					outstreambitsink.write(((Pairs) current.p).getCharacter(), 8);
//					counter++;
//					current = canontree.getRoot();
//					break;
//				} else {
//					int num = iSBS.next(1);
//					if (num == 0) {
//						current = current.l;
//					} else {
//						current = current.r;
//					}
//				}
//			}
//		}
		outstreambitsink.padToWord();
	}
}
