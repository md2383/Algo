package HW2;

import java.util.Scanner;

public class WeightedBySumInversions {
    
    // This method returns the weighted count of inversions
    public int countWeightedInversions(int[] sequence) {
        int[] temp = new int[sequence.length];
        return mergeSortAndCount(sequence, temp, 0, sequence.length - 1);
    }

    // This method recursively sorts the array and returns the weighted count of
    // inversions
    private int mergeSortAndCount(int[] sequence, int[] temp, int left, int right) {
        int inversions = 0; // Initialize inversions within this recursive call

        // print inversions and sequence
        System.out.println("Inversions: " + inversions);
        System.out.print("Sequence: ");
        for (int i = 0; i < sequence.length; i++) {
            System.out.print(sequence[i] + " ");
        }
        System.out.println();

        // If there's more than one element in the subarray
        if (left < right) {
            int mid = (left + right) / 2;

            // Recursively sort and count inversions in the left subarray
            inversions += mergeSortAndCount(sequence, temp, left, mid);

            // Recursively sort and count inversions in the right subarray
            inversions += mergeSortAndCount(sequence, temp, mid + 1, right);

            // Merge the two sorted subarrays and count inversions
            inversions += mergeAndCount(sequence, temp, left, mid, right);
        }

        // Return the total number of inversions for this recursive call
        return inversions;
    }

    // This method merges two sorted arrays and returns the weighted count of
    // inversions
    private int mergeAndCount(int[] sequence, int[] temp, int left, int mid, int right) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int mergeIndex = left; // Index for the temporary array
        int inversions = 0; // Counter for inversions

        // Merge the two sorted subarrays
        while (leftIndex <= mid && rightIndex <= right) {
            if (sequence[leftIndex] <= sequence[rightIndex]) {
                temp[mergeIndex++] = sequence[leftIndex++];
            } else {
                temp[mergeIndex++] = sequence[rightIndex++];

                // Count inversions: If an element in the left subarray is greater
                // than an element in the right subarray, it's an inversion.
                inversions += mid - leftIndex + 1;
            }
        }

        // Copy remaining elements from the left subarray, if any
        while (leftIndex <= mid) {
            temp[mergeIndex++] = sequence[leftIndex++];
        }

        // Copy remaining elements from the right subarray, if any
        while (rightIndex <= right) {
            temp[mergeIndex++] = sequence[rightIndex++];
        }

        // Copy the merged results back to the original array
        for (int i = left; i <= right; i++) {
            sequence[i] = temp[i];
        }

        // Return the total number of inversions in this merge step
        return inversions;
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
        int weightedCount = weightedBySumInversions.countWeightedInversions(sequence);

        // Print the result
        System.out.println(weightedCount);

        scanner.close();
    }
}
