package project_trie.trie;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileMenager {
	 @SuppressWarnings("resource")
	    public static void serialize(Trie words, String fileName) throws IOException {
	        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
	            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
	            objOut.writeObject(words);
	        }
	    }

	    @SuppressWarnings({ "resource" })
	    public static Trie deserialize(String fileName) throws IOException, ClassNotFoundException {
	        try (FileInputStream fileIn = new FileInputStream(fileName)) {
	            ObjectInputStream objIn = new ObjectInputStream(fileIn);
	            Trie coffe =  (Trie) objIn.readObject();
	            return coffe;
	        }
	    }
}
