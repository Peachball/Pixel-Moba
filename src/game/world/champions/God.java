package game.world.champions;

import game.screens.Frame;
import game.world.bullets.BulletBill;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.window.Mouse;

public class God extends Player {

    public final String DEFAULT_IMAGE_PATH = "ninja.jpg";
    public final int DEFAULT_SIZEX = 20;
    public final int DEFAULT_SIZEY = 20;
    public double reload;

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
            map.addSprite(new BulletBill(20, 20, (int) xPos, (int) yPos, x, y, this));
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
        this.speed++;
    }

    @Override
    public void useR(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void useD(int x, int y) {
        float a = this.xPos;
        float b = this.yPos;
        float distsq = (x - a) * (x - a) + (y - b) * (y - b);
        double dist = Math.sqrt(distsq);
        if (dist < 200) {
            this.xPos = x;
            this.yPos = y;
            this.xTar = x + 1;
            this.yTar = y + 1;
        } else {
            double factor = 200 / dist;
            double alterX = (x - a) * factor;
            double alterY = (y - b) * factor;
            this.xPos = (float) (a + alterX);
            this.yPos = (float) (b + alterY);
            this.xTar = this.xPos + 1;
            this.yTar = this.yPos + 1;
        }
    }

    @Override
    public void useF(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void levelup() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadSprite() {
        Sprite a = new Sprite();
        Texture buffer;
        try {
            buffer = Frame.loadImage(DEFAULT_IMAGE_PATH);
            a.setTexture(buffer);
            this.texture = a;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
