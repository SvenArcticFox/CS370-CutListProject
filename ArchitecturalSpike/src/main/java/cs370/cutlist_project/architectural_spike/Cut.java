package cs370.cutlist_project.architectural_spike;

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
    private String notes;
}
