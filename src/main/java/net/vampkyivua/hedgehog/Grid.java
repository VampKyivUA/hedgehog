package net.vampkyivua.hedgehog;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Author: Mykhailo Drozdov
 * Created: 2019-08-19 17:40
 */
public class Grid {
    private final Node[][] grid;
    private final int[][] matrix;
    private final int xSize;
    private final int ySize;

    public Grid(int[][] matrix) {
        this.matrix = matrix; // Preserving for output

        xSize = matrix[0].length;
        ySize = matrix.length;
        // Transposing matrix to represent (x, y) grid
        grid = new Node[xSize][ySize];

        for (int i = 0; i < xSize; i++)
        for (int j = 0; j < ySize; j++)
            grid[i][j] = new Node(i, j, matrix[j][i]);
    }

    public Node get(int x, int y) {
        return grid[x][y];
    }

    public Pair<Integer, Integer> size() {
        return new Pair<>(grid.length, grid[0].length);
    }

    // Constraining movement of bottom down and right directions
    public Node[] neighbors(Node node) {
        if (node.x == grid.length -1 && node.y == grid[0].length - 1)
            return new Node[0];

        if (node.x == grid.length - 1)
            return new Node[]{grid[node.x][node.y + 1]};

        if (node.y == grid[0].length - 1)
            return new Node[]{grid[node.x + 1][node.y]};

        return new Node[]{grid[node.x + 1][node.y], grid[node.x][node.y + 1]};
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix)
            sb.append(Arrays.toString(row)).append('\n');
        return sb.toString();
    }

    public static class Node {
        public final int x;
        public final int y;
        public final int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", this.x, this.y);
        }
    }

    public static Grid fromStrings(List<String> s) {
        int[] size = split(s.get(0), arr -> arr.length == 2);
        int m = size[0];
        int n = size[1];

        if (s.size() < m) {
            throw new HedgehogException(String.format(
                    "Input size (rows) %s is lower than matrix size %s",
                    s.size() - 1, m
            ));
        }

        int[][] grid = new int[m][n];
        for (int i = 1; i < s.size(); i++) {
            grid[i - 1] = split(s.get(i), arr -> arr.length == n);
        }


        return new Grid(grid);
    }

    @SafeVarargs
    private static int[] split(String str, Predicate<String[]>... validators) {
        String[] strings = str.split(" ");
        for (Predicate<String[]> v : validators) {
            if (!v.test(strings))
                throw new HedgehogException("Input validation error");
        }

        int[] arr = new int[strings.length];

        int i = 0;
        for (String s : strings)
            arr[i++] = Integer.valueOf(s);

        return arr;
    }
}
