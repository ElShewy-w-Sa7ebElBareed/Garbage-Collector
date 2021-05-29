package garbageCollector;

import java.util.ArrayList;

public class CopyGC {

	public static void main(String[] args) {
		ArrayList<Node> heap = new ArrayList<Node>();
		ArrayList<Node> root = new ArrayList<Node>();
		FileOperations.readheap("C:/Users/ahmed/Desktop/sample test/heap.csv", heap);
		FileOperations.readRoot("C:/Users/ahmed/Desktop/sample test/roots.txt", heap, root);
		FileOperations.setPointer("C:/Users/ahmed/Desktop/sample test/pointers.csv", heap);
		CopyGC.copyGC(heap, root);
	}
	
	private static ArrayList<Node> copyGC (ArrayList<Node> fromSpace ,ArrayList<Node> root) {
		ArrayList<Node> toSpace = new ArrayList<>();
		int firstPtr=0,lastPtr=0;
		for (Node r :root) {
			r.memory_end = lastPtr+(r.memory_end-r.memory_start);
			r.memory_start = lastPtr;
			lastPtr = r.memory_end+1;
			toSpace.add(r);
		}
		for (Node r : toSpace) {
			
		}
		
		
		return root;
	}

}
