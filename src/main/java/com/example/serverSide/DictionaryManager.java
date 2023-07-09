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
		String[] books = Arrays.copyOfRange(args, 0, args.length - 1);
		for (String book : books) {
			if (!Dictionaries.containsKey(book)) {
				Dictionaries.put(book, new Dictionary(book));
			}

		}
		String word = args[args.length - 1];
		boolean result = false;
		for (String book : books) {
			if (Dictionaries.get(book).query(word))
				result = true;
		}

		return result;
	}

	public boolean challenge(String... args) {
		String[] books = Arrays.copyOfRange(args, 0, args.length - 1);
		for (String book : books) {
			if (!Dictionaries.containsKey(book)) {
				Dictionaries.put(book, new Dictionary(book));
			}

		}
		String word = args[args.length - 1];

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