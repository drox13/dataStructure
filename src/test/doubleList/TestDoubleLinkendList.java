package test.doubleList;

import java.util.Comparator;
import java.util.Iterator;

import doubleList.MyDoubleLinkedList;
import test.model.Person;

public class TestDoubleLinkendList {
	
	public static void main(String[] args) {
		testWihtInt();
		testWithString();
		testWithClass();
	}

	private static void testWithClass() {
		MyDoubleLinkedList<Person> myDoubleLinkedList = new MyDoubleLinkedList<>(new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getId()-o2.getId();
			}
		});
		System.out.println("la lista esta: " + ((myDoubleLinkedList.isEmpty())?"vacia":"con datos"));
		myDoubleLinkedList.addByOrder(new Person(4,"jo"));
		myDoubleLinkedList.addByOrder(new Person(10,"lo"));
		myDoubleLinkedList.addByOrder(new Person(2,"lo"));
		myDoubleLinkedList.addByOrder(new Person(5,"lo"));
		myDoubleLinkedList.addByOrder(new Person(12,"lo"));
		myDoubleLinkedList.addByOrder(new Person(7,"lo"));
		myDoubleLinkedList.addByOrder(new Person(6,"lo"));
		System.out.println("el dato existe?:" + myDoubleLinkedList.isExist(new Person(1,"lo")));
//		myDoubleLinkedList.remove(new Person(2, "no"));
//		System.out.println(myDoubleLinkedList.showListByHead());
//		System.out.println(myDoubleLinkedList.showListByTail());
//		System.out.println(myDoubleLinkedList.getTail());
//		
		Iterator<Person> list = myDoubleLinkedList.iterator();
		while(list.hasNext()) {
			System.out.println(list.next());
		}
		
		Iterator<Person> listInvers = myDoubleLinkedList.iteratorPrevious();
		while(listInvers.hasNext()) {
			System.out.println(listInvers.next());
		}

	}

	private static void testWithString() {
		MyDoubleLinkedList<String> myDoubleLinkedList = new MyDoubleLinkedList<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
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
		System.out.println("tama�o: " + myDoubleLinkedList.sizeList() );
	}

	private static void testWihtInt() {
		MyDoubleLinkedList<Integer> myDoubleLinkedList = new MyDoubleLinkedList<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});
		System.out.println("la lista esta: " + ((myDoubleLinkedList.isEmpty())?"vacia":"con datos"));
		myDoubleLinkedList.addToHead(10);
		myDoubleLinkedList.addToHead(30);
		myDoubleLinkedList.addToHead(70);
		myDoubleLinkedList.addToTail(0);
		myDoubleLinkedList.addToTail(20);
		myDoubleLinkedList.addToTail(40);
		myDoubleLinkedList.addBefore(10, 80);
		myDoubleLinkedList.addAfter(20, 100);
		System.out.println(myDoubleLinkedList.showListByHead());
		
		myDoubleLinkedList.removeToHead();
		
//		myDoubleLinkedList.remove(40);
//		myDoubleLinkedList.remove(0);
//		myDoubleLinkedList.remove(30);
//		myDoubleLinkedList.remove(80);
// 		myDoubleLinkedList.remove(10);
// 		myDoubleLinkedList.remove(100);
// 		myDoubleLinkedList.remove(20);
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
		System.out.println("el tama�o de la lista es: " + myDoubleLinkedList.sizeList());
		
		System.out.println("--probando el iterador---");
		Iterator<Integer> iterator = myDoubleLinkedList.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		System.out.println("--probando el iteradorPrevios---");
		Iterator<Integer> iteratorPrevios = myDoubleLinkedList.iteratorPrevious();
		while(iteratorPrevios.hasNext()) {
			System.out.println(iteratorPrevios.next());
		}
	}
}