package team6.g13it18k.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import team6.g13it18k.ASGame;

/**
 * Данный класс реализует окно рекордов
 */
public class HighscoresScreen implements Screen {

    private final ASGame game;

    HighscoresScreen(final ASGame gam) {
        game = gam;

    }

    @Override
    public void show() {
        Gdx.app.log("HighscoresScreen", "show");

        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void render(float delta) {

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
