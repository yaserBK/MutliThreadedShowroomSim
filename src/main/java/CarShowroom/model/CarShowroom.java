package CarShowroom.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/*
* various threads synchronise on objects of this class
* */
public class CarShowroom {
    private int capacity; // variable that defines the maximum capacity of the car showroom
    private ArrayList<Car> carList; //Arraylist of type car declared (initialised with constructor)

    //constructor
    public CarShowroom(int capacity){ //takes in capacity as an argument
        this.capacity = capacity;//assigns capacity to the relevant variable
        carList = new ArrayList<>();//initialises carList
    }

    //returns true if carList ArrayList is empty, else false.
    public boolean isEmpty(){
        return carList.isEmpty();
    }
    //returns true if carlist size is equal to capacity, else false.
    public boolean isFull(){
        return carList.size() >= capacity;

    }

    //method checks if carlist is full and adds car Object if space is available
    public void addCar(Car c){
        if(!this.isFull()){ //if not full
            carList.add(c);//adds Car object from argument to carList arraylist
        }
    }

    public String takeCar(){
        Car car = carList.get(0); //holds first car in carlist in an instance variable
        carList.remove(0);// removes first car in carlist
        return car.toString();//returns the object.toString to the caller
    }

    //returns size of carList to caller.
    public int carCount(){
        return carList.size();
    }
}
