package com.example.Tests;

import com.example.Game.Tile;
import com.example.clientside.Models.PlayerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
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
    public void testTryToPlace() {
        // Prepare the input and output streams for testing
        String word = "HELLO";
        String inputString = "1";
        String tilesString = "ABCDEF";
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        Scanner scanner = new Scanner(inputStream);
        PrintWriter printWriter = new PrintWriter(outputStream, true);

        // Replace the input and output streams of the player model with the testing streams
        playerModel.inFromServer = scanner;
        playerModel.outToServer = printWriter;

        // Set up the player model with tiles
        playerModel.initTiles(tilesString);

        // Perform the tryToPlace operation
        playerModel.tryToPlace(word);

        // Verify the expected output
        String expectedOutput = playerModel.getName() + "-tryToPlace-" + word + "\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    // Add more tests for other methods as needed

}
