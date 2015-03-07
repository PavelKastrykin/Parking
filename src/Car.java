
public class Car {
    private String name;
    private int distanceToParking;
    private int triesToEnterParkingQuantity;
    private int patienceTime;
    private int stayTime;

    public Car(String name, int distanceToParking, int triesToEnterParkingQuantity, int patienceTime, int stayTime) {
        this.name = name;
        this.distanceToParking = distanceToParking;
        this.triesToEnterParkingQuantity = triesToEnterParkingQuantity;
        this.patienceTime = patienceTime;
        this.stayTime = stayTime;
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
}
