package game.world.champions;

import game.screens.Frame;
import game.world.bullets.BulletBill;
import java.io.IOException;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.window.Mouse;

public class God extends Player {

    public final String DEFAULT_IMAGE_PATH = "ninja.jpg";
    public final int DEFAULT_SIZEX = 20;
    public final int DEFAULT_SIZEY = 20;

    private void init() throws IOException {
        Sprite a = new Sprite();
        Texture buffer = Frame.loadImage(DEFAULT_IMAGE_PATH);
        a.setTexture(buffer);
        this.texture = a;
        hp = 1000;
        maxhp = 1000;
        mana = 1000;
        maxmana = 1000;
        hpregen = 10;
        dmg = 10;
    }

    public God() throws IOException {
        super();
        init();
        speed = 1;
        sizex = DEFAULT_SIZEX;
        sizey = DEFAULT_SIZEY;
    }

    @Override
    public void useQ(int x, int y) {
        try {
            map.addSprite(new BulletBill(20, 20, (int) xPos, (int) yPos, x, y));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void useW(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void useE(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void useR(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void useD(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void useF(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void levelup() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
