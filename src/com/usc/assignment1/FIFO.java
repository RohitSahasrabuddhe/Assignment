package com.usc.assignment1;

public class FIFO {
	FIFO(int length){
		this.state = new int[length*length*length*length*length*length*length][length][length];
		this.rear = 0;
		this.size = 0;
	}

	int[][][] state;
	int rear;
	int size;
	public void push(int[][] node) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < node.length ; i++) {
			for(int j = 0 ; j < node.length ;j++) {
				this.state[rear][i][j] = node[i][j];
			}
		}
		this.rear += 1;
		this.size += 1;
	}
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(rear > 0) {
			return false;
		}
		return true;
	}
	public int[][] pop() {
		// TODO Auto-generated method stub
		int length = this.state[0].length;
		int firstNode[][] = new int[length][length];
		
		for(int i = 0 ; i < length ; i ++) {
			for(int j = 0 ; j < length ; j++) {
				firstNode[i][j] = this.state[0][i][j];
				}
		}
		for(int i = 0 ; i < size ; i++) {
			this.state[i] = this.state[i+1];
		}
		this.rear -= 1;
		this.size -= 1;
		return firstNode;
		
	}
	public boolean findState(int[][] childState) {
		// TODO Auto-generated method stub
		if(this.size == 0)
			return false;
		for(int i = 0 ; i < this.size ; i++) {
			for(int j = 0 ; j < childState.length ; j++) {
				for(int k = 0 ; k < childState.length ; k++) {
					if(this.state[i][j][k] != childState[j][k])
						return false;
				}
			}
		}
		return true;
	}
	public void printQueue() {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < this.size ; i++) {
			for(int j = 0 ; j < this.state[0].length ; j++) {
				for(int k = 0 ; k < this.state[0][0].length ; k++) {
					System.out.print(this.state[i][j][k]+"\t");
				}
				System.out.print("\n");
			}
			System.out.print("\n\n");
		}
	}
	public void pushAtStart(int[][] node) {
		// TODO Auto-generated method stub
		if(this.size == 0) {
			for(int i = 0 ; i < node.length ; i++) {
				for(int j = 0 ; j < node.length ;j++) {
					this.state[0][i][j] = node[i][j];
				}
			}
		}
		else {
			for(int k = this.rear ; k > 0 ; k--) {					
				for(int i = 0 ; i < node.length ; i++) {
					for(int j = 0 ; j < node.length ;j++) {
						this.state[k][i][j] = this.state[k-1][i][j];
					}
				}
			}
			for(int i = 0 ; i < node.length ; i++) {
				for(int j = 0 ; j < node.length ;j++) {
					this.state[0][i][j] = node[i][j];
				}
			}
		}
		this.rear += 1;
		this.size += 1;
		
	}
}
