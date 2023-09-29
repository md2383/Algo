package HW2;
import java.util.Scanner;

public class WeightedBySumInversions {
    
    // This method returns the weighted count of inversions
    public int countInversions(int[] sequence) {

        // If the sequence length is 1, then return 0
        if (sequence.length == 1) {
            return 0;
        }

        // Split the sequence into two halves (B and C)
        int[] leftSequence = new int[sequence.length / 2];
        int[] rightSequence = new int[(sequence.length - leftSequence.length)];
        System.arraycopy(sequence, 0, leftSequence, 0, leftSequence.length);
        System.arraycopy(sequence, sequence.length / 2, rightSequence, 0, rightSequence.length);

        int leftCount = countInversions(leftSequence);
        int rightCount = countInversions(rightSequence);

        int midCount = countSplitInversions(leftSequence, rightSequence);

        System.out.println("Left Sequence: " + java.util.Arrays.toString(leftSequence));
        System.out.println("Left Count: " + leftCount);

        System.out.println("Right Sequence: " + java.util.Arrays.toString(rightSequence));
        System.out.println("Right Count: " + rightCount);
        System.out.println("Mid Count: " + midCount);

        return leftCount + rightCount + midCount;
    }

    // This method returns the count of split inversions
    public int countSplitInversions(int[] B, int[] C) {
        int mCount = 0;
        int i = 0;
        int j = 0;

        while (i < B.length && j < C.length) {
            if (B[i] <= C[j]) {
                //do nothing
            } else {
                mCount += B[i] + C[j];
                i++;
            }
            j++;
        }
        return mCount;
    }

    public static void main(String[] args) {
        System.out.println(
                "Enter the number of integers in the sequence, followed by the sequence of integers with spaces in between.");
        Scanner scanner = new Scanner(System.in);

        // Read the number of integers in the sequence
        int size = scanner.nextInt();

        // Create an array to hold the sequence
        int[] sequence = new int[size];

        // Read the sequence of integers
        for (int i = 0; i < size; i++) {
            sequence[i] = scanner.nextInt();
        }

        // Calculate the weighted count of inversions
        WeightedBySumInversions weightedBySumInversions = new WeightedBySumInversions();
        int weightedCount = weightedBySumInversions.countInversions(sequence);

        // Print the result
        System.out.println("Weighted Count of Inversions: " + weightedCount);

        scanner.close();
    }
}
