package avlTree;

import java.util.Comparator;

/**
 * esta clase hace la creacion de Nodos
 * 
 * @author Dario Leonidas Baron
 * @param <T> tipo de dato que va ha guardar el nodo
 */
public class MyNodeAvl<T> {
	protected T info;
	protected int balance;
	protected MyNodeAvl<T> right;
	protected MyNodeAvl<T> left;
	protected Comparator<MyNodeAvl<T>> comparator;
	protected int order;

	/**
	 * constructor que permite crear un nuevo nodo
	 * @param info informacion que guaradara el nodo
	 */
	public MyNodeAvl(T info){
		this.info = info;
	}
	/**
	 * obtienen el dato del nodo	
	 * @return retorna la informacion guardada en el nodo
	 */
	public T getInfo() {
		return info;
	}
}
