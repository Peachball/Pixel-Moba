package game.world.networking;

import game.world.Map;
import game.world.MapObject;
import java.io.Serializable;
import java.util.ArrayList;

public class MapMessage implements Serializable {

    int sizex;
    int sizey;

    public MapMessage(Map map) {
        this.sizex = map.sizex;
        this.sizey = map.sizey;
    }

}
