/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumassolver;

/**
 *
 * @author movillaf
 */
public class Combination {
    
    private int[] nums;
    private int chosenNum;

    public Combination(int num1, int num2) {
        this.nums = new int[2];
        this.nums[0] = num1;
        this.nums[1] = num2;
        this.chosenNum = 0;
    }

    public Combination(int[] nums) {
        this.nums = nums;
        this.chosenNum = 0;
    }
    
    public int getChosenNum() {
        return nums[chosenNum];
    }
    
    public void nextChosen() {
        chosenNum++;
    }
    
    public boolean isValid() {
        return (chosenNum < 2);
    }
}
