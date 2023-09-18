package cs370.cutlist_project.algorithm;

import cs370.cutlist_project.Cut;

import lombok.*;

@NoArgsConstructor
public class BinaryTree {
    @Getter
    @Setter
    private Node root;

}

class Node {

    Node left;

    Node right;
    Cut cut;


}
