package com.example.clientside.Models;
//
//import com.example.Game.Tile;
//import com.example.Game.Word;
//
//public class runClient {
//    public static void main(String[] args) {
//       // HostManager h=new HostManager(8080);
//        //h.dictionaryLegal("Q,shira,");
//        //boolean b=h.dictionaryLegal("Q,s1.txt,s2.txt,");
//       // PlayerModel p=new PlayerModel();
//        Tile.Bag b=new Tile.Bag();
//        Tile tile =b.getRand();
//        Tile tile1 =b.getRand();
//        Tile[] tiles= new Tile[]{tile,tile1};
//        Word w=new Word(tiles,5,4,true);
//        HostModeModel hm=new HostModeModel(8080);
//        hm.setName("shira");
//
//        //Test flow:
//        // check connctivity to main server
//        // check how much time(how to check start bottom) listen to other players- what happend if 5 players trying to connect
//        //check players list-size equals to guests+host
//        //
//       // hm.name="shira";
//        //hm.tryToPlace(w);
//
//
//        //String s=p.WordToString(w);
//        //HostModeModel h=new HostModeModel();
//      // h.dictionaryLegal()
//    }
//}

//package com.example.serverSide;

import com.example.Game.Tile;
import com.example.Game.Word;
import com.example.serverSide.GuestHandler;
import com.example.serverSide.HostManager;

import static java.lang.Thread.sleep;

public class runClient {
    public static HostModeModel host;
    public HostManager hostManager;
    public static GuestHandler guestHandler;

