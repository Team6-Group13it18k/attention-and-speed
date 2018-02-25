package team6.g13it18k.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import team6.g13it18k.ASGame;
import team6.g13it18k.objects.BackgroundActor;
import team6.g13it18k.objects.GeneratorFont;

/**
 * Данный класс реализует окно меню
 */
public class MenuScreen implements Screen {

    private final ASGame game;

    private Stage stage;
    private TextButton play, level, record, help;
    private Label.LabelStyle labelStyleTitle;

    MenuScreen(final ASGame gam) {
        game = gam;
        stage = new Stage();
        stage.addActor(game.background);

        labelStyleTitle = new Label.LabelStyle(
                new GeneratorFont(18, Color.WHITE, GeneratorFont.FontType.FONT_BOLD).getFont(),
                Color.WHITE
        );
    }

    @Override
    public void show() {
        Gdx.app.log("MenuScreen", "show");

        buttons();

        float button_width = Gdx.graphics.getWidth()  * 0.1f * 5.5f;
        float button_height = button_width / 3.1848f;

        Table table = new Table().center();
        table.setFillParent(true);
        table.setDebug(false);


        table.add(new Label("Внимание и Скорость", labelStyleTitle)).top().padBottom(50);
        table.row();
        table.add(play).width(button_width).height(button_height);
        table.row();
        table.add(level).width(button_width).height(button_height);
        table.row();
        table.add(record).width(button_width).height(button_height);
        table.row();
        table.add(help).width(button_width).height(button_height);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }


    private void buttons(){
        Skin skin = new Skin(new TextureAtlas(Gdx.files.internal("packer/images.pack")));

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = game.font;
        textButtonStyle.up = skin.getDrawable("button-up");
        textButtonStyle.down = skin.getDrawable("button-down");
        textButtonStyle.checked = skin.getDrawable("button-up");

        play = new TextButton("Играть", textButtonStyle);
        play.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.input.vibrate(20);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
                dispose();
            }
        });
        level = new TextButton("Уровни", textButtonStyle);
        level.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.input.vibrate(20);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LevelScreen(game));
                dispose();
            }
        });
        record = new TextButton("Рекорды", textButtonStyle);
        record.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.input.vibrate(20);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new HighscoresScreen(game));
                dispose();
            }
        });
        help = new TextButton("Помощь", textButtonStyle);
        help.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.input.vibrate(20);
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
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        game.dispose();
    }
}
