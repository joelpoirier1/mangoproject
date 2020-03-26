package Model;

import java.util.Random;

//This class is used for generating display names in the Comments and Posts classes

public class DisplayNameGenerator {

    final char SPACE = '_';

    String [] colors  = {"Red", "Blue", "Yellow", "White", "Black"};
    String [] fruitNames  = {"Apple", "Banana", "Lemon", "Grapes", "Plum"};

    //Generates a random display name and returns it. For example "Red_Apple"
    public String generateDisplayName(){
        Random rand = new Random();
        int colorIndex = rand.nextInt(colors.length - 1);
        int fruitIndex = rand.nextInt(colors.length - 1);
        String generatedDisplayName = colors[colorIndex] + SPACE + fruitNames[fruitIndex];
        return generatedDisplayName;
    }
}
