package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;

public class Hallway {
    Position start;
    int key;        // 0 for the vertical; 1 for the horizontal
    int length;

    public Hallway(Position s, int k, int l) {
        start = s;
        key = k;
        length = l;
    }

    public static void addVerticalHallway(TETile world[][], Position p, int length) {
        for (int y = 0; y < length; y++) {
            world[p.xPos - 1][p.yPos + y] = Tileset.WALL;
            world[p.xPos + 1][p.yPos + y] = Tileset.WALL;
        }
    }

    private static void fillVerticalHally(TETile world[][], Position p, int length){
        for (int y = 0; y < length; y++) {
            world[p.xPos][p.yPos + y] = Tileset.FLOOR;
        }
    }

    public static void addHorizontalHallway(TETile world[][], Position p, int length) {
        for (int x = 0; x < length; x++) {
            world[p.xPos + x][p.yPos - 1] = Tileset.WALL;
            world[p.xPos + x][p.yPos + 1] = Tileset.WALL;
        }
    }
    private static void fillHorizontalHally(TETile world[][], Position p, int length){
        for (int x = 0; x < length; x++) {
            world[p.xPos + x][p.yPos] = Tileset.FLOOR;
        }
    }

    public static void fillAllHallways(TETile world[][], ArrayList<Hallway> hallways) {
        for (Hallway h: hallways) {
            if (h.key == 0) {
                fillVerticalHally(world, h.start, h.length);
            } else {
                fillHorizontalHally(world, h.start, h.length);
            }
        }
    }



    public static void addCorner(TETile world[][], Position p) {
        world[p.xPos][p.yPos] = Tileset.FLOOR;
        world[p.xPos + 1][p.yPos + 1] = Tileset.WALL;
        world[p.xPos - 1][p.yPos - 1] = Tileset.WALL;
        world[p.xPos + 1][p.yPos - 1] = Tileset.WALL;
        world[p.xPos - 1][p.yPos + 1] = Tileset.WALL;
        if (world[p.xPos - 1][p.yPos] == Tileset.NOTHING) {
            world[p.xPos - 1][p.yPos] = Tileset.WALL;
        }
        if (world[p.xPos + 1][p.yPos] == Tileset.NOTHING) {
            world[p.xPos + 1][p.yPos] = Tileset.WALL;
        }
        if (world[p.xPos][p.yPos + 1] == Tileset.NOTHING) {
            world[p.xPos][p.yPos + 1] = Tileset.WALL;
        }
        if (world[p.xPos][p.yPos - 1] == Tileset.NOTHING) {
            world[p.xPos][p.yPos - 1] = Tileset.WALL;
        }
    }
}
