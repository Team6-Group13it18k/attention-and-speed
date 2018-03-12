package team6.g13it18k.managers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import java.util.LinkedHashSet;

import team6.g13it18k.ASGame;

public class PetsImageButton {

    Skin skinPets;

    private Array<ImageButton> allPets;
    private Array<ImageButton> pets;
    private Integer[] indexPets = {};
    private int indexPetTask;

    public PetsImageButton(ASGame gam){
        skinPets = new Skin(gam.manager.get("atlas/pets.atlas", TextureAtlas.class));

        loadAllPets();
        setRandomNumber();
    }

    private void loadAllPets(){
        allPets = new Array<>();
        for (int i = 1; i < 21; i++) {
            allPets.add(new ImageButton(getStylePets(skinPets, i, i)));
        }
    }

    private void setRandomNumber() {
        LinkedHashSet<Integer> numberPets = new LinkedHashSet<>(6);

        while (numberPets.size() != 6){
            numberPets.add(MathUtils.random(0, allPets.size - 1) + 1);
        }

        indexPets = numberPets.toArray(new Integer[numberPets.size()]);

        indexPetTask = indexPets[MathUtils.random(0, indexPets.length - 1)];
    }

    public Array<ImageButton> getPets(Sound btnClick){
        pets = new Array<>();

        for (int indexPet : indexPets) {
            ImageButton pet = new ImageButton(getStylePets(skinPets, indexPet, indexPet));
            pet.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    btnClick.play();
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    //TODO обработка нажатий
                }
            });


            pets.add(pet);
        }

        return pets;
    }

    public ImageButton getPet(){
        return new ImageButton(getStylePets(skinPets, indexPetTask, indexPetTask));
    }

    private ImageButton.ImageButtonStyle getStylePets(Skin skin, int nameUp, int nameDown){
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skin.getDrawable("pets" + String.valueOf(nameUp));
        imageButtonStyle.down = skin.getDrawable("pets" + String.valueOf(nameDown));
        return imageButtonStyle;
    }
}
