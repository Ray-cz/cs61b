import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    private final int MAXDEPTH = 7;
    private double[] depthDPP;      // distance per pixel for every depth
    private double[] depthXDist;    // distance in x direction for every depth
    private double[] depthYDist;
    private int[] depthMaxSize;
    public Rasterer() {
        depthDPP = new double[MAXDEPTH + 1];
        depthXDist = new double[MAXDEPTH + 1];
        depthYDist = new double[MAXDEPTH + 1];
        depthMaxSize = new int[MAXDEPTH + 1];
        depthXDist[0] = MapServer.ROOT_LRLON - MapServer.ROOT_ULLON;
        depthYDist[0] = MapServer.ROOT_ULLAT - MapServer.ROOT_LRLAT;
        depthDPP[0] = depthXDist[0] / MapServer.TILE_SIZE;
        depthMaxSize[0] = 1;
        for (int i = 1; i < MAXDEPTH + 1; i++) {
            depthDPP[i] = depthDPP[i - 1] / 2;
            depthXDist[i] = depthXDist[i - 1] / 2;
            depthYDist[i] = depthYDist[i - 1] / 2;
            depthMaxSize[i] = depthMaxSize[i - 1] * 2;
        }
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        System.out.println(params);
        Map<String, Object> results = new HashMap<>();

        double param_lrlon = params.get("lrlon");
        double param_ullon = params.get("ullon");
        double param_ullat = params.get("ullat");
        double param_lrlat = params.get("lrlat");
        if (!validate(param_ullon, param_ullat, param_lrlon, param_lrlat)) {
            results.put("query_success", false);
            System.out.println(results);
            return results;
        }
        double width = params.get("w");
        double xDist = param_lrlon - param_ullon;
        double lonDPP = xDist / width;

        // get depth
        int depth = getDepth(lonDPP);

        // get xStart
        double ul_xDiff = param_ullon - MapServer.ROOT_ULLON;
        int xStart = getX(ul_xDiff, depth);

        // get upper left longitude
        double raster_ul_lon = MapServer.ROOT_ULLON + xStart * depthXDist[depth];

        // get yStart
        double ul_yDiff = MapServer.ROOT_ULLAT - param_ullat;
        int yStart = getY(ul_yDiff, depth);

        // get upper left latitude
        double raster_ul_lat = MapServer.ROOT_ULLAT - yStart * depthYDist[depth];

        // get xEnd
        double lr_xDiff = param_lrlon - MapServer.ROOT_ULLON;
        int xEnd = getX(lr_xDiff, depth);
        if (xEnd > depthMaxSize[depth] - 1) {
            xEnd = depthMaxSize[depth] - 1;
        }
        // get lower right longitude
        double raster_lr_lon = MapServer.ROOT_ULLON + (xEnd + 1) * depthXDist[depth];

        // get yEnd
        double lr_yDiff = MapServer.ROOT_ULLAT - param_lrlat;
        int yEnd = getY(lr_yDiff, depth);
        if (yEnd > depthMaxSize[depth] - 1) {
            yEnd = depthMaxSize[depth] - 1;
        }
        // get lower right latitude
        double raster_lr_lat = MapServer.ROOT_ULLAT - (yEnd + 1) * depthYDist[depth];

        String[][] grid = getGrid(xStart, yStart, xEnd, yEnd, depth);

        results.put("raster_ul_lon", raster_ul_lon);
        results.put("raster_ul_lat", raster_ul_lat);
        results.put("depth", depth);
        results.put("raster_lr_lon", raster_lr_lon);
        results.put("raster_lr_lat", raster_lr_lat);
        results.put("render_grid", grid);
        results.put("query_success", true);

        //System.out.println(results);

        return results;
    }

    private boolean validate(double ullon, double ullat, double lrlon, double lrlat) {
        if (ullon >= MapServer.ROOT_LRLON || ullat <= MapServer.ROOT_LRLAT ||
        lrlon <= MapServer.ROOT_ULLON || lrlat >= MapServer.ROOT_ULLAT) {
            return false;
        }
        return true;
    }
    private int getDepth(double dpp) {
        int depth = 1;
        while (dpp < depthDPP[depth]) {
            depth++;
            if (depth >= MAXDEPTH) {
                break;
            }
        }
        return depth;
    }

    private int getX(double xDiff, int depth) {
        int x = -1;
        double xCord = 0.0;
         do {
            xCord += depthXDist[depth];
            x++;
        } while (xDiff > xCord);
        return x;
    }
    private int getY(double yDiff, int depth) {
        int y = -1;
        double yCord = 0.0;
        do {
            yCord += depthYDist[depth];
            y++;
        } while (yDiff > yCord);
        return y;
    }

    private String[][] getGrid(int xStart, int yStart, int xEnd, int yEnd, int depth) {
        String[][] grid = new String[yEnd - yStart + 1][xEnd - xStart + 1];
        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                String s = "d" + depth + "_" + "x";
                s += x + "_" + "y" + y + ".png";
                grid[y - yStart][x - xStart] = s;
            }
        }
        return grid;
    }



}
