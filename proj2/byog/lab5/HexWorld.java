package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 60;
    private static final long SEED = 666;
    private static final Random RANDOM = new Random(SEED);


    /**
     * Computes the width of row i for a size s hexagon
     * @param s The size of the hexagon
     * @param i The row number where i = 0 is the bottom row
     * @return
     */
    private static int hexRowWidth(int s, int i) {
        if (i < s) {
            return s + i * 2;
        }
        return 3 * s - 2 - (i - s) * 2;//5s-2i-2
    }

    /**
     * Computes relative x coordinate of leftmost tile in the ith
     * row of a hexagon, assuming that the bottom row has an x-coordinate
     * of zero. For example, if s = 3 and i = 2, this function returns -2
     * e.g.
     *   xxx
     *  xxxxx
     * xxxxxxx
     * xxxxxxx   --> i = 2, starts 2 spots to the left of the bottom
     *  xxxxx
     *   xxx
     * @param s The size of the hexagon
     * @param i The row number where i = 0 is the bottom row
     * @return
     */
    private static int hexRowOffset(int s, int i) {
        if (i < s) {
            return -i;
        }
        return -(s - 1) + i - s;    //-2s+i+1
    }

    /**
     * Adds a row of tiles
     * @param world The world to draw on
     * @param p The leftmost coordinate of the row
     * @param width The width of the row
     * @param t The tile to draw
     */
    private static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi++) {
            int xCoord = p.x +xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    /**
     * Adds a hexagon to the world
     * @param world The world to draw on
     * @param p The leftmost coordinate of bottom line
     * @param s The size of the hexagon
     * @param t The tile to draw
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        for (int yi = 0; yi < 2 * s; yi++) {
            int thisRowY = p.y + yi;
            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);
            int rowWidth = hexRowWidth(s, yi);
            addRow(world, rowStartP, rowWidth, t);
        }
    }

    /** Picks a RANDOM tile */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.FLOOR;
            case 4: return Tileset.TREE;
            case 5: return Tileset.WATER;
            default: return Tileset.SAND;
        }
    }

    /**
     * Draws a collum of hexagons, each one with a random tile
     * @param world The world to draw on
     * @param p The leftmost bottom coordinate of the collum
     * @param num The number of hexagons to draw
     * @param size The size of the hexagon
     */
    private static void drawRandomVerticalHexes(TETile[][] world, Position p, int num, int size) {
        int xCoord = p.x;
        int yCoord = p.y;
        Position newP = new Position(xCoord, yCoord);
        for (int i = 0; i < num; i++) {
            addHexagon(world, newP, size, randomTile());
            newP.y += size * 2;
        }
    }

    private static void bottomRightNeighbor(Position p, int size) {
        p.x += 2 * size - 1;
        p.y -= size;
    }

    private static void topRightNeighbor(Position p, int size) {
        p.x += 2 * size - 1;
        p.y += size;
    }

    /**
     * Draws a tesselation hexagons
     * @param world The world to draw on
     * @param p The leftmost coordinate of bottom line
     * @param size The size of the hexagon
     */
    public static void drawTesselationHexes(TETile[][] world, Position p, int size) {
        drawRandomVerticalHexes(world, p, 3, size);
        bottomRightNeighbor(p, size);
        drawRandomVerticalHexes(world, p, 4, size);
        bottomRightNeighbor(p, size);
        drawRandomVerticalHexes(world, p, 5, size);
        topRightNeighbor(p, size);
        drawRandomVerticalHexes(world, p, 4, size);
        topRightNeighbor(p, size);
        drawRandomVerticalHexes(world, p, 3, size);
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // draw the big hexagon from the position p which is the leftmost coordinate of the bottom row
        Position p = new Position(25, 20);
        drawTesselationHexes(world, p, 4);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
