package team6.g13it18k.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GeneratorFont {

    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.:;,{}\"´`'<>";

    private BitmapFont font;

    private int size;
    private Color color;

    public enum FontType {
        FONT_BOLD("fonts/YanoneKaffeesatz-Bold.ttf"),
        FONT_EXTRA_LIGHT("fonts/YanoneKaffeesatz-ExtraLight.ttf"),
        FONT_LIGHT("fonts/YanoneKaffeesatz-Light.ttf"),
        FONT_REGULAR("fonts/YanoneKaffeesatz-Regular.ttf");

        private String text;

        FontType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public GeneratorFont(int size, Color color, FontType type) {
        this.size = size;
        this.color = color;
        generatorFont(type);
    }

    private void generatorFont(FontType type){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(type.getText()));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARACTERS;
        parameter.size = size;
        font = generator.generateFont(parameter);
        font.setColor(color);
        generator.dispose();
    }


    public BitmapFont getFont() {
        return font;
    }

}
