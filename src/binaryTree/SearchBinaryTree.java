package binaryTree;

import java.util.Comparator;

import exception.MyException;
import queue_Stack_SimpleList.MyQueue;
import queue_Stack_SimpleList.MySimpleLinkendList;

/**
 * Clase que permite crear un arbol binario
 * @author Dario Baron
 * @param <T> tipo de dato del arbol binario
 */
public class SearchBinaryTree <T>{
	private static final String NO_DATA_FOUND = "No se ha encontrado un nodo con la informacion especificada";
	private NodeTree<T> root;
	private Comparator<T> comparator;

	/**
	 * permite crear un arbol binario
	 * @param comparator por medio del cual se va ha decidir
	 * la forma en que se llenara el arbol
	 */
	public SearchBinaryTree(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	/**
	 * permite saber si el arbol esta vacio
	 * @return
	 */
	public boolean isEmpty() {
		return root == null;
	}
	/**
	 * permite agegar un nodo al arbol
	 * @param info informacion que contendra el nodo
	 */
	public void add(T info) {
		if(root != null) {
			addRecursive(new NodeTree<T>(info), root);
		}else {
			root = new NodeTree<>(info);
		}
	}

	/**
	 * Agrega recursivamente un nodo al arbol
	 * @param newData nuevo nodo con la nueva informacion
	 * @param reference nodo referencia donde se encuentra la recursividad
	 */
	private void addRecursive(NodeTree<T> newData, NodeTree<T> reference) {
		int result = comparator.compare(newData.info, reference.info);
		if(result < 0) {
			if(reference.left != null) {
				addRecursive(newData, reference.left);
			}else {
				reference.left = newData;
			}
		}else if(result > 0) {
			if(reference.right != null) {
				addRecursive(newData, reference.right);
			}else {
				reference.right = newData;
			}
		}
	}

	/**
	 * permite saber si una informacion se encuentra en el arbol
	 * @param info informacion que deseamos buscar
	 * @return verdadero si la informacion existe, false de lo contrario
	 */
	public boolean isExist(T info) {
		NodeTree<T> nodeSearch = new NodeTree<>(info);
		return (seachRecursive(nodeSearch, root) != null)? true: false;
	}

	/**
	 * Busca una unformacion dentro del arbol
	 * @param info Informacion que desea buscar
	 * @return el Nodo que contiene dicha informacion
	 * @throws se lanza cuando no encuentra la informacion deltro del arbol
	 */
	public NodeTree<T> getNode(T info) throws MyException{
		NodeTree<T> node = seachRecursive(new NodeTree<>(info), root);
		if(node != null) {
			return node;
		}else {
			throw new MyException(NO_DATA_FOUND);
		}
	}

	/**
	 * Busca el nodo que contenga la informacion
	 * @param nodeSearch nodo con la informacion que se desea buscar
	 * @param base nodo de referencia donde se encuentra en el momento
	 * @return el nodo con la informacion
	 */
	private NodeTree<T> seachRecursive(NodeTree<T>nodeSearch, NodeTree<T> base) {
		NodeTree<T> nodeResult = null; // se puede igualar a base(funciona como else)
		if(base != null) {
			int diference = comparator.compare(nodeSearch.info, base.info);
			if(diference < 0) {
				nodeResult = seachRecursive(nodeSearch, base.left);
			}else if(diference > 0) {
				nodeResult = seachRecursive(nodeSearch, base.right);
			}else {
				nodeResult = base; //condicion de salida
			}
		}
		return nodeResult;
	}
	/**
	 * permite borrar un dato del arbol
	 * @param info onformacion que se desea borrar
	 */
	public void deleat(T info) {
		deleatRecursive(info, root);
	}

	/**
	 * busca recursivamente el nodo que contiene la informacion que se desea borrar
	 * @param info informacion que deseamos borrar
	 * @param base nodo en donde se encuentra la recursividad
	 * @return el nodo padre del nodo que se borro
	 */
	private NodeTree<T> deleatRecursive(T info, NodeTree<T> base) {
		if(base != null) {
			int value = comparator.compare(info, base.info);
			if(value < 0) {
				NodeTree<T> elementReplace = deleatRecursive(info, base.left);
				base.left = elementReplace;
			}else if(value > 0) {
				NodeTree<T> elementRemplace = deleatRecursive(info, base.right);
				base.right = elementRemplace;
			}else { // condicion de salida (busqueda del dato
				if(base.left != null && base.right != null) { //tiene hijo izq y derecho
					base.info = getElemetApropiate(base);
				}else if(base.left != null) { //tiene hijo izq
					return base.left;
				}else if(base.right != null) { // tiene hijo derecho
					return base.right;
				}else { //es un nodo hoja
					return base.right;
				}
			}
		}
		return base;
	}

	/**
	 * Determina el elemento mas cercano al que va ha ser borrado
	 * para que este sea el que remplace al dato que se va ha borrar
	 * @param base nodo del cual se desea identificar su dato mas cercano
	 * @return el dato mas cercano del nodo base
	 */
	public T getElemetApropiate(NodeTree<T> base) {
		T minSubTreeRight = getMinSubTreeRight(base).info;
		T maxSubTreeLeft = getMaxSubTreeLeft(base).info;
		int min = Math.abs(comparator.compare(base.info, minSubTreeRight));
		int max = Math.abs(comparator.compare(base.info, maxSubTreeLeft));
		if(max < min ) {
			deleatRecursive(maxSubTreeLeft, base);
			return maxSubTreeLeft;
		}
		deleatRecursive(minSubTreeRight, base);
		return minSubTreeRight;
	}

	/**
	 * Permite encontrar el nodo con menor valor del sub arbol derecho de un nodo especifico
	 * @param base nodo (sub arbol) del que se desea encontrar el nodo con el dato mas pequeño
	 * @return el nodo con el dato mas pequeño del sub arbol derecho
	 */
	private NodeTree<T> getMinSubTreeRight(NodeTree<T> base) {
		NodeTree<T> aux = base.right;
		while(aux.left != null) {
			aux = aux.left;
		}
		return aux;
	}

	/**
	 * Permite encontrar el nodo con maximo valor de un sub arbol izquierdo 
	 * @param base nodo (sub arbol) del que se desea encontrar el nodo con el dato mas grande
	 * @return el nodo con el dato mas grande del sub arbol izquierdo
	 */
	private NodeTree<T> getMaxSubTreeLeft(NodeTree<T> base){
		NodeTree<T> aux = base.left;
		while(aux.right != null) {
			aux = aux.right;
		}
		return aux;
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
	private void inOrder(MySimpleLinkendList<T> list, NodeTree<T> base) {
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
	private void preOrder(MySimpleLinkendList<T> preOrdrList, NodeTree<T> base) {
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
	private void posOrder(MySimpleLinkendList<T> posOrderList, NodeTree<T> base) {
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
	public  MySimpleLinkendList<NodeTree<T>> amplitudeTour(){
		System.out.println("recorrido en amplitud o anchura");		
		MyQueue<NodeTree<T>> cola = new MyQueue<>();
		MySimpleLinkendList<NodeTree<T>> result = new MySimpleLinkendList<>();
		NodeTree<T> base;
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
		NodeTree<T> nodeSearch = seachRecursive(new NodeTree<>(info), root);
		if(nodeSearch == null) {
			throw new MyException(NO_DATA_FOUND);
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
	private int height(NodeTree<T> base) {
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
	public int heightNode(NodeTree<T> base) {
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
	 *calacula la profundida de un nodo con una informacion especifoca 
	 * @param info informacion que debera contener el nodo
	 * @param base nodo donde se encuentra en el momento (apuntador)
	 * @return la profundidad donde se encontro la informacion
	 */
	private int depthRecursive(T info, NodeTree<T> base) {
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
	public NodeTree<T> getFather(T info) {
		NodeTree<T> searchDate = root;
		NodeTree<T> father = root;
		while(searchDate != null && comparator.compare(info, searchDate.info) != 0) {
			father = searchDate;
			if(comparator.compare(info, searchDate.info) < 0) {
				searchDate = searchDate.left;
			}else if(comparator.compare(info, searchDate.info ) > 0){
				searchDate = searchDate.right;				
			}
		}
		return father;
	}

	/**
	 * Optiene la cantidad de niveles que hay por debajo de un nodo
	 * @param node nodo del que se desea obtener el numero de niveles que hay debajo
	 * @return cantidad de niveles por debajo del nodo
	 */
	public int quantityLevelDown(NodeTree<T> node) {
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
	public int quatityNodes(NodeTree<T> node) {
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
	public int quantityChild(NodeTree<T> node) {
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
	private int quantityLeafRecursive(NodeTree<T> base) {
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
	 * Imprime el arbol por consola
	 */
	public void showTree() {
		int count = 0;
		showTree(root, count);
	}

	/**
	 * Inprime recursivamente el arbol
	 * @param actual
	 * @param count
	 */
	private void showTree(NodeTree<T> actual, int count) {
		if(actual == null) {
			return;
		}else {
			showTree(actual.right, count+1);
			for (int i = 0; i < count; i++) {
				System.out.print("   ");
			}
			System.out.println(actual.info);
			showTree(actual.left, count+1);
		}
	}

	public NodeTree<T> getRoot() {
		return root;
	}
}	