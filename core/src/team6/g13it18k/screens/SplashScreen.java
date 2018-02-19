package team6.g13it18k.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import team6.g13it18k.ASGame;

/**
 * Данный класс реализует окно заставки
 */
public class SplashScreen implements Screen {

    private Texture splashImage;
    private Stage stage;

    private final ASGame game;

    public SplashScreen(final ASGame gam) {
        game = gam;
        stage = new Stage();
    }

    @Override
    public void show() {
        splashImage = new Texture("logo.png");
        splashImage.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        Image actorImage = new Image(new TextureRegion(splashImage, 0, 0, 512, 128));

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        float desiredWidth = width * .9f;
        float scale = desiredWidth / actorImage.getWidth();

        actorImage.setSize(actorImage.getWidth() * scale, actorImage.getHeight() * scale);
        actorImage.setPosition(width / 2 - actorImage.getWidth() / 2, height / 2 - actorImage.getHeight() / 2);

        actorImage.addAction(sequence(
                fadeOut(3),
                run(new Runnable(){
                    @Override
                    public void run() {
                        game.setScreen(new MenuScreen(game));
                        dispose();
                    }
                })
        ));
        stage.addActor(actorImage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        splashImage.dispose();
        stage.dispose();
        game.dispose();
    }
}
