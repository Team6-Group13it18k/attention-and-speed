package team6.g13it18k.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import team6.g13it18k.ASGame;
import team6.g13it18k.database.ScoresTable;
import team6.g13it18k.managers.ASGameStage;

/**
 * Данный класс реализует окно рекордов
 */
public class ScoresScreen implements Screen {

    private ScoresTable scoresTable;

    private final ASGame game;
    private ASGameStage stage;

    private ImageButton backToMenu;
    private Skin skinButtons;

    private int sizeButton;

    ScoresScreen(final ASGame gam) {
        game = gam;

        stage = new ASGameStage();
        stage.addActor(game.background);

        scoresTable = new ScoresTable(game.dbHandler);

        skinButtons = new Skin(game.manager.get("atlas/buttons.atlas", TextureAtlas.class));

        sizeButton = Gdx.graphics.getWidth() / 8;

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        stage.setHardKeyListener(new ASGameStage.OnHardKeyListener() {
            @Override
            public void onHardKey(int keyCode, int state) {
                if((keyCode == Input.Keys.BACK  || keyCode == Input.Keys.ESCAPE) && state == 1){
                    if(game.getPreferences().isSoundEffectsEnabled()){
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
        Gdx.app.log("ScoresScreen", "show");


        Table container = new Table();
        container.setFillParent(true);
        container.pad(10);

        container.add(new Label("Внимание и Скорость : Рекорды", new Label.LabelStyle(game.fontTitle, Color.WHITE)));
        container.row();

        container.add(scrollPane()).expand().fill().padBottom(5).padTop(5);
        container.row();

        generateButton();

        container.add(backToMenu).size(sizeButton).bottom().left();
        
        stage.addActor(container);
    }

    private ScrollPane scrollPane(){
        Table table = new Table();
        table.defaults().expandX();

        scoresTable.addData();

        Label.LabelStyle labelStyleText = new Label.LabelStyle(game.fontText, Color.WHITE);

        table.add(new Label("ID записи", labelStyleText));
        table.add(new Label("Статистика", labelStyleText));
        table.row();

        for (ScoresTable.Record record: scoresTable.getDataFromFields()) {
            table.add(new Label(String.valueOf(record.id), labelStyleText));
            table.add(new Label(record.toString(), labelStyleText));
            table.row();
        }
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
                if(game.getPreferences().isSoundEffectsEnabled()){
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
        scoresTable.dispose();
    }
}
