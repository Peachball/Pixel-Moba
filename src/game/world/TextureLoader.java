package game.world;

import java.io.IOException;
import java.io.Serializable;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

public enum TextureLoader implements Serializable {

    BULLET_BILL(0), CIRCLE(1), HEALTH_BAR(2), NINJA(3), SAMPLEMAP(4);
    int id;

    TextureLoader(int id) {
        this.id = id;

    }

    public Sprite getSprite() throws IOException {
        Texture texture = new Texture();
        switch (id) {
            case 0:
                texture.loadFromStream(ClassLoader.getSystemResourceAsStream("res/bulletbill.png"));
                break;
            case 1:
                texture.loadFromStream(ClassLoader.getSystemResourceAsStream("res/circle.png"));
                break;
            case 2:
                texture.loadFromStream(ClassLoader.getSystemResourceAsStream("res/healthbar.png"));
                break;
            case 3:
                texture.loadFromStream(ClassLoader.getSystemResourceAsStream("res/samplemap.png"));
                break;
            case 4:
                break;
        }
        Sprite buffer = new Sprite();
        buffer.setTexture(texture);
        return buffer;
    }

}
