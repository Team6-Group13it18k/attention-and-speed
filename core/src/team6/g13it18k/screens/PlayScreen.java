package team6.g13it18k.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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

    private Label level, round, scores, time;

    private ImageButton backToMenu, play_and_pause, soundOn_soundOff, musicOn_musicOff;

    public PlayScreen(final ASGame gam) {
        game = gam;
        stage = new Stage();
        stage.addActor(game.background);


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

        Label.LabelStyle labelStyleTitle = new Label.LabelStyle(new GeneratorFont(16, Color.WHITE, GeneratorFont.FontType.FONT_BOLD).getFont(), Color.WHITE);

        table.add(new Label("Внимание и Скорость", labelStyleTitle)).left();

        table.add(backToMenu).size(25).right();
        table.add(soundOn_soundOff).size(25).right();
        table.add(musicOn_musicOff).size(25).right();
        table.add(play_and_pause).size(25).right();

        table.row();
        table.add(level);
        table.add(round);
        table.add(scores);
        table.add(time).colspan(2);



        stage.addActor(table);

    }

    private ImageButton.ImageButtonStyle getStyle(String nameUp, String nameDown){
        Skin skin = new Skin(new TextureAtlas(Gdx.files.internal("atlas/buttons.atlas")));

        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skin.getDrawable(nameUp);
        imageButtonStyle.down = skin.getDrawable(nameDown);

        return imageButtonStyle;
    }

    private void generateButton() {
        backToMenu = new ImageButton(getStyle("back", "back"));
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

        play_and_pause = new ImageButton(getStyle("pause", "pause"));
        play_and_pause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (play_and_pause.isPressed()) {
                    if (play_and_pause.isChecked()) {
                        play_and_pause.setStyle(getStyle("play", "play"));
                        pause();
                    } else {
                        play_and_pause.setStyle(getStyle("pause", "pause"));
                        resume();
                    }
                }
            }
        });
        soundOn_soundOff = new ImageButton(getStyle("soundOff", "soundOff"));
        soundOn_soundOff.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (soundOn_soundOff.isPressed()) {
                    if (soundOn_soundOff.isChecked()) {
                        soundOn_soundOff.setStyle(getStyle("soundOn", "soundOn"));
                    } else {
                        soundOn_soundOff.setStyle(getStyle("soundOff", "soundOff"));
                    }
                }
            }
        });
        musicOn_musicOff = new ImageButton(getStyle("musicOff", "musicOff"));
        musicOn_musicOff.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (musicOn_musicOff.isPressed()) {
                    if (musicOn_musicOff.isChecked()) {
                        musicOn_musicOff.setStyle(getStyle("musicOn", "musicOn"));

                    } else {
                        musicOn_musicOff.setStyle(getStyle("musicOff", "musicOff"));

                    }
                }
            }
        });

    }

    private void generateLabel(){
        Label.LabelStyle labelStyleText = new Label.LabelStyle(new GeneratorFont(14, Color.WHITE, GeneratorFont.FontType.FONT_REGULAR).getFont(), Color.WHITE);

        level = new Label("Уровень 4", labelStyleText);
        round = new Label("Раунд 2", labelStyleText);
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
