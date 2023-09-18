package cs370.cutlist_project.architectural_spike;

import lombok.*;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sheet {
    private double width;
    private double length;
    private double thickness;
    private String material;
    private int quantity;
    private double totalArea;
    private double leftOverArea;
    private double usedArea;
    private ArrayList<Cut> cuts = new ArrayList<Cut>();

    public Sheet (double length, double width, int qty) {
        this.length = length;
        this.width = width;
        quantity = qty;
        totalArea = width * length;
        leftOverArea = totalArea;
        usedArea = 0;
    }

    public void makeCut(double length, double width) {
        Cut cut = new Cut(length, width);
        cuts.add(cut);
        usedArea += cut.getArea();
        leftOverArea -= cut.getArea();

    }
    public double getTotalArea() {
        return totalArea;
    }
    public double getUsedArea() {
        return usedArea;
    }
    public double getLeftOverArea() {
        return leftOverArea;
    }

}
