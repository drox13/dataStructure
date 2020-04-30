package test.simpleList;

import queue_Stack_SimpleList.MySimpleLinkendList;

public class TestSimpleList {

	public static void main(String[] args) {
		testWithInt();
		testWithSting();
	}

	private static void testWithSting() {
		MySimpleLinkendList<String> mySimpleLinkendList = new MySimpleLinkendList<>();
		mySimpleLinkendList.addToTail("dario");
		mySimpleLinkendList.addToTail("hector");
		mySimpleLinkendList.addToHead("lilia");
		mySimpleLinkendList.addToMedium("dario", "luis");
		mySimpleLinkendList.deleatToHead();
		mySimpleLinkendList.deleatToHead();
		mySimpleLinkendList.deleatToHead();
		mySimpleLinkendList.deleatToHead();
		System.out.println("el dato existe?: " +mySimpleLinkendList.isExist("DARIO"));
		System.out.println("la lista simple esta: " + ((mySimpleLinkendList.isEmpty())?"Vacia": "con datos"));
		System.out.println("la cabeza es: " + mySimpleLinkendList.getHead());
		System.out.println("el ultimo dato de la lista es: " + mySimpleLinkendList.getDatalast());
		System.out.println("el tamaño de la lista es: " + mySimpleLinkendList.sizeList());
		mySimpleLinkendList.showList();
	}

	private static void testWithInt() {
		MySimpleLinkendList<Integer> mySimpleList = new MySimpleLinkendList<>();
		System.out.println("la lista simple esta: " + ((mySimpleList.isEmpty())?"Vacia": "con datos"));
		mySimpleList.addToMedium(2, 10);
		mySimpleList.addToMedium(10, 20);
		mySimpleList.addToMedium(10, 15);
		mySimpleList.addToHead(0);
		mySimpleList.addToTail(80);
		System.out.println("el dato existe?: " +mySimpleList.isExist(10));
		mySimpleList.deleatToMedium(15);
		mySimpleList.deleatToHead();
		mySimpleList.deleatToTail();
		mySimpleList.deleatToHead();
		System.out.println("la cabeza es: " + mySimpleList.getHead());
		System.out.println("el ultimo dato de la lista es: " + mySimpleList.getDatalast());
		System.out.println("el tamaño de la lista es: " + mySimpleList.sizeList());
		mySimpleList.showList();
	}
}