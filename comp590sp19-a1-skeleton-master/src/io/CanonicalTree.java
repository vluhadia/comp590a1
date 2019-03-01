//worked with Tony Ma
package io;

import java.util.ArrayList;
import java.util.HashMap;

public class CanonicalTree {
	
	private Node rootNode = new Node();
	

	private HashMap<Character, String> chart = new HashMap<Character, String>();
	
	
	public CanonicalTree(ArrayList<Pairs> lengths) {
		
		
		for (int i = 0; i < lengths.size(); i++) { //loop creates tree nodes
			
			Node curr = rootNode;
			
			for (int j = 0; j < lengths.get(i).getLength(); j++) {

				if (j == lengths.get(i).getLength()-1) {
					
					if (curr.l == null) {
						curr.l = new Node(lengths.get(i)); 
						
						chart.put(curr.l.pairs.getCharacter(), "test");
					} else {
						curr.r = new Node(lengths.get(i)); 
						
						chart.put(curr.r.pairs.getCharacter(), "test");
					}
				} else if (curr.l == null) {
					curr.l = new Node(); 
					curr = curr.l; 
					
				} else if (!curr.l.isFull() && !curr.l.isLeaf()) {
					
					curr = curr.l;
					
				} else if (curr.r == null) {
					curr.r = new Node();
					curr = curr.r;
					
				} else {
					curr = curr.r;
					
				} 
			}
		}
	}
	
	public Node getRoot() {
		return rootNode;
	}
	
	public HashMap<Character, String> getStrings() {
		return chart;
	}
	
	public void printLeafNodes(Node node) { //works
		if (node == null) {
			return;
		} 
		if (node.l == null && node.r == null) {
			
		}
		printLeafNodes(node.l); printLeafNodes(node.r);
	}

		
}
