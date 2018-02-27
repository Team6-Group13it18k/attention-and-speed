package team6.g13it18k.objects;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class HelperStyle {

    public static ImageButtonStyle getStyleButtons(Skin skin, String nameUp, String nameDown){
        ImageButtonStyle imageButtonStyle = new ImageButtonStyle();
        imageButtonStyle.up = skin.getDrawable(nameUp);
        imageButtonStyle.down = skin.getDrawable(nameDown);

        return imageButtonStyle;
    }

    public static ImageButtonStyle getStylePets(Skin skin, int nameUp, int nameDown){
        ImageButtonStyle imageButtonStyle = new ImageButtonStyle();
        imageButtonStyle.up = skin.getDrawable("pets" + String.valueOf(nameUp));
        imageButtonStyle.down = skin.getDrawable("pets" + String.valueOf(nameDown));
        return imageButtonStyle;
    }

}
