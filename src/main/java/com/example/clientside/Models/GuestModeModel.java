package com.example.clientside.Models;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class GuestModeModel extends PlayerModel {

//    public static class GuestHandler implements IClientHandler{
//        {
//            PrintWriter out;
//            Scanner in;
//
//            @Override
//            public void handleClient(InputStream inFromclient, OutputStream outToClient) {
//            out = new PrintWriter(outToClient);
//            in = new Scanner(inFromclient);
//            String text = in.next();
//            out.println(new StringBuilder(text).reverse().toString());
//            out.flush();
//        }
//
//            @Override
//            public void close() {
//            in.close();
//            out.close();
//        }
//        }
//    }

    private String host;
    private int hostPort;
    private String guestName;


    public GuestModeModel(String host, int hostPort, String guestName) {
        this.host = "localhost";
        this.hostPort = hostPort;
        this.guestName = guestName;
    }

    //TODO:implement- connect the server
    @Override
    public void connectServer(){
    // Start a separate thread to receive messages from the host
        new Thread(() -> {
        try {
            Socket server = new Socket(host, hostPort);
            // Read user input and send it to the host
            PrintWriter outToServer = new PrintWriter(server.getOutputStream());
            Scanner in = new Scanner(server.getInputStream());
            String text = "";//TODO: change it
            String rev = new StringBuilder(text).reverse().toString();
            outToServer.println(text);
            outToServer.flush();
            String response = in.next();
//                Scanner in = new Scanner(System.in);
//                System.out.print("Enter your name: ");
//                String name = in.nextLine();
//                output.writeObject("name: " + name);
//                output.flush();
            if (response == null || !response.equals(rev))
                System.out.println(
                        "problem getting the right response from your server, cannot continue the test (-25)");
            in.close();
            outToServer.close();
            server.close();
        } catch (Exception e) {
            System.out.println("Exception was thrown when running a client (-25)");
        }
    }).start();
}


    public void connectAndSendArray(String[] array) {
        try {
            Socket serverSocket = new Socket(host, hostPort);
            ObjectOutputStream output = new ObjectOutputStream(serverSocket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(serverSocket.getInputStream());

            // Send array to Host
            output.writeObject(array);

            // Receive location from HostModel
            String[] location = (String[]) input.readObject();
            System.out.println("Received location from HostModel: " + Arrays.toString(location)); // [i, j, score]

//            // Receive response from MyServer
//            String[] response = (String[]) input.readObject();
//            System.out.println("Received response from MyServer: " + Arrays.toString(response));

            serverSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


//
//    @Override
//    public void run() {
//        try {
//            // Read guest name
////                String guestName = (String) input.readObject();
////                System.out.println("Guest connected: " + guestName);
//
//            // Receive array from guest
//            String[] array = (String[]) input.readObject();
//            System.out.println("Received array from guest: " + arrayToString(array));
//
//            // Send word to MyServer
//            output.writeObject(array);
//
//
//            // Forward array to MyServer
//            server.clientHandler.handleClient(array);
//
//            // Receive response from MyServer
//            String[] response = server.clientHandler.getResponse();
//            System.out.println("Received response from MyServer: " + arrayToString(response));
//
//            // Send response back to guest
//            output.writeObject(response);
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            close();
//        }
//    }

//    private String arrayToString(String[] array) {
//        StringBuilder sb = new StringBuilder();
//        for (String item : array) {
//            sb.append(item).append(" ");
//        }
//        return sb.toString().trim();
//    }
//
//    private void handleGuestMessage(String message) {
//        if (!guestHandlers.containsKey(guestName)) {
//            if (message.startsWith("name: ")) {
//                handleNameMessage(message);
//            }
//        } else {
//            if (message.startsWith("word: ")) {
//                handleWordMessage(message);
//            }
//        }
//    }
//
//    private void handleNameMessage(String message) {
//        guestName = message.substring(6);
//        guestHandlers.put(guestName, this);
//        System.out.println("New guest connected: " + guestName);
//        sendMessage("Welcome, " + guestName);
//    }
//
//    private void handleWordMessage(String message) {
//        String word = message.substring(6);
//        System.out.println("Received word from " + guestName + ": " + word);
//
//        // Send the word to the server (not implemented)
//        // Replace the following line with your actual logic to send the word to the server
//        String serverResponse = sendWordToServer(guestName, word);
//
//        sendMessage("Server response: " + serverResponse);
//    }
//
//    private String sendWordToServer(String guestName, String word) {
//        // Logic to send the word to the server and get the server response
//        // Replace with your actual implementation to communicate with the server
//
//        // Simulating server response with a simple echo mechanism
//        return "Server echoed: " + word;
//    }
//
//    public void sendMessage(String message) {
//        try {
//            output.writeObject(message);
//            output.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void close() {
//        try {
//            input.close();
//            output.close();
//            guestSocket.close();
//            guestHandlers.remove(guestName);
//            System.out.println("Guest disconnected: " + guestName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}


