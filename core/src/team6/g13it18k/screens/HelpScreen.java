package team6.g13it18k.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import team6.g13it18k.ASGame;
import team6.g13it18k.objects.GeneratorFont;
import team6.g13it18k.objects.GeneratorFont.FontType;

/**
 * Данный класс реализует окно справки
 */
public class HelpScreen implements Screen {

    private final ASGame game;
    private Stage stage;
    private ImageButton backToMenu;
    private Skin skinButtons;

    HelpScreen(final ASGame gam) {
        game = gam;

        stage = new Stage();
        stage.addActor(game.background);

        skinButtons = new Skin(game.manager.get("atlas/buttons.atlas", TextureAtlas.class));

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Gdx.app.log("HelpScreen", "show");

        Table container = new Table();
        container.setFillParent(true);
        container.pad(10);

        LabelStyle labelStyleTitle = new LabelStyle(
                getFont(Gdx.graphics.getHeight() / 18, FontType.FONT_BOLD, .9f),
                Color.WHITE
        );

        container.add(new Label("Внимание и Скорость : Помощь", labelStyleTitle));
        container.row();

        container.add(scrollPane()).expand().fill().padBottom(5).padTop(5);
        container.row();

        generateButton();

        int size = Gdx.graphics.getWidth() / 8;
        Gdx.app.log("HelpScreen", "width - " + Gdx.graphics.getWidth());
        Gdx.app.log("HelpScreen", "size - " + size);

        container.add(backToMenu).size(size).bottom().left();


        stage.addActor(container);
    }

    private ScrollPane scrollPane(){
        Table table = new Table();

        LabelStyle labelStyleText = new LabelStyle(
                getFont(Gdx.graphics.getHeight() / 14, FontType.FONT_REGULAR, .8f),
                Color.WHITE
        );

        Label text = new Label(Gdx.files.internal("txt/help.txt").readString("UTF-8"), labelStyleText);
        text.setWrap(true);

        table.add(text).expandX().width(Gdx.graphics.getWidth()  * .9f).expandY();


        return new ScrollPane(table);
    }

    private BitmapFont getFont(int size, FontType type, float scale){
        BitmapFont font = new GeneratorFont(size, Color.WHITE, type).getFont();
        font.getData().setScale(scale);
        return font;
    }


    private void generateButton() {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skinButtons.getDrawable("back");
        imageButtonStyle.down = skinButtons.getDrawable("back");

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
