package de.cubeisland.games.util;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class LoggingInputMultiplexer extends InputMultiplexer {
    @Override
    public void addProcessor(int index, InputProcessor processor) {
        super.addProcessor(index, processor);
        System.out.println("Added " + processor + " at index " + index);
    }

    @Override
    public void removeProcessor(int index) {
        super.removeProcessor(index);
        System.out.println("Removed processor at index " + index);
    }

    @Override
    public void addProcessor(InputProcessor processor) {
        super.addProcessor(processor);
        System.out.println("Appended " + processor);
    }

    @Override
    public void removeProcessor(InputProcessor processor) {
        super.removeProcessor(processor);
        System.out.println("Removed " + processor);
    }
}
