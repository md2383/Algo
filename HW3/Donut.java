package HW3;

import java.util.Scanner;

public class Donut {

    // This method finds the median of an array of integers
    public int findMedian(int[] nums) {

        // check if the array has an odd or even number of elements
        int n = nums.length;
        if (n % 2 == 1) {
            return quickSelect(nums, n / 2);
        } else {
            int left = quickSelect(nums, n / 2 - 1);
            int right = quickSelect(nums, n / 2);
            return (left + right) / 2;
        }
    }

    // This method finds the kth smallest element in an array of integers
    public static int quickSelect(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }

        // find the pivot
        int pivot = nums[nums.length / 2];
        int leftCount = 0;
        int rightCount = 0;
        int equalCount = 0;

        // count the number of elements less than, greater than, and equal to the pivot
        for (int num : nums) {
            if (num < pivot) {
                leftCount++;
            } else if (num > pivot) {
                rightCount++;
            } else {
                equalCount++;
            }
        }

        // check if the kth smallest element is less than, greater than, or equal to the
        // pivot
        if (k < leftCount) {
            int[] left = new int[leftCount];
            int j = 0;
            // add the elements less than the pivot to the left array
            for (int num : nums) {
                if (num < pivot) {
                    left[j++] = num;
                }
            }
            return quickSelect(left, k);
        } else if (k < leftCount + equalCount) {
            return pivot;
        } else {
            int[] right = new int[rightCount];
            int j = 0;
            // add the elements greater than the pivot to the right array
            for (int num : nums) {
                if (num > pivot) {
                    right[j++] = num;
                }
            }
            return quickSelect(right, k - leftCount - equalCount);
        }
    }

    // This method returns the absolute value of an integer in case the result is
    // negative
    public static int abs(int a) {
        if (a < 0) {
            return -a;
        }
        return a;
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

        // Find the median of the x and y coordinates
        int xbest = d.findMedian(x_vals);
        int ybest = d.findMedian(y_vals);

        // Find the sum of distances from the median to each police car
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += abs(xbest - x_vals[i]) + abs(ybest - y_vals[i]);
        }

        // Output the sum of distances
        System.out.println(sum);
    }
}
