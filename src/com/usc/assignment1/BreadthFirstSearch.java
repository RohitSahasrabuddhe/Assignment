package com.usc.assignment1;

public class BreadthFirstSearch {
	public static void main(String args[]) {
		int n = 4;
		System.out.print("N Queen Proplem using BFS where n is " + n);
		
		int node[][] = new int[n][n];
		
		if(isGoalState(node,n) ) {
			System.out.print("\nSolution Found");
			System.exit(0);
		}
		
		FIFO frontier = new FIFO(n);
		
		frontier.push(node);
		
		FIFO explored = new FIFO(n);
		int[][] currentNode;
		int counter = 0 ;
		do {
			/*System.out.println("\n-------------Displaying Frontier-------\n");
			frontier.printQueue();
			System.out.println("\n-------------Displaying Explored Set-------\n");
			explored.printQueue();*/
			System.out.println("----------------Iteration " + counter++ + "---------");
			System.out.print("\nCurrent Node\n");
			currentNode = frontier.pop();
			displayState(currentNode);
			explored.push(currentNode);
			
			System.out.println("---------------1--------");
			for(int i = 0 ; i < currentNode.length ; i++) {
				for(int j = 0 ; j < currentNode.length ; j++) {
					if(isSafe(i,j,currentNode)) {
						int[][] childState = getChildState(i,j,currentNode);
						//System.out.println("-Child State is\n");
						//displayState(childState);
						
						if(!frontier.findState(childState) && !explored.findState(childState)) {
							System.out.println("---------------3--------");
							if(isGoalState(childState,childState.length) ) {
								System.out.print("\nSolution Found\n");
								displayState(childState);
								System.exit(0);
							}
							
							/*System.out.print("\nCurrent Node\n");
							displayState(currentNode);*/
							//System.out.print("\nPushing Child Node\n");
							//displayState(childState);
							frontier.push(childState);
						}
					}
				}
			}
			
		}while(!frontier.isEmpty());
		
	}

	private static int[][] getChildState(int xPos, int yPos, int[][] currentNode) {
		// TODO Auto-generated method stub
		int length = currentNode.length;
		int childState[][] = new int[length][length];
		for(int i = 0 ; i < length ; i++) {
			for(int j = 0 ; j < length ; j++) {
				if(i == xPos && j == yPos)
					childState[i][j] = 1;
				else
					childState[i][j] = currentNode[i][j];
			}
		}
			
		return childState;
	}

	private static boolean isSafe(int xPos, int yPos, int[][] currentNode) {
		// TODO Auto-generated method stub
		int length = currentNode.length;
		for(int i = 0 ; i < length ; i++) {
			for(int j = 0 ; j < length ; j++) {
				if(currentNode[i][j] == 1) {
					int a = (xPos - i)*(xPos - i) - (yPos - j)*(yPos - j);
					if(xPos == i || yPos == j || a ==0 ) {
						return false;
					}
				}
			}
		}
		return true;
	}


	private static void displayState(int[][] currentNode) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < currentNode.length ; i ++) {
			for(int j = 0 ; j < currentNode.length ; j++) {
				System.out.print(currentNode[i][j]+"\t");
				}
			System.out.print("\n");
		}
		
	}

	private static boolean isGoalState(int[][] node , int noOfQueens) {
		// TODO Auto-generated method stub
		int count = 0 ;
		for(int i = 0 ; i < node.length ; i++) {
			for(int j = 0 ; j < node.length ; j++) {
				if(node[i][j] == 1) {
					count++;
				}
			}
		}
		if(noOfQueens == count) {
			return true;
		}
		return false;
	}

}
