package team6.g13it18k;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import team6.g13it18k.objects.BackgroundActor;
import team6.g13it18k.objects.GeneratorFont;
import team6.g13it18k.objects.GeneratorFont.FontType;
import team6.g13it18k.screens.SplashScreen;

/**
 * Главный класс игры, все движуха идет через этот класс
 */
public class ASGame extends Game {

    private static final int SIZE_TITLE = 20;
    private static final int SIZE_TEXT = 26;

    public BitmapFont fontTitle;
    public BitmapFont fontText;

    public BackgroundActor background;
    public AssetManager manager;

    @Override
    public void create() {
        fontTitle = new GeneratorFont(Gdx.graphics.getHeight() / SIZE_TITLE, Color.WHITE, FontType.FONT_BOLD).getFont();
        fontText = new GeneratorFont(Gdx.graphics.getHeight() / SIZE_TEXT, Color.WHITE, FontType.FONT_REGULAR).getFont();

        background = new BackgroundActor(0, 0);

        manager = new AssetManager();
        manager.load("atlas/buttons.atlas", TextureAtlas.class);
        manager.load("atlas/pets.atlas", TextureAtlas.class);
        manager.load("music.mp3", Music.class);
        manager.load("btnClick.wav", Sound.class);

        setScreen(new SplashScreen(this));
    }
}