    //    @BeforeEach
    public static void main(String[] args) throws InterruptedException {
        int serverPort = 8080; // Change the server port if needed
        guestHandler = new GuestHandler(serverPort);
        host = new HostModeModel(8080, guestHandler, "host");
//    }
//    @Test
//    public void testConnectivityToServer() {
        // Simulate a new host being up
        sleep(100);
        // Check if the host is connected
        if (!guestHandler.HM.playersList.contains("host")) System.out.println("player list should contain host");

//        GuestModeModel guest1 = new GuestModeModel(host.serverPort, "guest1");
//        GuestModeModel guest2 = new GuestModeModel(host.serverPort, "guest2");
//        GuestModeModel guest3 = new GuestModeModel(host.serverPort, "guest3");
//        GuestModeModel guest4 = new GuestModeModel(host.serverPort, "guest4");
        // Check if only 4 guests are connected
//        if (!guestHandler.HM.playersList.contains("guest1")) System.out.println("player list not contain player 1");
//        if (!guestHandler.HM.playersList.contains("guest2")) System.out.println("player list not contain player 2");
//        if (!guestHandler.HM.playersList.contains("guest3")) System.out.println("player list not contain player 3");
//        if (!guestHandler.HM.playersList.contains("guest4")) System.out.println("player list not contain player 4"); // 4'th player not in the game

        sleep(100);
        host.startGame();

        System.out.println(host.service.tilesArrToString(host.p_tiles));
//        System.out.println(guest1.service.tilesArrToString(guest1.p_tiles));
//        System.out.println(guest2.service.tilesArrToString(guest2.p_tiles));
//        System.out.println(guest3.service.tilesArrToString(guest3.p_tiles));
//    @Test
//    public void testTryToPlaceWord() {
        // Simulate a player trying to place a word
        Word word = new Word(new Tile[]{new Tile('T', 1), new Tile('H', 4), new Tile('E', 1)}, 0, 0, true);
        //till here





//        guestHandler.HM.addPlayerToGame("player1");
//        guestHandler.HM.addPlayerToGame("player2");
//        guest1.tryToPlace(word);
//        InputStream inputStream = new ByteArrayInputStream("guest1-tryToPlace-THE".getBytes());
//        OutputStream outputStream = new ByteArrayOutputStream();
//        guestHandler.handleClient(inputStream, outputStream);
//
//        // Check the response
//        String expectedOutput = "player1-tryToPlace-4-/THE\nboard-";
//        if(!outputStream.toString().startsWith(expectedOutput)) System.out.println(" expected output not equal");;
//}

//    @Test
////    public void testGetTileFromBag() {
//        // Simulate a player requesting a tile from the bag
//        // Create an instance of the GuestHandler class
//        GuestHandler guestHandler = new GuestHandler();
//
//        // Add some tiles to the bag
//        ArrayList<Tile> bagTiles = new ArrayList<>();
//        bagTiles.add(new Tile('A'));
//        bagTiles.add(new Tile('B'));
//        bagTiles.add(new Tile('C'));
//        guestHandler.getGameManager().setBagTiles(bagTiles);
//
//        // Add a player to the game
//        guestHandler.getGameManager().addPlayerToGame("player1");
//
//        // Simulate a player requesting a tile from the bag
//        InputStream inputStream = new ByteArrayInputStream("player1-getTileFromBag".getBytes());
//        OutputStream outputStream = new ByteArrayOutputStream();
//        guestHandler.handleClient(inputStream, outputStream);
//
//        // Check the response
//        String expectedOutput = "player1-getTileFromBag-A\n";
//        assertEquals(expectedOutput, outputStream.toString());
//    }


//package com.example.clientside.Models;

//
//public class runClient {
//    public HostManager hostManager;
//    public GuestHandler guestHandler;
//    public PlayerModel player1;
//    private PlayerModel player2;
//
//    @BeforeEach
//    public void setUp() {
//        int serverPort = 8080; // Change the server port if needed
//        hostManager = new HostManager(serverPort);
//        guestHandler = new GuestHandler(serverPort);
//        player1 = new PlayerModel();
//        player2 = new PlayerModel();
//    }
//
//    @Test
//    public void testConnectivityToServer() {
//        // Simulate a new host being up
//        InputStream hostInputStream = new ByteArrayInputStream("joinToGame".getBytes());
//        OutputStream hostOutputStream = new ByteArrayOutputStream();
//        guestHandler.handleClient(hostInputStream, hostOutputStream);
//
////        // Check if the host is connected
////        assertTrue(guestHandler.HM.playersList.contains("host"));
////        assertEquals("host-message-you joind to game", hostOutputStream.toString().trim());
//
//        // Simulate up to 4 guests being up
//        InputStream guest1InputStream = new ByteArrayInputStream("joinToGame".getBytes());
//        OutputStream guest1OutputStream = new ByteArrayOutputStream();
//        guestHandler.handleClient(guest1InputStream, guest1OutputStream);
//
//        InputStream guest2InputStream = new ByteArrayInputStream("joinToGame".getBytes());
//        OutputStream guest2OutputStream = new ByteArrayOutputStream();
//        guestHandler.handleClient(guest2InputStream, guest2OutputStream);
//
//        InputStream guest3InputStream = new ByteArrayInputStream("joinToGame".getBytes());
//        OutputStream guest3OutputStream = new ByteArrayOutputStream();
//        guestHandler.handleClient(guest3InputStream, guest3OutputStream);
//
//        // Try to add a fifth guest
//        InputStream guest4InputStream = new ByteArrayInputStream("joinToGame".getBytes());
//        OutputStream guest4OutputStream = new ByteArrayOutputStream();
//        guestHandler.handleClient(guest4InputStream, guest4OutputStream);
//
//        // Check if only 4 guests are connected
//        assertTrue(guestHandler.HM.playersList.contains("guest1"));
//        assertTrue(guestHandler.HM.playersList.contains("guest2"));
//        assertTrue(guestHandler.HM.playersList.contains("guest3"));
//        assertFalse(guestHandler.HM.playersList.contains("guest4"));
//        assertEquals("guest4-message-you not joind to game", guest4OutputStream.toString().trim());
//    }
//
//    @Test
//    public void testTryToPlaceWord() {
//        // Simulate a player trying to place a word
//        Word word = new Word(new Tile[] {new Tile('T'), new Tile('E'), new Tile('S'), new Tile('T')}, 0, 0, true);
//        guestHandler.HM.addPlayerToGame("player1");
//        guestHandler.HM.addPlayerToGame("player2");
//
//        InputStream inputStream = new ByteArrayInputStream(("player1-tryToPlace-" + player1.service.WordToString(word)).getBytes());
//        OutputStream outputStream = new ByteArrayOutputStream();
//        guestHandler.handleClient(inputStream, outputStream);
//
//        // Check the response
//        String expectedOutput = "player1-tryToPlace-4-/TEST\nboard-";
//        assertTrue(outputStream.toString().startsWith(expectedOutput));
//    }
//
//    @Test
//    public void testGetTileFromBag() {
//        // Simulate a player requesting a tile from the bag
//        // Create an instance of the GuestHandler class
//        GuestHandler guestHandler = new GuestHandler();
//
//        // Add some tiles to the bag
//        ArrayList<Tile> bagTiles = new ArrayList<>();
//        bagTiles.add(new Tile('A'));
//        bagTiles.add(new Tile('B'));
//        bagTiles.add(new Tile('C'));
//        guestHandler.getGameManager().setBagTiles(bagTiles);
//
//        // Add a player to the game
//        guestHandler.getGameManager().addPlayerToGame("player1");
//
//        // Simulate a player requesting a tile from the bag
//        InputStream inputStream = new ByteArrayInputStream("player1-getTileFromBag".getBytes());
//        OutputStream outputStream = new ByteArrayOutputStream();
//        guestHandler.handleClient(inputStream, outputStream);
//
//        // Check the response
//        String expectedOutput = "player1-getTileFromBag-A\n";
//        assertEquals(expectedOutput, outputStream.toString());
//    }
//    }


    }
}