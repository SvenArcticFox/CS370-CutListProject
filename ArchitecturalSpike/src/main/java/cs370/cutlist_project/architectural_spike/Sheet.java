package cs370.cutlist_project.architectural_spike;

import lombok.*;

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
}
