package com.usc.assignment1;

public class Queue {
	
	int front, rear,size;
	int queue[][];
	
	Queue(int length){
		queue = new int[length][2];
		front = 0;
		rear = 0;
		size = 0;
	}
	

	public void pushAtStart(int xPos, int yPos) {
		System.out.println("Pushing : ("+xPos+","+yPos+")");
		this.queue[rear][0] = xPos;
		this.queue[rear][1] = yPos;
		this.rear += 1;
		this.size += 1;
		
	}
	public void pushAtEnd(int xPos, int yPos) {
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
	public int[] pop() {
		int pos[] = new int[2];
		pos[0] =  this.queue[0][0];
		pos[1] = this.queue[0][1];
		for(int i = 1 ; i < rear ; i++ ) {
			this.queue[i-1] = this.queue[i];
		}
		this.rear -= 1;
		this.size -= 1;
		return pos;
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

