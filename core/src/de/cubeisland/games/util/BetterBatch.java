package de.cubeisland.games.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class BetterBatch extends SpriteBatch {
    private ShaderProgram prevShader;
    private ShaderProgram currShader;

    public BetterBatch() {
        super();
    }
    public BetterBatch(int size) {
        super(size);
    }
    public BetterBatch(int size, ShaderProgram defaultShader) {
        super(size, defaultShader);
    }

    public void pauseShader() {
        super.setShader(null);
    }
    public void continueShader() {
        super.setShader(currShader);
    }

    public boolean resetShader() {
        if (prevShader != null) {
            currShader = prevShader;
            super.setShader(currShader);
            return true;
        }
        return false;
    }

    @Override
    public void setShader(ShaderProgram shader) {
        prevShader = currShader;
        currShader = shader;
        super.setShader(currShader);
    }
}
