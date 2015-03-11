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
        leaveParking();
    }

    private Integer getParkingSlotNumber(Car car){
        for (Map.Entry<Integer, Car> entry : Parking.parkingPlaces.entrySet()){
            if (car.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
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

    private synchronized void enterParking(){

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

    private synchronized void stayAtParkingPlace(){
        if (car.isInterrupted()){
            try {
                int i;
                for (i = 0; i < car.getAttemptsToSwitchPlace(); i++){
                    switchPlace();
                    TimeUnit.SECONDS.sleep(car.getStayTime()/car.getAttemptsToSwitchPlace());
                    if (car.isSwithedPlace()){
                        break;
                    }
                }
                TimeUnit.SECONDS.sleep((car.getAttemptsToSwitchPlace() - i) * car.getStayTime()/car.getAttemptsToSwitchPlace());
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private synchronized void switchPlace(){
        if (!Parking.parkingPlaces.containsValue(null)){
            for (Map.Entry<Integer, Car> entry : Parking.parkingPlaces.entrySet()){
                if (!entry.getValue().isSwithedPlace() && !car.isSwithedPlace()){
                    lock.lock();
                    Integer thisParkingSlot = getParkingSlotNumber(car);
                    Integer otherParkingSlot = entry.getKey();
                    Parking.parkingPlaces.put(thisParkingSlot, entry.getValue());
                    System.out.println("Car " + entry.getValue().getBrand() + " now stays at parking slot " + thisParkingSlot);
                    entry.getValue().setSwithedPlace();
                    Parking.parkingPlaces.put(otherParkingSlot, car);
                    System.out.println("Car " + entry.getValue().getBrand() + " now stays at parking slot " + otherParkingSlot);
                    car.setSwithedPlace();
                    lock.unlock();
                }
            }
        }

    }

    private synchronized void leaveParking(){
        if (car.isInterrupted()){
            for (Map.Entry<Integer, Car> entry : Parking.parkingPlaces.entrySet()){

                if (car.equals(entry.getValue())){
                    entry.setValue(null);
                    System.out.println("Car " + car.getBrand() + " left parking slot number " + entry.getKey() + ". Bye-bye!");
                    break;
                }

            }

        }

    }
}
