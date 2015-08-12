package project_trie.trie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class FileManager {
	public static Trie dictionary;
	private static final String FILE_NAME = "dictionary.ser";

	public FileManager() {
		File f = new File(FILE_NAME);
		if (!f.exists()) {
			JOptionPane.showMessageDialog(null, "Dictionary is Empty");
			dictionary = new Trie();
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

	public  static void saveChanges() {
		// save changes everytime when word is added or removed
		try {
			serialize(dictionary, FILE_NAME);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	private static void serialize(Trie words, String fileName) throws IOException {
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
