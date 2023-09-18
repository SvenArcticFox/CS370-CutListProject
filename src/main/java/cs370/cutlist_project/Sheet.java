package cs370.cutlist_project;
import lombok.*;

@NoArgsConstructor
public class Sheet {
    @Getter
    private double length;
    @Getter
    private double width;
    @Setter
    @Getter
    private Grain grain;
    @Getter
    private double totalArea;
    @Getter
    private double leftOverArea;
    @Setter
    @Getter
    private double thickness;
    @Getter
    private double usedArea;
    @Setter
    @Getter
    private String notes;

    public Sheet(double length, double width) {
        this.length = length;
        this.width = width;
        this.totalArea = length * width;
    }

    public void setUsedArea(double usedArea) {
        this.usedArea = usedArea;
        this.leftOverArea = this.totalArea - this.usedArea;
    }

    public void setLength(double length) {
        this.length = length;
        this.totalArea = this.length * this.width;
    }

    public void setWidth(double width) {
        this.width = width;
        this.totalArea = this.length * this.width;
    }
}