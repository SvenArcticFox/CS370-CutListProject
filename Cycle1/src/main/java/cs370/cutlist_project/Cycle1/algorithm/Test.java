package cs370.cutlist_project.Cycle1.algorithm;

public class Test {
    private static class Node {
        Node right;
        Node left;
        int value;

        Node(int value) {
            this.value = value;
            this.right = null;
            this.left = null;
        }
    }

    private static Node root;


    public static void main(String[] args) {
        root = new Node(7);
        root.right = new Node(9);
        root.left = new Node(5);
        root.left.right = new Node(6);
        root.left.left = new Node(3);
        root.left.left.left = new Node((1));
        root.left.left.right = new Node(4);
        root.right.left = new Node(8);
        root.right.right = new Node(11);

        printTree();

    }

    public static void printTree() {
        printTreeUtil(root, 0, 10);
    }

    private static void printTreeUtil(Node currentNode, int space, int spaceCount) {
        if (currentNode == null)
            return;

        space += spaceCount;

        printTreeUtil(currentNode.right, space, spaceCount);

        System.out.println("\n");

        for (int i = spaceCount; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(currentNode.value);

        printTreeUtil(currentNode.left, space, spaceCount);
    }
}
