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
	private static boolean[] occupied;
//    private static ArrayList<Entry> history;
	private static Silk web;
	private static int doSolveNext;
	
    public static void init() {
//		history = new ArrayList<>();
        boxes = new Box[5][5];
		occupied = new boolean[16];
//		for (int i = 0; i < occupied.length; i++) {
//			occupied[i] = false;
//		}
//		web = new Silk();
//		doSolveNext = 1;

        window = new Window();
    }
	
	public static void receiveValues(int[][] startPosition) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int current = startPosition[i][j];
                if (current == 0) {
                    boxes[i][j] = new Box();
                } else {
                    boxes[i][j] = new Box(current, i == 4 || j == 4);
					if (!boxes[i][j].isAnswer) {
						occupied[current-1] = true;
					}
                }
            }
        }
		
	}
	
	public static void trySolve() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int sumaX = boxes[i][4].number;
				for (int k = 0; k < 4; k++) {
					sumaX -= boxes[i][k].number;
				}
				int sumaY = boxes[4][j].number;
				for (int k = 0; k < 4; k++) {
					sumaY -= boxes[k][j].number;
				}
				boxes[i][j].setPossibleNumbers(possibleNumbers(sumaX));
				boxes[i][j].setPossibleNumbers(possibleNumbers(sumaY));
				if (boxes[i][j].isSolved) {
					occupied[boxes[i][j].number-1] = true;
				}
			}
		}
	}
	
	public static ArrayList<Integer> possibleNumbers(int total) {
		// GENERATE
		ArrayList<int[]> list = gen(total,new int[4]);
		
		
		// REMOVE DUPLICATES
		for (int i = list.size()-1; i >= 0; i--) {
			int[] combo = list.get(i);
			boolean duplicate = false;
			for (int j = 0; j < combo.length; j++) {
				for (int k = j+1; k < combo.length; k++) {
					if (combo[j] == combo[k]) {
						duplicate = true;
					}
				}
			}
			if (duplicate) {
				list.remove(i);
			}
		}
		
		
		// APPLY CAP
		int top = 16;
		for (int i = list.size()-1; i >= 0; i--) {
			int[] combo = list.get(i);
			boolean overTop = false;
			for (int j = 0; j < combo.length; j++) {
				if (combo[j] > top) {
					overTop = true;
				}
			}
			if (overTop) {
				list.remove(i);
			}
		}
		
		// APPLY OCCUPIED
		for (int i = list.size()-1; i >= 0; i--) {
			int[] combo = list.get(i);
			boolean hasOccupied = false;
			for (int j = 0; j < combo.length; j++) {
				if (!occupied[combo[j]]) {
					hasOccupied = true;
				}
			}
			if (hasOccupied) {
				list.remove(i);
			}
		}
		
		
		// SUMMARY
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			int[] combo = list.get(i);
			for (int j = 0; j < combo.length; j++) {
				numbers.add(combo[j]);
			}
		}
		for (int i = 0; i < numbers.size(); i++) {
			for (int j = numbers.size()-1; j > i; j--) {
				if (numbers.get(i) == numbers.get(j)) {
					numbers.remove(j);
				}
			}
		}
		
		return numbers;
	}
	
	public static ArrayList<int[]> gen(int total, int[] digs) {
		ArrayList<int[]> al = new ArrayList<>();
		
		int[] wrapDigs = new int[4];
		for (int i = 0; i < 4; i++) {
			wrapDigs[i] = digs[i];
		}
		
		if (digs[2] != 0 && digs[3] == 0) {
			wrapDigs[3] = total;
			al.add(wrapDigs);
		}
		
		if (digs[1] != 0 && digs[2] == 0) {
			for (int i = 1; i < total; i++) {
				wrapDigs[2] = i;
				al.addAll(gen(total-i,wrapDigs));
			}
		}
		
		if (digs[0] != 0 && digs[1] == 0) {
			for (int i = 1; i < total; i++) {
				wrapDigs[1] = i;
				al.addAll(gen(total-i,wrapDigs));
			}
		}
		
		if (digs[0] == 0) {
			for (int i = 1; i < total; i++) {
				wrapDigs[0] = i;
				al.addAll(gen(total-i,wrapDigs));
			}
		}
		
		return al;
	}
