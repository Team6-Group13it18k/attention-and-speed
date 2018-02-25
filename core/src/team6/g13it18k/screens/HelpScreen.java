package team6.g13it18k.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import team6.g13it18k.ASGame;
import team6.g13it18k.objects.GeneratorFont;

/**
 * Данный класс реализует окно справки
 */
public class HelpScreen implements Screen {

    private final ASGame game;
    private Stage stage;
    private ImageButton backToMenu;

    public HelpScreen(final ASGame gam) {
        game = gam;

        stage = new Stage();
        stage.addActor(game.background);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void show() {
        Gdx.app.log("HelpScreen", "show");

        Table container = new Table();
        container.setFillParent(true);
        container.pad(10);

        LabelStyle labelStyleTitle = new LabelStyle(new GeneratorFont(18, Color.WHITE, GeneratorFont.FontType.FONT_BOLD).getFont(), Color.WHITE);
        container.add(new Label("Внимание и Скорость : Помощь", labelStyleTitle));
        container.row();

        Table table = new Table();

        LabelStyle labelStyleText = new LabelStyle(new GeneratorFont(14, Color.WHITE, GeneratorFont.FontType.FONT_REGULAR).getFont(), Color.WHITE);
        Label text = new Label(Gdx.files.internal("txt/help.txt").readString("UTF-8"), labelStyleText);
        text.setWrap(true);

        table.add(text).expandX().width(Gdx.graphics.getWidth()  * .9f).expandY();

        generateButton();

        container.add(new ScrollPane(table)).expand().fill().padBottom(5).padTop(5);
        container.row();
        container.add(backToMenu).bottom().left();


        stage.addActor(container);
    }

    private void generateButton() {
        Skin skin = new Skin(new TextureAtlas(Gdx.files.internal("atlas/back.atlas")));

        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skin.getDrawable("back");
        imageButtonStyle.down = skin.getDrawable("back");
        backToMenu = new ImageButton(imageButtonStyle);

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
        game.background.getBackgroundSprite().setSize(width, height);
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
