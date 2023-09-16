import java.util.Scanner;
import java.math.*;

public class FindMaxPairsDouble {
    /*
     * Given are n real numbers a1, a2, . . . , an. Find a value t that can be
     * obtained by adding two
     * of these numbers and, moreover, the number of pairs of these numbers that add
     * up to t is the
     * largest possible. Formally, find a t such that the size of the set {(i, j) |
     * i < j, ai + aj = t} is
     * maximum among all possible t’s. For example, for 2, 7, 3, 1, 5, 6, we have:
     * 2+6 = 7+1 = 3+5.
     * This is the largest number of pairs that sum to the same number, and,
     * therefore, t = 8. If
     * there are multiple possible t’s, find the smallest one. Give an O(nlog n)
     * algorithm for this
     * problem.
     * 
     */
    public static void merge(
            double[] a, double[] l, double[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public static void mergeSort(double[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        double[] l = new double[mid];
        double[] r = new double[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" ");

            int size = Integer.parseInt(line[0]);

            double[] numbers = new double[size];

            line = sc.nextLine().split(" ");
            for (int i = 0; i < size; i++) {
                numbers[i] = Double.parseDouble(line[i]);
            }

            // create an array for the sums
            double[] sums = new double[size * (size - 1) / 2];

            // make sums
            int c = 0;
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    sums[c] = numbers[i] + numbers[j];
                    c++;
                }
            }

            // sort the sums
            mergeSort(sums, sums.length);

            // find the lowest value with the highest number of duplicates
            double t = 0;
            int maxCount = 0;
            int count = 0;

            for (int i = 0; i < sums.length - 1; i++) {
                if (sums[i] == sums[i + 1]) {
                    count++;
                } else {
                    if (count > maxCount) {
                        maxCount = count;
                        t = sums[i];
                    }
                    count = 1;
                }
            }

            System.out.print(maxCount + " ");
            System.out.println(String.format("%.6f", t));
        }
    }
}
