package com.example.Tests;

import com.example.clientside.Models.PlayerModel;
import com.example.clientside.viewmodel.GameScreenViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameScreenViewModelTest {

    private PlayerModel playerModel;
    private GameScreenViewModel viewModel;

    @BeforeEach
    public void setUp() {
        playerModel = new PlayerModel();
        viewModel = new GameScreenViewModel(playerModel);
    }

    @Test
    public void testScoreResultBinding() {
        StringProperty scoreResult = new SimpleStringProperty("Score Result");
        viewModel.scoreResult.bind(scoreResult);

        assertEquals(scoreResult.get(), viewModel.scoreResult.get());
    }

    @Test
    public void testTotalScoreBinding() {
        StringProperty totalScore = new SimpleStringProperty("Total Score");
        viewModel.totalScore.bind(totalScore);

        assertEquals(totalScore.get(), viewModel.totalScore.get());
    }

    @Test
    public void testMessageBinding() {
        StringProperty message = new SimpleStringProperty("Message");
        viewModel.message.bind(message);

        assertEquals(message.get(), viewModel.message.get());
    }

    @Test
    public void testCurrentPlayerBinding() {
        StringProperty currentPlayer = new SimpleStringProperty("Current Player");
        viewModel.currentPlayer.bind(currentPlayer);

        assertEquals(currentPlayer.get(), viewModel.currentPlayer.get());
    }
    @Test
    public void testWordBinding() {
        StringProperty word = new SimpleStringProperty("Word");
        viewModel.word.bind(word);

        assertEquals(word.get(), viewModel.word.get());
    }


    @Test
    public void testRowBinding() {
        StringProperty row = new SimpleStringProperty("Row");
        viewModel.row.bind(row);

        assertEquals(row.get(), viewModel.row.get());
    }

    @Test
    public void testColBinding() {
        StringProperty col = new SimpleStringProperty("Col");
        viewModel.col.bind(col);

        assertEquals(col.get(), viewModel.col.get());
    }



    // Add more tests for other bindings if needed

}
