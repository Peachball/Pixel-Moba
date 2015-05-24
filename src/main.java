
import game.screens.Screen;
import game.screens.StartScreen;
import java.io.IOException;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;


public class main {

    public static void main(String[] args) throws  IOException {
        RenderWindow window = new RenderWindow(new VideoMode(800,600),"HI");
        Screen screen = new StartScreen(window);
        while(window.isOpen()){
            screen = screen.update();
            if(Keyboard.isKeyPressed(Keyboard.Key.ESCAPE)){
                break;
            }
        }
        window.close();
        System.exit(0);
    }
}
