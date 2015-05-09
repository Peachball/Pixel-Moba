package game.screens;

import org.jsfml.graphics.RenderWindow;

public abstract class Screen {

    protected RenderWindow window;

    public Screen(RenderWindow window) {
        this.window = window;
    }

    public abstract Screen update();
}
