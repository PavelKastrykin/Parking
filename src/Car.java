import java.util.concurrent.TimeUnit;

public class Car extends Thread{
    private String brand;
    private int distanceToParking;
    private int patienceTime;
    private int stayTime;
    private int attemptsToSwitchPlace;
    private volatile boolean interrupted = false;
    private volatile boolean hasSwithedPlace = false;

    public Car(String brand, int distanceToParking, int patienceTime, int stayTime, int attemptsToSwitchPlace) {
        this.brand = brand;
        this.distanceToParking = distanceToParking;
        this.patienceTime = patienceTime;
        this.stayTime = stayTime;
        this.attemptsToSwitchPlace = attemptsToSwitchPlace;
    }

    public void run(){
        try {
            TimeUnit.SECONDS.sleep(patienceTime);
        }

        catch (InterruptedException e){
            e.printStackTrace();
        }

        if (!isInterrupted()){
            System.out.println("Car " + getBrand() + " tired to wait and left home.");
            Parking.queueToEnterParking.remove(this);

        }

    }


    public String getBrand() {
        return brand;
    }

    public int getDistanceToParking() {
        return distanceToParking;
    }

    public int getPatienceTime() {
        return patienceTime;
    }

    public int getStayTime() {
        return stayTime;
    }

    public boolean isInterrupted(){
        return interrupted;
    }

    public void setInterrupted(){
        interrupted = true;
    }

    public boolean isSwithedPlace(){
        return hasSwithedPlace;
    }

    public void setSwithedPlace(){
        hasSwithedPlace = true;
    }

    public int getAttemptsToSwitchPlace(){
        return attemptsToSwitchPlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (distanceToParking != car.distanceToParking) return false;
        if (interrupted != car.interrupted) return false;
        if (patienceTime != car.patienceTime) return false;
        if (stayTime != car.stayTime) return false;
        if (!brand.equals(car.brand)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = 31 * result + distanceToParking;
        result = 31 * result + patienceTime;
        result = 31 * result + stayTime;
        result = 31 * result + (interrupted ? 1 : 0);
        return result;
    }
}
