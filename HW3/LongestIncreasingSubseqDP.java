package HW3;

public class LongestIncreasingSubseqDP {

    public int incrSubseqDP(int[] S, int[] A, int size, int max) {
        for (int i = 0; i < size; i++) {
            S[i] = 1;
            for (int j = 0; j < i; j++) {
                if ((A[i] > A[j]) && (S[i] < S[j] + 1)) {
                    S[i] = S[j] + 1;
                }
            }
            max = 0;
            for (int j = 0; j < size; j++) {
                if (max < S[j]) {
                    max = Math.max(S[j], max);
                }
            }
        }
        return max;
    }

    public void generateInputs(int size) {

        int[] A = new int[size];
        int[] S = new int[size];

        // generate random numbers into A
        for (int i = 0; i < size; i++) {
            A[i] = A[i] = (int) (Math.random() * 1000);
            S[i] = 0;
        }

        long time = System.currentTimeMillis();

        incrSubseqDP(S, A, size, 0);

        long time2 = System.currentTimeMillis();

        System.out.println("Size: " + size);
        System.out.println("Time Difference: " + (time2 - time) + " ms");
    }

    public static void main(String[] args) {
        LongestIncreasingSubseqDP lis = new LongestIncreasingSubseqDP();

        // int count = 1;
        // // Generate for 10 values
        // for (int i = 10; i < 110; i += 10) {
        // System.out.println(count++ + ".");
        // lis.generateInputs(i);
        // }

        java.util.Scanner sc = new java.util.Scanner(System.in);

        int size = sc.nextInt();

        // take input
        int[] A = new int[size];
        for (int i = 0; i < size; i++) {
            A[i] = sc.nextInt();
        }

        int[] S = new int[size];
        for (int i = 0; i < size; i++) {
            S[i] = 0;
        }
        int max = 0;
        System.out.println(lis.incrSubseqDP(S, A, size, max));

    }
}
