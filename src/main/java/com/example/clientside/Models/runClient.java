package com.example.clientside.Models;

import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.serverSide.HostManager;

public class runClient {
    public static void main(String[] args) {
        HostManager h=new HostManager(8080);
        h.dictionaryLegal("Q,shira,");
        //boolean b=h.dictionaryLegal("Q,s1.txt,s2.txt,");
       // PlayerModel p=new PlayerModel();
        Tile.Bag b=new Tile.Bag();
        Tile tile =b.getRand();
        Tile tile1 =b.getRand();
        Tile[] tiles= new Tile[]{tile,tile1};
        Word w=new Word(tiles,5,4,true);
        HostModeModel hm=new HostModeModel(8080);
       // hm.name="shira";
        //hm.tryToPlace(w);


        //String s=p.WordToString(w);
        //HostModeModel h=new HostModeModel();
      // h.dictionaryLegal()
    }
}
