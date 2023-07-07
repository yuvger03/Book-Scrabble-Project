package com.example.RunClasses;

import com.example.Game.Tile;
import com.example.clientside.Models.PlayerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerModelTest {
    private PlayerModel playerModel;

    @BeforeEach
    public void setup() {
        playerModel = new PlayerModel();
    }

    @Test
    public void testSetName() {
        String name = "Alice";
        playerModel.setName(name);
        assertEquals(name, playerModel.getName());
    }

    @Test
    public void testInitTiles() {
        String tilesString = "ABCDEF";
        playerModel.initTiles(tilesString);
        List<Tile> pTiles = playerModel.getP_tiles();
        assertEquals(tilesString.length(), pTiles.size());
        for (int i = 0; i < tilesString.length(); i++) {
            assertEquals(tilesString.charAt(i), pTiles.get(i).letter);
        }
    }

    @Test
    public void testTryToPlace() throws InterruptedException {
        String word = "HELLO,8,8,T";
        String inputString = "1";
        String tilesString = "HEALOAB";

        playerModel.setName("shira");
        playerModel.initTiles(tilesString);
        playerModel.tryToPlace(word);

        String expectedOutput = playerModel.getName() + "-tryToPlace-" + word + "\n";
        String expectedScore = "not valid placement- you don't have the tiles";

        assertEquals(expectedScore,playerModel.score);

    }

    @Test
    public void testGetFunc_tryToPlaceValidWord() {
        // Set up initial state
        playerModel.score = "";
        playerModel.p_tiles = new ArrayList<>();
        playerModel.p_tiles.add(new Tile('A',1));
        playerModel.p_tiles.add(new Tile('B',3));
        String[] args = {"tryToPlace", "1", "CD/AB"};

        // Invoke the getFunc method
        playerModel.getFunc(args);

        // Assert the expected changes
        assertEquals("YOUR SCORE- 1", playerModel.score);
        assertEquals(2, playerModel.p_tiles.size());
        assertEquals('C', playerModel.p_tiles.get(0).letter);
        assertEquals('D', playerModel.p_tiles.get(1).letter);
    }

    @Test
    public void testGetFunc_tryToPlaceInvalidWord() {
        // Set up initial state
        playerModel.score = "";
        String[] args = {"tryToPlace", "0", ""};

        // Invoke the getFunc method
        playerModel.getFunc(args);

        // Assert the expected changes
        assertEquals("YOUR SCORE - 0 : Invalid placement", playerModel.score);
    }

    @Test
    public void testGetFunc_totalScore() {
        // Set up initial state
        playerModel.totalScore = "0";
        String[] args = {"totalScore", "100"};

        // Invoke the getFunc method
        playerModel.getFunc(args);

        // Assert the expected changes
        assertEquals("100", playerModel.totalScore);
    }

    @Test
    public void testProcessMessage_boardUpdate() {
        // Set up initial state
        playerModel.gameBoard = "";
        String message = "board-ABCDEF";

        // Invoke the processMessage method
        playerModel.processMessage(message);

        // Assert the expected changes
        assertEquals("ABCDEF", playerModel.gameBoard);
    }

    @Test
    public void testProcessMessage_messageUpdate() {
        // Set up initial state
        playerModel.message = "";
        String message = "message-Hello";

        // Invoke the processMessage method
        playerModel.processMessage(message);

        // Assert the expected changes
        assertEquals("Hello", playerModel.message);
    }

    @Test
    public void testProcessMessage_currentPlayerUpdate() {
        // Set up initial state
        playerModel.currentPlayer = "";
        String message = "turn-Alice";

        // Invoke the processMessage method
        playerModel.processMessage(message);

        // Assert the expected changes
        assertEquals("Alice", playerModel.currentPlayer);
    }

    @Test
    public void testProcessMessage_invalidMessage() {
        // Set up initial state
        playerModel.message = "";
        String message = "invalidMessage-Hello";

        // Invoke the processMessage method
        playerModel.processMessage(message);

        // Assert the expected changes
        assertEquals("", playerModel.message);
        // You can add additional assertions here to verify the behavior
    }


}
