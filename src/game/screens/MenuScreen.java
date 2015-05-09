package game.screens;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.event.Event;

public class MenuScreen extends Screen {

    public MenuScreen(RenderWindow window) {
        super(window);
    }

    @Override
    public Screen update() {
        window.clear();

        //Display current game modes that you can play
        //Add a graphic and some mouse listeners for this later...too hard to implement
        window.display();
        Event e;
        while ((e = window.pollEvent()) != null) {
            switch (e.type) {
                case KEY_PRESSED:
                    switch (e.asKeyEvent().key) {
                        case S: {
                            try {
                                return new GameScreen(window);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        break;
                    }
                    break;

            }
        }
        return this;
    }

}
