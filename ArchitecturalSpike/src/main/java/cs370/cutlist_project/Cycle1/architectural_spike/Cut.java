package cs370.cutlist_project.Cycle1.architectural_spike;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cut {
    private double length;
    private double width;
    private Sheet sheet;
    private String cutPartCode;
    private double area;

    public Cut(double length, double width) {
        this.length = length;
        this.width = width;
        area = length * width;

    }

    public double getArea() {
        return area;
    }
    private String notes;
}
