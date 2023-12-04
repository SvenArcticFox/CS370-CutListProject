package cs370.cutlist_project.Cycle3.algorithm;

import cs370.cutlist_project.Cycle3.Cut;
import cs370.cutlist_project.Cycle3.Sheet;

import lombok.Getter;

import java.util.ArrayList;


public class CutTree {

    private Sheet sheet;
    @Getter
    private Node root;
    private final ArrayList<Cut> reject = new ArrayList<>();

    public class Node {
        @Getter
        Node widthAxis;

        @Getter
        Node lengthAxis;

        @Getter
        Cut cut;

        double leftOverWidth;
        double leftOverLength;



        Node(Cut cut, double leftOverWidth, double leftOverLength) {
            this.widthAxis = null;
            this.lengthAxis = null;
            this.cut = cut;
            this.leftOverWidth = leftOverWidth;
            this.leftOverLength = leftOverLength;

            //this.sheet = sheet;
        }

    }

    //private double totalCutsWidth;
    //private double totalCutsLength;

    CutTree() {
        this.root = null;
        this.sheet = null;
    }

    public CutTree(Sheet sheet) {
        this.root = null;
        this.sheet = sheet;
    }


    /**
     *
     * @param currentNode The current node that is being worked with in the tree
     * @param addedCut The cut object to be placed in the tree
     * @param sheetLength The length of the sheet or the length of the cut if placing the cut on the width axis
     * @return The new node that contains the cut object
     */
    //RecursiveAdd will take a Node, cut, and 3 double objects one being the totalCutLength, totalCutWidth and sheetLength
    private Node recursiveAdd(Node currentNode, Cut addedCut,
                              double sheetLength) {
        //if node object is null, make new node from the cut object brought in
        if (currentNode == null) {
            addedCut.setPlaced(true);
            return new Node(addedCut, sheet.getWidth() - addedCut.getWidth(), sheetLength - addedCut.getLength());
        }


        // System.out.println(totalCutsWidth + "\t" + totalCutsLength);


        if (currentNode.widthAxis == null) {
            if (addedCut.getWidth() <= currentNode.leftOverWidth && addedCut.getLength() <= sheetLength) {
                currentNode.widthAxis = new Node(addedCut, currentNode.leftOverWidth - addedCut.getWidth(),
                        currentNode.leftOverLength + (currentNode.cut.getLength() - addedCut.getLength()));
                addedCut.setPlaced(true);
                return currentNode;
            }



        }
        ///////////////////////////////////////////////
        else if (currentNode.widthAxis != null) {
            //"looks ahead" and checks to see if the leftover width is greater than or equal to the width of the new cut

            if (currentNode.widthAxis.leftOverWidth >= addedCut.getWidth() && addedCut.getLength() <= sheetLength) {
                currentNode.widthAxis = recursiveAdd(currentNode.widthAxis, addedCut, sheetLength);
                return currentNode;
            }
            // If the leftover width is less than the width of the new cut, add it to the length axis
            else if (currentNode.widthAxis.leftOverLength - currentNode.leftOverLength >= addedCut.getLength() &&
                    currentNode.leftOverWidth >= addedCut.getWidth()) {
                currentNode.widthAxis = recursiveAdd(currentNode.widthAxis, addedCut,
                        sheetLength);
                return currentNode;
            }
        }

        if (currentNode.lengthAxis == null && !addedCut.isPlaced()) {
            if (currentNode.leftOverLength >= addedCut.getLength() && addedCut.getWidth() <= sheet.getWidth()) {
                currentNode.lengthAxis = new Node(addedCut, currentNode.leftOverWidth +
                        (currentNode.cut.getWidth() - addedCut.getWidth()), currentNode.leftOverLength - addedCut.getLength());
                addedCut.setPlaced(true);
                return currentNode;
            }
        }
        //&&&????///////////////////////////////
        else if (currentNode.lengthAxis != null && !addedCut.isPlaced()) {
            if (currentNode.lengthAxis.leftOverLength >= addedCut.getLength() && addedCut.getWidth() <= sheet.getWidth()) {
                currentNode.lengthAxis = recursiveAdd(currentNode.lengthAxis, addedCut, sheetLength);
                return currentNode;
            } else if (currentNode.lengthAxis.leftOverWidth - currentNode.leftOverLength >= addedCut.getLength() &&
                    currentNode.leftOverLength >= addedCut.getLength()) {
                currentNode.lengthAxis = recursiveAdd(currentNode.lengthAxis, addedCut,
                        sheetLength);
                return currentNode;
            }

        }
        else {
            reject.add(addedCut);
        }

        return currentNode;
    }

    public void add(Cut cut) {
        this.root = recursiveAdd(root, cut, sheet.getLength());

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
