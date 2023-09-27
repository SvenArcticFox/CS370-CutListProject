package cs370.cutlist_project.algorithm;

import cs370.cutlist_project.*;

import java.util.Arrays;

public class Algorithm {

    public static CutTree entrance(Sheet sheet, Cut[] cuts) {
        quickSort(cuts, 0, cuts.length -1);
        CutTree cutTree = new CutTree(sheet);

        for (Cut cut : cuts) {
            cutTree.add(cut);
        }

        return cutTree;
    }

    private static void quickSort(Cut arr[], int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int partitionIndex = partition(arr, lowIndex, highIndex);

            quickSort(arr, lowIndex, partitionIndex-1);
            quickSort(arr, partitionIndex+1, highIndex);
        }
    }

    private static int partition(Cut arr[], int lowIndex, int highIndex) {
        Cut pivot = arr[highIndex];
        int i = (lowIndex-1);

        for (int j = lowIndex; j < highIndex; j++) {
            if (arr[j].getArea() >= pivot.getArea()) {
                i++;

                Cut swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        Cut swapTemp = arr[i+1];
        arr[i+1] = arr[highIndex];
        arr[highIndex] = swapTemp;

        return i+1;
    }

    public static void main(String[] args) {
        Sheet sheet = new Sheet(50, 50);
        Cut[] cuts = new Cut[10];

        cuts[0] = new Cut(10, 20);
        cuts[1] = new Cut(20, 15);
        cuts[2] = new Cut(18, 12);
        cuts[3] = new Cut(14, 8);
        cuts[4] = new Cut(9, 4);
        cuts[5] = new Cut(30, 15);
        cuts[6] = new Cut(5, 9);
        cuts[7] = new Cut(12, 3);
        cuts[8] = new Cut(18, 9);
        cuts[9] = new Cut(5, 6);

        CutTree cutTree = entrance(sheet, cuts);
        cutTree.printTree();
    }
}
