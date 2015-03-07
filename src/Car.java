
public class Car {
    private String name;
    private int distanceToParking;
    private int triesToEnterParkingQuantity;
    private int patienceTime;
    private int stayTime;
    private int totalTime;

    public Car(String name, int distanceToParking, int triesToEnterParkingQuantity, int patienceTime, int stayTime,
               int totalTime) {
        this.name = name;
        this.distanceToParking = distanceToParking;
        this.triesToEnterParkingQuantity = triesToEnterParkingQuantity;
        this.patienceTime = patienceTime;
        this.stayTime = stayTime;
        this.totalTime = totalTime;
    }

    public String getName() {
        return name;
    }

    public int getDistanceToParking() {
        return distanceToParking;
    }

    public int getTriesToEnterParkingQuantity() {
        return triesToEnterParkingQuantity;
    }

    public int getPatienceTime() {
        return patienceTime;
    }

    public int getStayTime() {
        return stayTime;
    }

    public int getTotalTime(){
        return totalTime;
    }
}
