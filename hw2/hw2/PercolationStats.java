package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] results;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        results = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
            }
            double threshold = p.numberOfOpenSites() / (1.0 * N * N);
            results[i] = threshold;
        }
    }
    public double mean() {
        return StdStats.mean(results);
    }
    public double stddev() {
        return StdStats.stddev(results);
    }
    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev() / results.length);
    }
    public double confidenceHigh() {
        return mean() + 1.96 * Math.sqrt(stddev() / results.length);
    }


}
