package team6.g13it18k.objects;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class PetImageButtonStyle extends ImageButtonStyle {

    public int indexPet;


    public PetImageButtonStyle(Skin skin, int regionName) {
        this.indexPet = regionName;
        this.up = skin.getDrawable("pets" + String.valueOf(regionName));
        this.down = skin.getDrawable("pets" + String.valueOf(regionName));
    }
}
