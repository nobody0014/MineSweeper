import ScreenController.Screen;

/**
 * Created by wit on 7/5/2016.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Making a Screen");
        Screen someScreen = new Screen();
        System.out.println("Done");
        System.out.println("Bringing up the windows");
        someScreen.makeScreen();
        System.out.println("Done");
    }
}
