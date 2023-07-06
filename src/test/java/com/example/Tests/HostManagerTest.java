package com.example.Tests;

import com.example.Game.Tile;
import com.example.serverSide.HostManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HostManagerTest {
    private HostManager hostManager;

    @BeforeEach
    void setUp() {
        hostManager = new HostManager(1234);
    }

    @Test
    void testAddPlayerToGame() {
        // Add players to the game
        assertTrue(hostManager.addPlayerToGame("Player1"));
        assertTrue(hostManager.addPlayerToGame("Player2"));
        assertTrue(hostManager.addPlayerToGame("Player3"));
        assertTrue(hostManager.addPlayerToGame("Player4"));

        // Try to add more players than the maximum limit (4)
        assertFalse(hostManager.addPlayerToGame("Player5"));
    }

    @Test
    void testGetPlayerTurn() {
        // Add players to the game
        hostManager.addPlayerToGame("Player1");
        hostManager.addPlayerToGame("Player2");
        hostManager.addPlayerToGame("Player3");

        // Verify the initial player turn
        assertEquals("Player1", hostManager.getPlayerTurn());

        // Perform multiple nextPlayer() calls and verify the player turn
        hostManager.nextPlayer();
        assertEquals("Player2", hostManager.getPlayerTurn());

        hostManager.nextPlayer();
        assertEquals("Player3", hostManager.getPlayerTurn());

        hostManager.nextPlayer();
        assertEquals("Player1", hostManager.getPlayerTurn());
    }

    @Test
    void testInitTileArray() {
        ArrayList<Tile> tilesArray = hostManager.initTileArray();
        assertEquals(7, tilesArray.size());
        for (Tile tile : tilesArray) {
            assertNotNull(tile);
        }
    }

    @Test
    void testFillTilesArray() {
        String tiles = hostManager.fillTilesArray(5);
        assertEquals(5, tiles.length());
    }

    @Test
    void testSetPlayerScore() {
        // Set scores for two players
        hostManager.setPlayerScore(100, "Player1");
        hostManager.setPlayerScore(50, "Player2");
        hostManager.setPlayerScore(100, "Player1");
        hostManager.setPlayerScore(50, "Player2");
        // Verify the scores for the players
        assertEquals("100", hostManager.scoreMap.get("Player1"));
        assertEquals("50", hostManager.scoreMap.get("Player2"));

        // Increase the score for Player1
        hostManager.setPlayerScore(50, "Player1");

        // Verify the updated score for Player1
        assertEquals("150", hostManager.scoreMap.get("Player1"));
    }

    @Test
    void testSetPlayerTiles() {
        // Set initial tiles for a player
        hostManager.pTilesMap.put("Player1", "ABCDEFI");

        // Modify the player's tiles
        hostManager.setPlayerTiles("GHI/ABC", "Player1");

        // Verify the modified tiles for the player
        assertEquals("DEFIGHI", hostManager.pTilesMap.get("Player1"));
    }
}
