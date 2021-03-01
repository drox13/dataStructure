package test.priorityQueueWithQueues;

import queue_Stack_SimpleList.MyPriorityQueue;
import test.model.Client;
import test.model.Priority;

public class TestPriorityQueueWithQueues {
	/**
	 * La priodad mas alta la tinen los adultos mayores,
	 *  seguidos por las embarazadas y por ultimo las personas normales
	 * @param args
	 */
	public static void main(String[] args) {
		MyPriorityQueue<Client> myPriorityQueue = new MyPriorityQueue<>(Priority.values().length);
		
		Client clientOne = new Client("Alberto", Priority.NORMAL_USER);
		Client clientTwo = new Client("Juan", Priority.NORMAL_USER);
		Client clientThree = new Client("Adolfo", Priority.NORMAL_USER);
		Client clientFour = new Client("Juliana", Priority.PREGNACY);
		Client clientFive = new Client("johan", Priority.NORMAL_USER);
		Client clientSix = new Client("Augisto", Priority.OLDER_ADULT);
		myPriorityQueue.push(clientOne, clientOne.getPriority().ordinal());
		myPriorityQueue.push(clientTwo, clientTwo.getPriority().ordinal());
		myPriorityQueue.push(clientThree, clientThree.getPriority().ordinal());
		myPriorityQueue.push(clientFour, clientFour.getPriority().ordinal());
		myPriorityQueue.push(clientFive, clientFive.getPriority().ordinal());
		myPriorityQueue.push(clientSix, clientSix.getPriority().ordinal());
		System.out.println(myPriorityQueue.show());
		System.out.println("atendiendo ando");
		while(!myPriorityQueue.isEmpty()) {
			System.out.println(myPriorityQueue.poll());
		}
	}
}