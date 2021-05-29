package garbageCollector;

import java.util.ArrayList;

public class Node {
    int ID;
    int memory_start , memory_end ;
    boolean Marked ;
    ArrayList<Node> children = new ArrayList<Node>();
    Node(int ID ,int memory_start,int memory_end ){
    	this.ID = ID;
    	this.memory_start = memory_start ;
    	this.memory_end = memory_end ;
    	this.Marked = false ;
    }
    
    public void addChildren(Node n) {
    	children.add(n);
    }

	@Override
	public String toString() {
		return "Node [ID=" + ID + ", memory_start=" + memory_start + ", memory_end=" + memory_end + ", Marked=" + Marked
				+ ", children=" + children + "]";
	}
    
 }
