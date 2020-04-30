package binaryTree;

/**
 * Clase que permite crear un nodo de orden 2
 * @author Dario Baron
 *
 * @param <T> tipo de dato del nodo
 */
public class NodeTree<T> {
	protected T info;
	protected NodeTree<T> left;
	protected NodeTree<T> right;
	
	/**
	 * permite crear un nodo con una informacion
	 * @param info informacion que contendra el nodo
	 */
	public NodeTree(T info) {
		this.info = info;
	}
	
	/**
	 * permite crear un nodo binario con todos tuas atributos
	 * @param info informacion que contendra el nodo
	 * @param left subarbol izquierdo
	 * @param right subarbol derecho
	 */
	public NodeTree(T info, NodeTree<T> left, NodeTree<T> right) {
		this.info = info;
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		return " " + info;
	}
}