package com.example.RunClasses;

import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.Service;
import com.example.clientside.Models.GuestModeModel;

public class checkRun2 {
    static Service service=new Service();
    public static void main(String[] args) {
        Tile.Bag b=new Tile.Bag();
        Tile tile =b.getRand();
        Tile tile1 =b.getRand();
        Tile[] tiles= new Tile[]{tile,tile1};
        Word w=new Word(tiles,5,4,true);
        String wd=service.WordToString(w);
        GuestModeModel guest2 = new GuestModeModel(8081, "guest2");
        guest2.tryToPlace("TC,8,9,F");
        //GuestModeModel guest4 = new GuestModeModel(8081, "guest4");
        //guest4.tryToPlace(wd);
        GuestModeModel guest5 = new GuestModeModel(8081, "guest5");
        guest5.tryToPlace(wd);
//        GuestModeModel guest3 = new GuestModeModel(8081, "guest3");
        System.out.println("play");
    }

}
