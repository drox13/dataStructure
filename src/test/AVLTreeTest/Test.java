package test.AVLTreeTest;

import java.util.Comparator;

import avlTree.AvlTree;

/**
 * 
 * @author Dario Leonidas Baron
 *
 */
public class Test {
	public static void main(String[] args) {
		character();
		integer();
	}
	
	private static void integer() {
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		};
		AvlTree<Integer> avlTreeDro = new AvlTree<>(comparator);
		avlTreeDro.put(100);
		avlTreeDro.put(50);
		avlTreeDro.put(150);
		avlTreeDro.put(25);
		avlTreeDro.put(75);
		avlTreeDro.put(65);
		avlTreeDro.put(85);
		avlTreeDro.put(125);
		avlTreeDro.put(175);
		avlTreeDro.printTree();
	}

	private static void character() {
		Comparator<Character> comparator = new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				switch (o1) {
				case 'Á':
					o1 = 'A';
					break;
				case 'É':
					o1 = 'E';
					break;
				case 'Í':
					o1 = 'I';
					break;
				case 'Ó':
					o1 = 'O';
					break;
				case 'Ú':
					o1 = 'U';
					break;
				case 'Ñ':
					o1 = 'N';
				default:
					break;
				}
				return o1.compareTo(o2);
			}
		};

		AvlTree<Character> avlTreeDro = new AvlTree<>(comparator);

		System.out.println((avlTreeDro.isEmpty()==true)?"OK": "ERROR");
		avlTreeDro.put('Z');
		System.out.println((avlTreeDro.isEmpty()!=true)?"OK": "ERROR");
		System.out.println((avlTreeDro.getRoot().getInfo() == 'Z')? "OK": "Error");
		avlTreeDro.put('K');
		avlTreeDro.put('J');
		System.out.println((avlTreeDro.getRoot().getInfo() == 'K')? "OK": "Error");
		avlTreeDro.put('H');
		avlTreeDro.put('G');
		avlTreeDro.put('F');
		System.out.println((avlTreeDro.getRoot().getInfo() == 'H')? "OK": "Error");
		avlTreeDro.put('P');
		avlTreeDro.put('U');
		avlTreeDro.put('Y');
		avlTreeDro.put('W');
		avlTreeDro.put('Q');
		System.out.println((avlTreeDro.getRoot().getInfo() == 'K')? "OK": "Error");
		avlTreeDro.put('T');
		avlTreeDro.put('É');
		avlTreeDro.put('B');
		avlTreeDro.put('Á');
		avlTreeDro.put('S');
		avlTreeDro.put('N');
		avlTreeDro.put('E');
		avlTreeDro.put('L');
		avlTreeDro.put('Ñ');
		avlTreeDro.remove('J');
		avlTreeDro.remove('P');
		avlTreeDro.put('O');
		avlTreeDro.put('I');
		avlTreeDro.put('R');
		avlTreeDro.put('A');
		avlTreeDro.put('D');
		System.out.println((avlTreeDro.getRoot().getInfo() == 'Q')? "OK": "Error");
		avlTreeDro.printTree();

		System.out.println((avlTreeDro.get('Z') == 'Z')? "El dato existe": "Error");
	}
}