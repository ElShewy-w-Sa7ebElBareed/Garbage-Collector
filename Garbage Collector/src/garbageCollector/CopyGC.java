package garbageCollector;

import java.util.ArrayList;

public class CopyGC {

	public static void main(String[] args) {
		ArrayList<Node> heap = new ArrayList<Node>();
		ArrayList<Node> root = new ArrayList<Node>();
		FileOperations.readheap("C:/Users/ahmed/Desktop/sample test/heap.csv", heap);
		FileOperations.readRoot("C:/Users/ahmed/Desktop/sample test/roots.txt", heap, root);
		FileOperations.setPointer("C:/Users/ahmed/Desktop/sample test/pointers.csv", heap);

	}

}
