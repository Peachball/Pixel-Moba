package game.world.networking;

import java.io.Serializable;

public class Request implements Serializable {
    public int id;
    public int keycode;
    public float x;
    public float y;
    
    public Request(int id, int keycode, float x, float y){
        this.id = id;
        this.keycode = keycode;
        this.x = x;
        this.y = y;
    }
    
}
