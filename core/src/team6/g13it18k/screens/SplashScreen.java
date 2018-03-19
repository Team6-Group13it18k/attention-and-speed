package team6.g13it18k.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

    private final ASGame game;
    private Stage stage;
    private Texture textureLogo;
    private Image logo;

    public SplashScreen(final ASGame gam) {
        game = gam;
        stage = new Stage();

        textureLogo = new Texture("logo.png");
        textureLogo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        logo = new Image(new TextureRegion(textureLogo, 0, 0, 512, 128));
    }

    @Override
    public void show() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        float desiredWidth = width * .9f;
        float scale = desiredWidth / logo.getWidth();

        logo.setSize(logo.getWidth() * scale, logo.getHeight() * scale);
        logo.setPosition(width / 2 - logo.getWidth() / 2, height / 2 - logo.getHeight() / 2);
        logo.addAction(sequence(fadeOut(2)));
        stage.addActor(logo);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        if(game.manager.update()){

            game.music = game.manager.get("music.mp3", Music.class);
            game.music.setLooping(true);
            game.music.setVolume(0.1f);

            game.btnClick = game.manager.get("btnClick.wav", Sound.class);

            game.setScreen(new MenuScreen(game));
            dispose();
        }
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
        game.dispose();
        stage.dispose();
        textureLogo.dispose();
    }
}
