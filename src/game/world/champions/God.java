package game.world.champions;

import game.screens.Frame;
import game.world.bullets.BulletBill;
import java.io.IOException;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.window.Mouse;
import game.world.MapObject;
import game.world.bullets.AOE;

public class God extends Player {

    public final String DEFAULT_IMAGE_PATH = "ninja.jpg";
    public final int DEFAULT_SIZEX = 20;
    public final int DEFAULT_SIZEY = 20;
    public double reload;
    private long cooldownQ;
    private long cooldownW;
    private long cooldownE;
    private long cooldownR;
    private long cooldownD;
    private long cooldownF;

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
        cooldownQ = -200000;
        cooldownW = -200000;
        cooldownE = -200000;
        cooldownR = -200000;
        cooldownD = -200000;
        cooldownF = -200000;
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
        long temp = System.currentTimeMillis();
        if (temp - cooldownQ > 500) {
            try {
                map.addSprite(new BulletBill(20, 20, (int) xPos, (int) yPos, x, y, this));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            cooldownQ = System.currentTimeMillis();
        }
    }

    @Override
    public void useW(int x, int y) {
       if(System.currentTimeMillis() - cooldownW > 1000)
       {
           try
           {
               map.addSprite(new AOE(20, 20, (int) xPos, (int) yPos, this));
           }
           catch(IOException io)
           {
               io.printStackTrace();
           }
           cooldownW = System.currentTimeMillis();
        }
    }
    @Override
    public void useE(int x, int y) {
        if (System.currentTimeMillis() - cooldownE > 15000) {
            this.speed++;
            cooldownE = System.currentTimeMillis();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {

            }
            this.speed--;
        }
    }

    @Override
    public void useF(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void useD(int x, int y) {
        long temp = System.currentTimeMillis();
        if (temp - cooldownD > 60000) {
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
            cooldownD = System.currentTimeMillis();
        }
    }

    private void D(float x, float y, Player p) {
        float a = p.xPos;
        float b = p.yPos;
        float distsq = (x - a) * (x - a) + (y - b) * (y - b);
        double dist = Math.sqrt(distsq);
        if (dist < 200) {
            p.xPos = x;
            p.yPos = y;
            p.xTar = x + 1;
            p.yTar = y + 1;
        } else {
            double factor = 200 / dist;
            double alterX = (x - a) * factor;
            double alterY = (y - b) * factor;
            p.xPos = (float) (a + alterX);
            p.yPos = (float) (b + alterY);
            p.xTar = p.xPos + 1;
            p.yTar = p.yPos + 1;
        }
    }

    @Override
    public void useR(int x, int y) {
        if (System.currentTimeMillis() - cooldownF > 120000) {
            split();
            cooldownF = System.currentTimeMillis();
        }
    }

    @Override
    public void levelup() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void split() {
        float tempx = this.xPos;
        float tempy = this.yPos;
        try {
            God g = (God)this.clone();
            g.display = true;
            g.remove = false;
            D(this.xPos + 199, this.yPos + 199, g);
            D(this.xPos - 199, this.yPos - 199, this);
            try {
                Thread.sleep(1000);
                g.display = false;
            g.remove = true;
            D(tempx, tempy, this);
            } catch (InterruptedException ie) {
            }
        }
        catch (CloneNotSupportedException cnse) {}
        }
    }


