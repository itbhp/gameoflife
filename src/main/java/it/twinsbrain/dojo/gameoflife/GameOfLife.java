package it.twinsbrain.dojo.gameoflife;

public class GameOfLife {

    private final char[][] grid;

    private GameOfLife(String representation) {
        var rows = representation.split("\n");
        var columns = rows[0].length();
        grid = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            grid[i] = new char[columns];
        }
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < columns; j++) {
                grid[rows.length - i - 1][j] = rows[i].charAt(j);
            }
        }
    }

    public static GameOfLife from(String representation) {
        return new GameOfLife(representation);
    }

    public boolean isAliveCellAt(X x, Y y) {
        return grid[y.value][x.value] == '*';
    }

    public record X(int value) {
        public static X of(int value) {
            return new X(value);
        }
    }

    public record Y(int value) {
        public static Y of(int value) {
            return new Y(value);
        }
    }
}
