package com.example.serverSide;

import com.example.Game.Board;
import com.example.Game.Tile;
import com.example.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.client.*;
import org.bson.Document;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

import static java.lang.Thread.sleep;

public class DBcom {

    // Create a MongoDB client
//    public static MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

    // Access the database
//    public static MongoDatabase database = mongoClient.getDatabase("mydb");
    public static String collectionName = "games";
//    public static MongoCollection<Document> collection = database.getCollection(collectionName);

    public DBcom() {
    }

    public void saveToDB(HostManager hm) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("mydb");
        MongoCollection<Document> collection = database.getCollection(collectionName);
        int server = hm.serverPort; //TODO: take the server port of the guest not the host
        Document document = new Document("Game port" , server);
        document.append("current_player" ,hm.current_player);
        document.append("pTilesMap" ,MaptoDocument(hm.pTilesMap));
        document.append("playersList" ,arrayListTodocument(hm.playersList));
        document.append("scoreMap" ,MaptoDocument(hm.scoreMap));
        document.append("gameBoard" ,boardToDocument(hm.gameboard));
        document.append("bag" ,bagToDocument(hm.b));

        collection.insertOne(document);

        mongoClient.close();

    }
    public Document MaptoDocument(Map<String,String> map) {
        Document doc=new Document();
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            doc.append(key, map.get(key));
        }
        return doc;
    }
    public Document arrayListTodocument(ArrayList<String>list) {
        Document doc=new Document();
        int i =0;
        for (String element : list){
            i++;
            doc.append(""+i,element);
        }
        return doc;
    }
    public Document boardToDocument(Board B){
        Document doc=new Document();
        Service s = new Service();
        String board = s.matrixToString(B.tiles);
        doc.append("Board", board);
        return doc;
    }
    public Document bagToDocument(Tile.Bag bag){
        Document doc=new Document();
        String s = "";
        for(int element:bag.quantities)
            s = s + "," + element;
        doc.append("bag",s);
        return doc;
    }
    public Tile[][] getBoardFromDocument(Document document){
        Service s=new Service();
        return s.stringToMatrix((String) document.get("Board"));
    }
    public int[] getBagFromDocument(Document document){
        Service s=new Service();
        String[] quantitisString = ((String) document.get("Board")).split(",");
        int[] quantitis = new int[quantitisString.length];
        for (int i =0;i<quantitis.length;i++)
            quantitis[i] = Integer.parseInt(quantitisString[i]);
        return quantitis;
    }
    public static Map<String,String> getMapFromJSON(Document document, String mapName) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(document);
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode scoreMapNode = rootNode.get(mapName);
        Iterator<String> fieldNames = scoreMapNode.fieldNames();

        while (fieldNames.hasNext()) {
            String key = fieldNames.next();
            String value = scoreMapNode.get(key).asText();
            map.put(key,value);
        }
        return map;
    }
    public static ArrayList<String> getArrayListFromJSON(Document document, String listName) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<String> arrayList = new ArrayList<>();
        String json = objectMapper.writeValueAsString(document);
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode list = rootNode.get(listName);
        Iterator<String> fieldNames = list.fieldNames();
        while (fieldNames.hasNext()) {
            String key = fieldNames.next();
            String value = list.get(key).asText();
            arrayList.add(value);
        }
        return arrayList;
    }

    public static Document readFromDB(int serverPort) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("mydb");
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document query = new Document("Game port" , serverPort);
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

//    public static void main(String[] args) throws InterruptedException, JsonProcessingException {
//
//        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
//        MongoDatabase database = mongoClient.getDatabase("mydb");
//        MongoCollection<Document> collection = database.getCollection(collectionName);
//        Document document = readFromDB(8080);
//        System.out.println(getMapFromJSON(document,"scoreMap"));
//        getArrayListFromJSON(document,"playersList");
//
//
//    }
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