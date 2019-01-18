package team6.g13it18k.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import team6.g13it18k.ASGame;
import team6.g13it18k.managers.ASGameStage;

/**
 * Данный класс реализует окно меню
 */
//todo разобраться почему падает
public class MenuScreen implements Screen {

    private final ASGame game;

    private ASGameStage stage;

    private TextButton play, record, settings, help;

    MenuScreen(final ASGame gam) {
        game = gam;
        stage = new ASGameStage();
        stage.addActor(game.background);

        createButtons();

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        stage.setHardKeyListener(new ASGameStage.OnHardKeyListener() {
            @Override
            public void onHardKey(int keyCode, int state) {
                if((keyCode == Input.Keys.BACK  || keyCode == Input.Keys.ESCAPE) && state == 1){
                    if(game.getPreferences().isSoundEffectsEnabled()){
                        game.btnClick.play();
                    }
                    Gdx.app.exit();
                    dispose();
                }
            }
        });
    }

    @Override
    public void show() {
        if(game.getPreferences().isMusicEnabled()){
            game.music.play();
        }

        Table table = new Table().center();
        table.setFillParent(true);

        float button_width = Gdx.graphics.getWidth()  * 0.1f * 5.5f;
        float button_height = button_width / 3.1848f;

        table.add(new Label("Внимание и Скорость", new Label.LabelStyle(game.fontTitle, Color.WHITE))).top().padBottom(50);
        table.row();
        table.add(play).width(button_width).height(button_height);
        table.row();
        table.add(record).width(button_width).height(button_height);
        table.row();
        table.add(settings).width(button_width).height(button_height);
        table.row();
        table.add(help).width(button_width).height(button_height);
        stage.addActor(table);
    }


    private void createButtons(){
        Skin skin = new Skin(game.manager.get("atlas/images.pack", TextureAtlas.class));

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = game.fontText;
        textButtonStyle.up = skin.getDrawable("button-up");
        textButtonStyle.down = skin.getDrawable("button-down");
        textButtonStyle.checked = skin.getDrawable("button-up");

        play = new TextButton("Играть", textButtonStyle);
        play.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(game.getPreferences().isSoundEffectsEnabled()){
                    game.btnClick.play();
                }
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
                dispose();
            }
        });

        record = new TextButton("Рекорды", textButtonStyle);
        record.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(game.getPreferences().isSoundEffectsEnabled()){
                    game.btnClick.play();
                }
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new ScoresScreen(game));
                dispose();
            }
        });

        settings = new TextButton("Настройки", textButtonStyle);
        settings.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(game.getPreferences().isSoundEffectsEnabled()){
                    game.btnClick.play();
                }
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new SettingsScreen(game));
                dispose();
            }
        });

        help = new TextButton("Помощь", textButtonStyle);
        help.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(game.getPreferences().isSoundEffectsEnabled()){
                    game.btnClick.play();
                }
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new HelpScreen(game));
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
    }
}
