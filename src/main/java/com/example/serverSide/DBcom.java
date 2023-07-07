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
public class DBcom {

    public static String collectionName = "games";
    public DBcom() {
    }

    public void saveToDB(HostManager hm) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("mydb");
        MongoCollection<Document> collection = database.getCollection(collectionName);
        int server = hm.hostPort; //TODO: take the server port of the guest not the host
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
        for (Map.Entry<String, String> entry : map.entrySet()) {
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
            doc.append(String.valueOf(i),element);
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
        String s = String.valueOf(bag.quantities[0]);
        for(int i=1;i< bag.quantities.length;i++)
            s += "," + bag.quantities[i];
        doc.append("bag",s);
        return doc;
    }
    public String getBoardFromDocument(Document document){
        return document.get("gameBoard").toString().split("=")[1].split("}}")[0];
    }
    public int[] getBagFromDocument(Document document){
        String[] quantitisString = (document.get("bag")).toString().split("bag:")[0].split(",");
        quantitisString[0] = quantitisString[0].split("=")[1];
        quantitisString[quantitisString.length-1] = quantitisString[quantitisString.length-1].split("}}")[0];
        int[] quantities = new int[quantitisString.length];
        for (int i =0;i<quantities.length;i++){
            quantities[i] = Integer.parseInt(quantitisString[i]);
        }
        return quantities;
    }
    public Map<String,String> getMapFromJSON(Document document, String mapName) throws JsonProcessingException {
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
    public ArrayList<String> getArrayListFromJSON(Document document, String listName) throws JsonProcessingException {
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

    public Document readFromDB(int serverPort) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("mydb");
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document query = new Document("Game port" , serverPort);
        Document d =  collection.find(query).first();
        mongoClient.close();
        return d;
    }
}
