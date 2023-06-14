package com.example.clientside.Models;

import com.example.Game.Board;
import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.Service;
import com.example.serverSide.HostManager;

public class runClient {
    public static void main(String[] args) {
        HostManager h=new HostManager(8080);
        //boolean b=h.dictionaryLegal("Q,s1.txt,s2.txt,");
        Service p= new Service();
        Tile.Bag b=new Tile.Bag();
        Tile tile =b.getRand();
        Tile tile1 =b.getRand();
        Tile[] tiles= new Tile[]{tile,tile1};
        Word w=new Word(tiles,5,4,true);
        String s=p.WordToString(w);
        w=p.stringToWord(s);
       Tile[][] tilesMatrix = new Tile[15][15];
       String m=p.matrixToString(tilesMatrix);
       tilesMatrix=p.stringToMatrix(m);


    }
}
