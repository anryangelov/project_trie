package project_trie.trie;

<<<<<<< HEAD
import java.io.Serializable;

public class Trie implements Serializable {
=======
import java.util.ArrayList;
>>>>>>> refactoring

	public Node top;
<<<<<<< HEAD
	private String stringResult;

=======
	int size;
	
>>>>>>> refactoring
	public Trie() {
		top = new Node();
		size = 0;
	}

	public String toString() {
<<<<<<< HEAD
		stringResult = "";
		traverseRecursivly(top);
		return stringResult;
	}

	private void traverseRecursivly(Node node) {
		String letter;
		if (node.value != null)
			stringResult += "--> " + "( " + node.value + " ) ";
		for (int i = 0; i < node.path.length; i++) {
			if (node.path[i] != null) {
				letter = Character.toString((char) (i + 97));
				stringResult += letter + " ";
				// if (node.path[i].value != null) res += "--> " +
				// node.path[i].value + "\n" + res.replace("[a-z]", " ");
				traverseRecursivly(node.path[i]);
			}
=======
		String res = "";
		ArrayList<String> words = new ArrayList<String>();
		listRecursivly(top, words, "", true);
		for (int i = 0; i < words.size(); i += 2) {
			res += words.get(i) + " - " + words.get(i+1) + "\n";
>>>>>>> refactoring
		}
		return res;
	}

	public void add(String key, String value) {
		addRecursivly(key, value, top);
		size++;
	}

	private void addRecursivly(String key, String value, Node node) {

		if (key.length() == 0) {
			node.value = value;
			return;
		}
		key = key.toLowerCase();
		Node nextNode;
		int ascii_int = key.charAt(0) - 97;
		key = key.substring(1, key.length());
		if (node.path[ascii_int] == null) {
			nextNode = new Node();
			node.path[ascii_int] = nextNode;
		} else {
			nextNode = node.path[ascii_int];
		}
		addRecursivly(key, value, nextNode);
	}

	public String get(String key) {
		Node node = getRrecursivly(key, top);
		if (node != null)
			return node.value;
		return null;
	}
<<<<<<< HEAD

=======
	
	public boolean has(String key) {
		return get(key) != null;
	}
	
>>>>>>> refactoring
	private Node getRrecursivly(String key, Node node) {
		if (key.length() == 0) {
			return node;
		}
		key = key.toLowerCase();
		int ascii_int = key.charAt(0) - 97;
		key = key.substring(1, key.length());
		if (node.path[ascii_int] != null) {
			return getRrecursivly(key, node.path[ascii_int]);
		}
		return null;
	}
<<<<<<< HEAD

	public boolean delete(String key) {
=======
	
	public boolean remove(String key) {
>>>>>>> refactoring
		Node node = getRrecursivly(key, top);
		if (node != null) {
			node.value = null;
			size--;
			return true;
		}
		return false;
	}
	
	public void update(String key, String value) {
		add(key, value);
	}
	
	private void listRecursivly(Node node, ArrayList<String> result, String word, boolean withValue) {
		char letter;
		for (int i = 0; i < node.path.length; i++) {
			if (node.path[i] != null ) {
				 letter = (char) (i + 97);
				 word += letter;
				 if (node.path[i].value != null) {
					 result.add(word);
					 if (withValue) result.add(node.path[i].value);
				 }
				 listRecursivly(node.path[i], result, word, withValue);
				 word = word.substring(0, word.length() - 1);
			}
		}
	}
	
	public ArrayList<String> list() {
		ArrayList<String> words = new ArrayList<String>();
		listRecursivly(top, words, "", false);
		return words;
	}
	
	public ArrayList<String> list(String root) {
		Node node = getRrecursivly(root, top);
		ArrayList<String> words = new ArrayList<String>();
		listRecursivly(node, words, root, false);
		return words;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		top = new Node();
	}
}
