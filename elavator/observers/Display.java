package elavator.observers;

public class Display implements ElevatorObserver {
    public void displayFloor(int floor) {
        System.out.println("Current floor is: " + floor);
    }
}
