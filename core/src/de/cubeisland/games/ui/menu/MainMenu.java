package de.cubeisland.games.ui.menu;

public class MainMenu extends Menu {

    @Entry("Start")
    private MenuTarget start = new ScreenTarget();

    @Entry("Options")
    private MenuTarget options = new SubMenuTarget(new OptionsMenu());

    @Entry("Credits")
    private MenuTarget credits = new ScreenTarget();

    @Entry("Exit")
    private MenuTarget exit = new ActionTarget(new Runnable() {
        public void run() {
            // exit
        }
    });

}
