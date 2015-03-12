import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Parking {
    public static volatile Queue<Car> queueToEnterParking = new ConcurrentLinkedQueue<Car>();
    public static volatile HashMap<Integer, Car> parkingPlaces;
    static {
        parkingPlaces = new HashMap<Integer, Car>();
        parkingPlaces.put(1, null);
        parkingPlaces.put(2, null);
        parkingPlaces.put(3, null);
        parkingPlaces.put(4, null);
        parkingPlaces.put(5, null);
    }
}
