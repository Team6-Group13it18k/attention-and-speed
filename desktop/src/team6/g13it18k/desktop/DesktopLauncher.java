package team6.g13it18k.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import team6.g13it18k.ASGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Attention and Speed";
		config.width =  360;
		config.height = 700;
		new LwjglApplication(new ASGame(), config);
	}
}
