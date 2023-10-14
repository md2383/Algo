package HW3;
import java.util.Scanner;

public class LongestIncreasingSubseqRecursive {

    public int incrSubseqRecursive(int[] A, int j, int size, int max) {
        if (j < size) {
            return 0;
        }
        int withoutCurr = incrSubseqRecursive(A, j - 1, size, max);
        int withCurr = 0;
        if (A[j] < max) {
            withCurr = incrSubseqRecursive(A, j - 1, size, A[j]) + 1;
        }
        return Math.max(withoutCurr, withCurr);
    }

    public void generateInputs(int size) {

        int[] A = new int[size];

        // generate random numbers into A
        for (int i = 0; i < size; i++) {
            A[i] = (int) (Math.random() * 1000);
        }

        long time = System.currentTimeMillis();

        incrSubseqRecursive(A, size - 1, 0, Integer.MAX_VALUE);

        long time2 = System.currentTimeMillis();

        System.out.println("\n");
        System.out.println("Size: " + size);
        System.out.println("Time Difference: " + (time2 - time) + " ms");
        System.out.println("\n");
    }

    public static void main(String[] args) {
        LongestIncreasingSubseqRecursive lis = new LongestIncreasingSubseqRecursive();

        // int count = 1;
        // // Generate for 10 values
        // for (int i = 10; i < 110; i += 10) {
        // System.out.println(count++ + ".");
        // lis.generateInputs(i);
        // }

        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();

        // take input
        int[] A = new int[size];
        for (int i = 0; i < size; i++) {
            A[i] = sc.nextInt();
        }

        System.out.println(lis.incrSubseqRecursive(A, size - 1, 0,
                Integer.MAX_VALUE));

    }
}
