package avlTree;

import java.util.Comparator;
import java.util.NoSuchElementException;

import exception.MyException;
import queue_Stack_SimpleList.MyQueue;
import queue_Stack_SimpleList.MySimpleLinkendList;

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
	public MyNodeAvl<T> get(T info) {
		if (this.root != null) {
			MyNodeAvl<T> actual = this.root;
			while (actual != null) {
				if (comparator.compare(info, actual.info) != 0) {
					if (comparator.compare(info, actual.info) > 0) {
						actual = actual.right;
					} else {
						actual = actual.left;
					}
				} else {
					return actual;
				}
			}
			return null;
		} else {
			throw new NoSuchElementException(WARNING_NOT_ELEMENTS);
		}
	}

	/**
	 * Verifica la existencia de dato
	 * @param info dato a buscar
	 * @return true si existe de lo contrario, false
	 */
	public boolean isExist(T info) {
		MyNodeAvl<T> find= get(info);
		return find != null;
	}

	/**
	 * permite eliminar un dato
	 * @param info dato a eliminar
	 * @return true si se logro eliminar el dato
	 */
	public boolean remove(T info) {
		if (this.root != null) {
			int diference = comparator.compare(info, this.root.info);
			if (diference != 0) {
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
		if (comparator.compare(info, actual.info) > 0) {
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
		} else if (comparator.compare(info, actual.info) < 0) {
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
		return actual;
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
				if (comparator.compare(e, actual.right.info) != 0) {
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
				if (comparator.compare(e, actual.left.info) != 0) {
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

	/**
	 * permite crear una lista que guarda el recorrido inOrder
	 * izq-raiz-derecha
	 * @return una lista Simple con los datos en inOrder
	 */
	public MySimpleLinkendList<T> inOrder(){
		System.out.println("recorrido inOrder");
		MySimpleLinkendList<T> inOrderList = new MySimpleLinkendList<>();
		inOrder(inOrderList, root);
		return inOrderList;
	}

	/**
	 * recorre el arbol recursivamente para ir agregando a la lista
	 * visitar sub arbol izquierdo, visitar el nodo raiz, visitar el subarbol derecho
	 * @param list Lista en que se va ha guardar el recorrido en inOrder
	 * @param base Nodo donde se posiciona actualmente
	 */
	private void inOrder(MySimpleLinkendList<T> list, MyNodeAvl<T> base) {
		if(base != null) {
			inOrder(list, base.left);
			list.addToTail(base.info);
			inOrder(list, base.right);
		}
	}

	/**
	 * Permite obtener una lista con el recorrido en preOrder
	 * raiz-izq-derecho
	 * @return una lista simple con el recorrido en preOrder
	 */
	public MySimpleLinkendList<T> preOrder(){
		System.out.println("recorrido en preOrder");
		MySimpleLinkendList<T> preOrderList = new MySimpleLinkendList<>();
		preOrder(preOrderList, root);
		return preOrderList;
	}

	/**
	 * Recorre el arbol de manera recursiva de manera preOrder
	 * @param preOrdrList Lista en que se guardara el recorrido
	 * @param base Nodo en el que va el recorrido
	 */
	private void preOrder(MySimpleLinkendList<T> preOrdrList, MyNodeAvl<T> base) {
		if(base != null) {
			preOrdrList.addToTail(base.info);
			preOrder(preOrdrList, base.left);
			preOrder(preOrdrList, base.right);
		}
	}

	/**
	 * Permite obtener una lista simple con el recorrido en posOrder
	 * @return una lista simple con el recorrido
	 */
	public MySimpleLinkendList<T> posOrder(){
		System.out.println("Recorrigo en posOrder");
		MySimpleLinkendList<T> posOrderList = new MySimpleLinkendList<>();
		posOrder(posOrderList, root);
		return posOrderList;
	}

	/**
	 * recorre el arbol recursivamente en posOrder
	 * izq- derecha- raiz
	 * @param posOrderList lista simple que guardara el recorrido
	 * @param base nodo en el que va el recorrido
	 */
	private void posOrder(MySimpleLinkendList<T> posOrderList, MyNodeAvl<T> base) {
		if(base != null) {
			posOrder(posOrderList, base.left);
			posOrder(posOrderList, base.right);
			posOrderList.addToTail(base.info);
		}
	}

	/**
	 * Permite optener el recorrido en amplitud del arbol
	 * @return una Lista Simple con los nodos ordenados de acuerdo al recorrido por niveles
	 */
	public  MySimpleLinkendList<MyNodeAvl<T>> amplitudeTour(){
		System.out.println("recorrido en amplitud o anchura");		
		MyQueue<MyNodeAvl<T>> cola = new MyQueue<>();
		MySimpleLinkendList<MyNodeAvl<T>> result = new MySimpleLinkendList<>();
		MyNodeAvl<T> base;
		if (root != null){
			cola.putToQueue(root); 
			while (!cola.isEmtry()){
				base = cola.getToQueue();
				result.addToTail(base);
				if (base.left != null){
					cola.putToQueue(base.left);
				}
				if (base.right!= null){ 
					cola.putToQueue(base.right);
				}
			}
		}
		return result;
	}

	/**
	 * Permite Saber si es un nodo hoja
	 * de no existir la informacion retorna falso
	 * @param info informacion que se desea berificar
	 * @return verdadero si es una hoja, de lo contrario false
	 * @throws MyException se lanza cuando no encuentra el dato
	 */
	public boolean isLeaf(T info) throws MyException {
		MyNodeAvl<T> nodeSearch = get(info);
		if(nodeSearch == null) {
			throw new MyException(WARNING_NOT_ELEMENTS);
		}
		return nodeSearch.left == null && nodeSearch.right == null;			
	}

	/**
	 * Obtiene la altura del arbol
	 * @return la altura del arbol, tienen en cuenta la raiz
	 */
	public int getHeightTree() {
		return height(root);
	}

	/**
	 * Permite calcular la altura  de un nodo
	 * @param base nodo del que se desea calcular la altura
	 * @return la altura a la que se encuentra el nodo
	 */
	private int height(MyNodeAvl<T> base) {
		int level = 0; 
		if(base != null) {
			level = 1; //para tener en cuenta la raiz
			if(!isLeaf(base.info)) {
				if(base.left != null) {
					level = Math.max(level, height(base.left));
				}
				if(base.right != null) {
					level = Math.max(level, height(base.right));
				}
				level++; 
			}	
		}
		return level;
	}

	/**
	 * Permite optener la altura de un nodo
	 * otro metodo
	 * la altura se refiere a cuantos niveles hay
	 * se toma la raiz como nivel 1
	 * @param base nodo del arbol del que se desea conocer la altura
	 * @return la altura del arbol
	 */
	public int heightNode(MyNodeAvl<T> base) {
		return base == null ? 0:((heightNode(base.right) > heightNode(base.left))?
				heightNode(base.right) + 1:heightNode( base.left) + 1);
	}

	/**
	 * Permite obtener el peso del arbol
	 * el peso es el numero de nodos que tiene el arbol
	 * @return el numero de nodos en el arbol
	 */
	public int getWeight() {
		return preOrder().sizeList();
	}

	/**
	 * determina la profundidad del nodo
	 * la profundidad hace referencia a cuantos nodos tiene por encima nodos padre)
	 * la profundidad del nodo se puede hayar teniendo en cuenta el nivel al que esta el nodo
	 * @param info informacion de la que deseamos conocer su profundidad
	 * @return la profundidad a la que esta la informacion, de no existir dicha informacion
	 * retorna -1
	 */
	public int depth(T info) {
		if(isExist(info)) {
			return depthRecursive(info, root);
		}else {
			return -1;
		}
	}

	/**
	 *calacula la profundidad de un nodo con una informacion especifica 
	 * @param info informacion que debera contener el nodo
	 * @param base nodo donde se encuentra en el momento (apuntador)
	 * @return la profundidad donde se encontro la informacion
	 */
	private int depthRecursive(T info, MyNodeAvl<T> base) {
		int level = 0; // condicion de salida reemplaza al else
		if(base != null) {
			int diference = comparator.compare(info, base.info);
			if(diference < 0) {
				level = depthRecursive(info, base.left) + 1;
			}else if(diference > 0) {
				level = depthRecursive(info, base.right) + 1;
			}
		}
		return level;
	}

	/**
	 * obtienen el nodo padre de la indormacion deseada
	 * @param info inforcacion de la que se desea obtener el padre
	 * @return el nodo padre de la informacion
	 * si la informacion no existe reotarna el nodo que seria su padre
	 * si la informacion es la de la raiz retorna como padre la misma raiz
	 */
	public MyNodeAvl<T> getFather(T info) {
		MyNodeAvl<T> searchData = root;
		MyNodeAvl<T> father = root;
		while(searchData != null && comparator.compare(info, searchData.info) != 0) {
			father = searchData;
			if(comparator.compare(info, searchData.info) < 0) {
				searchData = searchData.left;
			}else if(comparator.compare(info, searchData.info ) > 0){
				searchData = searchData.right;				
			}
		}
		return father;
	}

	/**
	 * Optiene la cantidad de niveles que hay por debajo de un nodo
	 * @param node nodo del que se desea obtener el numero de niveles que hay debajo
	 * @return cantidad de niveles por debajo del nodo
	 */
	public int quantityLevelDown(MyNodeAvl<T> node) {
		if(node != null) {
			if(node.left!= null && node.right!= null) {
				return quantityLevelDown(node.left) + quantityLevelDown(node.right)+1;
			}else {
				return quantityLevelDown(node.left) + quantityLevelDown(node.right);
			}
		}
		return 0;
	}

	/**
	 * cuenta los nodos que hay apartir de un nodo dado
	 * cuenta cuantos hijos tiene un nodo + el nodo propio
	 * @param node nodo desde el que desea contar
	 * @return el numero de nodos existentes, incluye el nodo inicial
	 */
	public int quatityNodes(MyNodeAvl<T> node) {
		int childs = 1;
		if(node.left != null) {
			childs += quatityNodes(node.left);
		}
		if(node.right != null) {
			childs += quatityNodes(node.right);
		}
		return childs;
	}

	/**
	 * obtiene el numero de hijos que tiene un nodo en especifico
	 * @param node nodo del que desea contar sus hijos
	 * @return el numero de nodos hijos
	 */
	public int quantityChild(MyNodeAvl<T> node) {
		return quatityNodes(node) - 1;
	}

	/**
	 * Obtiene el numero de hojas en el arbol
	 * @return el numero de nodos hoja
	 */
	public int quantityLeaf() {
		return quantityLeafRecursive(root);
	}

	/**
	 * recorre el arbol recursivamente
	 * para contar las hojas
	 * @param base nodo en el que va la recursividad
	 * @return el numero de hojas
	 */
	private int quantityLeafRecursive(MyNodeAvl<T> base) {
		int contador = 0;
		if(isLeaf(base.info)) {
			contador++;
		}
		if(base.left != null) {
			contador += quantityLeafRecursive(base.left);
		}
		if(base.right != null) {
			contador += quantityLeafRecursive(base.right);
		}
		return contador;
	}
	/**
	 * obtiene el valor menor dentro del arbol
	 * @returnel valor menor
	 */
	public T leastValueItem() {
		MyNodeAvl<T> actualNode = root;
		while (actualNode.left != null) {
			actualNode = actualNode.left;
		}
		return actualNode.getInfo();
	}
	/**
	 * obtiene el valor menor dentro del arbol
	 * @return valor mayor
	 */
	public Object highestValueItem() {
		MyNodeAvl<T> actualNode = root;
		while (actualNode.right != null) {
			actualNode = actualNode.right;
		}
		return actualNode.getInfo();
	}
}