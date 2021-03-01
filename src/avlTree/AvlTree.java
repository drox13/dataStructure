package avlTree;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Esta clase permite la creacion de arboles binarios de busqueda
 *
 * @author Dario Leonidas Baron
 * @param <T> tipo de objeto de los datos que se van a agregar al arbol
 */
public class AvlTree<T>{
	protected static final String WARNING_NOT_ELEMENTS = "No hay elementos en el arbol";
	private MyNodeAvl<T> root;
	private Comparator<T> comparator;

	/**
	 * Permite crear un arbol binario de busqueda con el criterio de
	 * ordenamiento
	 *
	 * @param comparator criterio de ordenamiento del arbol
	 */
	public AvlTree(Comparator<T> comparator) {
		this.comparator = comparator;
		this.root = null;
	}

	/**
	 * mira si el arbol esta vacio
	 * @return verdadero si esta vacio
	 */
	public boolean isEmpty() {
		return this.root == null;
	}

	/**
	 * agrega el dato deseado
	 * @param info dato para agregar al arbol
	 */
	public void put(T info) {
		if (this.root != null) {
			this.root = put(info, this.root);
		} else {
			this.root = new MyNodeAvl<T>(info);
		}
	}


	/**
	 * obtiene el dato que se quiere buscar
	 * @param info dato que se desea buscar
	 * @return el dato encontrado
	 */
	public T get(T info) {
		if (this.root != null) {
			MyNodeAvl<T> actual = this.root;
			while (actual != null) {
				if (!info.equals(actual.info)) {
					if (comparator.compare(info, actual.info) >= 0) {
						actual = actual.right;
					} else {
						actual = actual.left;
					}
				} else {
					return actual.info;
				}
			}
			return null;
		} else {
			throw new NoSuchElementException(WARNING_NOT_ELEMENTS);
		}
	}

