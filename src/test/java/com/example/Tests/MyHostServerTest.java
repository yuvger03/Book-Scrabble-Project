package com.example.Tests;

import com.example.serverSide.GuestHandler;
import com.example.serverSide.MyHostServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

public class MyHostServerTest {

    private MyHostServer hostServer;
    private GuestHandler mockGuestHandler;
    private ExecutorService mockExecutorService;

    @BeforeEach
    public void setup() {
        mockGuestHandler = mock(GuestHandler.class);
        mockExecutorService = mock(ExecutorService.class);
        hostServer = new MyHostServer(1234, mockGuestHandler, 4);
        hostServer.executorService = mockExecutorService;
    }

    @AfterEach
    public void cleanup() {
        hostServer.close();
    }

    @Test
    public void testStart() {
        hostServer.start();
        // Check if the executor service is used to execute the startServer method
        verify(mockExecutorService).execute(any(Runnable.class));
    }

    @Test
    public void testStartServer() throws IOException {
        hostServer.startServer();
        // Add assertions here to check the expected behavior
    }

    @Test
    public void testHandleClient() throws IOException {
        InputStream mockInputStream = mock(InputStream.class);
        OutputStream mockOutputStream = mock(OutputStream.class);
        Socket mockSocket = mock(Socket.class);
        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        when(mockSocket.getOutputStream()).thenReturn(mockOutputStream);

        hostServer.handleClient(mockSocket);
        // Add assertions here to check the expected behavior
    }

    @Test
    public void testNotifyAll() throws IOException {
        Socket mockSocket1 = mock(Socket.class);
        Socket mockSocket2 = mock(Socket.class);
        when(mockSocket1.getOutputStream()).thenReturn(mock(OutputStream.class));
        when(mockSocket2.getOutputStream()).thenReturn(mock(OutputStream.class));
        hostServer.clientSockets.add(mockSocket1);
        hostServer.clientSockets.add(mockSocket2);

        hostServer.notifyAll("Test Message");
        // Add assertions here to check the expected behavior
    }

    @Test
    public void testClose() throws IOException {
        hostServer.close();
        // Check if the executor service is shutdown and server socket is closed
        verify(mockExecutorService).shutdown();
        verify(hostServer.serverSocket).close();
    }

    @Test
    public void testStartGame() {
        hostServer.startGame();
        // Add assertions here to check the expected behavior
    }

    @Test
    public void testResumeGame() throws JsonProcessingException {
        int port = 5678;
        hostServer.resumeGame(port);
        // Add assertions here to check the expected behavior
    }

    @Test
    public void testSaveGame() {
        hostServer.saveGame();
        // Add assertions here to check the expected behavior
    }
}
