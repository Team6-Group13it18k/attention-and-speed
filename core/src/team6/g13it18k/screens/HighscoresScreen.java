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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import team6.g13it18k.ASGame;
import team6.g13it18k.managers.ASGameStage;
import team6.g13it18k.objects.GeneratorFont;

/**
 * Данный класс реализует окно рекордов
 */
public class HighscoresScreen implements Screen {

    private final ASGame game;
    private ASGameStage stage;
    private ImageButton backToMenu;
    private Skin skinButtons;

    private int sizeButton;

    private Music music;
    private Sound btnClick;

    private HashMap<Integer, HashMap> dataRecords;

    HighscoresScreen(final ASGame gam) {
        game = gam;

        stage = new ASGameStage();
        stage.addActor(game.background);

        skinButtons = new Skin(game.manager.get("atlas/buttons.atlas", TextureAtlas.class));

        sizeButton = Gdx.graphics.getWidth() / 8;

        music = game.manager.get("music.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(0.1f);

        btnClick = game.manager.get("btnClick.wav", Sound.class);

        dataRecords = generateTestDataRecords();


        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void show() {
        Gdx.app.log("HighscoresScreen", "show");

        music.play();

        Table container = new Table();
        container.setFillParent(true);
        container.pad(10);

        Label.LabelStyle labelStyleTitle = new Label.LabelStyle(
                getFont(Gdx.graphics.getHeight() / 16, GeneratorFont.FontType.FONT_BOLD, .9f),
                Color.WHITE
        );

        container.add(new Label("Внимание и Скорость : Рекорды", labelStyleTitle));
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

        Label.LabelStyle labelStyleText = new Label.LabelStyle(
                getFont(Gdx.graphics.getHeight() / 14, GeneratorFont.FontType.FONT_REGULAR, .8f),
                Color.WHITE
        );

        for (Map.Entry<Integer, HashMap> entry : dataRecords.entrySet()){
            HashMap<String, String> record = entry.getValue();

            Gdx.app.log("HighscoresScreen", record.toString());

            /*table.add(new Label(record.get(), labelStyleText));
            table.add(new Label(dataRecords.get(session), labelStyleText));
            table.row();*/
        }

        Iterator recordsIterator = dataRecords.values().iterator();

        while (recordsIterator.hasNext()){

        }



        /*for (int idRecord : dataRecords.keySet()){
            HashMap<String, String> records = dataRecords.get(idRecord);

            for (String session : records.keySet()){
                table.add(new Label(session, labelStyleText));
                table.add(new Label(dataRecords.get(session), labelStyleText));
                table.row();
            }
        }*/





        return new ScrollPane(table);
    }

    private HashMap generateTestDataRecords() {
        HashMap<Integer, HashMap> dataRecords = new HashMap<Integer, HashMap>();

        HashMap<String, String> records = new HashMap<String, String>();

        for (int i = 0; i < 30; i++){
            records.put(i++ + " января 2018", "Уровень " + i);

            dataRecords.put(i, records);
        }

        return dataRecords;
    }

    private BitmapFont getFont(int size, GeneratorFont.FontType type, float scale){
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
                btnClick.play();
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

    }
}
