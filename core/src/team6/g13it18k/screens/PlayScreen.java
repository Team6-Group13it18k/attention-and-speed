package team6.g13it18k.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import team6.g13it18k.ASGame;
import team6.g13it18k.objects.GeneratorFont;
import team6.g13it18k.objects.MyImageButton;

/**
 * Данный класс реализует окно самой игры
 */
public class PlayScreen implements Screen {

    private final ASGame game;

    private Stage stage;

    private Label.LabelStyle labelStyleText, labelStyleTitle;

    private Label level, scores, time;

    private MyImageButton backToMenu, pause, play, sound, music;

    PlayScreen(final ASGame gam) {
        game = gam;
        stage = new Stage();
        stage.addActor(game.background);

        labelStyleText = new Label.LabelStyle(
                new GeneratorFont(12, Color.WHITE, GeneratorFont.FontType.FONT_REGULAR).getFont(),
                Color.WHITE
        );

        labelStyleTitle = new Label.LabelStyle(
                new GeneratorFont(14, Color.WHITE, GeneratorFont.FontType.FONT_BOLD).getFont(),
                Color.WHITE
        );

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void show() {
        Gdx.app.log("PlayScreen", "show");

        generateButton();
        generateLabel();

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        table.add(new Label("Внимание и Скорость", labelStyleTitle)).left().expandX();
        table.add(backToMenu).size(25, 25).right();
        table.add(sound).size(25, 25).right();
        table.add(music).size(25, 25).right();
        table.add(pause).size(25, 25).right();
        table.row();
        table.add(level);
        table.add(scores);
        table.add(time).colspan(2);



        stage.addActor(table);

    }

    private void generateButton() {
        backToMenu = new MyImageButton(
                new Texture(Gdx.files.internal("back.png")),
                new Texture(Gdx.files.internal("back.png")),
                new Texture(Gdx.files.internal("back.png"))
        );
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

        pause = new MyImageButton(
                new Texture(Gdx.files.internal("pause.png")),
                new Texture(Gdx.files.internal("pause.png")),
                new Texture(Gdx.files.internal("pause.png"))
        );
        pause.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.input.vibrate(20);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                pause();
            }
        });

        sound = new MyImageButton(
                new Texture(Gdx.files.internal("sound_mute.png")),
                new Texture(Gdx.files.internal("sound_mute.png")),
                new Texture(Gdx.files.internal("sound_mute.png"))
        );

        music = new MyImageButton(
                new Texture(Gdx.files.internal("music_mute.png")),
                new Texture(Gdx.files.internal("music_mute.png")),
                new Texture(Gdx.files.internal("music_mute.png"))
        );
    }

    private void generateLabel(){
        level = new Label("Уровень 4", labelStyleText);
        scores = new Label("Счет", labelStyleText);
        time = new Label("Таймер", labelStyleText);
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
