package com.example.RunClasses;

import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.Service;
import com.example.clientside.Models.GuestModeModel;

public class checkRun3 {

        public static void main(String[] args) {
        Tile.Bag b=new Tile.Bag();
        Tile tile =b.getRand();
        Tile tile1 =b.getRand();
        Tile[] tiles= new Tile[]{tile,tile1};
        Service service=new Service();
        Word w=new Word(tiles,5,4,true);
        String wd=service.WordToString(w);
         GuestModeModel guest3 = new GuestModeModel(8081, "guest3");
         guest3.tryToPlace(wd);

        }

    }


