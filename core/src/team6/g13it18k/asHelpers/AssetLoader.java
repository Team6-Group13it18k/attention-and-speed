package team6.g13it18k.asHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    private static Texture texture, logoTexture;
    public static TextureRegion logo;
    public static TextureRegion asLogo;
    public static TextureRegion bg;
    public static TextureRegion grass;
    public static TextureRegion bird;
    public static TextureRegion skullUp;
    public static TextureRegion skullDown;
    public static TextureRegion bar;

    static TextureRegion playButtonDown;
    static TextureRegion playButtonUp;

    public static Animation<TextureRegion> birdAnimation; //тут
    public static Sound dead, flap, coin;
    public static BitmapFont font, shadow;
    private static Preferences prefs;


    public static void load() {

        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        asLogo = new TextureRegion(texture, 0, 55, 135, 24);
        asLogo.flip(false, true);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        TextureRegion birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        TextureRegion birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);

//тут проблема
        TextureRegion[] birds = {birdDown, bird, birdUp};
        birdAnimation = new Animation<>(0.06f, birds);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
//тут проблема

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

        prefs = Gdx.app.getPreferences("AttentionAndSpeed");

        // Создадим переменую для хранения лучшего счета со значением по умолчанию 0
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        texture.dispose();
        logoTexture.dispose();
        dead.dispose();
        flap.dispose();
        coin.dispose();
        font.dispose();
        shadow.dispose();
    }

}
