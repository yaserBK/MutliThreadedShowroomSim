package CarShowroom.model;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Seller implements Runnable {

    private AtomicInteger sales = new AtomicInteger(1); //keeps track of the total number of sales that occur through the course of the simulation
    private AtomicInteger sellerCount = new AtomicInteger(1); // used to assign seller ids to every seller that appears.
    private int sellerID; //the seller id assigned to every individual seller
    private Random r = new Random(); //instance of class random (used in init method)
    private CarShowroom showroom;

    //constructor that takes instance of CarShowroom class
    public Seller(CarShowroom showroom){
        this.showroom = showroom; //this will act as our buffer to synchronise on in the run method
    }

    //initiates random number of threads
    public void init() throws InterruptedException {
        int x = r.nextInt(4); //returns integer values from 0 to 4
        Thread t; //declaring thread object
        for (int i = 0; i < x; i++) { //iterates the same number of times as the value of x (randomly generated integer)
            Thread.sleep(500); //thread sleep for the sake of simulation.
            t = new Thread(this); //instantiating thread objects with current object as the target.
            t.start(); //running thread.
        }
    }

    //run method (called on start of each thread)
    @Override
    public void run() {
        synchronized (showroom) {//synchronising thread on showroom object
            Car car = new Car(); //creating a new car object for every buyer thread
            sellerID = sellerCount.getAndIncrement(); // incrementing sellerCount and assigning it to sellerID variable
            //outputs following to screen on initial run of thread:
            System.out.println("A new seller #" + getSellerID() + " just appeared with a " + car.toString());

            while(true){ // Infinite loop (breaks when showroom is no longer at maximum capacity)

                try {
                    Thread.sleep(10); //thread sleep for sake of simulation runs
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(showroom.isFull()){ //if the showroom is full the following is done and the while-loop reiterates
                    try {
                        //output
                        System.out.println("new seller " + getSellerID()+ " is trying to sell their car but the showroom is full");
                        showroom.wait(); //threads synced to showroom are told to wait and showroom object is locked
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{ // when the showroom is no longer full

                    showroom.addCar(car); //Car object is added to the showroom
                    System.out.println("Seller # " + getSellerID() + " just sold their " + car.toString() + " to the showroom\n"
                            + "This is sale number "
                            + sales.getAndIncrement()); //increments number of sales and outputs it.

                    showroom.notifyAll(); //all waiting threads are notified
                    break; // LOOP BROKEN
                }
            }
        }
    }

    // getter to return value of sellerID variable.
    public int getSellerID() {
        return sellerID;
    }
}
