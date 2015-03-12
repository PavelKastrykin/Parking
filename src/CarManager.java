import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CarManager extends Thread{
    private Car car;

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
            Parking.queueToEnterParking.offer(car);
            System.out.println("Car " + car.getBrand() + " stays in queue to enter Parking.");
            car.start();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void enterParking(){
        if (!car.isInterrupted()){
            outer:
            while (Parking.queueToEnterParking.contains(car)){
                if (Parking.parkingPlaces.containsValue(null) && car.equals(Parking.queueToEnterParking.peek())){
                    synchronized (Parking.parkingPlaces){
                        for (Map.Entry<Integer, Car> entry : Parking.parkingPlaces.entrySet()){
                            if (entry.getValue() == null){
                                entry.setValue(Parking.queueToEnterParking.poll());
                                System.out.println("Car " + car.getBrand() + " took place at parking slot number "
                                        + entry.getKey());
                                car.setInterrupted();
                                break outer;
                            }
                        }
                    }
                }
            }
        }
    }

    private void stayAtParkingPlace(){
        if (car.isInterrupted()){
            try {
                int i;
                for (i = 0; i < car.getAttemptsToSwitchPlace(); i++){
                    switchPlace();
                    TimeUnit.SECONDS.sleep(car.getStayTime()/car.getAttemptsToSwitchPlace());
                    if (car.isSwitchedPlace()){
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

    private void switchPlace(){
        synchronized (Parking.parkingPlaces){
            if (!Parking.parkingPlaces.containsValue(null)){
                for (Map.Entry<Integer, Car> entry : Parking.parkingPlaces.entrySet()){
                    if (!entry.getValue().isSwitchedPlace() && !car.isSwitchedPlace()){
                        if (entry.getKey().equals(this.getParkingSlotNumber(car))){
                            continue;
                        }
                        Integer thisParkingSlot = getParkingSlotNumber(car);
                        if (thisParkingSlot == null){
                            break;
                        }
                        Integer otherParkingSlot = entry.getKey();
                        Parking.parkingPlaces.put(thisParkingSlot, entry.getValue());
                        System.out.println("Car " + entry.getValue().getBrand() + " now stays at parking slot number "
                                + thisParkingSlot);
                        entry.getValue().setSwitchedPlace();
                        Parking.parkingPlaces.put(otherParkingSlot, car);
                        System.out.println("Car " + entry.getValue().getBrand() + " now stays at parking slot number "
                                + otherParkingSlot);
                        car.setSwitchedPlace();
                        break;
                    }
                }
            }
        }
    }

    private void leaveParking() {
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
