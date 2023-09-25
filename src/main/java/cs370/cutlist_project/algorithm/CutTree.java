package cs370.cutlist_project.algorithm;

import cs370.cutlist_project.Cut;

import cs370.cutlist_project.Sheet;

import java.util.ArrayList;


public class CutTree {
    class Node {

        Node widthAxis;

        Node lengthAxis;

        Cut cut;

        Node(Cut cut) {
            this.widthAxis = null;
            this.lengthAxis = null;
            this.cut = cut;
        }

    }
    private Sheet sheet;
    private Node root;
    private final ArrayList<Cut> reject = new ArrayList<>();

    CutTree() {
        this.root = null;
        this.sheet = null;
    }

    CutTree(Sheet sheet) {
        this.root = null;
        this.sheet = sheet;
    }

    private Node recursiveAdd(Node currentNode, Cut cut, double cutsLength, double cutsWidth) {
        if (currentNode == null) {
            return new Node(cut);
        }

        double leftOverLength = this.sheet.getLength() - cutsLength;
        double leftOverWidth = this.sheet.getWidth() - cutsWidth;

        if (cut.getWidth() <= leftOverWidth && cut.getLength() <= currentNode.cut.getLength()) {
            currentNode.widthAxis = recursiveAdd(currentNode.widthAxis, cut, currentNode.cut.getLength(), cutsWidth + cut.getWidth());
        }
        else if (cut.getLength() <= leftOverLength /*&& cut.getWidth() <= currentNode.cut.getWidth()*/) {
            currentNode.lengthAxis = recursiveAdd(currentNode.lengthAxis, cut, cutsLength + cut.getLength(), cutsWidth);
        }
        else {
            return currentNode;
        }

        return currentNode;
    }

    public void add(Cut cut) {
        this.root = recursiveAdd(root, cut, cut.getLength(), cut.getWidth());

    }

}


