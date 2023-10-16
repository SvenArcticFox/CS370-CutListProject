package cs370.cutlist_project.Cycle1.algorithm;

import cs370.cutlist_project.Cycle1.Cut;
import cs370.cutlist_project.Cycle1.Sheet;
import lombok.Getter;

import java.util.ArrayList;


public class CutTree {
    class Node {
        Sheet sheet;

        Node widthAxis;

        Node lengthAxis;

        Cut cut;

        Node(Cut cut/*, Sheet sheet*/) {
            this.widthAxis = null;
            this.lengthAxis = null;
            this.cut = cut;
            //this.sheet = sheet;
        }

    }
    private Sheet sheet;
    @Getter
    private Node root;
    private final ArrayList<Cut> reject = new ArrayList<>();
    //private double totalCutsWidth;
    //private double totalCutsLength;

    CutTree() {
        this.root = null;
        this.sheet = null;
    }

    CutTree(Sheet sheet) {
        this.root = null;
        this.sheet = sheet;
    }

    /**
     *
     * @param currentNode The current node that is being worked with in the tree
     * @param addedCut The cut object to be placed in the tree
     * @param totalCutsLength The total length of all the cuts
     * @param totalCutsWidth The total width of all the cuts
     * @param sheetLength The length of the sheet or the length of the cut if placing the cut on the width axis
     * @return The new node that contains the cut object
     */
    private Node recursiveAdd(Node currentNode, Cut addedCut, double totalCutsLength, double totalCutsWidth,
                              double sheetLength) {
        if (currentNode == null) {
            //totalCutsLength += addedCut.getLength();
            //totalCutsWidth += addedCut.getWidth();
            return new Node(addedCut/*, addedSheet*/);
        }

        System.out.println(totalCutsWidth + "\t" + totalCutsLength);

        double leftOverLength = sheetLength - totalCutsLength;
        double leftOverWidth = sheet.getWidth() - totalCutsWidth;

        if (addedCut.getWidth() <= leftOverWidth && addedCut.getLength() <= currentNode.cut.getLength()) {
            //Cut sheet off by the length of the last cut
            currentNode.widthAxis = recursiveAdd(currentNode.widthAxis, addedCut, currentNode.cut.getLength(),
                    currentNode.cut.getWidth() + addedCut.getWidth(),  currentNode.cut.getLength());
        }
        else if (addedCut.getLength() <= leftOverLength /*&& addedCut.getWidth() <= currentNode.cut.getWidth()*/) {
            currentNode.lengthAxis = recursiveAdd(currentNode.lengthAxis, addedCut,
                    currentNode.cut.getLength() + addedCut.getLength(), totalCutsWidth, sheetLength);
        }
        else {
            return currentNode;
        }

        return currentNode;
    }

    public void add(Cut cut) {
        this.root = recursiveAdd(root, cut, cut.getLength(), cut.getWidth(), sheet.getLength());

    }

    public void printTree() {
        printTreeUtil(this.root, 0, 10);
    }

    private void printTreeUtil(Node currentNode, int space, int spaceCount) {
        if (currentNode == null)
            return;

        space += spaceCount;

        printTreeUtil(currentNode.widthAxis, space, spaceCount);

        System.out.println("\n");

        for (int i = spaceCount; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(currentNode.cut.getArea() + "\t" + currentNode.cut.getLength() + "\t"
                + currentNode.cut.getWidth()+ "\n");

        printTreeUtil(currentNode.lengthAxis, space, spaceCount);
    }

}


