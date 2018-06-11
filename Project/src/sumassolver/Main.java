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
public abstract class Main implements Runnable {
    
    private static Box[][] boxes;
    private static Window window;
	private static Silk web;
	
    public static void init() {
        boxes = new Box[5][5];

        window = new Window();
    }
	
	public static void receiveValues(int[][] startPosition) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int current = startPosition[i][j];
				boxes[i][j] = new Box(current);
            }
        }
		
	}
	
	public static void doStep() {
		
	}
	
	public static void trySolve() {
		
	}
	
	public static ArrayList<Integer> possibleNumbers(int sum) {
		ArrayList<Integer> al = new ArrayList<>();
		
		int n1 = sum-1;
		int n2 = 1;
		
		for (int i = 0; i < sum/2; i++) {
			if (n1 != n2 && n1 <= 16 && n2 <= 16) {
				al.add(n1);
				al.add(n2);
			}
			n1--;
			n2++;
		}
		
		return al;
	}
	
}
