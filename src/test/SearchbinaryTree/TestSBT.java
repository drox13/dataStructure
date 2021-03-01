package test.SearchbinaryTree;

import java.util.Comparator;
import binaryTree.SearchBinaryTree;

public class TestSBT {

	public static void main(String[] args) {
		testComparator();
		testInteger();
		testCharacter();
	}

	private static void testCharacter() {
		Comparator<Character> comparator = new Comparator<Character>() {

			@Override
			public int compare(Character o1, Character o2) {
				return o1.compareTo(o2);
			}
		};
		
		SearchBinaryTree<Character> SBT = new SearchBinaryTree<>(comparator);
		SBT.add('F');
		SBT.add('B');
		SBT.add('G');
		SBT.add('A');
		SBT.add('D');
		SBT.add('I');
		SBT.add('C');
		SBT.add('E');
		SBT.add('H');
		SBT.deleat('j');
//		SBT.inOrder().showList();
		SBT.preOrder().showList();
		SBT.showTree();
		System.out.println("profundidad " + SBT.depth('G') );
		System.out.println("altura " + SBT.getHeightTree());
		System.out.println(SBT.getNode('H') + " get node");
		System.out.println(SBT.getFather('A') + " father");
		System.out.println(SBT.quantityLevelDown(SBT.getNode('F')));
		System.out.println("numero de nodos: " + SBT.quatityNodes(SBT.getNode('B')));
		System.out.println("numero de hijos: " + SBT.quantityChild(SBT.getNode('B')));
		System.out.println("numero de nodos hoja:" + SBT.quantityLeaf());
		System.out.println("numero nodos internos: " + (SBT.getWeight() - SBT.quantityLeaf()));
	}

	private static void testInteger() {
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
		SearchBinaryTree<Integer> search = new SearchBinaryTree<>(comparator);
		System.out.println("el arbol esta: " + ((search.isEmpty())?"vacio": "con datos"));
		search.add(7);
		search.add(5);
		search.add(17);
		search.add(4);
		search.add(9);
		search.add(30);
		search.add(8);
		search.add(15);
		search.add(32);
		
		
		
//		search.add(20);
//		search.add(10);
//		search.add(30);
//		search.add(0);
//		search.add(11);
//		search.add(25);
//		search.add(40);
		
		
//		search.add(45);
//		search.add(23);
//		search.add(65);
//		search.add(2);
//		search.add(38);
//		search.add(52);
//		search.add(96);
//		search.add(7);
//		search.add(48);
		System.out.println("existe? " + search.isExist(110));
		System.out.println("es hoja? " + search.isLeaf(17));
		System.out.println("la altura del arbol es: " + search.getHeightTree());
		
		System.out.println();
		System.out.print(search.inOrder().showList());
		System.out.println(search.preOrder().showList());
		System.out.println(search.posOrder().showList());
		System.out.println(search.amplitudeTour().showList());
		search.showTree();
		System.out.println("--------------");
		search.deleat(17);
		search.deleat(30);
		search.showTree();
	}

	private static void testComparator() {
		Comparator<Integer> c = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
		System.out.println(c.compare(10, 8));
	}
}