package com.usc.assignment1;

public class NewQueue {
	
	int front, rear,size;
	int queue[][];
	int boardQueue[][][];
	
	NewQueue(int length){
		queue = new int[length*length*length*length][2];
		boardQueue = new int[length*length*length*length][length][length];
		front = 0;
		rear = 0;
		size = 0;
	}
	

	public void pushAtEnd(int xPos, int yPos, int[][] board) {
		System.out.println("Pushing : ("+xPos+","+yPos+")");
		System.out.println("\nPushing Board");
		for(int i = 0 ; i < board.length ; i++) {
			for(int j = 0 ; j < board.length ; j++) {
				System.out.print(board[i][j] + "\t");
			}System.out.print("\n");
		}
		this.queue[rear][0] = xPos;
		this.queue[rear][1] = yPos;
		this.boardQueue[rear] = board;
		this.rear += 1;
		this.size += 1;
		
	}
	public void pushAtStart(int xPos, int yPos) {
		System.out.println("Pushing : ("+xPos+","+yPos+")");
		for(int i = size ; i > 0 ; i--) {
			this.queue[i][0] = this.queue[i-1][0];
			this.queue[i][1] = this.queue[i-1][1];
		}
		this.queue[0][0] = xPos;
		this.queue[0][1] = yPos;
		this.rear += 1;
		this.size += 1;
		
	}
	public int[] popPosition() {
		int pos[] = new int[2];
		pos[0] =  this.queue[0][0];
		pos[1] = this.queue[0][1];
		for(int i = 1 ; i < rear ; i++ ) {
			this.queue[i-1] = this.queue[i];
		}
		
		return pos;
	}
	public int[][] popBoardState() {
		int[][] board = this.boardQueue[0];
		for(int i = 1 ; i < rear ; i++ ) {
			for(int j = 0 ; j < this.boardQueue[i].length ; j++) {
				for(int k = 0 ; k < this.boardQueue[i].length ; k++) {
					this.boardQueue[i-1][j][k] = this.boardQueue[i][j][k];
				}
			}
		}
		this.rear -= 1;
		this.size -= 1;
		return board;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		System.out.println("\nFront:" + this.front + "Rear : "+ this.rear);
		if(this.front == this.rear) {
			return true;
		}
		
		return false;
	}

	public void printQueue() {
		// TODO Auto-generated method stub
		System.out.println();
		for(int i = 0 ; i < this.rear ; i++) {
			System.out.print("("+this.queue[i][0] + ","+ this.queue[i][1]+") \t");
			for(int j = 0 ; j < this.boardQueue[i].length ; j++) {
				for(int k = 0 ; k < this.boardQueue[i].length ; k++) {
					System.out.print(this.boardQueue[i][j][k]+"\t");
				}
				System.out.print("\n");
			}
		}
	}

	public boolean searchQueue(int xPos, int yPos) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < this.rear ; i++) {
			if(this.queue[i][0] == xPos && this.queue[i][1] == yPos) {
				return true;
			}
		}
		
		return false;
	}
}

