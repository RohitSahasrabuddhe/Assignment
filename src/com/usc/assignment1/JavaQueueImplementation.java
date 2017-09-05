package com.usc.assignment1;

import java.util.*;

public class JavaQueueImplementation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = 7;
		System.out.print("N Queen Proplem using BFS where n is " + n);
		
		
		Node n1 = new Node(n);

		

		
		if(isGoalState(n1.node,n) ) {
			System.out.print("\nSolution Found");
			System.exit(0);
		}
		
		LinkedList<Node> frontier = new LinkedList<Node>();
		
		frontier.add(n1);
		
		LinkedList<Node> explored = new LinkedList<Node>();
		
		Node currentNode;
		int counter = 0 ;
		do {
			/*System.out.println("\n-------------Displaying Frontier-------\n");
			frontier.printQueue();
			System.out.println("\n-------------Displaying Explored Set-------\n");
			explored.printQueue();*/
			System.out.println("----------------Iteration " + counter++ + "---------");
			//System.out.print("\nCurrent Node\n");
			currentNode = frontier.remove();
			//displayState(currentNode.node);
			explored.addFirst(currentNode);
			
			//System.out.println("---------------1--------");
			for(int i = 0 ; i < currentNode.node.length ; i++) {
				for(int j = 0 ; j < currentNode.node.length ; j++) {
					if(isSafeOld(i,j,currentNode.node)) {
						int[][] childState = getChildState(i,j,currentNode.node);
						//System.out.println("-Child State is\n");
						//displayState(childState);
						
						if(!findState(frontier,childState) && !findState(explored,childState)) {
							//System.out.println("---------------3--------");
							if(isGoalState(childState,childState.length) ) {
								System.out.print("\nSolution Found\n");
								displayState(childState);
								System.exit(0);
							}
							
							/*System.out.print("\nCurrent Node\n");
							displayState(currentNode);*/
							//System.out.print("\nPushing Child Node\n");
							//displayState(childState);
							Node childNode = new Node(childState);
							frontier.addFirst(childNode);
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
	
	private static boolean isSafeOld(int xPos, int yPos, int[][] currentNode) {
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

	private static boolean isSafe(int xPos, int yPos, int[][] currentNode) {
		// TODO Auto-generated method stub
		int length = currentNode.length;
		boolean flag = true;
		for(int i = 0 ; i < length ; i++) {
			for(int j = 0 ; j < length ; j++) {
				if(currentNode[i][j] == 1) {
					int a = (xPos - i)*(xPos - i) - (yPos - j)*(yPos - j);
					
					if(xPos == i ) {		
						if(xPos < i) {
							for(int k = xPos + 1 ; k < i ; k++) {
								if(currentNode[k][j] == 2) {
									flag = false;
									break;
								}
							}
						}
						else {
							for(int k = i + 1 ; k < xPos ; k++) {
								if(currentNode[k][j] == 2) {
									flag = false;
									break;
								}
							}
						}
					}
					else if(yPos == j) {
						if(yPos < j) {
							for(int k = yPos + 1 ; k < j ; k++) {
								if(currentNode[i][k] == 2) {
									flag = false;
									break;
								}
							}
						}
						else {
							for(int k = j + 1 ; k < yPos ; k++) {
								if(currentNode[i][k] == 2) {
									flag = false;
									break;
								}
							}
						}
					
						
					}
					else if( a == 0 ) {
						flag = false;
						break;
					}
					else {
						flag = false;
					}
				}
			}
		}
		if(flag == true)
			return true;
		else
			return false;
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
	public static boolean findState(LinkedList<Node> list, int[][] childState) {
		// TODO Auto-generated method stub
		if(list.isEmpty())
			return false;
		Iterator<Node> iterator = list.listIterator();
		while(iterator.hasNext()) {
			Node tempListElement = iterator.next();
			for(int j = 0 ; j < childState.length ; j++) {
				for(int k = 0 ; k < childState.length ; k++) {
					
					if(tempListElement.node[j][k] != childState[j][k])
						return false;
				}
			}
		}
		return true;
	}

}

class Node{
	public int node[][];
	Node(int n){
		node = new int[n][n];
		}
	public Node(int[][] childState) {
		// TODO Auto-generated constructor stub
		node = new int[childState.length][childState.length];
		for(int i = 0 ; i < childState.length ; i++) {
			for(int j = 0 ; j < childState.length ; j++) {
				node[i][j] = childState[i][j];
			}
		}
	}
}
