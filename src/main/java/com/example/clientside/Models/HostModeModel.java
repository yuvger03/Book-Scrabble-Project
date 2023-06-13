package com.example.clientside.Models;

import com.example.serverSide.GuestHandler;
import com.example.serverSide.MyServer;

import java.io.PrintWriter;
import java.util.*;

public class HostModeModel extends PlayerModel {
    MyServer hostServer;

    public HostModeModel(int gameServer) {
        Random r = new Random();
        this.serverPort = 6000 + r.nextInt(1000);
        hostServer = new MyServer(serverPort, new GuestHandler(gameServer), 4);
        hostServer.start();
        //System.out.println("server started");
        Scanner s = new Scanner(System.in);
        String input;
        do {
            input = s.next();
        } while (!input.equals("stop"));
        s.close();
        hostServer.close();
        System.out.println("server stopped");
    }

    public void close() {
        inFromServer.close();
        outToServer.close();
        hostServer.close();
    }
    //TODO: think of good location
    public static String[] writeFileWord(String file_name, ArrayList<TileModel> p_tiles) {
        int word_len = p_tiles.size();
        String txt[]=new String[word_len];
        TileModel tile;
        for(int i=0;i<txt.length;i++) {
            tile = p_tiles.get(i);
            txt[i] = ""+ tile.getLetter();
        }
        try {
            PrintWriter out=new PrintWriter(new FileWriter(name));
            for(String s : txt) {
                out.print(s+" ");
            }
            out.println();
            out.close();
        }catch(Exception e) {}

        return txt;
    }
}



