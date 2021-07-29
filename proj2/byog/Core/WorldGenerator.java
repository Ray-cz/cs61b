package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenerator {
    private static final int MAXROOMS = 10;
    public static final int WIDTH = 60;
    public static final int HEIGHT = 40;

    private static Room setRandomRoom(TETile world[][], Random rand) {
        final int maxWidth = 8;
        final int maxHeight = 8;

        int roomWidth = rand.nextInt(maxWidth) + 3;
        int roomHeight = rand.nextInt(maxHeight) + 3;

        int roomPx = rand.nextInt(WorldGenerator.WIDTH - roomWidth);
        int roomPy = rand.nextInt(WorldGenerator.HEIGHT - roomHeight);
        Position starP = new Position(roomPx, roomPy);

        Room newRoom = new Room(starP, roomWidth, roomHeight);
        return newRoom;
    }

    private static ArrayList<Room> drawRoom(TETile world[][], Random rand, int roomNumber) {
        ArrayList<Room> roomList = new ArrayList<>();

        for (int i = 0; i < roomNumber; i++) {
            Room newRoom = setRandomRoom(world, rand);
            while (newRoom.isOverlap(world)) {
                newRoom = setRandomRoom(world, rand);
            }
            Room.addRoom(world, newRoom);
            roomList.add(newRoom);
        }
        return roomList;
    }


    private static ArrayList<Hallway> drawHallway(TETile world[][], Random rand, ArrayList<Room> roomList) {
        ArrayList<Hallway> hallwayList = new ArrayList<>();

        for (int i = 0; i < roomList.size() - 1; i++) {
            Position p1 = roomList.get(i).findInnerPosition(rand);
            Position p2 = roomList.get(i + 1).findInnerPosition(rand);

            Position pLeft = Position.findSmallerX(p1, p2);
            Hallway newHorizontalWay = new Hallway(pLeft, 1, Math.abs(p1.xPos - p2.xPos));
            Hallway.addHorizontalHallway(world, pLeft, Math.abs(p1.xPos - p2.xPos));
            hallwayList.add(newHorizontalWay);

            Position pCorner = new Position(pLeft.xPos + Math.abs(p1.xPos - p2.xPos), pLeft.yPos);

            Position pDown = Position.findSmallerY(pCorner, Position.findLargerX(p1, p2));
            Hallway newVerticalWay = new Hallway(pDown, 0, Math.abs(p1.yPos - p2.yPos));
            Hallway.addVerticalHallway(world, pDown, Math.abs(p1.yPos - p2.yPos));
            hallwayList.add(newVerticalWay);


            Hallway.addCorner(world, pCorner);

        }
        return hallwayList;
    }

    public static void generateWorld() {
        //initialize the world
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        //initialize random seed
        Random rand = new Random(11451);

        //initialize rooms
        int roomNumber = rand.nextInt(MAXROOMS) ;
        System.out.println(roomNumber);

        ArrayList<Room> roomList =  drawRoom(world, rand, roomNumber);

        ArrayList<Hallway> hallwayList = drawHallway(world, rand, roomList);

        Room.fillAllRooms(world, roomList);
        Hallway.fillAllHallways(world, hallwayList);
        ter.renderFrame(world);
    }

    public static void main(String[] args) {
        generateWorld();
    }

}
