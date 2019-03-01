package io;

public class NodeE implements Comparable<NodeE> { //encoder
	NodeE leftnode;
	NodeE right;
	private int height;
	private int frequency;
	PairsEncoder pairs;
	private int length;
	
	public NodeE() {
		pairs = null;
		height = 0;
	}

	public NodeE (PairsEncoder pair) {
		pairs = pair;
		height = 0;
	}
	
	public NodeE (NodeE left, NodeE right) { //for encode
		this.leftnode = left;
		this.right = right;
		pairs = new PairsEncoder(left.getFreq() + right.getFreq(), '-');
	}
	
	public void setHeight(int h) {
		height = h;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getFreq() {
		if (pairs == null) {
			return frequency;
		} else {
			return pairs.getFreq();
		}
	}

	public char getChar() {
		if (pairs == null) {
			return '-';
		} else {
			return pairs.getCharacter();
		}
	}
	
	public int getLength() {
		return length;
	}
	
	public void calcHeight() {
		
		int l = 0;
		int r = 0;
		if (leftnode != null) {
			l = leftnode.height;
		}
		if (right != null) {
			r = right.height;
		}
		height = Math.max(l, r);
	}

	public void calcFreq() {
		int l = 0;
		int r = 0;
		if (leftnode != null) {
			r = right.getFreq();
		}
		if (right != null) {
			r = right.getFreq();
		}
		frequency = l + r;
	}
	
	public boolean isLeaf() {
		if (leftnode == null && right == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isFull() {
		
		if(isLeaf()) {
			return true;
		}
		if (leftnode == null || right == null) {
			return false;
		}
		if (leftnode.isLeaf() && right.isLeaf()) {
			return true;
		}
		return (leftnode.isFull() && right.isFull());	
	}

	public void setLengths(int length) {
		if (leftnode != null && right != null) {
			leftnode.setLengths(length + 1);
			right.setLengths(length + 1);
		} else if (leftnode != null) {
			leftnode.setLengths(length + 1);
		} else if (right != null) {
			right.setLengths(length + 1);
		} else {
			this.length = length;
		}
	}
	
	@Override
	public int compareTo(NodeE o) { 
		
		
		if (this.frequency == o.getFreq()) {
			if (this.height == o.getHeight()) {
				return this.getChar() - o.getChar();
			} else {
				return this.height - o.getHeight();
			}
		} else {
			return this.frequency - o.getFreq();
		}
	}
	
}
