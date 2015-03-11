
public class MainApplication {
    public static void main(String[] args){
        CarManager carManager1 = new CarManager(new Car("Toyota", 4, 15, 3, 1));
        CarManager carManager2 = new CarManager(new Car("Mercedes", 5, 20, 13, 2));
        CarManager carManager3 = new CarManager(new Car("BMW", 7, 13, 18, 3));
        CarManager carManager4 = new CarManager(new Car("Honda", 8, 11, 11, 4));
        CarManager carManager5 = new CarManager(new Car("Audi", 10, 24, 13, 5));
        CarManager carManager6 = new CarManager(new Car("VW", 15, 14, 15, 4));
        CarManager carManager7 = new CarManager(new Car("Opel", 17, 31, 8, 3));
        CarManager carManager8 = new CarManager(new Car("Ford", 21, 1, 6, 2));
        CarManager carManager9 = new CarManager(new Car("Saab", 24, 7, 8, 1));
        CarManager carManager10 = new CarManager(new Car("GMC", 20, 9, 10, 2));
        CarManager carManager11 = new CarManager(new Car("Lexus", 17, 13, 14, 3));
        CarManager carManager12 = new CarManager(new Car("Nissan", 15, 12, 20, 4));
        CarManager carManager13 = new CarManager(new Car("KIA", 11, 18, 15, 5));
        CarManager carManager14 = new CarManager(new Car("Skoda", 16, 20, 11, 4));
        CarManager carManager15 = new CarManager(new Car("Seat", 19, 15, 7, 3));
        CarManager carManager16 = new CarManager(new Car("Subaru", 23, 10, 13, 2));
        carManager1.start();
        carManager2.start();
        carManager3.start();
        carManager4.start();
        carManager5.start();
        carManager6.start();
        carManager7.start();
        carManager8.start();
        carManager9.start();
        carManager10.start();
        carManager11.start();
        carManager12.start();
        carManager13.start();
        carManager14.start();
        carManager15.start();
        carManager16.start();
    }



}
