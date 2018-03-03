package team6.g13it18k.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import team6.g13it18k.ASGame;
import team6.g13it18k.managers.ASGameStage;
import team6.g13it18k.objects.GeneratorFont;
import team6.g13it18k.objects.HelperStyle;

public class SettingsScreen implements Screen {

    private final ASGame game;
    private ASGameStage stage;
    private ImageButton backToMenu, soundOn_soundOff, musicOn_musicOff;
    private Skin skinButtons;

    private int sizeButton;

    private Music music;
    private Sound btnClick;

    SettingsScreen(final ASGame gam) {
        game = gam;

        stage = new ASGameStage();
        stage.addActor(game.background);

        skinButtons = new Skin(game.manager.get("atlas/buttons.atlas", TextureAtlas.class));

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        sizeButton = Gdx.graphics.getWidth() / 8;

        music = game.manager.get("music.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(0.1f);

        btnClick = game.manager.get("btnClick.wav", Sound.class);
    }

    @Override
    public void show() {
        Gdx.app.log("SettingsScreen", "show");

        music.play();

        Table container = new Table();
        container.setFillParent(true);
        container.setDebug(true);
        container.pad(10);

        Label.LabelStyle labelStyleTitle = new Label.LabelStyle(
                getFont(Gdx.graphics.getHeight() / 16, GeneratorFont.FontType.FONT_BOLD, .9f),
                Color.WHITE
        );

        container.add(new Label("Внимание и Скорость : Настройки", labelStyleTitle));
        container.row();

        container.add(scrollPane()).expand().fill().padBottom(5).padTop(5);
        container.row();

        generateButton();

        container.add(backToMenu).size(sizeButton).bottom().left();

        stage.addActor(container);

        stage.setHardKeyListener(new ASGameStage.OnHardKeyListener() {
            @Override
            public void onHardKey(int keyCode, int state) {
                if((keyCode == Input.Keys.BACK  || keyCode == Input.Keys.ESCAPE) && state == 1){
                    btnClick.play();
                    game.setScreen(new MenuScreen(game));
                    dispose();
                }
            }
        });
    }

    private ScrollPane scrollPane(){
        Table table = new Table();
        table.setDebug(true);

        Label.LabelStyle labelStyleText = new Label.LabelStyle(
                getFont(Gdx.graphics.getHeight() / 14, GeneratorFont.FontType.FONT_REGULAR, .8f),
                Color.WHITE
        );

        table.add(soundOn_soundOff).size(sizeButton);
        table.add(new Label("Звуки включены", labelStyleText));
        table.row();

        Gdx.app.log("SettingsScreen", String.valueOf(sizeButton));
        table.add(musicOn_musicOff).size(sizeButton);
        table.add(new Label("Музыка включена", labelStyleText));
        table.row();


        //table.add(text).expandX().width(Gdx.graphics.getWidth()  * .9f).expandY();


        return new ScrollPane(table);
    }

    private void generateButton() {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skinButtons.getDrawable("back");
        imageButtonStyle.down = skinButtons.getDrawable("back");

        backToMenu = new ImageButton(imageButtonStyle);
        backToMenu.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btnClick.play();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });

        soundOn_soundOff = new ImageButton(HelperStyle.getStyleButtons(skinButtons,"soundOff","soundOff"));
        soundOn_soundOff.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (soundOn_soundOff.isPressed()) {
                    btnClick.play();
                    if (soundOn_soundOff.isChecked()) {
                        soundOn_soundOff.setStyle(HelperStyle.getStyleButtons(skinButtons,"soundOn", "soundOn"));
                    } else {
                        soundOn_soundOff.setStyle(HelperStyle.getStyleButtons(skinButtons,"soundOff","soundOff"));
                    }
                }
            }
        });
        musicOn_musicOff = new ImageButton(HelperStyle.getStyleButtons(skinButtons,"musicOff","musicOff"));
        musicOn_musicOff.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (musicOn_musicOff.isPressed()) {
                    btnClick.play();
                    if (musicOn_musicOff.isChecked()) {
                        musicOn_musicOff.setStyle(HelperStyle.getStyleButtons(skinButtons,"musicOn","musicOn"));

                    } else {
                        musicOn_musicOff.setStyle(HelperStyle.getStyleButtons(skinButtons,"musicOff","musicOff"));

                    }
                }
            }
        });

    }

    private BitmapFont getFont(int size, GeneratorFont.FontType type, float scale){
        BitmapFont font = new GeneratorFont(size, Color.WHITE, type).getFont();
        font.getData().setScale(scale);
        return font;
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
