package garbageCollector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperations {
	
	public void readheap(String path,ArrayList<Node> heap) {
		try {
			File file = new File(path);
			String row ;
			String [] rowData;
			Scanner reader = new Scanner(file);
			while ((row = reader.nextLine()) != null) {
				rowData =row.split(",");
				heap.add(new Node(Integer.parseInt(rowData[0]),Integer.parseInt(rowData[1]),Integer.parseInt(rowData[2])));
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void readRoot(String rootpath,ArrayList<Node> heap,ArrayList<Node> root) {
		String row ;
		int id;
		try {
			File file = new File(rootpath);
			Scanner reader = new Scanner(file);
			while ((row = reader.nextLine()) != null) {
				id = Integer.parseInt(row);
				for (Node n : heap) {
					if (n.ID==id) {
						root.add(n);
						break;
					}
				}
			
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPointer(String pointerPath,ArrayList<Node> heap) {
		String row ;
		String rowData[];
		int ID1,ID2;
		try {
			File file = new File(pointerPath);
			Scanner reader = new Scanner(file);
			while ((row = reader.nextLine()) != null) {
				rowData = row.split(",");
				ID1 = Integer.parseInt(rowData[0]);
				ID2 = Integer.parseInt(rowData[1]);
				for (Node n1 : heap) {
					if (n1.ID==ID1) {
						for (Node n2 : heap) {
							if (n2.ID==ID2) {
								n1.addChildren(n2);
								break;
							}
						}
						break;
					}
				}
			
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
