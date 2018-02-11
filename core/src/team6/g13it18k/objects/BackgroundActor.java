package team6.g13it18k.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Отвечает за фон игры
 */
public class BackgroundActor extends Actor {

    private Sprite backgroundSprite;

    public BackgroundActor() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        Texture backgroundTexture = new Texture("sky-background.jpg");
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        backgroundSprite.draw(batch);
    }


}
