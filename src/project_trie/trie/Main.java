package project_trie.trie;

public class Main {

	public static void main(String[] args) {
		
		Trie data = new Trie();
		System.out.println(data.isEmpty());
		data.add("bbzz", "meaning of bbzz");
		data.add("cbzd", "meaning of cbzd");
		data.add("kkzz", "meaning of kkzz");
		data.add("kkkmm", "meaning of kkkmm");
		data.add("kkccccc", "meaning of kkccccc");
		data.add("funrr", "meaning of funrr");
		data.add("function", "find yourself");
		data.add("funny", "new meaning");
		data.add("abc", "meaning of abc");
		data.add("abczz", "meaning of abczz");
		data.add("abzd", "meaning of abzd");
		data.add("bbbbb", "many b");
		System.out.println(data);
		System.out.println(data.get("abc"));
		System.out.println(data.get("abczz"));
		System.out.println(data.get("ab"));
		System.out.println(data.get("afdj"));
		System.out.println(data.get("funny"));
		System.out.println(data.remove("funny"));
		System.out.println(data.get("funny"));
		System.out.println(data.remove("opaopa"));
		System.out.println(data.list());
		System.out.println(data.list("ab"));
		System.out.println(data.size());
		data.update("abczz", "meaning of abcd is changed");
		System.out.println(data);
		System.out.println(data.list("kk"));
	}

}
