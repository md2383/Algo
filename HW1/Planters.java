//setup class

import java.util.Scanner;

public class Planters {

    public static void merge(
            int[] a, int[] l, int[] r, int left, int right) {

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

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

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

    public boolean replantable(int p, int[] r, int[] sizes) {

        int[] plants = sizes;
        int[] planters = r;
        int numPlants = p;

        // sort the plants and planters
        mergeSort(plants, plants.length);
        mergeSort(planters, planters.length);

        // check if the plants can be planted
        for (int i = plants.length - 1; i >= 0; i--) {
            boolean b = false;
            for (int j = planters.length - 1; j >= 0; j--) {
                if (b != true && planters[j] > plants[i]) {
                    b = true;
                    planters[j] = plants[i];
                }
            }
            if (b == false) {
                System.out.println("NO");
                return false;
            }
        }
        System.out.println("YES");
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" ");

            int plantSize = Integer.parseInt(line[0]);
            int planterSize = Integer.parseInt(line[1]);

            int[] plants = new int[plantSize];
            int[] planters = new int[planterSize];

            line = sc.nextLine().split(" ");
            for (int i = 0; i < plantSize; i++) {
                plants[i] = Integer.parseInt(line[i]);
            }

            line = sc.nextLine().split(" ");
            for (int i = 0; i < planterSize; i++) {
                planters[i] = Integer.parseInt(line[i]);
            }

            Planters p = new Planters();
            p.replantable(plantSize, planters, plants);

        }
    }
}
