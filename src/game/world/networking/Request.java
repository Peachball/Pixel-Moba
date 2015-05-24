package game.world.networking;

import java.io.Serializable;

public class Request implements Serializable {
    public long id;
    public int keycode;
    public float x;
    public float y;
    
    public Request(long id, int keycode, float x, float y){
        this.id = id;
        this.keycode = keycode;
        this.x = x;
        this.y = y;
    }
    
}
