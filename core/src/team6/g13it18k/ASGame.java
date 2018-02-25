package team6.g13it18k;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import team6.g13it18k.objects.BackgroundActor;
import team6.g13it18k.objects.GeneratorFont;
import team6.g13it18k.screens.SplashScreen;

/**
 * Главный класс игры, все движуха идет через этот класс
 */
public class ASGame extends Game {

    public BitmapFont font;
    public BackgroundActor background;

    @Override
    public void create() {
        font = new GeneratorFont(18, Color.WHITE, GeneratorFont.FontType.FONT_REGULAR).getFont();
        background = new BackgroundActor(0, 0);
        setScreen(new SplashScreen(this));
    }
}