//    
//    public static void startSolve() {
//		System.out.println("Starting...");
//		doSolveNext = 1;
//        web.startThread();
//    }
//	
//	public static void doStep() {
//		System.out.println("**************************");
//		if (doSolveNext == 1) {
//			System.out.println("Trying step...");
////			solve();
//		} else if (doSolveNext != 0) {
//			System.out.println("Stepping back...");
//			stepBack(doSolveNext == 2);
//		} else {
//			System.out.println("Done!");
//			web.stopThread();
//			window.solveBtn.setEnabled(true);
//			window.resetBtn.setEnabled(true);
//		}
//        
//		sendValues();
//	}
//    
////    public static void solve() {
////		doSolveNext = 0;
////        if (!finished()) {
////			doSolveNext = 1;
////            if (searchOptimumColumn() != -1) {
////				
////                int column = searchOptimumColumn();
////				System.out.println("Row: "+(column+1)); // rows and columns are visually inverted lol
////				
////				int currentSum = 0;
////				for (int i = 0; i < 4; i++)
////					currentSum += boxes[i][column].getCurrentNumber();
////				
////				ArrayList<Combination> combos = createCombinations(boxes[4][column].getCurrentNumber()-currentSum);
////				System.out.print("All combinations: ");
////				for (int i = 0; i < combos.size(); i++) {
////					System.out.print(combos.get(i)+", ");
////				}
////				System.out.println("");
////				
////				combos = removeOccupiedCombos(combos);
////				System.out.print("Available combinations: ");
////				for (int i = 0; i < combos.size(); i++) {
////					System.out.print(combos.get(i)+", ");
////				}
////				System.out.println("");
////				
////				int[] places = new int[2];
////				int c = 0;
////				for (int i = 0; i < 4; i++)
////					if (!boxes[i][column].hasNumber()) {
////						places[c] = i;
////						c++;
////					}
////				System.out.println("Position 1: ("+places[0]+","+column+")");
////				System.out.println("Position 2: ("+places[1]+","+column+")");
////				
////				Box box1 = boxes[places[0]][column];
////				box1.addCombos(combos);
////				combos = box1.getCombos();
////				
////				Box box2 = boxes[places[1]][column];
////				box2.addCombos(combos);
////				combos = box2.getCombos();
////				
////				box1.replaceCombos(combos);
////				System.out.print("Final combinations: ");
////				for (int i = 0; i < combos.size(); i++) {
////					System.out.print(combos.get(i)+", ");
////				}
////				System.out.println("");
////				
////				ArrayList<Combination> altCombos = new ArrayList<>();
////				for (int i = 0; i < combos.size(); i++) {
////					altCombos.add(combos.get(i).invert());
////				}
////				box2.replaceCombos(altCombos);
////				System.out.print("Final alternate combinations: ");
////				for (int i = 0; i < altCombos.size(); i++) {
////					System.out.print(altCombos.get(i)+", ");
////				}
////				System.out.println("");
////				
////				if (!box1.hasNumber() || !box2.hasNumber()) {
//////					stepBack(false);
////					doSolveNext = 3;
////				} else {
////					occupyCombination(combos.get(0));
////					Entry newEntry = new Entry(new Location(places[0],column),new Location(places[1],column),true);
////					history.add(newEntry);
////					System.out.println("Adding to history as step "+history.size());
////				}
////            } else if (searchOptimumRow() != -1) {
////				
////                int row = searchOptimumRow();
////				System.out.println("Column: "+(row+1)); // rows and columns are visually inverted lol
////				
////				int currentSum = 0;
////				for (int i = 0; i < 4; i++)
////					currentSum += boxes[row][i].getCurrentNumber();
////				
////				ArrayList<Combination> combos = createCombinations(boxes[row][4].getCurrentNumber()-currentSum);
////				System.out.print("All combinations: ");
////				for (int i = 0; i < combos.size(); i++) {
////					System.out.print(combos.get(i)+", ");
////				}
////				System.out.println("");
////				
////				combos = removeOccupiedCombos(combos);
////				System.out.print("Available combinations: ");
////				for (int i = 0; i < combos.size(); i++) {
////					System.out.print(combos.get(i)+", ");
////				}
////				System.out.println("");
////				
////				int[] places = new int[2];
////				int c = 0;
////				for (int i = 0; i < 4; i++)
////					if (!boxes[row][i].hasNumber()) {
////						places[c] = i;
////						c++;
////					}
////				System.out.println("Position 1: ("+row+","+places[0]+")");
////				System.out.println("Position 2: ("+row+","+places[1]+")");
////				
////				Box box1 = boxes[row][places[0]];
////				box1.addCombos(combos);
////				combos = box1.getCombos();
////				
////				Box box2 = boxes[row][places[1]];
////				box2.addCombos(combos);
////				combos = box2.getCombos();
////				
////				System.out.print("Final combinations: ");
////				for (int i = 0; i < combos.size(); i++) {
////					System.out.print(combos.get(i)+", ");
////				}
////				System.out.println("");
////				
////				box1.replaceCombos(combos);
////				
////				ArrayList<Combination> altCombos = new ArrayList<>();
////				for (int i = 0; i < combos.size(); i++) {
////					altCombos.add(combos.get(i).invert());
////				}
////				box2.replaceCombos(altCombos);
////				System.out.print("Final alternate combinations: ");
////				for (int i = 0; i < altCombos.size(); i++) {
////					System.out.print(altCombos.get(i)+", ");
////				}
////				System.out.println("");
////				
////				if (!box1.hasNumber() || !box2.hasNumber()) {
//////					stepBack(true);
////					doSolveNext = 2;
////				} else {
////					occupyCombination(combos.get(0));
////					Entry newEntry = new Entry(new Location(row, places[0]), new Location(row, places[1]), false);
////					history.add(newEntry);
////					System.out.println("Adding to history as step "+history.size());
////				}
////            } else {
////				stepBack(history.get(history.size()-1).isColumn);
////			}
////            
////		}
////    }
//	
//	private static void occupyCombination(Combination c) {
//		occupied[c.getNumber(0)-1] = true;
//		occupied[c.getNumber(1)-1] = true;
//	}
//	
//	private static void unoccupyCombination(Combination c) {
//		occupied[c.getNumber(0)-1] = false;
//		occupied[c.getNumber(1)-1] = false;
//	}
//	
//	private static void stepBack(boolean nextCol) {
//		doSolveNext = 1;
//		System.out.println("History steps left: "+(history.size()));
//		System.out.println("Action: Step back");
//		System.out.println("Looking for "+ (nextCol? "row" : "column") );
//		Entry e = history.get(history.size()-1);
//		System.out.println("History is "+ (e.isColumn? "row" : "column") );
//		System.out.println((e.isColumn? "Row" : "Column") + ": " + (e.isColumn? e.pos1.y+1 :  e.pos1.x+1 ));
//		
//		
//		System.out.println("Position 1: "+e.pos1);
//		System.out.println("Position 2: "+e.pos2);
//		
//		Box b1 = boxes[e.pos1.x][e.pos1.y];
//		Box b2 = boxes[e.pos2.x][e.pos2.y];
//		
////		if (e.isColumn == nextCol) {
////			System.out.println("Status: Correct.");
//			b2.advanceCombinations();
//			Combination c = b1.advanceCombinations();
//			if (c != null) {
//				unoccupyCombination(c);
//				System.out.println("Result: Removed combination.");
//			} else {
//				System.out.println("Result: Swapped number in combination.");
//				
//				System.out.print("Current combinations: ");
//				for (int i = 0; i < b1.getCombos().size(); i++) {
//					System.out.print(b1.getCombos().get(i)+", ");
//				}
//				System.out.println("");
//			}
//			if (!b1.hasNumber()) {
//				System.out.println("Result: Removed history entry.");
//				history.remove(history.size()-1);
//				doSolveNext = (nextCol? 2 : 3);
//			} else {
//				occupyCombination(b1.getCombos().get(0));
//			}
////		} else {
////			System.out.println("Status: Incorrect.");
////			Combination c = b1.getCombos().get(0);
////			b2.clearCombos();
////			b1.clearCombos();
////			
////			unoccupyCombination(c);
////			System.out.println("Result: Removed combination.");
////			
////
////			history.remove(history.size()-1);
////			System.out.println("Result: Removed history entry.");
////			doSolveNext = (nextCol? 2 : 3);
////			
////		}
//	}
//    
//	private static ArrayList<Combination> removeOccupiedCombos(ArrayList<Combination> combos) {
//		for (int i = 0; i < occupied.length; i++)
//			if (occupied[i]) {
//				for (int j = 0; j < combos.size(); j++) {
//					if (combos.get(j).contains(i+1)) {
//						combos.remove(j);
//						j--;
//					}
//				}
//			}
//		
//		return combos;
//	}
//	
//    private static ArrayList<Combination> createCombinations(int numeroOriginal) {
//        ArrayList<Combination> combos = new ArrayList<>();
//		
//		int numeroGrande = Integer.min(15, numeroOriginal-1);
//		int numeroPequeño = numeroOriginal-numeroGrande;
//		boolean validCombo = (numeroPequeño < numeroGrande) && 
//				(numeroPequeño < 17) && (numeroPequeño > 0) && 
//				(numeroGrande < 17) && (numeroGrande > 0);
//		
//		while (validCombo) {
////			combos.add(new Combination(numeroGrande, numeroPequeño));
////			numeroPequeño++;
////			numeroGrande--;
////			validCombo = (numeroPequeño < numeroGrande) && 
////				(numeroPequeño < 17) && (numeroPequeño > 0) && 
////				(numeroGrande < 17) && (numeroGrande > 0);
//		}
//		
//		return combos;
//    }
//    
//    private static void sendValues() {
//        int[][] values = new int[4][4];
//        for (int i = 0; i < 4; i++)
//            for (int j = 0; j < 4; j++)
//                values[i][j] = boxes[i][j].getCurrentNumber();
//        
//        window.receiveValues(values);
//    }
//    
//    private static boolean finished() {
//        return boxesFilled() && correctSums();
//    }
//    
//    private static boolean boxesFilled () {
//        boolean filled = true;
//        for (int i = 0; i < 5; i++)
//            for (int j = 0; j < 5; j++)
//                filled = boxes[i][j].hasNumber()? filled : false;
//        System.out.println("Fill: "+filled);
//        return filled;
//    }
//    
//    private static boolean correctSums() {
//        boolean foundMistake = false;
//        
//        int[] sumsColumn = {0, 0, 0, 0};
//        int[] sumsRow = {0, 0, 0, 0};
//        
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                sumsColumn[i] = sumsColumn[i] + boxes[i][j].getCurrentNumber();
//                sumsRow[j] = sumsRow[j] + boxes[i][j].getCurrentNumber();
//            }
//        }
//        
//        for (int check = 0; check < 4; check++) {
//            foundMistake = (sumsColumn[check] == boxes[check][4].getCurrentNumber())? foundMistake : true;
//            foundMistake = (sumsRow[check] == boxes[4][check].getCurrentNumber())? foundMistake : true;
//        }
//        System.out.println("Sums: "+!foundMistake);
//        
//        return !foundMistake;
//    }
//    
//    private static int numbersColumn (int column) {
//        int numbers = 0;
//        for (int i = 0; i < 4; i++)
//            if (boxes[i][column].hasNumber())
//                numbers++;
//            
//        return numbers;
//    }
//    
//    private static int numbersRow (int row) {
//        int numbers = 0;
//        for (int i = 0; i < 4; i++)
//            if (boxes[row][i].hasNumber())
//                numbers++;
//            
//        return numbers;
//    }
//    
//    private static int searchOptimumColumn() {
//        int index = 0;
//        boolean foundOne = false;
//        while (index < 5 && !foundOne ) {
//            foundOne = numbersColumn(index) == 2;
//            index++;
//        }
//        
//        if (foundOne) {
//            return index-1;
//        } else {
//            return -1;
//        }
//    }
//    
//    private static int searchOptimumRow() {
//        int index = 0;
//        boolean foundOne = false;
//        while (index < 5 && !foundOne ) {
//            foundOne = numbersRow(index) == 2;
//            index++;
//        }
//        
//        if (foundOne) {
//            return index-1;
//        } else {
//            return -1;
//        }
//    }
//	
//	public static class Entry {
//		Location pos1;
//		Location pos2;
//		boolean isColumn;
//
//		public Entry(Location pos1, Location pos2, boolean col) {
//			this.pos1 = pos1;
//			this.pos2 = pos2;
//			this.isColumn = col;
//		}
//		
//	}
//	
//	public static class Location {
//		int x,y;
//
//		public Location(int x, int y) {
//			this.x = x;
//			this.y = y;
//		}
//
//		public String toString() {
//			return ("("+x+","+y+")");
//		}
//	}
}
