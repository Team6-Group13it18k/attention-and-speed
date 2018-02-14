package team6.g13it18k;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import team6.g13it18k.objects.BackgroundActor;
import team6.g13it18k.screens.SplashScreen;

/**
 * Главный класс игры, все движуха идет через этот класс
 */
public class ASGame extends Game {

    private static final String PATH_TO_FONT_FILE = "fonts/YanoneKaffeesatz-Regular.ttf";
    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    public BackgroundActor background;
    public BitmapFont font;

    @Override
    public void create() {
        background = new BackgroundActor();
        background.setPosition(0, 0);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(PATH_TO_FONT_FILE));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = 18; //Gdx.graphics.getHeight() / 18
        parameter.characters = FONT_CHARACTERS;

        font = generator.generateFont(parameter);
        font.setColor(Color.WHITE);

        generator.dispose();

        setScreen(new SplashScreen(this));
    }


}