package io;
//worked with tony ma
public class PairsEncoder implements Comparable<PairsEncoder> {
	//pairsencoder fields
	public char chr;
	public int frequency;
	
	public PairsEncoder(int f, char c) {//constructor
		chr = c;
		frequency = f;
	}
	
	public int getFreq() {//getter
		return frequency;
	}
	
	public void setChar(char c) {//setter
		chr = c;
	}
	
	public char getCharacter() {//getter
		return chr;
	}
	
	@Override
	public int compareTo(PairsEncoder p) {
		if (this.frequency == p.frequency) {return this.chr - p.chr;} else {return this.frequency - p.frequency;}
	}
}
