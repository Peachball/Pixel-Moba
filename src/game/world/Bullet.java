package game.world;

import game.hitboxes.Hitbox;
import org.jsfml.graphics.Sprite;

public class Bullet extends MapObject {

    
    Hitbox hitbox;
    
    public Bullet(Sprite texture, int sizex, int sizey, float posx, float posy) {
        super(texture, sizex, sizey, posx, posy);
    }
    
}
