package com.example.serverSide;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class BookScrabbleHandler implements IClientHandler {

	BufferedReader in;
	PrintWriter out;
	DictionaryManager DM;

	@Override
	public void handleClient(InputStream inFromclient, OutputStream outToClient) {
		DictionaryManager DM = DictionaryManager.get();
		try {
			in = new BufferedReader(new InputStreamReader(inFromclient)); // remove the letter
			out = new PrintWriter(outToClient, true);
			String line = in.readLine();
			System.out.println("line "+line);

			char key = line.charAt(0);
			String[] lineAsList = line.split(",");
			String[] args = Arrays.copyOfRange(lineAsList, 1, lineAsList.length);
			if (key == 'Q') {
				out.println(DM.query(args));
			} else if (key == 'C') {
				out.println(DM.challenge(args));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
	}

}