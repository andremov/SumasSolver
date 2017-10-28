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
public class Combination {
    
    private int[] nums;

    public Combination(int[] values) {
        this.nums = values;
    }

	public static ArrayList<Combination> generateAll(int sum) {
		int[] nums = new int[4];
		ArrayList<Combination> combinations = new ArrayList<>();
		
		for (int i = 0; i < nums.length; i++) {
			nums[i] = possibleNextNum(sum,nums);
		}
		combinations.add(new Combination(nums));
		
		return combinations;
	}
	
	private static int possibleNextNum(int sum, int[] nums) {
		String s = "";
		for (int i = 0; i < nums.length; i++) {
			s += nums[i]+" ";
		}
		System.out.println(sum+": "+s);
		int numberSolved = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				numberSolved++;
			}
		}
		System.out.println("Current solved numbers: "+numberSolved);
		for (int i = 0; i < numberSolved; i++) {
			sum -= nums[i];
		}
		System.out.println("Remaining sum: "+sum);
		int largest = 16;
		while (largest > sum) {
			largest--;
		}
		System.out.println("Process1: "+largest);
		if (largest == sum && numberSolved < 3) {
			largest--;
		}
		System.out.println("Process2: "+largest);
		boolean validNum = false;
		while (!validNum) {
			validNum = true;
			for (int i = 0; i < numberSolved; i++) {
				if (largest == nums[i]) {
					validNum = false;
					largest--;
				}
			}
		}
		System.out.println("Process3: "+largest);
		return largest;
	}
	
	public boolean intersects(Combination other) {
		return (this.nums[0] == this.getNumber(0) || 
				this.nums[1] == this.getNumber(0) || 
				this.nums[0] == this.getNumber(1) || 
				this.nums[1] == this.getNumber(1));
	}
	
//	public Combination invert() {
//		return new Combination(nums[1],nums[0]);
//	}
	
	public int getNumber(int index) {
		return nums[index];
	}
	
	public boolean contains(int n) {
		return (this.nums[0] == n || this.nums[1] == n);
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i < nums.length; i++) {
			s += nums[i]+ " ";
		}
		return s;
	}
}
