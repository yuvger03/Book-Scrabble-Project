package com.example.clientside.view;

import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.clientside.Models.GuestModeModel;

public class checkRun3 {

        public static void main(String[] args) {
        Tile.Bag b=new Tile.Bag();
        Tile tile =b.getRand();
        Tile tile1 =b.getRand();
        Tile[] tiles= new Tile[]{tile,tile1};
        Word w=new Word(tiles,5,4,true);
         GuestModeModel guest3 = new GuestModeModel(8081, "guest3");
         guest3.tryToPlace(w);

        }

    }

