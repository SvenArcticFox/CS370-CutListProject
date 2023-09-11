package cs370.cutlist_project.architectural_spike;

public class DisplayArea {
    public static void main(String[] args) {
        Sheet sheet = new Sheet(100, 50, 1);
        sheet.makeCut(20, 30);
        display(sheet);
        sheet.makeCut(10, 15);
        display(sheet);

    }
    public static void display(Sheet sheet) {
        System.out.println("Total Area: " + sheet.getTotalArea());
        System.out.println("Used Area: " + sheet.getUsedArea() + " | " + (int)Math.round((sheet.getUsedArea() / sheet.getTotalArea() * 100)) + "%");
        System.out.println("Left Over Area: " + sheet.getLeftOverArea() + " | " + (int)Math.round((sheet.getLeftOverArea() / sheet.getTotalArea() * 100)) + "%");

        System.out.println();


    }

}
