package game.screens;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;

public class StartScreen extends Screen {

    String out;
    Texture ninja;
    Font font;

    public StartScreen(RenderWindow window) {
        super(window);
        this.window = window;
        out = "TEST";
        try {
            ninja = Frame.loadImage("ninja.jpg");
            font = new Font();
            font.loadFromStream(ClassLoader.getSystemResourceAsStream("res/Time Roman.ttf"));

        } catch (Exception e) {
        }
    }

    //This should all be about rendering stuff
    @Override
    public Screen update() {
        window.clear();
        
        //Ideally, the stuff hear should be a start screen with a nice background
        //but I suck at aesthetics
        Text output = new Text();
        output.setFont(font);
        output.setString(out);
        output.setColor(Color.RED);
        output.setPosition(new Vector2f(300, 300));
        output.setCharacterSize(24);
        window.draw(output);
        Sprite sprite = new Sprite();
        sprite.setTexture(ninja);
        window.draw(sprite);
        window.display();
        //Key input reading loop
        Event buffer;
        while ((buffer = window.pollEvent()) != null) {
            switch (buffer.type) {
                case CLOSED:
                    window.close();
                    break;
                case KEY_PRESSED:
                    switch (buffer.asKeyEvent().key) {
                        case A:
                            out = "YAY";
                            break;
                        case B:
                            out = "BOO";
                            break;
                        case C:
                            window.create(VideoMode.getFullscreenModes()[0], out, Window.FULLSCREEN);
                            break;
                        case D:
                            window.create(new VideoMode(800, 800), out, Window.DEFAULT);
                            break;
                        case E: {
                            try {
                                return new GameScreen(window);
                            } catch (IOException ex) {
                                Logger.getLogger(StartScreen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    break;

            }

        }
        return this;
    }

}
