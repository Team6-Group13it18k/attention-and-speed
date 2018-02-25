package team6.g13it18k.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import team6.g13it18k.ASGame;
import team6.g13it18k.objects.GeneratorFont;
import team6.g13it18k.objects.MyImageButton;

/**
 * Данный класс реализует окно справки
 */
public class HelpScreen implements Screen {

    private final ASGame game;

    private Stage stage;
    private LabelStyle labelStyle;

    private MyImageButton backToMenu;

    HelpScreen(final ASGame gam) {
        game = gam;

        stage = new Stage();
        stage.addActor(game.background);

        labelStyle = new LabelStyle(new GeneratorFont(14, Color.WHITE).getFont(), Color.WHITE);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void show() {
        Gdx.app.log("HelpScreen", "show");

        float widthText = Gdx.graphics.getWidth()  * .9f;

        Label text = new Label(Gdx.files.internal("txt/help.txt").readString("UTF-8"), labelStyle);
        text.setWrap(true);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true); //для отладки

        table.add(new Label("Attention and Speed", labelStyle));
        table.row();

        table.add(text).expandX().width(widthText).expandY();
        table.row();

        generateButton();

        table.add(backToMenu).bottom().left();


        stage.addActor(table);
    }

    private void generateButton() {

        Texture textureUp   = new Texture(Gdx.files.internal("back.png"));
        Texture textureDown = new Texture(Gdx.files.internal("back.png"));
        Texture background  = new Texture(Gdx.files.internal("back.png"));

        backToMenu = new MyImageButton(textureUp, textureDown, background);
        backToMenu.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.input.vibrate(20);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });

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
        game.dispose();
        stage.dispose();
    }
}
