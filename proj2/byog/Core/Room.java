package byog.Core;

import byog.TileEngine.*;

import java.util.ArrayList;
import java.util.Random;

public class Room {
    private Position p;     // The left bottom coordinate of the room
    private int height;
    private int width;

    public Room (Position p, int w, int h){
        this.p = p;
        height = h;
        width = w;
    }
    public Position getPosition() {
        return p;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    /** Adds a room to the world and builds its wall */
    public static void addRoom(TETile world[][], Room r) {
        for (int x = 0; x < r.width; x++) {
            world[r.p.xPos + x][r.p.yPos] = Tileset.WALL;
            world[r.p.xPos + x][r.p.yPos + r.height - 1] = Tileset.WALL;
        }
        for (int y = 0; y < r.height; y++) {
            world[r.p.xPos][r.p.yPos + y] = Tileset.WALL;
            world[r.p.xPos + r.width - 1][r.p.yPos + y] = Tileset.WALL;
        }

    }

    /** Fills the room inside with floor */
    private static void fillRoom(TETile world[][], Room r) {
        for (int i = 1; i < r.width - 1; i++) {
            for (int j = 1; j < r.height - 1; j++) {
                world[r.p.xPos + i][r.p.yPos + j] = Tileset.FLOOR;
            }
        }
    }
    public static void fillAllRooms(TETile world[][], ArrayList<Room> rooms) {
        for (Room r: rooms) {
            fillRoom(world, r);
        }
    }

    public boolean isOverlap(TETile world[][]) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (world[p.xPos + i][p.yPos + j] != Tileset.NOTHING) {
                    return true;
                }
            }
        }
        return false;
    }

    public Position findInnerPosition(Random rand) {
        int xPos = p.xPos + 1 + rand.nextInt(width - 2);
        int yPos = p.yPos + 1 + rand.nextInt(height - 2);
        return new Position(xPos, yPos);
    }














}
