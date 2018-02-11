package team6.g13it18k;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import team6.g13it18k.objects.BackgroundActor;
import team6.g13it18k.screens.SplashScreen;

/**
 * Главный класс игры, все движуха идет через этот класс
 */
public class ASGame extends Game {

    public BackgroundActor background;

    public BitmapFont font, levels;
    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    @Override
    public void create() {
        background = new BackgroundActor();
        background.setPosition(0, 0);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/YanoneKaffeesatz-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = Gdx.graphics.getHeight() / 18; // Размер шрифта. Я сделал его исходя из размеров экрана. Правда коряво, но вы сами можете поиграться, как вам угодно.
        param.characters = FONT_CHARACTERS; // Наши символы
        font = generator.generateFont(param); // Генерируем шрифт
        param.size = Gdx.graphics.getHeight() / 20;
        levels = generator.generateFont(param);
        font.setColor(Color.WHITE); // Цвет белый
        levels.setColor(Color.WHITE);
        generator.dispose();

        setScreen(new SplashScreen(this));
    }

}