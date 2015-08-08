package project_trie.trie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class FileManager {
	private Trie dictionary;

	public FileManager(String file) {
		File f = new File(file);
		if (!f.exists()) {
			JOptionPane.showMessageDialog(null, "Dictionary is Empty");
			return;
		} else {
			try {
				dictionary = deserialize(f.getName());
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@SuppressWarnings("resource")
	public void serialize(Trie words, String fileName) throws IOException {
		try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(words);
		}
	}

	@SuppressWarnings({ "resource" })
	private Trie deserialize(String fileName) throws IOException,
			ClassNotFoundException {
		try (FileInputStream fileIn = new FileInputStream(fileName)) {
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			Trie data = (Trie) objIn.readObject();
			return data;
		}
	}

	public Trie getDictionary() {
		return dictionary;
	}
}
