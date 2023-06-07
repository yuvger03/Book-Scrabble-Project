
package com.example.serverSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {
    private int port;
    private ClientHandler clientHandler;
    private int maxThreads;
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private volatile boolean stop;

    public MyServer(int port, ClientHandler clientHandler, int maxThreads) {
        this.port = port;
        this.clientHandler = clientHandler;
        this.maxThreads = maxThreads;
        this.executorService = Executors.newFixedThreadPool(maxThreads);
    }

    public void start() {
        stop = false;
        executorService.execute(() -> startServer());
    }

    private void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(1000);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    try {
                        clientHandler.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    finally {
                        try {
                            clientHandler.close();
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SocketTimeoutException e) {
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        stop = true;
        try {
            executorService.shutdown();
            if (serverSocket.isClosed())
                serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}