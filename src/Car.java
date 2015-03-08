import java.util.concurrent.TimeUnit;

public class Car extends Thread{
    private String brand;
    private int distanceToParking;
    private int patienceTime;
    private int stayTime;
    private boolean interrupted = false;

    public Car(String name, int distanceToParking, int patienceTime, int stayTime) {
        this.brand = name;
        this.distanceToParking = distanceToParking;
        this.patienceTime = patienceTime;
        this.stayTime = stayTime;
    }

    public void run(){
        try {
            TimeUnit.SECONDS.sleep(patienceTime);
        }

        catch (InterruptedException e){
            e.printStackTrace();
        }
        if (!isInterrupted()){
            System.out.println();
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

}
