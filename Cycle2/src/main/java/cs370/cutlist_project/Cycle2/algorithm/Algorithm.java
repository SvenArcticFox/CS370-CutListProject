package cs370.cutlist_project.Cycle2.algorithm;

import cs370.cutlist_project.Cycle2.Cut;
import cs370.cutlist_project.Cycle2.Sheet;

import java.util.ArrayList;

public class Algorithm {

    public static CutTree entrance(Sheet sheet, ArrayList<Cut> cuts) {
        quickSort(cuts, 0, cuts.size() -1);
        CutTree cutTree = new CutTree(sheet);

      /*  for (Cut cut : cuts) {
            System.out.println(cut.getArea() + "\t" + cut.getWidth() + "\t" + cut.getLength());
        }*/

        for (Cut cut : cuts) {
            cutTree.add(cut);
        }

        return cutTree;
    }

    private static void quickSort(ArrayList<Cut> arr, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int partitionIndex = partition(arr, lowIndex, highIndex);

            quickSort(arr, lowIndex, partitionIndex-1);
            quickSort(arr, partitionIndex+1, highIndex);
        }
    }

    private static int partition(ArrayList<Cut> arr, int lowIndex, int highIndex) {
        Cut pivot = arr.get(highIndex);
        int i = (lowIndex-1);

        for (int j = lowIndex; j < highIndex; j++) {
            if (arr.get(j).getArea() >= pivot.getArea()) {
                i++;

                Cut swapTemp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, swapTemp);

            }
        }

        Cut swapTemp = arr.get(i + 1);
        arr.set(i+1, arr.get(highIndex));
        arr.set(highIndex, swapTemp);

        return i+1;
    }

    public static void main(String[] args) {
        Sheet sheet = new Sheet(1000, 1000);
         ArrayList<Cut> cuts = new ArrayList<Cut>();

        cuts.add(new Cut(500, 500));
        cuts.add(new Cut(200, 200));
        cuts.add(new Cut(400, 400));
        cuts.add(new Cut(75, 75));
        cuts.add(new Cut(400, 400));
        cuts.add(new Cut(50, 50));

        System.out.println("Before:");
        for(Cut cut: cuts) {
            System.out.println("Length: " + cut.getLength() + "  Width: " + cut.getWidth());
        }

        /*
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
         */

        CutTree cutTree = entrance(sheet, cuts);
        cuts = cutTree.toArrayList(cutTree.getRoot());

        System.out.println("After:");
        for(Cut cut: cuts) {
            System.out.println("Length: " + cut.getLength() + "  Width: " + cut.getWidth());
        }
    }
}
