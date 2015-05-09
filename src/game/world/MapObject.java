package game.world;

import org.jsfml.graphics.Sprite;

public class MapObject {

    public Sprite texture;
    public float xPos;
    public float yPos;
    public int sizex;
    public int sizey;
    public boolean remove;
    public boolean display;

    public MapObject(Sprite texture, int sizex, int sizey, float posx, float posy) {
        this.sizex = sizex;
        this.texture = texture;
        this.sizey = sizey;
        texture.setPosition(posx, posy);
        remove = false;
        display = true;
        resize();
    }
    
    public void update(){
        
    }
    public void setSize(int sizex, int sizey) {
        this.sizex = sizex;
        this.sizey = sizey;
        resize();
    }

    public void setOriginMiddle() {
        texture.setScale(1, 1);
        setOrigin(texture.getTexture().getSize().x / 2, texture.getTexture().getSize().y / 2);
        resize();
    }

    public void setOrigin(int x, int y) {
        texture.setOrigin(x, y);
    }

    protected void resize() {
        texture.setScale(sizex * 1.0f / texture.getTexture().getSize().x, sizey * 1.0f / texture.getTexture().getSize().y);
    }

    public void setPosition(float x, float y) {
        xPos = x;
        yPos = y;
    }

    public void shift(float x, float y) {
        xPos += x;
        yPos += y;
    }
}
