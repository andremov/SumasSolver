/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumassolver;

import java.util.ArrayList;

/**
 *
 * @author movillaf
 */
public class Box {
    
    private ArrayList<Integer> numbers;
    boolean isSolved;
    boolean isAnswer;
	private int currentTest;
    int finalNumber;
	
    public Box(int number) {
        this.finalNumber = number;
		this.isSolved = number != 0;
		this.currentTest = 0;
		this.numbers = null;
    }
	
	public void setPossibleNumbers(ArrayList<Integer> pNum) {
		if (this.numbers == null) {
			this.numbers = pNum;
		} else {
			int i = 0;
			while (i < this.numbers.size()) {
				boolean repeated = false;
				int j = 0;
				while (j < pNum.size() && !repeated) {
					if (pNum.get(j) == this.numbers.get(i)) {
						 repeated = true;
					}
					j++;
				}
				if (!repeated) {
					this.numbers.remove(i);
				} else {
					i++;
				}
			}
		}
		if (this.numbers.size() == 1) {
			this.finalNumber = this.numbers.get(0);
			this.currentTest = 0;
			this.numbers = null;
			this.isSolved = true;
		}
	}
	
	public boolean nextTest() {
		this.currentTest++;
		if (this.currentTest == this.numbers.size()) {
			this.currentTest = 0;
			return true;
		}
		return false;
	}
	
}
