package test.queue;

import java.util.Comparator;

import queue_Stack_SimpleList.MyQueue;

public class TestPriorityQueque {
	
	public static void main(String[] args) {
		testWhitInt();
		testWhitCharacters();
	}
	
	private static void testWhitCharacters() {
		Comparator<Character> comparator = new Comparator<Character>() {

			@Override
			public int compare(Character o1, Character o2) {
				switch (o1) {
				case '�':
					o1 = 'A';
					break;
				case '�':
					o1 = 'E';
					break;
				case '�':
					o1 = 'I';
					break;
				case '�':
					o1 = 'O';
					break;
				case '�':
					o1 = 'U';
					break;
				case '�':
					o1 = 'N';
					break;
				}
				return o1.compareTo(o2);
			}
		};
		MyQueue<Character> myQueue = new MyQueue<>(comparator);
		myQueue.putToQueue('H');
		myQueue.putToQueue('A');
		myQueue.putToQueue('Z');
		myQueue.putToQueue('T');
		myQueue.putToQueue('U');
		myQueue.putToQueue('J');
		myQueue.putToQueue('P');
		myQueue.putToQueue('P');
		myQueue.putToQueue('N');
		myQueue.putToQueue('�');
		myQueue.putToQueue('�');
		myQueue.putToQueue('�');
		myQueue.putToQueue('E');
		myQueue.putToQueue('�');
		myQueue.showQueue();
	}

	private static void testWhitInt() {
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
		MyQueue<Integer> myQueue = new MyQueue<>(comparator);
		myQueue.putToQueue(8);
		myQueue.putToQueue(1);
		myQueue.putToQueue(16);
		myQueue.putToQueue(20);
		myQueue.putToQueue(26);
		myQueue.putToQueue(40);
		myQueue.putToQueue(4);
		myQueue.putToQueue(9);
		myQueue.putToQueue(10);
		myQueue.putToQueue(15);
		myQueue.showQueue();
	}
}