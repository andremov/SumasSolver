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
    
    private ArrayList<Combination> combos;
    boolean isSolved;
    boolean isAnswer;
    int number;
	int currentCombo;
    
    public Box() {
        this.combos = new ArrayList<>();
        this.number = 0;
        this.isSolved = false;
        this.isAnswer = false;
		this.currentCombo = 0;
    }
    public Box(int number, boolean solved, boolean answer) {
        this.number = number;
        this.isSolved = solved;
        this.isAnswer = answer;
    }
    
    public boolean hasNumber() {
        return isSolved || !combos.isEmpty();
    }
    
    public int getCurrentNumber() {
        return isSolved? number : combos.isEmpty()? 0 : combos.get(0).getNumber(currentCombo);
    }
    
	public void addCombos(ArrayList<Combination> inCombos) {
		if (combos.isEmpty()) {
			this.replaceCombos(inCombos);
		} else {
			ArrayList<Combination> newCombos = new ArrayList<>();
			newCombos.addAll(combos);
			newCombos.addAll(inCombos);

			for (int i = 0; i < newCombos.size(); i++) {
				boolean intersects = false;
				for (int j = 0; j < newCombos.size(); j++) {
					if (i != j) {
						if (newCombos.get(i).intersects(newCombos.get(j))) {
							intersects = true;
						}
					}
				}
				if (!intersects) {
					newCombos.remove(i);
					i--;
				}
			}

			this.combos = newCombos;
		}
	}
	
	public void clearCombos() {
		this.combos.clear();
	}
    
	public void replaceCombos(ArrayList<Combination> combos) {
		this.combos = combos;
	}
	
	public ArrayList<Combination> getCombos() {
		return this.combos;
	}
	
    public Combination advanceCombinations() {
        currentCombo++;
        if (currentCombo>1) {
            Combination combo = combos.get(0);
			combos.remove(0);
			currentCombo = 0;
			return combo;
		}
        return null;
    }
    
    
}
