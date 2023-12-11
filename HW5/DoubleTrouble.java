package HW5;

import java.util.Scanner;

public class DoubleTrouble {

    public static int solveMaze(char[][] grid, boolean[][] vis, int row1, int col1, int row2, int col2, int num_moves) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Check if both Things reached an exit
        if ((row1 == 0 || row1 == rows - 1 || col1 == 0 || col1 == cols - 1) &&
                (row2 == 0 || row2 == rows - 1 || col2 == 0 || col2 == cols - 1)) {
            return num_moves; // Return the number of moves
        }

        vis[row1][col1] = true;
        vis[row2][col2] = true;

        int newRow1 = row1, newCol1 = col1, newRow2 = row2, newCol2 = col2;
        boolean move1left = true, move1right = true, move1up = true, move1down = true;
        boolean move2left = true, move2right = true, move2up = true, move2down = true;

        if (row1 == 0) {
            move1up = false;
        } else if (row1 == rows - 1) {
            move1down = false;
        }
        if (row2 == 0) {
            move2up = false;
        } else if (row2 == rows - 1) {
            move2down = false;
        }
        if (col1 == 0) {
            move1left = false;
        } else if (col1 == cols - 1) {
            move1right = false;
        }
        if (col2 == 0) {
            move1left = false;
        } else if (col2 == cols - 1) {
            move1right = false;
        }
        if (move1up && grid[row1 - 1][col1] == 'x') {
            move1up = false;
        }
        if (move1down && grid[row1 + 1][col1] == 'x') {
            move1down = false;
        }
        if (move1left && grid[row1][col1 - 1] == 'x') {
            move1left = false;
        }
        if (move1right && grid[row1][col1 + 1] == 'x') {
            move1right = false;
        }
        if (move2up && grid[row2 - 1][col2] == 'x') {
            move2up = false;
        }
        if (move2down && grid[row2 + 1][col2] == 'x') {
            move2down = false;
        }
        if (move2left && grid[row2][col2 - 1] == 'x') {
            move2left = false;
        }
        if (move2right && grid[row2][col2 + 1] == 'x') {
            move2right = false;
        }

        int moveNorth = Integer.MAX_VALUE, moveSouth = Integer.MAX_VALUE, moveWest = Integer.MAX_VALUE,
                moveEast = Integer.MAX_VALUE;
        if (move1up) {
            newRow1 = row1 - 1;
        }
        if (move2up) {
            newRow2 = row2 - 1;
        }
        if (move1up == false && move2up == false) {
        } else {
            moveNorth = solveMaze(grid, vis, newRow1, col1, newRow2, col2, num_moves + 1);
        }

        if (move1down) {
            newRow1 = row1 + 1;
        }
        if (move2down) {
            newRow2 = row2 + 1;
        }
        if (move1down == false && move2down == false) {
        } else {
            moveSouth = solveMaze(grid, vis, newRow1, col1, newRow2, col2, num_moves + 1);
        }

        if (move1left) {
            newCol1 = col1 - 1;
        }
        if (move2left) {
            newCol2 = col2 - 1;
        }
        if (move1left == false && move2left == false) {
        } else {
            moveWest = solveMaze(grid, vis, row1, newCol1, row2, newCol2, num_moves + 1);
        }

        if (move1right) {
            newCol1 = col1 + 1;
        }
        if (move2right) {
            newCol2 = col2 + 1;
        }
        if (move1right == false && move2right == false) {
        } else {
            moveEast = solveMaze(grid, vis, row1, newCol1, row2, newCol2, num_moves + 1);
        }

        // Unmark both Things as visited (backtrack)
        vis[row1][col1] = true;
        vis[row2][col2] = true;

        // Find the minimum moves among valid moves
        int minMoves = Math.min(Math.min(moveNorth, moveSouth), Math.min(moveWest, moveEast));

        // Increment the minimum moves by 1 (representing the current move)
        return minMoves == Integer.MAX_VALUE ? Integer.MAX_VALUE : minMoves + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        int cols = sc.nextInt();

        char[][] grid = new char[rows][cols];

        // Take input
        for (int i = 0; i < rows; i++) {
            String line = sc.next();
            for (int j = 0; j < cols; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        // Find starting positions of '1' and '2'
        int startRow1 = -1, startCol1 = -1, startRow2 = -1, startCol2 = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    startRow1 = i;
                    startCol1 = j;
                } else if (grid[i][j] == '2') {
                    startRow2 = i;
                    startCol2 = j;
                }
            }
        }

        // Initialize visited array
        boolean[][] visited = new boolean[rows][cols];

        // Find the minimum moves for both Thing One and Thing Two
        int minMoves = solveMaze(grid, visited, startRow1, startCol1, startRow2, startCol2, 0);

        // Check if it's impossible or print the minimum moves
        if (minMoves == Integer.MAX_VALUE) {
            System.out.println("It is impossible to exit.");
        } else {
            System.out.println("The minimum number of moves to exit is: " + minMoves);
        }
    }
}
