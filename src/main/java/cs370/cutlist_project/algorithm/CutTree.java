package cs370.cutlist_project.algorithm;

import cs370.cutlist_project.Cut;

import cs370.cutlist_project.Sheet;


public class CutTree {
    class Node {

        Node left;

        Node right;

        Cut cut;

        Node(Cut cut) {
            this.left = null;
            this.right = null;
            this.cut = cut;
        }

    }
    private Sheet sheet;
    private Node root;

    CutTree() {
        this.root = null;
        this.sheet = null;
    }

    CutTree(Sheet sheet) {
        this.root = null;
        this.sheet = sheet;
    }

    private Node recursiveAdd(Node currentNode, Cut cut, double length, double width) {
        if (currentNode == null) {
            return new Node(cut);
        }

        double leftOverLength = this.sheet.getLength() - length;
        double leftOverWidth = this.sheet.getWidth() - width;

        if (cut.getWidth() <= leftOverWidth && cut.getLength() <= currentNode.cut.getLength()) {
            currentNode.left = recursiveAdd(currentNode.left, cut, length, width + cut.getWidth());
        }
        else if (cut.getLength() <= leftOverLength && cut.getWidth() <= currentNode.cut.getWidth()) {
            currentNode.right = recursiveAdd(currentNode.right, cut, length + cut.getLength(), width);
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


