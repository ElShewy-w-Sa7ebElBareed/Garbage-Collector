package garbageCollector;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperations {
	public static final String UTF8_BOM = "\uFEFF";

	public static void readheap(String path, ArrayList<Node> heap) {
		try {
			String[] rowData;
			Scanner reader = new Scanner(new InputStreamReader(new FileInputStream(path), "UTF-8"));
			while (reader.hasNext()) {
				String row = reader.next();
				while (row.startsWith(UTF8_BOM)) {
					row = row.substring(1);
				}
				rowData = row.split(",");
				heap.add(new Node(Integer.parseInt(rowData[0]), Integer.parseInt(rowData[1]),
						Integer.parseInt(rowData[2])));
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void readRoot(String rootpath, ArrayList<Node> heap, ArrayList<Node> root) {
		String row;
		int id;
		try {
			Scanner reader = new Scanner(new InputStreamReader(new FileInputStream(rootpath), "UTF-8"));
			while (reader.hasNext()) {
				row = reader.next();
				while (row.startsWith(UTF8_BOM)) {
					row = row.substring(1);
				}
				id = Integer.parseInt(row);
				for (Node n : heap) {
					if (n.ID == id) {
						root.add(n);
						break;
					}
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setPointer(String pointerPath, ArrayList<Node> heap) {
		String row;
		String rowData[];
		int ID1, ID2;
		try {
			Scanner reader = new Scanner(new InputStreamReader(new FileInputStream(pointerPath), "UTF-8"));
			while (reader.hasNext()) {
				row = reader.next();
				while (row.startsWith(UTF8_BOM)) {
					row = row.substring(1);
				}
				rowData = row.split(",");
				ID1 = Integer.parseInt(rowData[0]);
				ID2 = Integer.parseInt(rowData[1]);
				for (Node n1 : heap) {
					if (n1.ID == ID1) {
						for (Node n2 : heap) {
							if (n2.ID == ID2) {
								n1.addChildren(n2);
								break;
							}
						}
						break;
					}
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
