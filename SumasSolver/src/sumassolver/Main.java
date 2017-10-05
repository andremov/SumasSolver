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
public abstract class Main {
    
    private static Box[][] boxes;
    private static Window window;
    
    public static void init() {
        window = new Window();
        boxes = new Box[5][5];
    }
    
    public static void startSolve(int[][] startPosition) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int current = startPosition[i][j];
                if (current != 0) {
                    boxes[i][j] = new Box();
                } else {
                    boxes[i][j] = new Box(current, true, (i == 4 || j == 4));
                }
            }
        }
        
        solve();
    }
    
    private static void solve() {
        if (!finished()) {
            if (searchOptimumColumn() != -1) {
                
            } else if (searchOptimumRow() != -1) {
                
            }
            
            
            sendValues();
            solve();
        }
    }
    
    private static ArrayList<Combination> createCombinations(int sum) {
        ArrayList<Combination> combos = new ArrayList<>();
        
    }
    
    private static void sendValues() {
        int[][] values = new int[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                values[i][j] = boxes[i][j].getCurrentNumber();
        
        window.receiveValues(values);
    }
    
    private static boolean finished() {
        return boxesFilled() && correctSums();
    }
    
    private static boolean boxesFilled () {
        boolean filled = true;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                filled = boxes[i][j].hasNumber()? filled : false;
        
        return filled;
    }
    
    private static boolean correctSums() {
        boolean foundMistake = false;
        
        int[] sumsColumn = {0, 0, 0, 0};
        int[] sumsRow = {0, 0, 0, 0};
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sumsColumn[i] = sumsColumn[i] + boxes[i][j].getCurrentNumber();
                sumsRow[j] = sumsRow[j] + boxes[i][j].getCurrentNumber();
            }
        }
        
        for (int check = 0; check < 4; check++) {
            foundMistake = (sumsColumn[check] == boxes[check][4].getCurrentNumber())? foundMistake : true;
            foundMistake = (sumsRow[check] == boxes[4][check].getCurrentNumber())? foundMistake : true;
        }
        
        return !foundMistake;
    }
    
    private static int numbersColumn (int column) {
        int numbers = 0;
        for (int i = 0; i < 4; i++)
            if (boxes[i][column].hasNumber())
                numbers++;
            
        return numbers;
    }
    
    private static int numbersRow (int row) {
        int numbers = 0;
        for (int i = 0; i < 4; i++)
            if (boxes[row][i].hasNumber())
                numbers++;
            
        return numbers;
    }
    
    private static int searchOptimumColumn() {
        int index = 0;
        boolean foundOne = false;
        while (index < 5 && !foundOne ) {
            foundOne = numbersColumn(index) == 2;
            index++;
        }
        
        if (foundOne) {
            return index-1;
        } else {
            return -1;
        }
    }
    
    private static int searchOptimumRow() {
        int index = 0;
        boolean foundOne = false;
        while (index < 5 && !foundOne ) {
            foundOne = numbersRow(index) == 2;
            index++;
        }
        
        if (foundOne) {
            return index-1;
        } else {
            return -1;
        }
    }
}
