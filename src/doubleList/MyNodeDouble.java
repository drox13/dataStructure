package doubleList;

/**
 * 
 * @author Dario Baron
 *
 * @param <T> tipo de dato del que sera el nodo
 */
public class MyNodeDouble <T>{
	protected T info;
	protected MyNodeDouble<T> next;
	protected MyNodeDouble<T> previus;

	/**
	 * Constructor de nodo doble
	 * @param info informacion perteneciente a este nuevo nodo
	 */
	public MyNodeDouble(T info) {
		this.info = info;
	}
	/**
	 * Constructor que resive todas las caracteristicas del nodo
	 * @param info informacion que contendra el nodo
	 * @param next nodo al que apuntara hacia adelanta
	 * @param previus nodo al que apuntara hacia atrás
	 */
	public MyNodeDouble(T info, MyNodeDouble<T> next, MyNodeDouble<T> previus) {
		this.info = info;
		this.next = next;
		this.previus = previus;
	}
	
	@Override
	public String toString() {
		return info + "";
	}
}