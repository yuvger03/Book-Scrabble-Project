package com.example.serverSide;

import com.example.Game.Board;
import com.example.clientside.Models.GuestModeModel;
import com.example.clientside.Models.PlayerModel;
import com.mongodb.client.*;
import com.mongodb.ConnectionString;
import org.bson.Document;

public class GameLoader {
    public static void main(String[] args) {
        // Create a MongoDB client
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        // Access the database
        MongoDatabase database = mongoClient.getDatabase("Games");
        String collectionName = "games";
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Board a = new Board();
        Document document = new Document("gameData", a);
        collection.insertOne(document);
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> cursor = documents.iterator();

        while (cursor.hasNext()) {
            Document document1 = cursor.next();
            String json = document1.toJson();
            System.out.println(json);
        }
//        PlayerModel p = document.toJson();
        // Close the connection
        mongoClient.close();
    }
}