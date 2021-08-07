package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.Stopwatch;

public class Percolation {
    private int[][] system;
    private WeightedQuickUnionUF openSites;
    private int topSite;
    private int bottomSite;
    private int numOfOpenSites;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be larger than zero.");
        }

        system = new int[N][N];
        topSite = N * N;
        bottomSite = N * N + 1;
        openSites = new WeightedQuickUnionUF(N * N + 2);
        numOfOpenSites = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                system[i][j] = 0;
            }
        }
    }

    private void validate(int row, int col) {
        if (row >= system.length || row < 0 || col >= system.length || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return;
    }

    private int xyTo1D(int row, int col) {
        return row * system.length + col;
    }

    public void open(int row, int col) {
        validate(row, col);
        if (system[row][col] == 1) {
            return;
        }
        system[row][col] = 1;
        numOfOpenSites += 1;
        int site = xyTo1D(row, col);
        if (row == 0) {
            openSites.union(topSite, site);
        }
        if (row == system.length - 1) {
            if (isFull(row, col)) {
                openSites.union(bottomSite, site);
            }
        }

        if (row < system.length - 1 && isOpen(row + 1, col)) {
            openSites.union(site, xyTo1D(row + 1, col));
        }
        if (row > 0 && isOpen(row - 1, col)) {
            openSites.union(site, xyTo1D(row - 1, col));
        }
        if (col < system.length - 1 && isOpen(row, col + 1)) {
            openSites.union(site, xyTo1D(row, col + 1));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            openSites.union(site, xyTo1D(row, col - 1));
        }
    }
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return system[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        return openSites.connected(xyTo1D(row, col), topSite);
    }
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }
    public boolean percolates() {
        return openSites.connected(topSite, bottomSite);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(3, 4);
        p.open(2, 4);
        p.open(2, 2);
        p.open(2, 3);
        p.open(0, 2);
        p.open(1, 2);
        if (p.isFull(2, 2)) {
            System.out.println("Work!");
        }

        PercolationFactory pf = new PercolationFactory();

        Stopwatch timer1 = new Stopwatch();
        PercolationStats ps1 = new PercolationStats(500, 1, pf);
        double time1 = timer1.elapsedTime();
        System.out.println(time1);

        Stopwatch timer2 = new Stopwatch();
        PercolationStats ps2 = new PercolationStats(1000, 1, pf);
        double time2 = timer2.elapsedTime();
        System.out.println(time2);

        Stopwatch timer3 = new Stopwatch();
        PercolationStats ps3 = new PercolationStats(1000, 2, pf);
        double time3 = timer3.elapsedTime();
        System.out.println(time3);

    }

}
