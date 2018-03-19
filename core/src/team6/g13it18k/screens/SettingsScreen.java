package team6.g13it18k.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import team6.g13it18k.ASGame;
import team6.g13it18k.managers.ASGameStage;

public class SettingsScreen implements Screen {

    private final ASGame game;
    private ASGameStage stage;

    private ImageButton backToMenu;
    private Skin skinButtons;

    private CheckBox musicCheckbox, soundEffectsCheckbox;

    private int sizeButton;

    SettingsScreen(final ASGame gam) {
        game = gam;

        stage = new ASGameStage();
        stage.addActor(game.background);

        skinButtons = new Skin(game.manager.get("atlas/buttons.atlas", TextureAtlas.class));
        sizeButton = Gdx.graphics.getWidth() / 8;

        generateButton();

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        stage.setHardKeyListener(new ASGameStage.OnHardKeyListener() {
            @Override
            public void onHardKey(int keyCode, int state) {
                if ((keyCode == Input.Keys.BACK || keyCode == Input.Keys.ESCAPE) && state == 1) {
                    if (game.getPreferences().isSoundEffectsEnabled()) {
                        game.btnClick.play();
                    }
                    game.setScreen(new MenuScreen(game));
                    dispose();
                }
            }
        });
    }

    @Override
    public void show() {
        Table container = new Table();
        container.setFillParent(true);
        container.pad(10);

        container.add(new Label("Внимание и Скорость : Настройки", new Label.LabelStyle(game.fontTitle, Color.WHITE)));
        container.row();

        container.add(getTable()).expand().padBottom(5).padTop(5);
        container.row();

        container.add(backToMenu).size(sizeButton).bottom().left();

        stage.addActor(container);
    }

    private Table getTable() {
        Table table = new Table();

        Label.LabelStyle styleText = new Label.LabelStyle(game.fontText, Color.WHITE);

        table.add(musicCheckbox);
        table.add(new Label("Музыка", styleText));
        table.row();

        table.add(soundEffectsCheckbox);
        table.add(new Label("Звуковые эффекты", styleText));
        table.row();


        return table;
    }

    private void generateButton() {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skinButtons.getDrawable("back");
        imageButtonStyle.down = skinButtons.getDrawable("back");

        backToMenu = new ImageButton(imageButtonStyle);
        backToMenu.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (game.getPreferences().isSoundEffectsEnabled()) {
                    game.btnClick.play();
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });

        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.checkboxOn = skinButtons.getDrawable("checkboxFill");
        checkBoxStyle.checkboxOff = skinButtons.getDrawable("checkboxEmpty");
        checkBoxStyle.font = game.fontText;

        musicCheckbox = new CheckBox(null, checkBoxStyle);
        musicCheckbox.setChecked(game.getPreferences().isMusicEnabled());
        musicCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                game.getPreferences().setMusicEnabled(enabled);
                return false;
            }
        });

        soundEffectsCheckbox = new CheckBox(null, checkBoxStyle);
        soundEffectsCheckbox.setChecked(game.getPreferences().isSoundEffectsEnabled());
        soundEffectsCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundEffectsCheckbox.isChecked();
                game.getPreferences().setSoundEffectsEnabled(enabled);
                return false;
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        if (game.getPreferences().isMusicEnabled()) {
            game.music.play();
        } else {
            game.music.stop();
        }
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
