package mmu.bsu.edu;
public class MMU {
	private Frame[] frames;
	private int numberOfFreeFrames;

	public MMU(int numberOfFrames) {
		numberOfFreeFrames = numberOfFrames;
		frames = new Frame[numberOfFreeFrames];
		initMemory(); 
	}
	
	private void initMemory() {
		for (int i = 0; i < numberOfFreeFrames; i++) {
			frames[i] = new Frame(i);
		}
	}

	public Frame[] requestFrames(int numberOfFrames) {
		if (numberOfFrames <= numberOfFreeFrames) {
			Frame[] requestedFrames = new Frame[numberOfFrames];
			int requestedIndex = 0;
			for (int i = 0; i < frames.length && requestedIndex < numberOfFrames; i++) {
				Frame frame = frames[i];
				if (frame.isFree()) {
					frame.setFree(false);
					requestedFrames[requestedIndex++] = frame; 
				}
			}
			numberOfFreeFrames -= numberOfFrames;
			return requestedFrames;
		}
		return null;
	}

	public void setFrames(Frame[] frames) {
		this.frames = frames;
	}

	public int getNumberOfFreeFrames() {
		return numberOfFreeFrames;
	}
	
	public void printFreeFrames() {
		for (Frame frame : frames) {
			System.out.println(frame.getNumber());
		}
	}
}
