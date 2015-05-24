package game.world.bullets;

import game.hitboxes.CircleBox;
import game.screens.Frame;
import game.world.MapObject;
import game.world.champions.Player;
import java.io.IOException;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class BulletBill extends Bullet {

    public final String DEFAULT_PATH = "bulletbill.png";
    double direction;
    int speed;
    private Player source;

    private void init() throws IOException {
        Texture buffer = Frame.loadImage(DEFAULT_PATH);
        Sprite texture = new Sprite();
        texture.setTexture(buffer);
        this.texture = texture;
        setOriginMiddle();
        texture.rotate((float) Math.toDegrees(direction + Math.PI));
        hitbox = new CircleBox(new Vector2f(xPos, yPos), 10);
    }

    public BulletBill(int sizex, int sizey, int xPos, int yPos, double direction, Player source) throws IOException {
        super();

        this.sizex = sizex;
        this.sizey = sizey;
        this.xPos = xPos;
        this.yPos = yPos;

        this.direction = direction;
        speed = 1;
        init();
        this.source = source;
    }

    public BulletBill(int sizex, int sizey, int xPos, int yPos, int xTar, int yTar,Player source) throws IOException {
        super();

        this.sizex = sizex;
        this.sizey = sizey;
        this.xPos = xPos;
        this.yPos = yPos;
        float x = xTar - xPos;
        float y = yTar - yPos;
        if (x < 0) {
            direction = Math.atan(y * 1.0 / (x)) + Math.PI;
        } else if (x != 0) {
            direction = Math.atan(y * 1.0 / x);
        } else {
            direction = Math.atan(y * 1.0 / (.1));
        }

        speed = 1;
        init();
        this.source = source;
    }

    @Override
    public void update() {
        shift(speed * (float) Math.cos(direction), speed * (float) Math.sin(direction));
        if (xPos > map.sizex + 100 || xPos < -20 || yPos > map.sizey + 100 || yPos < -20) {
            remove = true;
        }
        ((CircleBox) hitbox).origin = new Vector2f(xPos, yPos);
    }

    @Override
    public void intersectsWith(MapObject object) {
        if (object == source) {
            return;
        }
        if (object instanceof Player) {
            Player buffer = (Player) object;
            buffer.hp -= 10;
            remove = true;
        }
    }

}
