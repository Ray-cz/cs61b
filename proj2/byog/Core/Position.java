package byog.Core;

public class Position {
    int xPos;
    int yPos;

    public Position(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    public static Position findLargerX(Position p1, Position p2) {
        if (p1.xPos > p2.xPos) {
            return p1;
        }
        return p2;
    }
    public static Position findSmallerX(Position p1, Position p2) {
        if (p1.xPos < p2.xPos) {
            return p1;
        }
        return p2;
    }

    public static Position findSmallerY(Position p1, Position p2) {
        if (p1.yPos < p2.yPos) {
            return p1;
        }
        return p2;
    }
    public static Position findLargerY(Position p1, Position p2) {
        if (p1.yPos > p2.yPos) {
            return p1;
        }
        return p2;
    }
}
