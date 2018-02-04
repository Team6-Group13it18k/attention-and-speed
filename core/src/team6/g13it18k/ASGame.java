package team6.g13it18k;

import com.badlogic.gdx.Game;

import team6.g13it18k.asHelpers.AssetLoader;
import team6.g13it18k.screens.SplashScreen;

public class ASGame extends Game {

    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
