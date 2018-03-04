package team6.g13it18k;

import com.badlogic.gdx.Game;
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

    public BitmapFont font;
    public BackgroundActor background;
    public AssetManager manager;

    @Override
    public void create() {
        font = new GeneratorFont(18, Color.WHITE, FontType.FONT_REGULAR).getFont();
        background = new BackgroundActor(0, 0);

        manager = new AssetManager();

        manager.load("atlas/buttons.atlas", TextureAtlas.class);
        manager.load("atlas/pets.atlas", TextureAtlas.class);
        manager.load("music.mp3", Music.class);
        manager.load("btnClick.wav", Sound.class);

        setScreen(new SplashScreen(this));
    }
}