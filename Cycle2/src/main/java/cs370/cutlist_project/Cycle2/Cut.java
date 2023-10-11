package cs370.cutlist_project.Cycle2;

import lombok.*;

@NoArgsConstructor
public class Cut {
    @Getter
    private double length;
    @Getter
    private double width;
    @Setter
    @Getter
    private Grain grain;
    @Getter
    private double area;
    @Setter
    @Getter
    private String cutPartCode;
    @Setter
    @Getter
    private String notes;

    public Cut(double length, double width) {
        this.length = length;
        this.width = width;
        this.area = this.length * this.width;
    }

    public Cut(double length, double width, String cutPartCode) {
        this.length = length;
        this.width = width;
        this.cutPartCode = cutPartCode;
        this.area = this.length * this.width;
    }

    public void setLength(double length) {
        this.length = length;
        this.area = this.length * this.width;
    }

    public void setWidth(double width) {
        this.width = width;
        this.area = this.length * this.width;
    }
}
