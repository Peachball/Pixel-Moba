package game.world.bullets;

import game.hitboxes.Hitbox;
import game.world.MapObject;
import org.jsfml.graphics.Sprite;

public abstract class Bullet extends MapObject {

    Hitbox hitbox;

    private void init(){
        display = true;
        remove= false;
    }
    public Bullet(Sprite texture, int sizex, int sizey, float posx, float posy) {
        super(texture, sizex, sizey, posx, posy);
        init();
    }
    public Bullet(){
        super();
        init();
    }
    
    
    
}
