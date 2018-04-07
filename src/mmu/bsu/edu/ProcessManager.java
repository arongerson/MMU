package mmu.bsu.edu;

import java.util.ArrayList;
import java.util.List;

public class ProcessManager {
	
	private List<Process> processes;
	private MMU mmu;
	private int maxPID;
	
	public ProcessManager(MMU mmu) {
		processes = new ArrayList<>();
		this.mmu = mmu;
		maxPID = 0;
	}
	
	private int getPid() {
		for (int i = 0; i <= maxPID + 1; i++) {
			if (getProcess(i) == null) {
				if (i > maxPID) {
					maxPID = i;
				}
				return i;
			}
		}
		return 0;
	}
	
	public String addProcess(String name, int numberOfPages) {
		Frame[] requestedFrames = mmu.requestFrames(numberOfPages);
		if (requestedFrames != null) {
			int pid = createProcess(requestedFrames, name);
			return "Process " + name + " has been created with pid " + pid;
		} else {
			return "There are no enough free frames";
		}
	}
	
	private int createProcess(Frame[] requestedFrames, String name) {
		int pid = getPid();
		Process proess = new Process(pid, name, requestedFrames);
		processes.add(proess);
		return pid;
	}
	
	public String removeProcess(int pid) {
		for (Process process: processes) {
			if (process.getPid() == pid) {
				process.releaseMemory();
				processes.remove(process);
				return "Process " + process.getName() + " has been removed";
			}
		}
		return "Process with pid " + pid + " not found";
	}
	
	public void printAllProcesses() {
		System.out.println("Available processes: \n---------------------");
		for (Process process : processes) {
			System.out.println(process.getName() + ", pid: " + process.getPid());
		}
	}
	
	public Process getProcess(int pid) {
		for (Process process : processes) {
			if (process.getPid() == pid) {
				return process;
			}
		}
		return null;
	}
}
