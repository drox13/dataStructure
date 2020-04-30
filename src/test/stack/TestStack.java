package test.stack;

import queue_Stack_SimpleList.MyStack;

public class TestStack {

	public static void main(String[] args) {
		MyStack<Integer> myStack = new MyStack<Integer>();
		System.out.println("la pila esta: " + ((myStack.isEmpty())?"vacia": "con datos"));
		myStack.push(10);
		System.out.println("la pila esta " + ((myStack.isEmpty())?"vacia": "con datos"));
		myStack.push(1);
		myStack.push(4);
		myStack.push(20);
		myStack.push(90);
		System.out.println("la pila tiene " + myStack.sizeStack() + " datos");
		myStack.showStack();
		System.out.println("el dato recuperado es: " + myStack.pop());
		System.out.println("el dato recuperado es: " + myStack.pop());
		System.out.println("el dato recuperado es: " + myStack.pop());
		System.out.println("el dato recuperado es: " + myStack.pop());
		System.out.println("el dato recuperado es: " + myStack.pop());
		System.out.println("el dato recuperado es: " + myStack.pop());
	}
}