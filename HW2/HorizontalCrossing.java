package HW2;

import java.util.Scanner;

public class HorizontalCrossing {

    public int findMaxCrossing(int[] y_vals, int[] indecies, int index) {

        int[] y_vals_unsorted = y_vals.clone();

        sort(y_vals, indecies, 0, y_vals.length - 1);

        int max = 0;
        int maxCurrent = 0;
        for (int i = 0; i < y_vals.length - 1; i++) {

            int point1 = indecies[i];
            int leftPoint = 0;
            int rightPoint = 0;

            if (point1 == 0) {
                leftPoint = y_vals_unsorted[y_vals_unsorted.length - 1];
                rightPoint = y_vals_unsorted[point1 + 1];
            } else if (point1 == y_vals_unsorted.length - 1) {
                leftPoint = y_vals_unsorted[point1 - 1];
                rightPoint = y_vals_unsorted[0];
            } else {
                leftPoint = y_vals_unsorted[point1 - 1];
                rightPoint = y_vals_unsorted[point1 + 1];
            }

            if (leftPoint != y_vals[i]) {
                if (leftPoint > y_vals[i]) {
                    maxCurrent++;
                } else {
                    maxCurrent--;
                }
            }
            if (rightPoint != y_vals[i]) {
                if (rightPoint > y_vals[i]) {
                    maxCurrent++;
                } else {
                    maxCurrent--;
                }
            }

            if (y_vals[i] != y_vals[i + 1 % y_vals.length]) {
                if (maxCurrent > max) {
                    max = maxCurrent;
                }
            }
        }

        return max;
    }

    void merge(int arr[], int[] indecies, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        int L_indecies[] = new int[n1];
        int R_indecies[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
            L_indecies[i] = indecies[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
            R_indecies[j] = indecies[m + 1 + j];
        }

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                indecies[k] = L_indecies[i];
                i++;
            } else {
                arr[k] = R[j];
                indecies[k] = R_indecies[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            indecies[k] = L_indecies[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            indecies[k] = R_indecies[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int arr[], int[] indecies, int l, int r) {
        if (l < r) {

            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, indecies, l, m);
            sort(arr, indecies, m + 1, r);

            // Merge the sorted halves
            merge(arr, indecies, l, m, r);
        }
    }

    // main function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of integers in the sequence
        int size = scanner.nextInt();

        // Create an array to hold the sequence
        int[] x_vals = new int[size];
        int[] indecies = new int[size];
        int[] y_vals = new int[size];

        // Read the sequence of integers
        for (int i = 0; i < size; i++) {
            x_vals[i] = scanner.nextInt();
            y_vals[i] = scanner.nextInt();
            indecies[i] = i;
        }

        int maxCrossing = new HorizontalCrossing().findMaxCrossing(y_vals, indecies, 0);
        System.out.println(maxCrossing);

        scanner.close();
    }
}
