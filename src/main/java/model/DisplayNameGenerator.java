package model;
import java.util.Random;

//This class is used in Comments and Posts classes

public class DisplayNameGenerator {

    final char SPACE = '_';

    String [] colors  = {"Red", "Blue", "Yellow", "White", "Black"};
    String [] fruitNames  = {"Apple", "Banana", "Lemon", "Grapes", "Plum"};

    //Generates a random display name and returns it. For example "Red_Apple"
    public String generateDisplayName(){
        Random rand = new Random();
        int colorNumber = rand.nextInt(colors.length) - 1;
        int fruitNumber = rand.nextInt(colors.length) - 1;
        String generatedDisplayName = colors[colorNumber] + SPACE + fruitNames[fruitNumber];
        return generatedDisplayName;
    }
}
