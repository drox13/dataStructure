package queue_Stack_SimpleList;

import exception.MyException;

/**
 * Clase que permte crea una pila
 * @author Dario Baron
 *
 * @param <T> tipo de informacion que se guardara en la pila
 */
public class MyStack <T>{
	private static final String ERROR_MESSAGE = "la Pila se encuentra vacia, no se pueden recuperar elementos";
	private MyNode<T> head;

	/**
	 * permite ver si la pila esta vacia
	 * @return verdadero si la pila esta vacis
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Permite apilar
	 * @param info informacion que se desea agregar
	 */
	public void push(T info) {
		if(head != null) {
			head = new MyNode<>(info, head);
		}else {
			head = new MyNode<>(info);
		}
	}
	/**
	 * Permite desapilar
	 * @return dato que se desapilo (retiro)
	 * @throws MyException se lanza si no hay mas datos
	 */
	public T pop() throws MyException {
		if(head != null) {
			MyNode<T> element  = head;
			head = head.next;
			return element.infoNode;
		}
		throw new MyException(ERROR_MESSAGE);
	}
	
	/**
	 * Permite determinar el tamaño de la pila
	 * @return
	 */
	public int sizeStack() {
		int count = 0;
		MyNode<T> aux = head;
		while(aux != null) {
			count++;
			aux = aux.next;
		}
		return count;
	}
	
	/**
	 * Permite ver toda la pila
	 */
	public void showStack() {
		MyNode<T> aux = this.head;
		while(aux != null) {
			System.out.println(aux.infoNode);
			aux = aux.next;
		}
	}
}
