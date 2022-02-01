package CarShowroom.model;

import java.util.Formatter;
import java.util.Random;

public class Car {
    //private variables
    private String registration;
    private int saleValue;
    private String color;
    private Random randomize = new Random();
    //array of colors, accessed by the generateColor method
    private static final String[] colors = {"yellow", "red", "green", "blue", "pink", "orange", "purple", "white",
            "grey", "maroon", "silver"};

    //publicly accessible constructor
    public Car(){
        //constructor generates and initialises values for the following when Car object is instantiated
        registration = generateRegistration();
        color = generateColor();
        saleValue = generateSaleValue();
    }

    public String generateRegistration(){
        //string of alphabets that from which one will be randomly selected when generating registration
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //int used to randomly select position of single letter from above string
        int alphabetPosition = randomize.nextInt(26);


        return String.format("%02d",randomize.nextInt(100)) + "-" //formats random int to follow format "XX" (00)
                + alphabet.substring(alphabetPosition, alphabetPosition+1) + "-" //selects single letter from alphabet string
                + String.format("%04d", randomize.nextInt(10000)); //formats random int to "XXXX"(0000)

    }

    // selects and returns a random color from static array colors
    private String generateColor(){
        int colorsIndex = randomize.nextInt(11);
        return colors[colorsIndex];
    }

    //generates random integer between 1,000 and 20,000
    private int generateSaleValue(){
        return randomize.nextInt(19001)+1000;
    }

    //getter methods for registration, color and salesValue
    public String getRegistration() {
        return registration;
    }
    public String getColor() {
        return color;
    }
    public int getSaleValue() {
        return saleValue;
    }

    @Override
    public String toString() {
        // method generates string in the following format:
        // [Color] car with registration [Registration] worth $[saleValue]
        return color + " car with registration " + registration + " worth €" + saleValue;
    }
}



