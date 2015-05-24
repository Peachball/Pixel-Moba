package game.screens;

import java.io.IOException;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;

public class ServersScreen extends Screen {

    Text text;

    public ServersScreen(RenderWindow window) throws IOException {
        super(window);
        Font font = new Font();
        font.loadFromStream(ClassLoader.getSystemResourceAsStream("res/Time Roman.ttf"));
        text = new Text();
        text.setFont(font);
    }

    @Override
    public Screen update() {
        window.clear();

        window.display();
        return this;
    }

}
