package mmu.bsu.edu;

public class Frame {
	
	private int number;
	private boolean free;
	
	public Frame(int number) {
		this.number = number;
		this.free = false;
	}
	
	public boolean isFree() {
		return free;
	}
	
	public void setFree(boolean free) {
		this.free = free;
	}
	
	public int getNumber() {
		return number;
	}
}
