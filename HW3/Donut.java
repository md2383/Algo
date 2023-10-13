package HW3;

import java.util.Arrays;
import java.util.Scanner;

public class Donut {

    public int findMedian(int[] nums) {
        int n = nums.length;
        if (n % 2 == 1) {
            return quickSelect(nums, n / 2);
        } else {
            int left = quickSelect(nums, n / 2 - 1);
            int right = quickSelect(nums, n / 2);
            return (left + right) / 2;
        }
    }

    public int quickSelect(int[] nums, int k) {
        // QuickSelect algorithm to find the k-th smallest element in nums
        if (nums.length == 1) {
            return nums[0];
        }

        int pivot = nums[nums.length / 2];
        int[] left = Arrays.stream(nums).filter(x -> x < pivot).toArray();
        int[] right = Arrays.stream(nums).filter(x -> x > pivot).toArray();
        int[] equal = Arrays.stream(nums).filter(x -> x == pivot).toArray();

        if (k < left.length) {
            return quickSelect(left, k);
        } else if (k < left.length + equal.length) {
            return equal[0];
        } else {
            return quickSelect(right, k - left.length - equal.length);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of police cars
        int size = scanner.nextInt();

        // Create arrays to hold the x and y coordinates of the police cars
        int[] x_vals = new int[size];
        int[] y_vals = new int[size];

        // Read the coordinates into the arrays
        for (int i = 0; i < size; i++) {
            x_vals[i] = scanner.nextInt();
            y_vals[i] = scanner.nextInt();
        }

        Donut d = new Donut();

        int xbest = d.findMedian(x_vals);
        int ybest = d.findMedian(y_vals);

        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += Math.abs(xbest - x_vals[i]) + Math.abs(ybest - y_vals[i]);
        }

        // Output the sum of distances
        System.out.println(sum);

    }
}
