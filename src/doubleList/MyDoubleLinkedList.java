package doubleList;

import java.util.Comparator;
import java.util.Iterator;

import exception.MyException;

/**
 * 
 * @author Dario Baron
 *
 * @param <T> tipo de dato de la lista doblemente elazada
 */
public class MyDoubleLinkedList <T> implements Iterable<T>{
	private static final String NO_DATA_FILD = "No se encontro la informacion";
	private MyNodeDouble<T> head;
	private MyNodeDouble<T> tail;
	private Comparator<T> comparator;

	/**
	 * Metodo constructor
	 * @param comparator por el cual se comparan los datos dentro de la lista
	 */
	public MyDoubleLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	/**
	 * Permite comprobar si la lista esta vacia
	 * @return true si esta vacia, de lo contrario false
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Permite agregar al inicio de la lista
	 * @param info informacion que se dea a�adir
	 */
	public void addToHead(T info){
		if(head != null) {
			head = new MyNodeDouble<>(info, head, null);
			head.next.previus = head;
		}else {
			tail = head = new MyNodeDouble<>(info, head, null);
		}
	}
	/**
	 * Permite agregar informacion Antes de una informacion de referencia
	 * si no existe cabeza, la info se convierte en la cabeza
	 * @param reference informacion de referencia
	 * @param info informacion ha agregar
	 * @throws MyException se produce cuando no se encuentra la informacion de referencia
	 */
	public void addBefore(T reference,T info) throws MyException{
		if(head != null) {
			MyNodeDouble<T> nodeReference = fildInfo(reference);
			if(nodeReference != null) {
				MyNodeDouble<T> newNode = new MyNodeDouble<>(info, nodeReference, nodeReference.previus);
				nodeReference.previus = newNode;
				newNode.previus.next = newNode;
			}else {
				throw new MyException(NO_DATA_FILD);
			}
		}else {
			head = tail = new MyNodeDouble<>(info);
		}
	}

	/**
	 * Permite agregar una informacion despues de una informacion de referencia
	 * de no existir cabeza, convierte esta informacion en la cabeza
	 * @param reference informacion de referencia para agregar despues de esta
	 * @param info informacion que se desea agregar
	 * @throws MyException se produce cuando no se encuentra la informacion de referencia
	 */
	public void addAfter(T reference, T info)throws MyException {
		if(head != null) {
			MyNodeDouble<T> nodeReference = fildInfo(reference);
			if(nodeReference != null) {
				MyNodeDouble<T> newNode = new MyNodeDouble<>(info, nodeReference.next, nodeReference);
				nodeReference.next = newNode;
				newNode.next.previus = newNode;
			}else {
				throw new MyException(NO_DATA_FILD);
			}
		}else {
			head = tail = new MyNodeDouble<>(info);
		}
	}
	
	/**
	 * Permite ir ordenando la lista (prioridad)
	 * @param info informacion nueva
	 */
	public void addByOrder(T info) {
		if(head != null){
				if(comparator.compare(info, head.info) >= 0 ) { // informacion es mayor que la cabeza
					MyNodeDouble<T> reference = head;
					while(reference.next != null && comparator.compare(info, reference.next.info) >= 0){
						reference = reference.next;
					}
					MyNodeDouble<T> newNode = new MyNodeDouble<>(info, reference.next, reference);
					reference.next = newNode;
					if(newNode.next != null) {
						newNode.next.previus = newNode;
					}else{
						tail = newNode;
					}
				}else {
					head = new MyNodeDouble<T>(info, head, null);
					head.next.previus = head;
				}
				return;
		}
		head = tail = new MyNodeDouble<>(info, head, tail);
	}

	/**
	 * Busca una informacion dentro de la lista
	 * @param info informacion que se desea buscar
	 * @return el nodo que contiene dicha informacion
	 */
	private MyNodeDouble<T> fildInfo(T info){
		MyNodeDouble<T> existInfo = head;
		while(existInfo != null) {
			if (comparator.compare(existInfo.info, info) == 0) {
				return existInfo;
			}
			existInfo = existInfo.next;
		}
		return existInfo;
	}

	/**
	 * comprueba si un elemento existe dentro de la lista
	 * @param info informacion que se desea ver si existe
	 * @return verdadero si existe, de lo contrario false
	 */
	public boolean isExist(T info) {
		if(fildInfo(info) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Permite agregar al final de la lista
	 * @param info informacion que vamos ha agregar
	 */
	public void addToTail(T info){
		if(tail != null) {
			tail = new MyNodeDouble<>(info, null, tail);
			tail.previus.next = tail;
		}else {
			tail = head =  new MyNodeDouble<>(info);
		} 
	}
	/**
	 * hace funcion de lanzador, remueve la infomacion deseada
	 * @param info informacion que desea borrar
	 */
	public void remove(T info) {
		if(head != null) {
			if(comparator.compare(head.info, info) == 0) {
				removeToHead();
			}else if(comparator.compare(tail.info, info) == 0) {
				removeToTail();
			}else {
				removeToMedium(info);
			}
		}
	}
	/**
	 * Borra la cabeza de la lista
	 */
	public void removeToHead() {
		if(head.next != null) {
			head = head.next;
			head.previus = null;			
		}else {
			head = tail = null;
		}
	}
	/**
	 * Remueve la infomacion requerida
	 * @param info informacion que se desea borrar
	 * @throws MyException se lanza cuando no se encuntra la informacion en la lista
	 */
	private void removeToMedium(T info) throws MyException{
		MyNodeDouble<T> nodeDeleat = fildInfo(info);
		if(nodeDeleat != null) {
			nodeDeleat.previus.next = nodeDeleat.next;
			nodeDeleat.next.previus = nodeDeleat.previus;			
		}else {
			throw new MyException(NO_DATA_FILD);
		}
	}
	/**
	 * borra la cola de la lista
	 */
	private void removeToTail() {
		tail = tail.previus;
		tail.next = null;
	}

	/**
	 * Permite conocer el tama�o de la lista
	 * @return el numero de datos en la lista
	 */
	public int sizeList() {
		int count = 0;
		MyNodeDouble<T> last = tail;
		while(last != null) {
			count++;
			last = last.previus;
		}
		return count;
	}

	/**
	 * Permite visualizar la lista doblemente enlazada
	 * iniciando desde la cabeza (orden)
	 */
	public String showListByHead() {
		String list = "-- Lista en Order ---\n";
		MyNodeDouble<T> iterator = head;
		while(iterator != null) {
			list += "<-- " + iterator.info + " -->\n";
			iterator = iterator.next;
		}
		return list;
	}

	/**
	 * Permite visualizar la lista doblemente enlazada
	 * iniciando desde la cola (inversa)
	 */
	public String showListByTail() {
		String list = "-- Lista invertida ---\n";
		MyNodeDouble<T> iterator = tail;
		while(iterator != null) {
			list += "<-- " + iterator.info + " --> \n";
			iterator = iterator.previus;
		}
		return list;
	}

	public MyNodeDouble<T> getHead() {
		return head;
	}

	public MyNodeDouble<T> getTail() {
		return tail;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private MyNodeDouble<T> node = head;
			
			@Override
			public boolean hasNext() {
				return node!=null;
			}

			@Override
			public T next() {
				T data= node.info;
				this.node= node.next;
				return data;
			}
		};
	}

	public Iterator<T> iteratorPrevious() {
		return new Iterator<T>() {
			private MyNodeDouble<T> node = tail;
			
			@Override
			public boolean hasNext() {
				return node!=null;
			}

			@Override
			public T next() {
				T data= node.info;
				this.node= node.previus;
				return data;
			}
		};
	}
}