package com.example.serverSide;

import java.util.Arrays;
import java.util.HashMap;

public class DictionaryManager {

	private static DictionaryManager DM = null;
	private HashMap<String, Dictionary> Dictionaries;

	private DictionaryManager() {
		Dictionaries = new HashMap<String, Dictionary>();
	}

	public static DictionaryManager get() {
		if (DM == null) {
			DM = new DictionaryManager();
		}
		return DM;
	}

	public boolean query(String... args) { // query for all books in Dictionaries or in books?
		// System.out.println("args len is: " + args.length);
		String[] books = Arrays.copyOfRange(args, 0, args.length - 1);
		for (String book : books) {
			if (!Dictionaries.containsKey(book)) {
				// System.out.println("New book-" + book);
				Dictionaries.put(book, new Dictionary(book));
			}

		}
		// System.out.println("books len is: " + books.length);
		String word = args[args.length - 1];
		// System.out.println("word: " + word);

		boolean result = false;
		for (String book : books) {
			if (Dictionaries.get(book).query(word))
				result = true;
		}

		return result;
	}

	public boolean challenge(String... args) {
		// System.out.println("args len is: " + args.length);
		String[] books = Arrays.copyOfRange(args, 0, args.length - 1);
		for (String book : books) {
			if (!Dictionaries.containsKey(book)) {
				// System.out.println("New book-" + book);
				Dictionaries.put(book, new Dictionary(book));
			}

		}
		// System.out.println("books len is: " + books.length);
		String word = args[args.length - 1];
		// System.out.println("word: " + word);

		boolean result = false;
		for (String book : books) {
			if (Dictionaries.get(book).challenge(word))
				result = true;
		}

		return result;
	}

	public int getSize() {
		return Dictionaries.size();
	}

}