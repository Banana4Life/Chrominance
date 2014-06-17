package de.cubeisland.games.screen;

import de.cubeisland.games.Base2DGame;

public abstract class AbstractGameScreen<T extends Base2DGame> extends AbstractScreen<T> {

    public AbstractGameScreen(T game) {
        super(game);
    }

    @Override
    public final void show() {
        super.show();

        onShow();
    }

    public void onShow() {
    }

    @Override
    public final void hide() {
        super.hide();

        onHide();
    }

    public void onHide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
