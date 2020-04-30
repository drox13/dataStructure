package test.queue;

import queue_Stack_SimpleList.MyQueue;

/**
 * 
 * @author Dario Baron
 *Test de la cola
 */
public class TestMyQueue {
	
	public static void main(String[] args) {
		MyQueue<Integer> myQueues = new MyQueue<>();
		System.out.println("La cola esta: " + ((myQueues.isEmtry())?"Vacia": "tiene elementos")	);
		myQueues.putToQueue(1);
		System.out.println("La cola esta: " + ((myQueues.isEmtry())?"Vacia": "tiene elementos")	);
		myQueues.putToQueue(0);
		myQueues.putToQueue(2);
		myQueues.putToQueue(3);
		myQueues.putToQueue(4);
		myQueues.putToQueue(5);
		myQueues.putToQueue(6);
		System.out.println("el tamaño de la lista es: " + myQueues.sizeQueue());
		
		System.out.println("el dato desencolado es: " + myQueues.getToQueue());
		System.out.println("el tamaño de la lista es: " + myQueues.sizeQueue());
		
		myQueues.addToHead(20);
		System.out.println("la cabeza es: " + myQueues.getHead());
		myQueues.showQueue();
	}
		
}
