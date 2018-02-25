package team6.g13it18k.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

import team6.g13it18k.ASGame;
import team6.g13it18k.objects.BackgroundActor;

/**
 * Данный класс реализует окно выбора уровней
 */
public class LevelScreen implements Screen {

    private final ASGame game;

    private Stage stage;

    LevelScreen(final ASGame gam) {
        this.game = gam;

        stage = new Stage();
        stage.addActor(game.background);
    }

    @Override
    public void show() {
        Gdx.app.log("LevelScreen", "show");

        Gdx.input.setCatchBackKey(true);



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