	/**
	 * permite eliminar un dato
	 * @param info dato a eliminar
	 * @return true si se logro eliminar el dato
	 */
	public boolean remove(T info) {
		if (this.root != null) {
			if (!info.equals(this.root.info)) {
				boolean result = remove(info, this.root);
				balanceRoot();
				return result;
			} else {
				removeRoot();
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * Permite agregar un nuevo dato a la rama actual
	 *
	 * @param info nuevo dato
	 * @param actual rama actual
	 * @return rama modificada
	 */
	private MyNodeAvl<T> put(T info, MyNodeAvl<T> actual) {
		if (comparator.compare(info, actual.info) >= 0) {
			if (actual.right != null) {
				actual.right = put(info, actual.right);
				actual = balanceRight(actual);
				updateBalance(actual);
				return actual;
			} else {
				actual.right = new MyNodeAvl<T>(info);
				updateBalance(actual);
				return actual;
			}
		} else {
			if (actual.left != null) {
				actual.left = put(info, actual.left);
				actual = balanceLeft(actual);
				updateBalance(actual);
				return actual;
			} else {
				actual.left = new MyNodeAvl<T>(info);
				updateBalance(actual);
				return actual;
			}
		}
	}

	/**
	 * Permite actualizar el factor de balanceo de la actual rama
	 *
	 * @param actual rama a la que actualizara el balanceo
	 */
	private void updateBalance(MyNodeAvl<T> actual) {
		if (actual.right != null) {
			if (actual.left != null) {
				actual.balance = Math.max(actual.right.balance, actual.left.balance) + 1;
			} else {
				actual.balance = actual.right.balance + 1;
			}
		} else if (actual.left != null) {
			actual.balance = actual.left.balance + 1;
		} else {
			actual.balance--;
		}
	}

	/**
	 * Revisa la rama actual para ver si debe ser balanceada a la derecha y de
	 * ser necesario realizada el balanceo
	 *
	 * @param actual rama que desea modificar
	 * @return rama modificada
	 */
	private MyNodeAvl<T> balanceRight(MyNodeAvl<T> actual) {
		if ((getBalance(actual.right) - getBalance(actual.left)) == 2) {
			if (actual.right.left != null) {
				return doubleRotationToTheRigh(actual);
			} else if (actual.right.right != null) {
				return simpleRotationToTheLeft(actual);
			} else if (actual.left.left != null) {
				return simpleRotationToTheRight(actual);
			}
		}
		return actual;
	}

	/**
	 * Revisa la rama actual para ver si debe ser balanceada a la izquierda y de
	 * ser necesario realizada el balanceo
	 *
	 * @param actual rama que desea modificar
	 * @return rama modificada
	 */
	private MyNodeAvl<T> balanceLeft(MyNodeAvl<T> actual) {
		if ((getBalance(actual.left) - getBalance(actual.right)) == 2) {
			if (actual.left.left != null) {
				return simpleRotationToTheRight(actual);
			} else if (actual.left.right != null) {
				return doubleRotationToTheLeft(actual);
			} else if (actual.right.right != null) {
				return simpleRotationToTheLeft(actual);
			}
		}
		return actual;
	}

	/**
	 * Permite obtener el balanceo del nodo actual
	 *
	 * @param actual nodo del cual se quiere obtener el balanceo
	 * @return si el nodo no es nulo retornara el balanceo de este, si es nulo
	 * retornara -1
	 */
	private int getBalance(MyNodeAvl<T> actual) {
		if (actual != null) {
			return actual.balance;
		} else {
			return -1;
		}
	}

	/**
	 * Permite dar una doble rotacion de la rama actual hacia la derecha
	 *
	 * @param actual rama a la que dara una doble rotacion
	 * @return rama modificada
	 */
	private MyNodeAvl<T> doubleRotationToTheRigh(MyNodeAvl<T> actual) {
		actual.right = simpleRotationToTheRight(actual.right);
		return simpleRotationToTheLeft(actual);
	}

	/**
	 * Permite dar una simple rotacion de la rama actual hacia la derecha
	 *
	 * @param actual rama a la que dara una simple rotacion
	 * @return rama modificada
	 */
	private MyNodeAvl<T> simpleRotationToTheRight(MyNodeAvl<T> actual) {
		MyNodeAvl<T> aux = actual.left;
		actual.left = aux.right;
		aux.right = actual;
		aux.right.balance = Math.max(getBalance(aux.right.left), getBalance(aux.right.right)) + 1;
		aux.balance = Math.max(getBalance(aux.right), getBalance(aux.left)) + 1;
		return aux;
	}

	/**
	 * Permite dar una simple rotacion de la rama actual hacia la izquierda
	 *
	 * @param actual rama a la que dara una simple rotacion
	 * @return rama modificada
	 */
	private MyNodeAvl<T> simpleRotationToTheLeft(MyNodeAvl<T> actual) {
		MyNodeAvl<T> aux = actual.right;
		actual.right = aux.left;
		aux.left = actual;
		aux.left.balance = Math.max(getBalance(aux.left.left), getBalance(aux.left.right)) + 1;
		aux.balance = Math.max(getBalance(aux.right), getBalance(aux.left)) + 1;
		return aux;
	}

	/**
	 * Permite dar una doble rotacion de la rama actual hacia la izquierda
	 *
	 * @param actual rama a la que dara una doble rotacion
	 * @return rama modificada
	 */
	private MyNodeAvl<T> doubleRotationToTheLeft(MyNodeAvl<T> actual) {
		actual.left = simpleRotationToTheLeft(actual.left);
		return simpleRotationToTheRight(actual);
	}

	/**
	 * Permite buscar y eliminar un elemento en la rama u hoja actual
	 *
	 * @param e elemento que desea eliminar
	 * @param actual rama de la que eliminara un elemento
	 * @return true si encontro el elemento
	 */
	private boolean remove(T e, MyNodeAvl<T> actual) {
		if (comparator.compare(e, actual.info) >= 0) {
			if (actual.right != null) {
				if (!e.equals(actual.right.info)) {
					boolean result = remove(e, actual.right);
					actual.right = balanceRight(actual.right);
					updateBalance(actual);
					return result;
				} else {
					removeRight(actual);
					return true;
				}
			}
			return false;
		} else {
			if (actual.left != null) {
				if (!e.equals(actual.left.info)) {
					boolean result = remove(e, actual.left);
					actual.left = balanceLeft(actual.left);
					updateBalance(actual);
					return result;
				} else {
					removeLeft(actual);
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Elimina la raiz del arbol
	 */
	private void removeRoot() {
		if (this.root.right != null) {
			if (this.root.left != null) {
				this.root.info = getMinorElement(this.root);
				updateBalance(this.root);
			} else {
				this.root = this.root.right;
			}
		} else if (this.root.left != null) {
			this.root = this.root.left;
		} else {
			this.root = null;
		}
	}

	/**
	 * Elimina el elemento que esta a la derecha de la rama actual
	 *
	 * @param actual rama actual
	 */
	private void removeRight(MyNodeAvl<T> actual) {
		if (actual.right.right != null) {
			if (actual.right.left != null) {
				actual.right.info = getMinorElement(actual.right);
				updateBalance(actual);
			} else {
				actual.right = actual.right.right;
				updateBalance(actual);
			}
		} else if (actual.right.left != null) {
			actual.right = actual.right.left;
			updateBalance(actual);
		} else {
			actual.right = null;
			updateBalance(actual);
		}
	}

	/**
	 * Elimina el elemento que esta a la izquierda de la rama actual
	 *
	 * @param actual rama actual
	 */
	private void removeLeft(MyNodeAvl<T> actual) {
		if (actual.left.left != null) {
			if (actual.left.right != null) {
				actual.left.info = getMinorElement(actual.left);
				updateBalance(actual);
			} else {
				actual.left = actual.left.left;
				updateBalance(actual);
			}
		} else if (actual.left.right != null) {
			actual.left = actual.left.right;
			updateBalance(actual);
		} else {
			actual.left = null;
			updateBalance(actual);
		}
	}

	/**
	 * Se encarga de obtener el elemento menor de la rama actual
	 *
	 * @param actual rama actual
	 * @return elemento de la rama menor
	 */
	private T getMinorElement(MyNodeAvl<T> actual) {
		if (actual.right.left != null) {
			MyNodeAvl<T> actualNode = actual.right;
			while (actualNode.left.left != null) {
				actualNode = actualNode.left;
			}
			T item = actualNode.left.info;
			removeLeft(actualNode);
			return item;
		} else {
			T item = actual.right.info;
			removeRight(actual);
			return item;
		}
	}

	/**
	 * Este metodo permite balancear la raiz del arbol
	 */
	private void balanceRoot() {
		if ((getBalance(this.root.right) - getBalance(this.root.left)) == 2) {
			if (this.root.right.left != null) {
				this.root = doubleRotationToTheRigh(this.root);
			} else if (this.root.right.right != null) {
				this.root = simpleRotationToTheLeft(this.root);
			} else if (this.root.left.left != null) {
				this.root = simpleRotationToTheRight(this.root);
			}
		} else if ((getBalance(this.root.left) - getBalance(this.root.right)) == 2) {
			if (this.root.left.left != null) {
				this.root = simpleRotationToTheRight(this.root);
			} else if (this.root.left.right != null) {
				this.root = doubleRotationToTheLeft(this.root);
			} else if (this.root.right.right != null) {
				this.root = simpleRotationToTheLeft(this.root);
			}
		}
	}
	
	/**
	 * obtiene el nodo raiz
	 * @return la riz del arbol
	 */
	public MyNodeAvl<T> getRoot() {
		return root;
	}
	
	
	/**
	 * Imprime el arbol por consola
	 */
	public void printTree() {
		int count = 0;
		showTree(root, count);
	}
	
	private void showTree(MyNodeAvl<T> actual, int count) {
		if(actual == null) {
			return;
		}else {
			showTree(actual.right, count+1);
			for (int i = 0; i < count; i++) {
				System.out.print("   ");
			}
			System.out.println(actual.getInfo());
			showTree(actual.left, count+1);
		}
	}
}