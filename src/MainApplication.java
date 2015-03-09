
public class MainApplication {
    public static void main(String[] args){
        CarManager carManager1 = new CarManager(new Car("Toyota", 4, 15, 3));
        CarManager carManager2 = new CarManager(new Car("Mercedes", 5, 20, 13));
        CarManager carManager3 = new CarManager(new Car("BMW", 7, 13, 18));
        CarManager carManager4 = new CarManager(new Car("Honda", 8, 11, 11));
        CarManager carManager5 = new CarManager(new Car("Audi", 10, 24, 13));
        CarManager carManager6 = new CarManager(new Car("VW", 15, 14, 15));
        CarManager carManager7 = new CarManager(new Car("Opel", 17, 31, 8));
        CarManager carManager8 = new CarManager(new Car("Ford", 21, 1, 6));
        carManager1.start();
        carManager2.start();
        carManager3.start();
        carManager4.start();
        carManager5.start();
        carManager6.start();
        carManager7.start();
        carManager8.start();
    }



}
