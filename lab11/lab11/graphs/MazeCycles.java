package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int[] pathTo;
    private boolean circleFound = false;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
    }

    @Override
    public void solve() {
        announce();
        pathTo = new int[maze.V()];
        dfs(0);
    }

    private void dfs(int v) {
        marked[v] = true;
        for (int w : maze.adj(v)) {
            if (circleFound) {
                announce();
                return;
            }
            if (!marked[w]) {
                pathTo[w] = v;
                dfs(w);
            } else if (w != pathTo[v]) {
                int cur = v;
                pathTo[w] = v;
                edgeTo[w] = v;
                while (cur != w) {
                    edgeTo[cur] = pathTo[cur];
                    cur = pathTo[cur];
                }
                circleFound = true;
            }
        }
    }
}

