package test.doubleList;

import doubleList.MyDoubleLinkedList;

public class TestDoubleLinkendList {
	
	public static void main(String[] args) {
		testWihtInt();
		testWithString();
	}

	private static void testWithString() {
		MyDoubleLinkedList<String> myDoubleLinkedList = new MyDoubleLinkedList<>();
		System.out.println("la lista esta: " + ((myDoubleLinkedList.isEmpty())?"vacia":"con datos"));
		myDoubleLinkedList.addAfter("d", "dario");
		myDoubleLinkedList.addToHead("cabeza");
		myDoubleLinkedList.addToTail("cola");
		myDoubleLinkedList.addBefore("dario", "before");
		myDoubleLinkedList.addAfter("dario", "after");
		myDoubleLinkedList.remove("before");
		myDoubleLinkedList.remove("cabeza");		
		myDoubleLinkedList.remove("dario");
		myDoubleLinkedList.remove("after");
		myDoubleLinkedList.remove("cola");
		myDoubleLinkedList.showListByHead();
		myDoubleLinkedList.showListByTail();
		System.out.println("tamaño: " + myDoubleLinkedList.sizeList() );
	}

	private static void testWihtInt() {
		MyDoubleLinkedList<Integer> myDoubleLinkedList = new MyDoubleLinkedList<>();
		System.out.println("la lista esta: " + ((myDoubleLinkedList.isEmpty())?"vacia":"con datos"));
		myDoubleLinkedList.addToHead(10);
		myDoubleLinkedList.addToHead(30);
		myDoubleLinkedList.addToHead(70);
		myDoubleLinkedList.addToTail(0);
		myDoubleLinkedList.addToTail(20);
		myDoubleLinkedList.addToTail(40);
		myDoubleLinkedList.addBefore(10, 80);
		myDoubleLinkedList.addAfter(20, 100);
		
		myDoubleLinkedList.remove(70);
		myDoubleLinkedList.remove(40);
		myDoubleLinkedList.remove(0);
		myDoubleLinkedList.remove(30);
		myDoubleLinkedList.remove(80);
 		myDoubleLinkedList.remove(10);
 		myDoubleLinkedList.remove(100);
 		myDoubleLinkedList.remove(20);
		System.out.println("la cabeza es: " + myDoubleLinkedList.getHead());
//		System.out.println("el anterior de cabeza es: " + myDoubleLinkedList.getHead().getPrevius());
//		System.out.println("el siguiente de cabeza es: " + myDoubleLinkedList.getHead().getNext());
		System.out.println("la cola es: " + myDoubleLinkedList.getTail());
//		System.out.println("el anterior de la cola es: " + myDoubleLinkedList.getTail().getPrevius());
//		System.out.println("el siguiente de la cola es: " + myDoubleLinkedList.getTail().getNext());
//		
		System.out.println("la lista esta: " + ((myDoubleLinkedList.isEmpty())?"vacia":"con datos"));
		System.out.println("el dato existe?:" + myDoubleLinkedList.isExist(100));
		myDoubleLinkedList.showListByHead();
		myDoubleLinkedList.showListByTail();
		System.out.println("el tamaño de la lista es: " + myDoubleLinkedList.sizeList());
	}
}