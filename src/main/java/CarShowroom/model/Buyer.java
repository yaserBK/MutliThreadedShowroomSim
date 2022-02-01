package CarShowroom.model;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Buyer implements Runnable {

    Random r = new Random(); // instance of Random Class
    private AtomicInteger counter = new AtomicInteger(0); //atomic integer used to count total number of buyers
    private CarShowroom showroom; // Declaring object of Type CarShowroom
    private static int purchaseCount = 0; //static integer to count total number of purchases

    //constructor that takes instance fo CarShowroom object and initialises object showroom
    public Buyer(CarShowroom showroom){
        this.showroom = showroom;
    } //buyer constructor

    //method that produces random number of threads
    public void init() throws InterruptedException {
        int x = r.nextInt(4);//randomiser that generates a number between 0 and 3
        Thread t; //declaring thread
        for(int i = 0; i < x; i++){
            Thread.sleep(500); //thread sleep for the sake of simulation
            t = new Thread(this); //instantiating thread with this class
            t.start();//starting thread
        }
    }

    @Override
    public void run() {
        synchronized (showroom) { //thread synchronised on Object of type CarShowroom
            int uniqueID = counter.incrementAndGet(); //generating a unique id by assigning the counter value to it

            System.out.println("A new buyer #" + uniqueID + " just appeared"); //outputs message
            /*
            * the following while loop has the threads wait
            * while the showroom is empty
            * the threads constantly checks to see if there is a car object availible
            * before finally buying an object, notifying the other waiting threads and
            * naturally terminating*/
            while (showroom.isEmpty()) { //continuously iterates while showroom is empty
                System.out.println("Buyer #" + uniqueID + " wants to buy a car, but the showroom is empty");
                    try {
                        showroom.wait(); //tells threads to wait while showroom is empty and locks the object
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            } //loop terminates if showroom is not empty
            //all threads are notified and
            showroom.notifyAll(); //notifying all waiting threads
            purchaseCount+=1; //incrementing purchase counter
            //the following line calls the takeCar method while outputting what it returns along with uniqueID and purchaseCount(i.e. total number of purchases)
            System.out.print("Buyer #"
                    + uniqueID
                    + " just bought a "
                    + showroom.takeCar()
                    + "\nThis is purchase number "
                    + purchaseCount + "\n");
        }
    }
}
