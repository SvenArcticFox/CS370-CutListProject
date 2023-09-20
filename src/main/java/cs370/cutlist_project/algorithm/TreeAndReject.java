package cs370.cutlist_project.algorithm;

import lombok.*;

import cs370.cutlist_project.Cut;

import java.util.ArrayList;

@NoArgsConstructor
public class TreeAndReject {
    @Setter
    @Getter
    private CutTree tree = new CutTree();

    @Getter
    private final ArrayList<Cut> rejectList = new ArrayList<>();

    public void addReject(Cut reject) {
        rejectList.add(reject);
    }

    public void removeReject(Cut reject) {
        rejectList.remove(reject);
    }

    public void removeReject(int index) {
        rejectList.remove(index);
    }
}
