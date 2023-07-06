package com.example.Tests;

import com.example.serverSide.GuestHandler;
import com.example.serverSide.MyHostServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

class MyHostServerTest {

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
    void cleanup() {
        hostServer.close();
    }

    @Test
    void testStart() {
        hostServer.start();
        verify(mockExecutorService).execute(any(Runnable.class));
    }

    @Test
    void testHandleClient() throws IOException {
        InputStream mockInputStream = mock(InputStream.class);
        OutputStream mockOutputStream = mock(OutputStream.class);
        Socket mockSocket = mock(Socket.class);
        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        when(mockSocket.getOutputStream()).thenReturn(mockOutputStream);

        hostServer.handleClient(mockSocket);

        // Add assertions here to check the expected behavior
    }

    @Test
    void testNotifyAll() throws IOException {
        Socket mockSocket1 = mock(Socket.class);
        Socket mockSocket2 = mock(Socket.class);
        when(mockSocket1.getOutputStream()).thenReturn(mock(OutputStream.class));
        when(mockSocket2.getOutputStream()).thenReturn(mock(OutputStream.class));
        List<Socket> clientSockets = hostServer.clientSockets;
        clientSockets.add(mockSocket1);
        clientSockets.add(mockSocket2);

        hostServer.notifyAll("Test Message");


    }





}