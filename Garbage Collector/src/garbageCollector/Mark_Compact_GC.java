package garbageCollector;

import java.util.ArrayList;

public class Mark_Compact_GC {
	public static void main(String[] args) {
		ArrayList<Node> heap = new ArrayList<Node>();
		ArrayList<Node> root = new ArrayList<Node>();
		FileOperations.readheap("C:/Users/Ahmed Ashraf/Desktop/sample test/heap.csv", heap);
		FileOperations.readRoot("C:/Users/Ahmed Ashraf/Desktop/sample test/roots.txt", heap, root);
		FileOperations.setPointer("C:/Users/Ahmed Ashraf/Desktop/sample test/pointers.csv", heap);
		FileOperations.writeHeapFile("C:/Users/Ahmed Ashraf/Desktop/sample test/Ahmed.csv",Mark_Compact_GC.MarkAndCompact(heap, root));
		
	}
	private static void DFS(Node n) {
		if(!n.children.isEmpty()) {
			for (Node r : n.children) {
				r.Marked=true;
				DFS(r);
			}
		}
	}
	private static ArrayList<Node> MarkAndCompact(ArrayList<Node> fromSpace, ArrayList<Node> root) {
		ArrayList<Node> toSpace = new ArrayList<>();
		for (Node r : root) {
			r.Marked=true;
			DFS(r);
		}
		int start = 0;
		for (Node n : fromSpace) {
			if(n.Marked) {
				int limit = n.memory_end-n.memory_start;
				n.memory_start=start;
				n.memory_end=start+limit;
				start = start + limit + 1 ;
				toSpace.add(n);
			}
		}
		for (Node n : toSpace) {
			System.out.println(n.ID+"  "+n.memory_start+"  "+n.memory_end);
		}
		
		return toSpace;
	}
}
