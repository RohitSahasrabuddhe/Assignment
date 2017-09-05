package com.usc.assignment1;

import java.util.Scanner;

public class BFSNQueen {
	static int size;
	
	public static void main(String args[]) {
		System.out.println("N Queen Problem \nEnter board size");
		Scanner scan = new Scanner(System.in);
		size = scan.nextInt();
		int board[][] = new int[size][size];
		
		NewQueue frontier = new NewQueue(size);
		
		
		for(int i = 0 ; i < size ; i++) {
			for( int j = 0 ; j < size ; j++) {
				frontier.pushAtEnd(i, j, board);
			}
		}
		/*for(int i = 0 ; i < size ; i++) {
			for( int j = 0 ; j < size ; j++) {
				int pos[] = frontier.popPosition();
				int boardState[][] = frontier.popBoardState();
				System.out.print("\nSelected Position: ("+pos[0]+","+pos[1]+")");
				printBoard(boardState);
			}
		}*/
		BFS(frontier);
		scan.close();
	}

	private static void BFS(NewQueue frontier) {
		// TODO Auto-generated method stub
		System.out.println("----------------Iteration Starts Here---------");
		if(frontier.isEmpty()) {
			System.out.println("Solution Doesn't Exists");
			System.exit(0);
		}
		
		int[] selectedNode = frontier.popPosition();
		int[][] selectedBoard = frontier.popBoardState();
		
		System.out.print("\nFrontier is\n");
		frontier.printQueue();
		System.out.print("\nSelected Position: ("+selectedNode[0]+","+selectedNode[1]+")");
		System.out.print("\nBoard is\n");
		printBoard(selectedBoard);
		
		
		if(isSafe(selectedNode , selectedBoard)) {
			System.out.println("Inside if");
			placeTheQueen(selectedBoard,selectedNode);
			
			if(getNoOfQueens(selectedBoard) == size) {
				System.out.println("Solution Exists");
				System.exit(0);
			}
			
			int[][] validPositions = getValidPositions(selectedNode,selectedBoard);
			System.out.println("\nValid Positions are\n");
			for(int i = 0 ; i < validPositions.length ; i++) {
				System.out.print("\n ("+validPositions[i][0]+","+validPositions[i][1]+")");
				System.out.print("\n");
			}
			for(int i = 0 ; i < validPositions.length; i++) {
				frontier.pushAtEnd(validPositions[i][0], validPositions[i][1],selectedBoard);
			}
			
		}
		
		BFS(frontier);
	}

	private static int[][] getValidPositions(int[] selectedNode, int[][] selectedBoard) {
		// TODO Auto-generated method stub
		int count = 0;
		
		for(int i = 0 ; i < selectedBoard.length ; i++) {
			for(int j = 0 ; j < selectedBoard.length ; j++) {
				int pos[] = { i , j };
				if(isSafe(pos,selectedBoard )) {
					count++; 
				}
			}
		}
		int[][] validPositions = new int[count][2];
		int count1 = 0 ;
		for(int i = 0 ; i < selectedBoard.length ; i++) {
			for(int j = 0 ; j < selectedBoard.length ; j++) {
				int pos[] = { i , j };
				if(isSafe(pos,selectedBoard )) {
					validPositions[count1][0] = i; 
					validPositions[count1++][1] = j;
				}
			}
		}
		return validPositions;

	}

	private static void placeTheQueen(int[][] board, int[] selectedNode) {
		// TODO Auto-generated method stub
		board[selectedNode[0]][selectedNode[1]] = 1;
		
	}

	private static boolean isSafe(int[] selectedPos, int[][] board) {
		// TODO Auto-generated method stub
		int length = board.length;
		for(int i = 0 ; i < length ; i++) {
			for(int j = 0 ; j < length ; j++) {
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

	private static int getNoOfQueens(int[][] board) {
		// TODO Auto-generated method stub
		int length = board.length;
		int count = 0;
		for(int i = 0 ; i < length ; i++) {
			for(int j = 0 ; j < length ; j++) {
				if(board[i][j] == 1) {
					count++;
				}
			}
		}
		return count;
	}

	private static void printBoard(int[][] board) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < board.length ; i++) {
			for(int j = 0 ; j < board.length ; j ++) {
				System.out.print(board[i][j]+"\t");
			}
			System.out.print("\n");
		}
		
	}
}
