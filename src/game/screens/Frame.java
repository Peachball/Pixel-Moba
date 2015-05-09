package game.screens;

import java.io.IOException;
import org.jsfml.graphics.Texture;

public class Frame {

    public static Texture loadImage(String path) throws IOException{
        Texture buffer = new Texture();
        buffer.loadFromStream(ClassLoader.getSystemResourceAsStream("res/"+path));
        return buffer;
    }

    
    /*
   BEGIN LWJGL STUFF
    public static void create(int x, int y) throws LWJGLException {
        Display.setDisplayMode(new DisplayMode(x, y));
        Display.setResizable(false);
        Display.setTitle("test");
        Display.create();
        GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
        GL11.glEnable(GL11.GL_BLEND);
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, 0);
        GL11.glMatrixMode(GL_MODELVIEW);
        glClearColor(0, 0, 0, 0);
    }

    public static Texture loadPNG(String path) throws IOException {
        Texture texture = TextureLoader.getTexture("PNG", ClassLoader.getSystemResource("res/" + path).openStream());
        return texture;
    }

    public static Texture loadJPG(String path) throws IOException {
        return TextureLoader.getTexture("JPG", ClassLoader.getSystemResource("res/" + path).openStream());
    }

    public static void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public static void drawImage(Texture image, float x, float y, float sizex, float sizey) {
        Color.white.bind();
        image.bind();
        GL11.glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0, image.getHeight());
            glVertex2f(x, y + sizey);
            glTexCoord2f(0, 0);
            glVertex2f(x, y);
            glTexCoord2f(image.getWidth(), 0);
            glVertex2f(sizex + x, y);
            glTexCoord2f(image.getWidth(), image.getHeight());
            glVertex2f(sizex + x, sizey + y);
        }
        glEnd();
        glDisable(GL_TEXTURE_2D);
    }

    public static void drawImage(Texture image, float x, float y) {
        drawImage(image, x, y, image.getImageWidth(), image.getImageHeight());
    }

    public static void close() {
        Display.destroy();
    }

    public static void display() {
        Display.update();
    }
    */
}
