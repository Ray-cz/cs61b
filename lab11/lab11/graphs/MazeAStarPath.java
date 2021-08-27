package lab11.graphs;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        PriorityQueue<Node> fringe = new PriorityQueue<Node>();
        fringe.add(new Node(s));
        while (!fringe.isEmpty()) {
            Node v = fringe.poll();
            for (int w : maze.adj(v.index)) {
                if (!marked[w]) {
                    edgeTo[w] = v.index;
                    distTo[w] = distTo[v.index] + 1;
                    marked[w] = true;
                    announce();
                    if (w == t) {
                        targetFound = true;
                    }
                    if (targetFound) {
                        return;
                    }
                    fringe.add(new Node(w));
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

    private class Node implements Comparable<Node>{
        int index;
        int val;
        public Node(int v) {
            index = v;
            val = distTo[v] + h(v);
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }
}

