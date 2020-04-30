package doubleList;

/**
 * Permite crear una lista circular doblemente enlazada
 * @author Dario Baron
 *
 * @param <T> tipo de dato de la lista circular doblemente enlazada
 */
public class MyDoubleLinkendCircularList<T> {
	private MyNodeDouble<T> first;
	private MyNodeDouble<T> last;

	/**
	 * Permite ver si la lista circular doble esta vacia
	 * @return verdader si esta vacia, de lo contrario falso
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Permite Agregar un dato a la lista Dcircular doble
	 * @param info informacion que se desea agregar
	 */
	public void add(T info) {
		if(first != null) {
			MyNodeDouble<T> newNode = new MyNodeDouble<>(info, first, last);
			last.next = newNode;
			last = newNode;
			first.previus = newNode;
		}else {
			first = last = new MyNodeDouble<>(info);
			first.next = first.previus = first;
		}
	}
	/**
	 * Permite remover un dato de la lista
	 * @param info informacion que deseamos remover
	 */
	public void remove(T info) {
		MyNodeDouble<T> cursor = first;
		if(cursor != null) {
			do {
				if(cursor.info.equals(info)){
					if(cursor.equals(first)) {
						if(cursor.next == first && cursor.previus == last) {
							first = last = null;
							return;
						}else {
							first = first.next;
							first.previus = last;
							last.next = first;
						}
					}else if(cursor.equals(last)) {
						last = last.previus;
						last.next = first;
						first.previus = last;
					}else {
						cursor.next.previus = cursor.previus;
						cursor.previus.next = cursor.next;
						return;
					}
				}
				cursor = cursor.next;
			} while (cursor != first);
		}
	}
	
	/**
	 * Permite buscar una informacion en la lista
	 * @param info informacion que deseamos buscar
	 * @return retorna el nodo que contiene dicha informacion
	 */
	private MyNodeDouble<T> find(T info){
		MyNodeDouble<T> nodeSearch = first;
		while(nodeSearch != last.next) {
			if(info.equals(nodeSearch.info)) {
				return nodeSearch;
			}else {
				nodeSearch = nodeSearch.next;				
			}
		}
		if(!info.equals(nodeSearch.info)) {
			nodeSearch = null;
		}
		return nodeSearch;
	}
	
	/**
	 * Permite saber si existe una informacion den la lista
	 * @param info informacion que se desea buscar
	 * @return verdadero si se encuentra, de lo contrario false
	 */
	public boolean isExist(T info) {
		return (find(info) != null)? true: false;
	}

	/**
	 * Permite conocer el tamaño de la lista
	 * @return el tamaño de la lista
	 */
	public int sizeList() {
		int count = 0;
		if(first != null) {
			MyNodeDouble<T> iterator = first;
			do {
				count++;
				iterator = iterator.next;
			} while (iterator != first);
		}
		return count;
	}

	/**
	 * permite ver la lista en orden
	 */
	public void showListOrder() {
		System.out.println("--- Lista en Orden ---");
		if(first != null) {
			MyNodeDouble<T> iterator = first;
			do {
				System.out.println(iterator);
				iterator = iterator.next;
			} while (iterator != first);			
		}
	}

	/**
	 * permite ver la lista invertida
	 */
	public void showListInverse() {
		System.out.println("--- Lista en Inverso ---");
		if(last != null) {
			MyNodeDouble<T> iterator = last;
			do {
				System.out.println(iterator);
				iterator = iterator.previus;
			} while (iterator != last);
		}
	}

	public MyNodeDouble<T> getFirst() {
		return first;
	}

	public MyNodeDouble<T> getLast() {
		return last;
	}
}