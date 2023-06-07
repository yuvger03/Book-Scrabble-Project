package com.example.serverSide;

public interface CacheReplacementPolicy {
	void add(String word);

	String remove();

}
