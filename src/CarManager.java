
public class CarManager extends Thread{
    private Car car;

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

    }

    private void enterParking(){

    }

    private void stayAtParkingPlace(){

    }

    private void switchPlace(){

    }

    private void leaveParking(){

    }

}
