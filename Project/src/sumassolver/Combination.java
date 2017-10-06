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

    public Combination(int num1, int num2) {
        this.nums = new int[2];
        this.nums[0] = num1;
        this.nums[1] = num2;
    }

    public Combination(int[] nums) {
        this.nums = nums;
    }
	
	public boolean intersects(Combination other) {
		return (this.nums[0] == this.getNumber(0) || 
				this.nums[1] == this.getNumber(0) || 
				this.nums[0] == this.getNumber(1) || 
				this.nums[1] == this.getNumber(1));
	}
	
	public Combination invert() {
		return new Combination(nums[1],nums[0]);
	}
	
	public int getNumber(int index) {
		return nums[index];
	}
	
	public boolean contains(int n) {
		return (this.nums[0] == n || this.nums[1] == n);
	}
	
	public String toString() {
		return ("("+nums[0]+", "+nums[1]+")");
	}
}
