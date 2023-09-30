package HW2;
import java.util.Scanner;

public class WeightedBySumInversions {
    
    // This method returns the weighted count of inversions
    public long countInversions(long[] sequence, int left, int right) {

        long sum = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            sum += countInversions(sequence, left, mid);
            sum += countInversions(sequence, mid + 1, right);
            sum += merge(sequence, left, mid, right);
        }
        return sum;
    }

    // This method merges two sorted arrays and returns the weighted count of inversions
    private long merge(long[] sequence, int left, int mid, int right) {

        // Create left and right arrays
        long[] L = new long[mid - left + 1];
        long[] R = new long[right - mid];

        // Populate left and right arrays w values from sequence
        for (int i = 0; i < L.length; i++) {
            L[i] = sequence[left + i];
        }
        for (int i = 0; i < R.length; i++) {
            R[i] = sequence[mid + 1 + i];
        }

        // Create variables to track indices of left and right arrays
        int i = 0;
        int j = 0;
        int k = left;

        // Create variables to track number of inversions, sum and totalsum
        long count = 0;
        long sum = 0;
        long totalSum = 0;

        // check if the left and right array
        while (j < R.length && i < L.length) {
            if (L[i] <= R[j]) {
                sequence[k++] = L[i++];
                sum += count * L[i - 1] + totalSum;
            } else {
                sequence[k++] = R[j++];
                count++;
                totalSum += R[j - 1];
            }
        }

        // Copy the remaining elements of L[], if there are any
        while (i < L.length) {
            sequence[k++] = L[i++];
            sum += count * L[i - 1] + totalSum;
        }
        while (j < R.length) {
            sequence[k++] = R[j++];
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of integers in the sequence
        int size = scanner.nextInt();

        // Create an array to hold the sequence
        long[] sequence = new long[size];

        // Read the sequence of integers
        for (int i = 0; i < size; i++) {
            sequence[i] = scanner.nextInt();
        }

        // Calculate the weighted count of inversions
        WeightedBySumInversions weightedBySumInversions = new WeightedBySumInversions();
        long weightedCount = weightedBySumInversions.countInversions(sequence, 0, sequence.length - 1);

        // Print the result
        System.out.println(weightedCount);

        scanner.close();
    }
}
