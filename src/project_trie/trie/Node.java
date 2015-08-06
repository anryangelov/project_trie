package project_trie.trie;

import java.io.Serializable;

public class Node implements Serializable {
	public Node[] path;
	public String value;

	public Node() {
		path = new Node[26];
		value = null;
	}

	public Node(String value) {
		this.value = value;
		path = new Node[26];
	}
}
