package test.CircularDouble;

import doubleList.MyDoubleLinkendCircularList;

public class TestCircularDouble {
	public static void main(String[] args) {
		MyDoubleLinkendCircularList<String> circularDouble = new MyDoubleLinkendCircularList<>();
		circularDouble.add("d");
		circularDouble.add("a");
		circularDouble.add("r");
		circularDouble.add("i");
		circularDouble.remove("a");
		circularDouble.remove("r");
		circularDouble.remove("i");
		circularDouble.remove("d");
		circularDouble.remove("ddads");
		circularDouble.add("otro");
		System.out.println("existe: ?" + circularDouble.isExist("otro"));
//		System.out.println("primero: " + circularDouble.getFirst());
//		System.out.println("primero -- siguiente: " + circularDouble.getFirst().getNext());
//		System.out.println("ultimo: " + circularDouble.getLast());
//		System.out.println("ultimo: --- anterior " + circularDouble.getLast().getPrevius());
		circularDouble.showListOrder();
		circularDouble.showListInverse();
		System.out.println("tamaño: " + circularDouble.sizeList());
	}
}