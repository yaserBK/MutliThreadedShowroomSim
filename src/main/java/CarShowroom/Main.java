package CarShowroom;
import CarShowroom.model.Buyer;
import CarShowroom.model.Car;
import CarShowroom.model.CarShowroom;
import CarShowroom.model.Seller;



public class Main {
    public static void main(String[] args) throws InterruptedException {

        CarShowroom showroom = new CarShowroom(10); //new CarShowroom object instantiated with capacity of 10 car objects

        //declaring and instantiating seller and buyer objects
        Seller seller = new Seller(showroom);
        Buyer buyer = new Buyer(showroom);

        //simulating a run of thirty days
        int day = 0;
        while(day<30) {
            System.out.println("--------------------------------------------");//improves output readability
            //outputs number of days that have passed and the number of cars in the showroom during each day.
            System.out.println("Day " + (day + 1) + " there are " + showroom.carCount() + " cars in the showroom today");
            System.out.println("--------------------------------------------");//improves output readability
            seller.init(); //initialises threads from seller class
            buyer.init(); //initialises random number of threads for buyer class
            day++; //incrementing day.
            Thread.sleep(20);//small delay in loop
        }









    }
}
