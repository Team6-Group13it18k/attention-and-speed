package team6.g13it18k;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.sql.Database;

import team6.g13it18k.database.AppDatabase;
import team6.g13it18k.database.AppPreferences;
import team6.g13it18k.objects.BackgroundActor;
import team6.g13it18k.objects.GeneratorFont;
import team6.g13it18k.objects.GeneratorFont.FontType;
import team6.g13it18k.screens.SplashScreen;

/**
 * Главный класс игры, все движуха идет через этот класс
 */
public class ASGame extends Game {

    private static final int SIZE_TITLE = 25;
    private static final int SIZE_TEXT = 30;

    public BitmapFont fontTitle;
    public BitmapFont fontText;

    public BackgroundActor background;
    public AssetManager manager;
    public Database dbHandler;

    public Music music;
    public Sound btnClick;

    private AppPreferences preferences;

    @Override
    public void create() {
        fontTitle = new GeneratorFont(Gdx.graphics.getHeight() / SIZE_TITLE, Color.WHITE, FontType.FONT_BOLD).getFont();
        fontText = new GeneratorFont(Gdx.graphics.getHeight() / SIZE_TEXT, Color.WHITE, FontType.FONT_REGULAR).getFont();

        background = new BackgroundActor(0, 0);

        music = null;
        btnClick = null;

        preferences = new AppPreferences();

        manager = new AssetManager();
        manager.load("atlas/buttons.atlas", TextureAtlas.class);
        manager.load("atlas/pets.atlas", TextureAtlas.class);
        manager.load("music.mp3", Music.class);
        manager.load("btnClick.wav", Sound.class);

        dbHandler = new AppDatabase().getDbHandler();

        setScreen(new SplashScreen(this));
    }

    public AppPreferences getPreferences() {
        return preferences;
    }
}