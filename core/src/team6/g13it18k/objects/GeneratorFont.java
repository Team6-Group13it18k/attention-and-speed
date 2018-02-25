package team6.g13it18k.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GeneratorFont {

    private static final String PATH_TO_FONT_FILE = "fonts/YanoneKaffeesatz-Regular.ttf";
    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    private BitmapFont font;

    public GeneratorFont(int size, Color color) {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(PATH_TO_FONT_FILE));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = FONT_CHARACTERS;
        parameter.size = size; //Gdx.graphics.getHeight() / 18

        font = generator.generateFont(parameter);
        font.setColor(color);

        generator.dispose();

    }

    public BitmapFont getFont() {
        return font;
    }

    public void dispose() {
        font.dispose();
    }
}
