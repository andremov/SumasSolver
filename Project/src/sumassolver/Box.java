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
    
    private ArrayList<Integer> possibleNumbers;
    boolean isSolved;
    boolean isAnswer;
    int number;
	int currentCombo;
//	boolean[] possibilities;
    
    public Box() {
        this.number = 0;
		this.possibleNumbers = null;
//        this.isSolved = false;
//        this.isAnswer = false;
//		this.currentCombo = 0;
    }
    
    public Box(int number, boolean isAnswer) {
        this.number = number;
		this.isSolved = true;
		this.possibleNumbers = null;
        this.isAnswer = isAnswer;
//		this.currentCombo = 0;
    }
	
	public void setPossibleNumbers(ArrayList<Integer> possibleNumbers) {
		if (possibleNumbers == null) {
			this.possibleNumbers = possibleNumbers;
		} else {
			for (int i = this.possibleNumbers.size()-1; i >= 0; i--) {
				boolean found = false;
				for (int j = 0; j < possibleNumbers.size(); j++) {
					if (this.possibleNumbers.get(i) == possibleNumbers.get(j)) {
						found = true;
					}
				}
				if (!found) {
					this.possibleNumbers.remove(i);
				}
			}
		}
		if (this.possibleNumbers.size() == 1) {
			this.number = this.possibleNumbers.get(0);
			this.isSolved = true;
		}
	}
	
//    public Box(int number, boolean solved, boolean answer) {
//        this.number = number;
//        this.isSolved = solved;
//        this.isAnswer = answer;
//		possibilities = new boolean[16];
//		clearCombos();
//    }
    
//	public void addCombo(Combination c) {
//		for (int i = 0; i < 4; i++) {
//			possibilities[c.getNumber(i)-1] = true;
//		}
//	}
//	
//	public void clearCombos() {
//		for (int i = 0; i < 16; i++) {
//			possibilities[i] = false;
//		}
//	}
	
//    public boolean hasNumber() {
//        return isSolved || !combos.isEmpty();
//    }
//    
//    public int getCurrentNumber() {
//        return isSolved? number : combos.isEmpty()? 0 : combos.get(0).getNumber(currentCombo);
//    }
//    
//	public void addCombos(ArrayList<Combination> inCombos) {
//		if (combos.isEmpty()) {
//			this.replaceCombos(inCombos);
//		} else {
//			ArrayList<Combination> newCombos = new ArrayList<>();
//			newCombos.addAll(combos);
//			newCombos.addAll(inCombos);
//
//			for (int i = 0; i < newCombos.size(); i++) {
//				boolean intersects = false;
//				for (int j = 0; j < newCombos.size(); j++) {
//					if (i != j) {
//						if (newCombos.get(i).intersects(newCombos.get(j))) {
//							intersects = true;
//						}
//					}
//				}
//				if (!intersects) {
//					newCombos.remove(i);
//					i--;
//				}
//			}
//
//			this.combos = newCombos;
//		}
//	}
//	
//	public void clearCombos() {
//		this.combos.clear();
//	}
//    
//	public void replaceCombos(ArrayList<Combination> combos) {
//		this.combos = combos;
//	}
//	
//	public ArrayList<Combination> getCombos() {
//		return this.combos;
//	}
//	
//    public Combination advanceCombinations() {
//        currentCombo++;
//        if (currentCombo>1) {
//            Combination combo = combos.get(0);
//			combos.remove(0);
//			currentCombo = 0;
//			return combo;
//		}
//        return null;
//    }
    
    
}
