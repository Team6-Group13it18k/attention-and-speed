package team6.g13it18k.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {

    Vector2 position;
    Vector2 velocity;
    protected int width;
    protected int height;
    private boolean isScrolledLeft;

    Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    void update(float delta) {
        position.add(velocity.cpy().scl(delta));

        if (position.x + width < 0) {
            isScrolledLeft = true;
        }
    }

    public void reset(float newX) {
        position.x = newX;
        isScrolledLeft = false;
    }
    
    void stop() {
        velocity.x = 0;
    }

    boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    float getTailX() {
        return position.x + width;
    }
    
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
