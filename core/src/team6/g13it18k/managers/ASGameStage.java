package team6.g13it18k.managers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ASGameStage extends Stage {

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Keys.BACK || keyCode == Keys.ESCAPE) {
            if (getHardKeyListener() != null)
                getHardKeyListener().onHardKey(keyCode, 1);
        }
        return super.keyDown(keyCode);
    }

    @Override
    public boolean keyUp(int keyCode) {
        if (keyCode == Keys.BACK || keyCode == Keys.ESCAPE){
            if (getHardKeyListener() != null)
                getHardKeyListener().onHardKey(keyCode, 0);
        }
        return super.keyUp(keyCode);
    }

    public interface OnHardKeyListener {
        void onHardKey(int keyCode, int state);
    }

    private OnHardKeyListener _HardKeyListener = null;

    private OnHardKeyListener getHardKeyListener() {
        return _HardKeyListener;
    }

    public void setHardKeyListener(OnHardKeyListener HardKeyListener) {
        _HardKeyListener = HardKeyListener;
    }

}
