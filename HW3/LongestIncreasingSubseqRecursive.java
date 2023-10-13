package HW3;

import java.util.Scanner;

public class LongestIncreasingSubseqRecursive {

    public int incrSubseqRecursive(int[] A, int j) {
        if (j == 0) {
            return 1;
        }
        int max = 0;
        for (int i = 0; i < j; i++) {
            int temp = incrSubseqRecursive(A, i);
            if (A[i] < A[j] && temp > max) {
                max = temp;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        LongestIncreasingSubseqRecursive lis = new LongestIncreasingSubseqRecursive();

        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();

        // take input
        int[] A = new int[size];
        for (int i = 0; i < size; i++) {
            A[i] = sc.nextInt();
        }

        System.out.println(lis.incrSubseqRecursive(A, A.length - 1));
    }
}
