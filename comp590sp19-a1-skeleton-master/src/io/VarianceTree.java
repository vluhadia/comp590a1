package io;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class VarianceTree { //used for encoder
	//worked with tony ma
	
	private NodeE rootNode = new NodeE();
	private ArrayList<NodeE> allt = new ArrayList<NodeE>();
	
	
	ArrayList<NodeE> leafNodes = new ArrayList<NodeE>(); 
	
	
	
	public VarianceTree(ArrayList<PairsEncoder> lengths) { //?
		System.out.println("lengths size: " + lengths.size());
		

		
		
		while (allt.size() > 1) {
			
			NodeE parent = new NodeE(allt.get(0), allt.get(1));
			parent.calcFreq();
			parent.calcHeight();			
			
			parent.setHeight(parent.getHeight() + 1); //fucked up
			
			
			System.out.println("" + parent.getFreq()); 
			System.out.println(parent.getHeight());
			
			allt.remove(1); allt.remove(0); allt.add(parent);
			
			Collections.sort(allt);
		}
		
		allt.get(0).setLengths(0);
		
		rootNode = allt.get(0);		
	}
	
	public NodeE getRoot() {
		return rootNode;
	}
	
	public ArrayList<NodeE> getLeafArray() {
		return leafNodes;
	}

	public void printLeafNodes(NodeE node) { //debugging to print out the tree
		if (node == null) {
			return;
		}
		if (node.leftnode == null && node.right == null) { leafNodes.add(node);}
		printLeafNodes(node.leftnode); 
		printLeafNodes(node.right);
	}
}
