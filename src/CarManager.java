import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarManager extends Thread{
    private Car car;
    Lock lock = new ReentrantLock();
    public CarManager(Car car){
        this.car = car;
    }

    public void run(){
        goToParking();
        enterParking();
        stayAtParkingPlace();
        switchPlace();
        stayAtParkingPlace();
        leaveParking();
    }

    private void goToParking(){
        try {
            System.out.println("Car " + car.getBrand() + " now runs towards Parking.");
            TimeUnit.SECONDS.sleep(car.getDistanceToParking());
            Parking.queueToEnterParking.add(car);
            System.out.println("Car " + car.getBrand() + " stays in queue to enter Parking.");
            car.start();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }


    }

    private void enterParking(){

        if (!car.isInterrupted()){
            try {
                outer:
                while (Parking.queueToEnterParking.contains(car)){
                    if (Parking.parkingPlaces.containsValue(null)){
                        for (Map.Entry<Integer, Car> entry : Parking.parkingPlaces.entrySet()){
                            if (entry.getValue() == null){
                                lock.lock();
                                entry.setValue(Parking.queueToEnterParking.take());
                                System.out.println("Car " + car.getBrand() + " took place at parking slot number "
                                        + entry.getKey());
                                car.setInterrupted();
                                lock.unlock();
                                break outer;

                            }
                        }
                    }
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void stayAtParkingPlace(){
        if (car.isInterrupted()){
            try {
                TimeUnit.SECONDS.sleep(car.getStayTime());
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void switchPlace(){

    }

    private void leaveParking(){
        if (car.isInterrupted()){
            for (Map.Entry<Integer, Car> entry : Parking.parkingPlaces.entrySet()){
                entry.setValue(null);
                break;
            }
            System.out.println("Car " + car.getBrand() + " left Parking. Bye-bye!");
        }

    }

}
