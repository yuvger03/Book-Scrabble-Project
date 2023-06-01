
package com.example.clientside.Models;

public class BoardModel {

    // indexes
    final byte dl = 2; // double letter
    final byte tl = 3; // triple letter
    final byte dw = 20; // double word
    final byte tw = 30; // triple word

    private byte[][] bonus = {
            { tw, 0, 0, dl, 0, 0, 0, tw, 0, 0, 0, dl, 0, 0, tw },
            { 0, dw, 0, 0, 0, tl, 0, 0, 0, tl, 0, 0, 0, dw, 0 },
            { 0, 0, dw, 0, 0, 0, dl, 0, dl, 0, 0, 0, dw, 0, 0 },
            { dl, 0, 0, dw, 0, 0, 0, dl, 0, 0, 0, dw, 0, 0, dl },
            { 0, 0, 0, 0, dw, 0, 0, 0, 0, 0, dw, 0, 0, 0, 0 },
            { 0, tl, 0, 0, 0, tl, 0, 0, 0, tl, 0, 0, 0, tl, 0 },
            { 0, 0, dl, 0, 0, 0, dl, 0, dl, 0, 0, 0, dl, 0, 0 },
            { tw, 0, 0, dl, 0, 0, 0, dw, 0, 0, 0, dl, 0, 0, tw },
            { 0, 0, dl, 0, 0, 0, dl, 0, dl, 0, 0, 0, dl, 0, 0 },
            { 0, tl, 0, 0, 0, tl, 0, 0, 0, tl, 0, 0, 0, tl, 0 },
            { 0, 0, 0, 0, dw, 0, 0, 0, 0, 0, dw, 0, 0, 0, 0 },
            { dl, 0, 0, dw, 0, 0, 0, dl, 0, 0, 0, dw, 0, 0, dl },
            { 0, 0, dw, 0, 0, 0, dl, 0, dl, 0, 0, 0, dw, 0, 0 },
            { 0, dw, 0, 0, 0, tl, 0, 0, 0, tl, 0, 0, 0, dw, 0 },
            { tw, 0, 0, dl, 0, 0, 0, tw, 0, 0, 0, dl, 0, 0, tw }
    };

    TileModel[][] tiles;

    public BoardModel() {
        tiles = new TileModel[15][15];
    }

}