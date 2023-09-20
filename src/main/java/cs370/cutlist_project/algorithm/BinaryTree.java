package cs370.cutlist_project.algorithm;

import cs370.cutlist_project.Cut;

import lombok.*;

@NoArgsConstructor
public class BinaryTree {
    class Node {

        Node left;

        Node right;
        Cut cut;

        void recursiveAdd(Node currentNode, Cut cut) {

        }

    }
    @Getter
    @Setter
    private Node root;

    public void add(Cut cut) {
        root.recursiveAdd(root, cut);
    }

}


