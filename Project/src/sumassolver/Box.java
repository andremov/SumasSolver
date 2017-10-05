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
    
    public Box() {
        combos = new ArrayList<>();
        number = 0;
        isSolved = false;
        isAnswer = false;
    }
    public Box(int number, boolean solved, boolean answer) {
        number = number;
        isSolved = solved;
        isAnswer = answer;
    }
    
    public boolean hasNumber() {
        return isSolved || !combos.isEmpty();
    }
    
    public int getCurrentNumber() {
        return isSolved? number : combos.get(0).getChosenNum();
    }
    
    public void advanceCombinations() {
        combos.get(0).nextChosen();
        if (!combos.get(0).isValid())
            combos.remove(0);
        
    }
    
    
}
