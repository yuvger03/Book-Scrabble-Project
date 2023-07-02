package com.example.serverSide;

import com.example.Game.Board;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class DBcom {

    // Create a MongoDB client
    public MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

    // Access the database
    public MongoDatabase database = mongoClient.getDatabase("Games");
    public String collectionName = "games";
    public MongoCollection<Document> collection = database.getCollection(collectionName);

    public DBcom() {
    }

    public void saveToDB(HostManager hm) {
        int server = hm.serverPort;
        Document document = new Document("Game port" , server);
        document.append("current_player" ,hm.current_player);
        document.append("pTilesMap" ,hm.pTilesMap);
        document.append("playersList" ,hm.playersList);
        document.append("scoreMap" ,hm.scoreMap);
        document.append("gameboard" ,hm.gameboard);
        document.append("bag" ,hm.b);
        collection.insertOne(document);


    }

    public Document readFromDB(int serverPort) {
        Document query = new Document("Game port:" , serverPort);
        Document document = collection.find(query).first();
        return document;
// Iterate over the result set


//        Document query = new Document("current player", searchKey); // Replace 'your_key' with the actual key in your document
//        FindIterable<Document> documents = collection.find(query);
//
//
//        if (fieldValue != null) {
//            String serverValue = (String) fieldValue;
//            System.out.println("Value of 'Game port:" + server + "': " + serverValue);
//        } else
//            System.out.println("Field 'Game port:" + server + "' not found in the document.");
//
//        mongoClient.close();
    }
}
//    Method to save the GameState document to MongoDB collection(table)
//    public void saveToMongoDB(MongoCollection<Document> collection) {
//        Document document = this.toDocument();
//        collection.insertOne(document);
//    }
    // Method to retrieve the GameState from a MongoDB document
//    public static GameState  readGameStatefromDocument(Document game_document) {
//        GameState game = new GameState();
//        //getting the simple fields
//        game.indexOfCurrentTurnPlayer=game_document.getInteger("indexOfCurrentTurnPlayer");
//        game.gameSaveName=game_document.getString("gameSaveName");
//        //getting the complex object fields
//        try {
//            // game.hashmap_name_to_id=(HashMap<String,Integer>)game_document.get("hashmap_name_to_id", HashMap.class);
//            //game.hashmap_name_to_id = new HashMap<String,Integer>(game_document.get("hashmap_name_to_id", Document.class));
//            Document hashMapDoc=game_document.get("hashmap_name_to_id",Document.class);
//            game.hashmap_name_to_id=getHashmapFromDocument(hashMapDoc);
//            //bag:
//            Document bag_document=game_document.get("bag",Document.class);
//            game.bag=Bag.fromDocument(bag_document);
//            //listOfplayers:
//            Document playerListDoc=game_document.get("listOfPlayers", Document.class);
//            game.listOfPlayers=getListOfPlayersFromDocument(playerListDoc);
//            //gameBoard:
//            Document gameBoardDoc=game_document.get("gameBoard", Document.class);
//            game.gameBoard=ConnectedBoard.fromDocument(gameBoardDoc);
//
//
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return game;
//    //        MongoCursor<Document> cursor = documents.iterator();
////
////        while (cursor.hasNext()) {
////            Document document1 = cursor.next();
////            String json = document1.toJson();
////            System.out.println(json);
////        }
////        PlayerModel p = document.toJson();
//    // Close the connection
//    public static void main(String[] args) {
//
//    }
//}