package test.simpleList;

import java.util.Comparator;

import queue_Stack_SimpleList.MySimpleLinkendList;
import test.model.Person;

public class TestSimpleList {

	public static void main(String[] args) {
		testWithInt();
		testWithSting();
		testWhitClass();
	}

	private static void testWithSting() {
		MySimpleLinkendList<String> mySimpleLinkendList = new MySimpleLinkendList<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		mySimpleLinkendList.addToTail("dario");
		mySimpleLinkendList.addToTail("hector");
		mySimpleLinkendList.addToHead("lilia");
		mySimpleLinkendList.addToMedium("dario", "luis");
		System.out.println("el dato existe?: " +mySimpleLinkendList.isExist("DARIO"));
		System.out.println("la lista simple esta: " + ((mySimpleLinkendList.isEmpty())?"Vacia": "con datos"));
		System.out.println("la cabeza es: " + mySimpleLinkendList.getHead());
		System.out.println("el ultimo dato de la lista es: " + mySimpleLinkendList.getDatalast());
		System.out.println("el tama�o de la lista es: " + mySimpleLinkendList.sizeList());
		System.out.println(mySimpleLinkendList.showList());
	}

	private static void testWithInt() {
		MySimpleLinkendList<Integer> mySimpleList = new MySimpleLinkendList<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});
		System.out.println("la lista simple esta: " + ((mySimpleList.isEmpty())?"Vacia": "con datos"));
		mySimpleList.addToMedium(2, 10);
		mySimpleList.addToMedium(10, 20);
		mySimpleList.addToMedium(10, 15);
		mySimpleList.addToHead(0);
		mySimpleList.addToTail(80);
		System.out.println(mySimpleList.showList());
		System.out.println("el dato existe?: " +mySimpleList.isExist(10));
		mySimpleList.deleat(80);
		System.out.println("la cabeza es: " + mySimpleList.getHead());
		System.out.println("el ultimo dato de la lista es: " + mySimpleList.getDatalast());
		System.out.println("el tama�o de la lista es: " + mySimpleList.sizeList());
		System.out.println(mySimpleList.showList());
	}
	
	private static void testWhitClass() {
		MySimpleLinkendList<Person> mySimpleList = new MySimpleLinkendList<>(new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o1.getId()-o2.getId();
			}
		});
		System.out.println("la lista simple esta: " + ((mySimpleList.isEmpty())?"Vacia": "con datos"));
		mySimpleList.addToHead(new Person(1, "juna"));
		mySimpleList.addToTail(new Person(2, "Maria"));
		System.out.println("el dato existe?: " +mySimpleList.isExist(new Person(1, "juna")));
		System.out.println(mySimpleList.showList());	
	}
}