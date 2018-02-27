package team6.g13it18k.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import team6.g13it18k.ASGame;
import team6.g13it18k.managers.PetImageButton;
import team6.g13it18k.objects.GeneratorFont;
import team6.g13it18k.objects.GeneratorFont.FontType;
import team6.g13it18k.objects.HelperStyle;

/**
 * Данный класс реализует окно самой игры
 */
public class PlayScreen implements Screen {

    private final ASGame game;

    private Stage stage;

    private Label level, round, scores, time;

    private ImageButton backToMenu, play_and_pause, soundOn_soundOff, musicOn_musicOff;

    private Skin skinButtons, skinPets;

    private PetImageButton petImageButton;

    private Array<ImageButton> pets;

    private int countPets = 1;
    private int petCurrent;


    PlayScreen(final ASGame gam) {
        game = gam;
        stage = new Stage();
        stage.addActor(game.background);

        skinButtons = new Skin(game.manager.get("atlas/buttons.atlas", TextureAtlas.class));
        skinPets = new Skin(game.manager.get("atlas/pets.atlas", TextureAtlas.class));

        petImageButton = new PetImageButton();

        pets = new Array<>();
        for (int i = 1; i < 7; i++){
            pets.add(new ImageButton(HelperStyle.getStylePets(skinPets, i, i)));
        }

        petCurrent = MathUtils.random(0, pets.size - 1) + 1;


        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void show() {
        Gdx.app.log("PlayScreen", "show");

        Table container = new Table();
        container.setFillParent(true);
        container.pad(10);
        container.defaults().expandX();
        container.setDebug(false);

        generateButton();
        generateLabel();

        container.add(tableTitle(false)).prefWidth(Gdx.graphics.getWidth()).padBottom(10);
        container.row();

        container.add(tableStatus(false)).prefWidth(Gdx.graphics.getWidth()).padBottom(5);
        container.row();

        container.add(tablePet(false)).prefWidth(Gdx.graphics.getWidth());
        container.row();

        container.add(tablePets(false)).prefWidth(Gdx.graphics.getWidth());
        container.row();

        stage.addActor(container);
    }

    private Table tableTitle(Boolean debug){
        LabelStyle labelStyleTitle = new LabelStyle(new GeneratorFont(16, Color.WHITE, FontType.FONT_BOLD).getFont(), Color.WHITE);
        Label titleApp = new Label("Внимание и Скорость", labelStyleTitle);

        Table table = new Table();
        table.setDebug(debug);

        table.add(titleApp).expandX().left();
        table.add(backToMenu).size(25);
        table.add(soundOn_soundOff).size(25);
        table.add(musicOn_musicOff).size(25);
        table.add(play_and_pause).size(25);

        return table;
    }

    private Table tableStatus(Boolean debug){
        Table table = new Table();
        table.setDebug(debug);

        table.defaults().uniform();
        table.defaults().expandX();
        table.add(level);
        table.add(round);
        table.add(scores);
        table.add(time);

        return table;
    }

    private Table tablePet(Boolean debug){
        Table table = new Table();
        table.setDebug(debug);

        ImageButton petsCurrent = new ImageButton(HelperStyle.getStylePets(skinPets, petCurrent, petCurrent));

        table.add(petsCurrent).size(Gdx.graphics.getWidth() * .8f);

        return table;
    }

    private Table tablePets(Boolean debug){
        int widthPet = (Gdx.graphics.getWidth() / 3) - 5;

        Table table = new Table();
        table.setDebug(debug);

        table.defaults().uniform();
        for (ImageButton pet: pets) {
            table.add(pet).size(widthPet);

            if(countPets % 3 == 0){
                table.row();
            }

            countPets++;
        }

        return table;
    }

    private void generateButton() {
        backToMenu = new ImageButton(HelperStyle.getStyleButtons(skinButtons,"back","back"));
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
        play_and_pause = new ImageButton(HelperStyle.getStyleButtons(skinButtons,"pause","pause"));
        play_and_pause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (play_and_pause.isPressed()) {
                    if (play_and_pause.isChecked()) {
                        play_and_pause.setStyle(HelperStyle.getStyleButtons(skinButtons,"play", "play"));
                        pause();
                    } else {
                        play_and_pause.setStyle(HelperStyle.getStyleButtons(skinButtons,"pause","pause"));
                        resume();
                    }
                }
            }
        });
        soundOn_soundOff = new ImageButton(HelperStyle.getStyleButtons(skinButtons,"soundOff","soundOff"));
        soundOn_soundOff.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (soundOn_soundOff.isPressed()) {
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
                    if (musicOn_musicOff.isChecked()) {
                        musicOn_musicOff.setStyle(HelperStyle.getStyleButtons(skinButtons,"musicOn","musicOn"));

                    } else {
                        musicOn_musicOff.setStyle(HelperStyle.getStyleButtons(skinButtons,"musicOff","musicOff"));

                    }
                }
            }
        });

    }

    private void generateLabel(){
        LabelStyle labelStyleText = new LabelStyle(new GeneratorFont(14, Color.WHITE, FontType.FONT_REGULAR).getFont(), Color.WHITE);

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
