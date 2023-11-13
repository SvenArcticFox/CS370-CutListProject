package cs370.cutlist_project.Cycle3;


import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Getter
    @Setter
    public Rectangle rec = new Rectangle(0,0);
    @Getter
    @Setter
    public Button delBut;


    public Cut(double length, double width) {
        this.length = length;
        this.width = width;
        this.area = this.length * this.width;
        this.delBut = new Button("Delete");
    }

    public Cut(double length, double width, String cutPartCode) {
        this.length = length;
        this.width = width;
        rec.setHeight(length);
        rec.setWidth(width);
        this.cutPartCode = cutPartCode;
        this.area = this.length * this.width;
        this.delBut = new Button("Delete");
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
