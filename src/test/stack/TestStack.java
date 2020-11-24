package test.stack;

import java.util.Comparator;

import queue_Stack_SimpleList.MyStack;

public class TestStack {

	public static void main(String[] args) {
		MyStack<Integer> myStack = new MyStack<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});
		System.out.println("la pila esta: " + ((myStack.isEmpty())?"vacia": "con datos"));
		myStack.push(10);
		System.out.println("la pila esta " + ((myStack.isEmpty())?"vacia": "con datos"));
		myStack.push(1);
		myStack.push(4);
		myStack.push(20);
		myStack.push(90);
		System.out.println("la pila tiene " + myStack.sizeStack() + " datos");
		System.out.println(myStack.isExist(25));
		System.out.println(myStack.peek());
		myStack.showStack();
//		System.out.println("el dato recuperado es: " + myStack.pop());
//		System.out.println("el dato recuperado es: " + myStack.pop());
//		System.out.println("el dato recuperado es: " + myStack.pop());
//		System.out.println("el dato recuperado es: " + myStack.pop());
//		System.out.println("el dato recuperado es: " + myStack.pop());
//		System.out.println("el dato recuperado es: " + myStack.pop());
	}
}