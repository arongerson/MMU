package mmu.bsu.edu;

public class Process {
	private int pid;
	private String name;
	private Frame[] pageTable;
	
	public Process(int pid, String name, Frame[] pageTable) {
		this.pid = pid;
		this.name = name;
		this.pageTable = pageTable;
	}
	
	public int getPid() {
		return pid;
	}
	
	public String getName() {
		return name;
	}
	
	public void releaseMemory() {
		for (int i = 0; i < pageTable.length; i++) {
			pageTable[i].setFree(true); 
		}
	}
	
	public void printPageTable() {
		System.out.println("----------------------");
		for (int i = 0; i < pageTable.length; i++) {
			System.out.println(pageTable[i].getNumber());
		}
		System.out.println("----------------------");
	}
}
