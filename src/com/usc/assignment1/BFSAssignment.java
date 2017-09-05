package com.usc.assignment1;

import java.util.Scanner;

public class BFSAssignment {
	
static int lizardCount, size;
static int board[][];
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter value of n:");
		size = scan.nextInt();
		board = new int[size][size];
		
		lizardCount = 0;
		
	
		// Step 1 : Initialize the frontier
		Queue frontier = new Queue(size*size*size*size);
		for(int i = 0 ; i < size ; i++) {
			for( int j = 0 ; j < size ; j++) {
				frontier.pushAtStart(i, j);
			}
		}
		//frontier.push(0, 0);
		
		/*for(int i = 0 ; i < size ; i++) {
			for( int j = 0 ; j < size ; j++) {
				pos = frontier.pop();
				System.out.print("(" + pos[0]+ "," + pos[1]+")\t");
			}
			System.out.print("\n");
		}*/
		
	/*	int selectedPos[], count=0;
		int[][] validPositions;*/
		//frontier.pushAtStart(0,0);
		BFS(frontier);
		/*do {
			System.out.println("\n----------------------Iteration "+ count + "------------------------------");
			selectedPos = frontier.pop();
			System.out.print("Selected Position is : ("+selectedPos[0]+","+selectedPos[1]+")\n");
			System.out.print("Frontier is : \n");
			frontier.printQueue();
			if(isValid(selectedPos)) {
				System.out.println("Valid position found");
				if(lizardCount == size-1) {
					System.out.println("Solution Found");
					System.exit(0);
				}
				addToBoard(selectedPos);
				printBoard();
				validPositions = getValidPositions(selectedPos);
				
				for(int i = 0 ; i < validPositions.length; i++) {
					System.out.print("("+validPositions[i][0]+","+validPositions[i][1]+")\n");
				}
				for(int i = 0 ; i < validPositions.length; i++) {
					//if(!frontier.searchQueue(validPositions[i][0], validPositions[i][1])) {
						frontier.push(validPositions[i][0], validPositions[i][1]);
					//}
				}
			}
			
			
			count++;
		}while(!frontier.isEmpty());*/
		
		scan.close();
	}
	
	public static void BFS(Queue frontier) {
		if(frontier.isEmpty()) {
			System.out.println("Solution does not exists");
			System.exit(0);
		}
		System.out.println("\n----------------------Iteration ------------------------------");
		int[] selectedPos = frontier.pop();
		System.out.print("Selected Position is : ("+selectedPos[0]+","+selectedPos[1]+")\n");
		System.out.print("Frontier is : \n");
		frontier.printQueue();
		if(isValid(selectedPos)) {
			System.out.println("Valid position found");
			if(lizardCount == size-1) {
				System.out.println("Solution Found");
				System.exit(0);
			}
			addToBoard(selectedPos);
			printBoard();
			int[][] validPositions = getValidPositions(selectedPos);
			
			for(int i = 0 ; i < validPositions.length; i++) {
				System.out.print("("+validPositions[i][0]+","+validPositions[i][1]+")\n");
			}
			for(int i = 0 ; i < validPositions.length; i++) {
				//if(!frontier.searchQueue(validPositions[i][0], validPositions[i][1])) {
					frontier.pushAtStart(validPositions[i][0], validPositions[i][1]);
				//}
			}
			BFS(frontier);
			System.out.println("\nRemoving position");
			printBoard();
			removeFromBoard(selectedPos);
			printBoard();
		}	}

	private static void removeFromBoard(int[] selectedPos) {
		// TODO Auto-generated method stub
		board[selectedPos[0]][selectedPos[1]] = 0;
	}

	private static void printBoard() {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < board.length ; i++) {
			for(int j = 0 ; j < board.length ; j ++) {
				System.out.print(board[i][j]+"\t");
			}
			System.out.print("\n");
		}
		
	}

	private static void addToBoard(int[] selectedPos) {
		// TODO Auto-generated method stub
		lizardCount++;
		board[selectedPos[0]][selectedPos[1]] = 1;		
		
	}

	private static int[][] getValidPositions(int[] selectedPos) {
		// TODO Auto-generated method stub
		
		int count = 0;
		
		for(int i = 0 ; i < board.length ; i++) {
			for(int j = 0 ; j < board.length ; j++) {
				int pos[] = { i , j };
				if(isValid(pos)) {
					count++; 
				}
			}
		}
		int[][] validPositions = new int[count][2];
		int count1 = 0 ;
		for(int i = 0 ; i < board.length ; i++) {
			for(int j = 0 ; j < board.length ; j++) {
				int pos[] = { i , j };
				if(isValid(pos)) {
					validPositions[count1][0] = i; 
					validPositions[count1++][1] = j;
				}
			}
		}
		return validPositions;
	}

	private static boolean isValid(int[] selectedPos) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < board.length ; i++) {
			for(int j = 0 ; j < board.length ; j++) {
				if(board[i][j] == 1) {
					int a = (selectedPos[0] - i)*(selectedPos[0] - i) - (selectedPos[1] - j)*(selectedPos[1] - j);
					if(selectedPos[0] == i || selectedPos[1] == j || a ==0 ) {
						return false;
					}
				}
			}
		}
		return true;
	}

}
