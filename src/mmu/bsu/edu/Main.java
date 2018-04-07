package mmu.bsu.edu;

import java.util.Scanner;

public class Main {
	private static int totalNumberOfFrames = 100; 
	private static MMU mmu = new MMU(totalNumberOfFrames);
	private static ProcessManager processManager = new ProcessManager(mmu);
	private static Scanner scanner = new Scanner(System.in); 
	public static void main(String[] args) {
		while (true) {
			System.out.println("Menu: \n1. Create Process\n2. Print process page table\n3."
					+ " Remove Process\n4. Print Number of free frames\n5. Quit");
			System.out.print("Choice: "); 
			scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			if (choice == 1) {
				createProcess();
			} else if (choice == 2) {
				printPageTable();
			} else if (choice == 3) {
				removeProcess();
			} else if (choice == 4) {
				printNumberOfFreeFrames();
			} else if (choice == 5) {
				break;
			}
		}
		scanner.close();
	}

	private static void printNumberOfFreeFrames() {
		System.out.println(mmu.getNumberOfFreeFrames() + " frames available."); 
	}

	private static void removeProcess() {
		processManager.printAllProcesses();
		System.out.print("PID: ");
		scanner = new Scanner(System.in);
		int processId = scanner.nextInt();
		String result = processManager.removeProcess(processId);
		System.out.println(result); 
	}

	private static void printPageTable() {
		processManager.printAllProcesses();
		System.out.print("PID: "); 
		scanner = new Scanner(System.in);
		int processId = scanner.nextInt();
		Process process = processManager.getProcess(processId);
		if (process != null) {
			process.printPageTable(); 
		} else {
			System.out.println("No process with pid " + processId); 
		}
	}

	private static void createProcess() {
		System.out.print("Process Name: "); 
		scanner = new Scanner(System.in);
		String processName = scanner.nextLine();
		System.out.print("Number of Pages: "); 
		scanner = new Scanner(System.in);
		int numberOfPages = scanner.nextInt();
		String result = processManager.addProcess(processName, numberOfPages);
		System.out.println(result);
	}
}
