package cs370.cutlist_project.algorithm;

import cs370.cutlist_project.Cut;

import lombok.*;

@NoArgsConstructor
public class CutTree {
    @Getter
    @Setter
    private CutTree parent;
    @Setter
    @Getter
    private CutTree left;
    @Setter
    @Getter
    private CutTree right;
    @Setter
    @Getter
    private Cut cut;
}
