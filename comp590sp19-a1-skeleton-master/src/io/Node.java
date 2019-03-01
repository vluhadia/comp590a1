package io;
//simple node class for tree
public class Node { //decoder
	Node l = null;
	
	Node r = null;
	
	Pairs pairs; //symbol

	public Object p;

	
	public Node() {
		pairs = null;
	}
	
	public Node (Pairs pair) {
		pairs = pair;
	}
	
	public boolean isLeaf() {
		
		if (pairs == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public char getChar() {
		if (pairs == null) {
			return '-';
		} else {
			return pairs.getCharacter();
		}
	}
	
	public boolean isFull() {
		
		if(isLeaf()) {
			return true;
		}
		if (l == null || r == null) {return false;}
		if (l.isLeaf() && r.isLeaf()) {return true;}
		
		return (l.isFull() && r.isFull());	
	}
}
