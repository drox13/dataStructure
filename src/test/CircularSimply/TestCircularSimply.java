package test.CircularSimply;

import queue_Stack_SimpleList.MySimplyLinkendCircularList;

public class TestCircularSimply {
	public static void main(String[] args) {
		MySimplyLinkendCircularList<Integer> circular = new MySimplyLinkendCircularList<>();
		System.out.println("la lista esta: " + ((circular.isEmpty())?"vacia": "con datos"));
		circular.add(10);
		circular.add(20);
		circular.add(30);
		circular.remove(10);
		circular.remove(20);
		circular.remove(30);
		circular.remove(30);
		circular.remove(30);
		circular.remove(30);
		circular.remove(30);
		System.out.println("primero: " + circular.getFirst());
//		System.out.println("el siguiente del primero: " + circular.getFirst().getNext());
//		System.out.println("el siguiente " + circular.getFirst().getNext().getNext());
		System.out.println("ultimo : " + circular.getLast());
		circular.showList();
		System.out.println("el tamaño de la lista es: " + circular.sizeList());
	}
}