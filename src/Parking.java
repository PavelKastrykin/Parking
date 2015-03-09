import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Parking {
    public static BlockingQueue<Car> queueToEnterParking = new ArrayBlockingQueue<Car>(50);
    public static HashMap<Integer, Car> parkingPlaces;
    static {
        parkingPlaces = new HashMap<Integer, Car>();
        parkingPlaces.put(1, null);
        parkingPlaces.put(2, null);
        parkingPlaces.put(3, null);
        parkingPlaces.put(4, null);
        parkingPlaces.put(5, null);
    }
}
