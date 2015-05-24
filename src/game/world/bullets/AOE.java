/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.world.bullets;

import game.hitboxes.CircleBox;
import game.screens.Frame;
import game.world.MapObject;
import game.world.champions.Player;
import java.io.IOException;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 *
 * @author tefsh_000
 */
public class AOE extends Bullet {
    public final String DEFAULT_PATH = "circle.png";
    double direction;
    private Player source;
    public AOE()
    {
        
    }
    
    public AOE(Sprite sprite, int sizex, int sizey, float xPos, float yPos) throws IOException{
        super(sprite, sizex, sizey, xPos, yPos);
        init();
    }
    public AOE(int sizex, int sizey, int xPos, int yPos, Player source) throws IOException
    {
        
    }
    
    private void init() throws IOException
    {
        display = true;
        remove = false;
        Texture buffer = Frame.loadImage(DEFAULT_PATH);
        Sprite texture = new Sprite();
        texture.setTexture(buffer);
        this.texture = texture;
        setOriginMiddle();
         texture.rotate((float) Math.toDegrees(direction + Math.PI));
        hitbox = new CircleBox(new Vector2f(xPos, yPos), 100);
    }
    @Override
    public void intersectsWith(MapObject object){
        if(object instanceof Player){
            
        }
    }
}