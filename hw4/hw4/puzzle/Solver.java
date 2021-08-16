package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;

public class Solver {
    int numOfMoves;
    SearchNode end;

    public class SearchNode implements Comparable<SearchNode> {
        public WorldState currWorld;
        public int distance;
        public SearchNode pre;

        public SearchNode(WorldState ws, int d, SearchNode p) {
            currWorld = ws;
            distance = d;
            pre = p;
        }

        @Override
        public int compareTo(SearchNode other) {
            return (this.distance + this.currWorld.estimatedDistanceToGoal())
                    - (other.distance + other.currWorld.estimatedDistanceToGoal());
        }
    }

    public Solver(WorldState initial) {
        MinPQ<SearchNode> moveSequence = new MinPQ<>();
        SearchNode init = new SearchNode(initial, 0, null);
        moveSequence.insert(init);
        numOfMoves = 1;
        while (!moveSequence.isEmpty()) {
            SearchNode X = moveSequence.delMin();
            if (X.currWorld.isGoal()) {
                end = X;
                return;
            }
            for (WorldState y: X.currWorld.neighbors()) {
                if (!X.equals(init) && y.equals(X.pre.currWorld)) {
                    continue;
                }
                SearchNode Y = new SearchNode(y, X.distance + 1, X);
                moveSequence.insert(Y);
                numOfMoves += 1;
            }
        }

    }
    public int moves() {
        return end.distance;
    }
    public Iterable<WorldState> solution() {
        ArrayDeque<WorldState> ws = new ArrayDeque<>();
        while (end.pre != null) {
            ws.push(end.currWorld);
            end = end.pre;
        }
        return ws;
    }
}
