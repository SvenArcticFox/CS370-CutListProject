package cs370.cutlist_project.algorithm;

import cs370.cutlist_project.*;

import java.util.Arrays;

public class Algorithm {

    public static void entrance(Sheet sheet, Cut[] cuts) {
        quickSort(cuts, 0, cuts.length -1);


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

    /*private static TreeAndReject algorithm(double maxLength, double maxWidth, Cut[] sortedCuts ,
                                           CutTree tree, int index) {


    }*/
}
