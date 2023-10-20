package HW3;

public class Hopscotch {

    public int findLargeSum(int[] squares) {

        int n = squares.length;

        squares[n - 1] = squares[n - 1];
        squares[n - 2] = squares[n - 2];
        squares[n - 3] = squares[n - 3] + squares[n - 1];

        for (int i = n - 4; i >= 0; i--) {
            squares[i] = squares[i] + Math.max(squares[i + 2], squares[i + 3]);
        }

        return squares[0];
    }

    public static void main(String[] args) {

        // scanner
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int n = sc.nextInt();
        int[] squares1 = new int[n];
        for (int i = 0; i < n; i++) {
            squares1[i] = sc.nextInt();
        }

        Hopscotch h = new Hopscotch();
        int largestSum1 = h.findLargeSum(squares1);

        System.out.println(largestSum1);
    }

}
