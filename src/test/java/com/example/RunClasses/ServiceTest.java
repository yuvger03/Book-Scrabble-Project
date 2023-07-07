package com.example.RunClasses;

import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceTest {

    @Test
    void testWordToString() {
        Service service = new Service();
        Tile[] tiles = {new Tile('H', 4), new Tile('E', 1), new Tile('L', 1), new Tile('L', 1), new Tile('O', 1)};
        Word word = new Word(tiles, 2, 3, true);

        String expected = "HELLO,2,3,T";
        String actual = service.WordToString(word);

        assertEquals(expected, actual);
    }

    @Test
    void testTilesArrToString() {
        Service service = new Service();
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile('H', 4));
        tiles.add(new Tile('E', 1));
        tiles.add(new Tile('L', 1));
        tiles.add(new Tile('L', 1));
        tiles.add(new Tile('O', 1));

        String expected = "HELLO";
        String actual = service.tilesArrToString(tiles);

        assertEquals(expected, actual);
    }

    @Test
    void testStringToWord() {
        Service service = new Service();
        String wordString = "HELLO,2,3,T";

        Tile[] expectedTiles = {new Tile('H', 4), new Tile('E', 1), new Tile('L', 1), new Tile('L', 1), new Tile('O', 1)};
        Word expectedWord = new Word(expectedTiles, 1, 2, true);

        Word actualWord = service.stringToWord(wordString);

        assertArrayEquals(expectedTiles, actualWord.getTiles());
        assertEquals(expectedWord.getRow(), actualWord.getRow());
        assertEquals(expectedWord.getCol(), actualWord.getCol());
        assertEquals(expectedWord.isVertical(), actualWord.isVertical());
    }

    @Test
    void testMatrixToString() {
        Service service = new Service();
        Tile[][] tiles = new Tile[2][2];
        tiles[0][0] = new Tile('H', 4);
        tiles[0][1] = new Tile('E', 1);
        tiles[1][0] = new Tile('L', 1);
        tiles[1][1] = null;

        String expected = "HELn";
        String actual = service.matrixToString(tiles);

        assertEquals(expected, actual);
    }


    @Test
    void testCalculateScore() {
        Service service = new Service();

        assertEquals(4, service.calculateScore('H'));
        assertEquals(1, service.calculateScore('E'));
        assertEquals(1, service.calculateScore('L'));
        assertEquals(1, service.calculateScore('O'));
        assertEquals(8, service.calculateScore('X'));
    }
    @Test
    public void testStringReplacement() {
        String originalString = "Hello_World";
        String expectedUpdatedString = "HelloWorld";

        String updatedString = originalString.replace("_", "");

        Assertions.assertEquals(expectedUpdatedString, updatedString);
    }


    @Test
    void testTileToString() {
        Service service = new Service();
        Tile tile = new Tile('H', 4);

        String expected = "H";
        String actual = service.TileToString(tile);

        assertEquals(expected, actual);
    }

    @Test
    void testStringToTile() {
        Service service = new Service();
        String tileString = "H";

        Tile expected = new Tile('H', 4);
        Tile actual = service.stringToTile(tileString);

        assertEquals(expected.letter, actual.letter);
        assertEquals(expected.score, actual.score);
    }

    @Test
    void testStringToTilesArray() {
        Service service = new Service();
        String tilesString = "HELLO";

        Tile[] expected = {
                new Tile('H', 4),
                new Tile('E', 1),
                new Tile('L', 1),
                new Tile('L', 1),
                new Tile('O', 1)
        };
        Tile[] actual = service.StringToTilesArray(tilesString);

        assertArrayEquals(expected, actual);
    }

    @Test
    void testTilessArrayToSTring() {
        Service service = new Service();
        ArrayList<Tile> pTiles = new ArrayList<>();
        pTiles.add(new Tile('H', 4));
        pTiles.add(new Tile('E', 1));
        pTiles.add(new Tile('L', 1));
        pTiles.add(new Tile('L', 1));
        pTiles.add(new Tile('O', 1));

        String expected = "HELLO";
        String actual = service.TilessArrayToSTring(pTiles);

        assertEquals(expected, actual);
    }

    @Test
    void testGetWordString() {
        Service service = new Service();
        String wordString = "HELLO,2,3,T";

        String expected = "HELLO";
        String actual = service.getWordString(wordString);

        assertEquals(expected, actual);
    }
}