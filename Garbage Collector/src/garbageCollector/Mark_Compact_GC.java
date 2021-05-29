package garbageCollector;

import java.util.ArrayList;

public class Mark_Compact_GC {
	public static void main(String[] args) {
		ArrayList<Node> heap = new ArrayList<Node>();
		ArrayList<Node> root = new ArrayList<Node>();
		FileOperations.readheap(args[0]+"/heap.csv", heap);
		FileOperations.readRoot(args[1]+"/roots.txt", heap, root);
		FileOperations.setPointer(args[2]+"/pointers.csv", heap);
		FileOperations.writeHeapFile(args[3]+"/Mark&Compact_new-heap.csv",Mark_Compact_GC.MarkAndCompact(heap, root));
	}
	private static void DFS(Node n) {
		if(!n.children.isEmpty()) {
			for (Node r : n.children) {
				if(!r.Marked) {
					r.Marked=true;
					DFS(r);}
			}
		}
	}
	private static ArrayList<Node> MarkAndCompact(ArrayList<Node> fromSpace, ArrayList<Node> root) {
		ArrayList<Node> toSpace = new ArrayList<>();
		for (Node r : root) {
			if(!r.Marked) {
			r.Marked=true;
			DFS(r);}
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
		/*for (Node n : toSpace) {
			System.out.println(n.ID+"  "+n.memory_start+"  "+n.memory_end);
		}*/
		
		return toSpace;
	}
}
