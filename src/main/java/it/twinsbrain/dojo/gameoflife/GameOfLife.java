package it.twinsbrain.dojo.gameoflife;

public class GameOfLife {

    public static final char ALIVE = '*';
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

    public String nextGeneration() {
        int columns = grid[0].length;
        var nexGrid = new char[grid.length][columns];
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < columns; x++) {
                nexGrid[y][x] = shouldBeAlive(y, x) ? '*' : '.';
            }
        }
        var nextRepresentation = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            nextRepresentation.append(new String(nexGrid[nexGrid.length - i - 1]));
            if (i < grid.length - 1) {
                nextRepresentation.append("\n");
            }
        }
        return nextRepresentation.toString();
    }

    private boolean shouldBeAlive(int y, int x) {
        int aliveNeighBoors = aliveNeighBoors(y, x);
        if (grid[y][x] == ALIVE) {
            return aliveNeighBoors >= 2 && aliveNeighBoors <= 3;
        } else {
            return aliveNeighBoors == 3;
        }
    }

    private int aliveNeighBoors(int j, int i) {
        var count = 0;
        var minY = Math.max(0, j - 1);
        var maxY = Math.min(j + 1, grid.length - 1);
        var minX = Math.max(0, i - 1);
        var maxX = Math.min(i + 1, grid[0].length - 1);
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                boolean current = x == i && y == j;
                if (!current) {
                    count += grid[y][x] == ALIVE ? 1 : 0;
                }
            }
        }
        return count;
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
