package team6.g13it18k.database;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AppPreferences {

    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";

    private static final String PREF_NAME = "MusicSound";

    private Preferences getPrefs(){
        return Gdx.app.getPreferences(PREF_NAME);
    }

    public boolean isSoundEffectsEnabled(){
        return getPrefs().getBoolean(PREF_SOUND_ENABLED);
    }

    public void setSoundEffectsEnabled(boolean soundEffectsEnabled){
        getPrefs().putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
        getPrefs().flush();
    }

    public boolean isMusicEnabled(){
        return getPrefs().getBoolean(PREF_MUSIC_ENABLED);
    }

    public void setMusicEnabled(boolean musicEffectsEnabled){
        getPrefs().putBoolean(PREF_MUSIC_ENABLED, musicEffectsEnabled);
        getPrefs().flush();
    }
}
