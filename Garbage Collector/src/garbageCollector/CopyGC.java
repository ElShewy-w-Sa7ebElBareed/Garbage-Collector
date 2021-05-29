package garbageCollector;

import java.util.ArrayList;

public class CopyGC {

	public static void main(String[] args) {
		ArrayList<Node> heap = new ArrayList<Node>();
		ArrayList<Node> root = new ArrayList<Node>();
		FileOperations.readheap(args[0]+"heap.csv", heap);
		FileOperations.readRoot(args[1]+"roots.txt", heap, root);
		FileOperations.setPointer(args[2]+"pointers.csv", heap);
		FileOperations.writeHeapFile(args[4]+"/Copy_new-heap.csv",CopyGC.copyGC(heap, root));
	}

	private static ArrayList<Node> copyGC(ArrayList<Node> fromSpace, ArrayList<Node> root) {
		ArrayList<Node> toSpace = new ArrayList<>();
		int lastPtr = 0;
		for (Node r : root) {
			r.memory_end = lastPtr + (r.memory_end - r.memory_start);
			r.memory_start = lastPtr;
			lastPtr = r.memory_end + 1;
			toSpace.add(r);
		}
		for (int firstPtr = 0; firstPtr < toSpace.size(); firstPtr++) {
			for (Node c : toSpace.get(firstPtr).getChildren()) {
				if (!toSpace.contains(c)) {
					c.memory_end = lastPtr + (c.memory_end - c.memory_start);
					c.memory_start = lastPtr;
					lastPtr = c.memory_end + 1;
					toSpace.add(c);
				}
			}
		}
		return toSpace;
	}
}
