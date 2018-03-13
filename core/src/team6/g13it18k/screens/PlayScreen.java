package team6.g13it18k.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

import team6.g13it18k.ASGame;
import team6.g13it18k.managers.ASGameStage;
import team6.g13it18k.objects.PetImageButtonStyle;

/**
 * Данный класс реализует окно самой игры
 */
public class PlayScreen implements Screen {

    private final ASGame game;
    private ASGameStage stage;

    private Label level, round, scores, time;

    private ImageButton backToMenu, play_and_pause;

    private ImageButton pet1, pet2, pet3, pet4, pet5, pet6;

    private Skin skinButtons, skinPets;

    private HashMap<Integer, PetImageButtonStyle> petsImageButtonStyles = new HashMap<>(6);
    private PetImageButtonStyle petImageButtonStyles;

    private int sizeButton;

    private Music music;
    private Sound btnClick;

    PlayScreen(final ASGame gam) {
        game = gam;
        stage = new ASGameStage();
        stage.addActor(game.background);

        skinButtons = new Skin(game.manager.get("atlas/buttons.atlas", TextureAtlas.class));
        skinPets = new Skin(gam.manager.get("atlas/pets.atlas", TextureAtlas.class));

        getRandomStyle();

        getStyleToTaskPet();

        sizeButton = Gdx.graphics.getWidth() / 8;

        music = game.manager.get("music.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(0.1f);

        btnClick = game.manager.get("btnClick.wav", Sound.class);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

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

    private void getRandomStyle() {
        LinkedHashSet<Integer> numberPets = new LinkedHashSet<>(6);

        while (numberPets.size() != 6){
            numberPets.add(MathUtils.random(1, 20));
        }

        int i = 0;
        for (Integer indexPet: numberPets) {
            petsImageButtonStyles.put(i, new PetImageButtonStyle(skinPets, indexPet));
            i++;
        }
    }

    private void getStyleToTaskPet() {
        petImageButtonStyles = petsImageButtonStyles.get(MathUtils.random(0, 5));
    }

    @Override
    public void show() {
        Gdx.app.log("PlayScreen", "show");

        music.play();

        Table container = new Table();
        container.setFillParent(true);
        container.pad(10);

        generateButton();
        generateLabel();

        container.add(tableTitle()).prefWidth(Gdx.graphics.getWidth()).padBottom(10);
        container.row();

        container.add(tableStatus()).prefWidth(Gdx.graphics.getWidth()).padBottom(5);
        container.row();

        container.add(tablePet()).prefWidth(Gdx.graphics.getWidth());
        container.row();

        container.add(tablePets()).prefWidth(Gdx.graphics.getWidth());
        container.row();

        stage.addActor(container);
    }

    private Table tableTitle(){
        Table table = new Table();

        table.add(new Label("Внимание и Скорость", new Label.LabelStyle(game.fontTitle, Color.WHITE))).expandX().left();
        table.add(backToMenu).size(sizeButton);
        table.add(play_and_pause).size(sizeButton);

        return table;
    }

    private Table tableStatus(){
        Table table = new Table();

        table.defaults().expandX();
        table.add(level);
        table.add(round);
        table.row();
        table.add(scores);
        table.add(time);

        return table;
    }

    private Table tablePet(){
        Table table = new Table();

        table.add(new ImageButton(petImageButtonStyles)).size(Gdx.graphics.getWidth() * .8f);

        return table;
    }

    private Table tablePets(){
        int widthPet = (Gdx.graphics.getWidth() / 3) - 5;

        Table table = new Table();
        table.defaults().uniform();

        generatePetsImageButton();

        table.add(pet1).size(widthPet);
        table.add(pet2).size(widthPet);
        table.add(pet3).size(widthPet);
        table.row();
        table.add(pet4).size(widthPet);
        table.add(pet5).size(widthPet);
        table.add(pet6).size(widthPet);

        return table;
    }

    private void generatePetsImageButton(){
        pet1 = new ImageButton(petsImageButtonStyles.get(0));
        pet2 = new ImageButton(petsImageButtonStyles.get(1));
        pet3 = new ImageButton(petsImageButtonStyles.get(2));
        pet4 = new ImageButton(petsImageButtonStyles.get(3));
        pet5 = new ImageButton(petsImageButtonStyles.get(4));
        pet6 = new ImageButton(petsImageButtonStyles.get(5));
    }

    private void generateButton() {
        backToMenu = new ImageButton(getStyleButtons(skinButtons,"back","back"));
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
        play_and_pause = new ImageButton(getStyleButtons(skinButtons,"pause","pause"));
        play_and_pause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (play_and_pause.isPressed()) {
                    btnClick.play();
                    if (play_and_pause.isChecked()) {
                        play_and_pause.setStyle(getStyleButtons(skinButtons,"play", "play"));
                        pause();
                    } else {
                        play_and_pause.setStyle(getStyleButtons(skinButtons,"pause","pause"));
                        resume();
                    }
                }
            }
        });
    }

    private void generateLabel(){
        LabelStyle labelStyleText = new LabelStyle(game.fontText, Color.WHITE);

        level = new Label("Уровень 4", labelStyleText);
        round = new Label("Раунд 2", labelStyleText);
        scores = new Label("Счет", labelStyleText);
        time = new Label("Таймер", labelStyleText);
    }

    private ImageButton.ImageButtonStyle getStyleButtons(Skin skin, String nameUp, String nameDown){
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skin.getDrawable(nameUp);
        imageButtonStyle.down = skin.getDrawable(nameDown);

        return imageButtonStyle;
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
